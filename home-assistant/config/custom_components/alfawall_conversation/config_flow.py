"""Config flow for AlfaWall AI Presence Agent integration."""
import logging
import voluptuous as vol

from homeassistant import config_entries
from homeassistant.const import CONF_NAME, CONF_URL
from homeassistant.core import HomeAssistant
from homeassistant.data_entry_flow import FlowResult

_LOGGER = logging.getLogger(__name__)

DOMAIN = "alfawall_conversation"

# Default configuration
DEFAULT_NAME = "AlfaWall AI Presence Agent"
DEFAULT_URL = "http://alfa-wall-addon:8080/api/conversation/process"


class AlfaWallConfigFlow(config_entries.ConfigFlow, domain=DOMAIN):
    """Handle a config flow for AlfaWall."""

    VERSION = 1

    async def async_step_import(self, import_data) -> FlowResult:
        """Handle import from configuration.yaml."""
        _LOGGER.info("Importing AlfaWall config from YAML: %s", import_data)

        # Check if already configured
        await self.async_set_unique_id(DOMAIN)
        self._abort_if_unique_id_configured()

        # Create config entry from YAML data
        return self.async_create_entry(
            title=import_data.get(CONF_NAME, DEFAULT_NAME),
            data={
                CONF_NAME: import_data.get(CONF_NAME, DEFAULT_NAME),
                CONF_URL: import_data.get(CONF_URL, DEFAULT_URL),
            },
        )

    async def async_step_user(self, user_input=None) -> FlowResult:
        """Handle the initial step."""
        errors = {}

        if user_input is not None:
            # Validate the input
            try:
                # Check if addon is reachable (optional validation)
                _LOGGER.info("Creating config entry with data: %s", user_input)

                # Create the config entry
                return self.async_create_entry(
                    title=user_input[CONF_NAME],
                    data=user_input,
                )
            except Exception as err:
                _LOGGER.exception("Error setting up AlfaWall integration: %s", err)
                errors["base"] = "unknown"

        # Show the configuration form
        data_schema = vol.Schema(
            {
                vol.Required(CONF_NAME, default=DEFAULT_NAME): str,
                vol.Required(CONF_URL, default=DEFAULT_URL): str,
            }
        )

        return self.async_show_form(
            step_id="user",
            data_schema=data_schema,
            errors=errors,
        )
