"""
AlfaWall AI Presence Agent Integration for Home Assistant.

This integration provides a conversation agent that connects to the
AlfaWall addon for AI-powered presence detection and LED control.
"""
from __future__ import annotations

import logging
import voluptuous as vol

from homeassistant.config_entries import ConfigEntry
from homeassistant.core import HomeAssistant
from homeassistant.const import Platform, CONF_NAME, CONF_URL
from homeassistant.helpers import discovery
from homeassistant.helpers import config_validation as cv

_LOGGER = logging.getLogger(__name__)

DOMAIN = "alfawall_conversation"
PLATFORMS = [Platform.CONVERSATION]

# Configuration schema for YAML setup
CONFIG_SCHEMA = vol.Schema(
    {
        DOMAIN: vol.Schema(
            {
                vol.Optional(CONF_NAME, default="AlfaWall AI Presence Agent"): cv.string,
                vol.Optional(
                    CONF_URL,
                    default="http://alfa-wall-addon:8080/api/conversation/process"
                ): cv.url,
            }
        )
    },
    extra=vol.ALLOW_EXTRA,
)


async def async_setup(hass: HomeAssistant, config: dict) -> bool:
    """Set up the AlfaWall Conversation integration from configuration.yaml."""
    _LOGGER.info("Setting up AlfaWall Conversation integration (YAML)")

    # Store configuration
    hass.data.setdefault(DOMAIN, {})

    if DOMAIN in config:
        hass.data[DOMAIN]["yaml_config"] = config[DOMAIN]
        _LOGGER.info("Stored YAML configuration: %s", config[DOMAIN])

        # Auto-create config entry from YAML if it doesn't exist
        existing_entries = hass.config_entries.async_entries(DOMAIN)
        if not existing_entries:
            _LOGGER.info("No existing config entries found, creating from YAML")
            hass.async_create_task(
                hass.config_entries.flow.async_init(
                    DOMAIN,
                    context={"source": "import"},
                    data=config[DOMAIN],
                )
            )

    return True


async def async_setup_entry(hass: HomeAssistant, entry: ConfigEntry) -> bool:
    """Set up AlfaWall Conversation from a config entry."""
    _LOGGER.info("Setting up AlfaWall Conversation entry: %s", entry.entry_id)

    hass.data.setdefault(DOMAIN, {})
    hass.data[DOMAIN][entry.entry_id] = entry.data

    # Forward setup to the conversation platform
    await hass.config_entries.async_forward_entry_setups(entry, PLATFORMS)

    _LOGGER.info("AlfaWall conversation integration loaded successfully")

    return True


async def async_unload_entry(hass: HomeAssistant, entry: ConfigEntry) -> bool:
    """Unload a config entry."""
    _LOGGER.info("Unloading AlfaWall Conversation entry: %s", entry.entry_id)

    # Unload platforms
    unload_ok = await hass.config_entries.async_unload_platforms(entry, PLATFORMS)

    if unload_ok:
        hass.data[DOMAIN].pop(entry.entry_id, None)

    return unload_ok
