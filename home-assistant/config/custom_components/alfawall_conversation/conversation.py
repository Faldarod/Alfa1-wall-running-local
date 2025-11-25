"""
Conversation entity for AlfaWall AI Presence Agent.

This entity integrates the AlfaWall addon as a conversation agent in Home Assistant.
"""
import logging
from typing import Literal

import aiohttp

from homeassistant.components.conversation import (
    AssistantContent,
    ChatLog,
    ConversationEntity,
    ConversationInput,
    ConversationResult,
)
from homeassistant.helpers.intent import IntentResponse
from homeassistant.config_entries import ConfigEntry
from homeassistant.const import CONF_URL, CONF_NAME, MATCH_ALL
from homeassistant.core import HomeAssistant
from homeassistant.helpers.aiohttp_client import async_get_clientsession
from homeassistant.helpers.entity_platform import AddEntitiesCallback

_LOGGER = logging.getLogger(__name__)

DOMAIN = "alfawall_conversation"


async def async_setup_entry(
    hass: HomeAssistant,
    entry: ConfigEntry,
    async_add_entities: AddEntitiesCallback,
) -> None:
    """Set up the AlfaWall conversation entity from a config entry."""
    _LOGGER.info("Setting up AlfaWall conversation entity from config entry")

    name = entry.data.get(CONF_NAME, "AlfaWall AI Presence Agent")
    url = entry.data.get(CONF_URL, "http://alfa-wall-addon:8080/api/conversation/process")

    _LOGGER.info("Creating conversation entity: %s at %s", name, url)

    entity = AlfaWallConversationEntity(hass, entry, name, url)
    async_add_entities([entity])


class AlfaWallConversationEntity(ConversationEntity):
    """AlfaWall AI Presence conversation entity."""

    def __init__(
        self, hass: HomeAssistant, entry: ConfigEntry, name: str, url: str
    ) -> None:
        """Initialize the entity."""
        super().__init__()
        self.hass = hass
        self._entry = entry
        self._attr_name = name
        self._url = url
        self._attr_unique_id = entry.entry_id
        self._attr_has_entity_name = True
        self.entity_id = f"conversation.{entry.entry_id}"
        _LOGGER.info("Initialized AlfaWall entity: %s -> %s (entity_id: %s)", name, url, self.entity_id)

    @property
    def supported_languages(self) -> list[str] | Literal["*"]:
        """Return a list of supported languages."""
        return MATCH_ALL

    @property
    def device_info(self):
        """Return device information about this entity."""
        return {
            "identifiers": {(DOMAIN, self._entry.entry_id)},
            "name": self._attr_name,
            "manufacturer": "AlfaOne",
            "model": "AI Presence Agent",
        }

    async def async_prepare(self, language: str | None = None) -> None:
        """Optional: prepare resources before processing."""
        _LOGGER.debug("Preparing AlfaWall conversation entity for language: %s", language)

    async def _async_handle_message(
        self, user_input: ConversationInput, chat_log: ChatLog
    ) -> ConversationResult:
        """Process a user message."""
        _LOGGER.info("Processing conversation input: %s", user_input.text)

        try:
            # Prepare request to AlfaWall addon
            session = async_get_clientsession(self.hass)

            payload = {
                "text": user_input.text,
                "language": user_input.language,
                "conversation_id": user_input.conversation_id,
            }

            _LOGGER.debug("Sending request to %s with payload: %s", self._url, payload)

            async with session.post(
                self._url,
                json=payload,
                timeout=aiohttp.ClientTimeout(total=30),
            ) as response:
                if response.status != 200:
                    error_text = await response.text()
                    _LOGGER.error(
                        "AlfaWall addon returned status %s: %s",
                        response.status,
                        error_text,
                    )
                    return self._create_error_response(
                        user_input,
                        chat_log,
                        f"Service unavailable (status {response.status})",
                    )

                data = await response.json()
                _LOGGER.debug("Received response from AlfaWall: %s", data)

                # Extract response text from AlfaWall response format
                response_text = self._extract_response_text(data)

                # Add assistant response to chat log
                chat_log.async_add_assistant_content_without_tools(
                    AssistantContent(
                        agent_id=user_input.agent_id,
                        content=response_text,
                    )
                )

                # Create successful response
                response = IntentResponse(language=user_input.language)
                response.async_set_speech(response_text)

                return ConversationResult(
                    conversation_id=user_input.conversation_id,
                    response=response,
                    continue_conversation=False,
                )

        except aiohttp.ClientError as err:
            _LOGGER.error("Error communicating with AlfaWall addon: %s", err)
            return self._create_error_response(
                user_input, chat_log, "Could not connect to AlfaWall addon"
            )
        except Exception as err:
            _LOGGER.exception("Unexpected error processing conversation: %s", err)
            return self._create_error_response(
                user_input, chat_log, "An unexpected error occurred"
            )

    def _extract_response_text(self, data: dict) -> str:
        """Extract response text from AlfaWall API response."""
        try:
            # AlfaWall response format: {"response": {"speech": {"plain": {"speech": "text"}}}}
            return data["response"]["speech"]["plain"]["speech"]
        except (KeyError, TypeError) as err:
            _LOGGER.warning("Unexpected response format from AlfaWall: %s", err)
            # Fallback: try to find speech text anywhere in response
            if isinstance(data, dict):
                if "speech" in data:
                    return str(data["speech"])
                if "response" in data:
                    return str(data["response"])
            return "Received response from AlfaWall"

    def _create_error_response(
        self, user_input: ConversationInput, chat_log: ChatLog, error_message: str
    ) -> ConversationResult:
        """Create an error response."""
        # Add error to chat log
        chat_log.async_add_assistant_content_without_tools(
            AssistantContent(
                agent_id=user_input.agent_id,
                content=error_message,
            )
        )

        response = IntentResponse(language=user_input.language)
        response.async_set_speech(error_message)

        return ConversationResult(
            conversation_id=user_input.conversation_id,
            response=response,
            continue_conversation=False,
        )
