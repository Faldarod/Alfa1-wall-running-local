# CLAUDE.md - AlfaWall Local Testing Environment

This file provides guidance to Claude Code (claude.ai/code) when working with the local testing environment.

## Repository Purpose

This repository provides a complete Docker Compose-based local testing environment for the AlfaWall system. It includes:
- Home Assistant with HACS auto-installation
- WLED Simulator for LED visualization
- AlfaWall Addon (AI presence assistant)
- Mock device trackers for testing without real hardware

## Quick Start Commands

### Starting the Environment

```bash
# Start all services
docker-compose --profile local-testing up -d

# Start with rebuild
docker-compose --profile local-testing up -d --build

# View logs
docker-compose logs -f
```

### Stopping the Environment

```bash
# Stop all services
docker-compose down

# Stop and remove volumes
docker-compose down -v
```

### Simulating Device Presence

Use Home Assistant Developer Tools (http://localhost:8123):

```yaml
Service: device_tracker.see
Service Data:
  dev_id: john_phone
  location_name: home
```

### Testing Queries

```bash
curl -X POST http://localhost:8080/api/conversation/process \
  -H "Content-Type: application/json" \
  -d '{"text":"Who is here now?"}'
```

## Directory Structure

```
Alfa1-wall-running-local/
├── docker-compose.yml        # Service orchestration
├── .env                      # Environment variables (API keys, etc.)
├── home-assistant/           # Home Assistant configuration
│   ├── docker/               # Custom HA image with HACS
│   │   ├── Dockerfile
│   │   ├── entrypoint.sh
│   │   └── install-hacs.sh
│   └── config/               # HA configuration files
│       ├── configuration.yaml
│       ├── known_devices.yaml  # Mock device trackers
│       └── custom_components/
├── wled-simulator/           # LED simulator
│   ├── src/
│   └── pom.xml
├── unifi/                    # Optional UniFi setup (currently unused)
├── documentation/            # Testing and setup guides
└── scripts/                  # Helper scripts
```

## Services

### 1. alfa-wall-addon (port 8080)
- Main AI application
- Processes natural language queries
- Controls LEDs via Home Assistant
- **Source:** Separate repository (alfa1-wall-addon-repo)

### 2. home-assistant (port 8123)
- Home automation platform
- HACS auto-installed on first run
- Mock device_tracker entities for testing
- REST API for AlfaWall integration

### 3. wled-simulator (port 8081)
- Visual LED strip simulator
- WebSocket real-time updates
- Simulates 5 segments (76 LEDs each)
- Web interface for manual control

## Configuration Files

### docker-compose.yml
Defines all services and their relationships. Currently includes:
- `alfa-wall-addon` (always runs)
- `home-assistant` (local-testing profile)
- `wled-simulator` (local-testing profile)

UniFi services removed (not needed for testing with mock devices).

### .env
Environment variables for LLM configuration:
```bash
OPENAI_API_KEY=your-key-here
SPRING_AI_OPENAI_BASE_URL=https://openrouter.ai/api/v1
SPRING_AI_OPENAI_CHAT_OPTIONS_MODEL=openai/gpt-4o-mini
```

### home-assistant/config/configuration.yaml
Main HA configuration including:
- `input_boolean` helpers for presence simulation
- `known_devices.yaml` reference for mock device trackers
- HACS integration (auto-installed)

### home-assistant/config/known_devices.yaml
Pre-configured mock device_tracker entities:
- john_phone, john_laptop
- jane_phone
- alice_phone, alice_laptop
- bob_phone, charlie_phone

## Testing Workflow

### 1. Start Environment
```bash
docker-compose --profile local-testing up -d
```

### 2. Set Employee Present
In Home Assistant Developer Tools:
```yaml
Service: device_tracker.see
Service Data:
  dev_id: john_phone
  location_name: home
```

### 3. Query System
```bash
curl -X POST http://localhost:8080/api/conversation/process \
  -H "Content-Type: application/json" \
  -d '{"text":"Who is here now?"}'
```

### 4. Watch LEDs
Open http://localhost:8081 to see John's LED segment light up.

## Common Development Tasks

### Rebuild AlfaWall Addon
```bash
docker-compose up -d --build alfa-wall-addon
```

### Restart Home Assistant
```bash
docker-compose restart home-assistant
```

### View Service Logs
```bash
docker-compose logs -f alfa-wall-addon
docker-compose logs -f home-assistant
docker-compose logs -f wled-simulator
```

### Clean Environment
```bash
# Stop and remove containers
docker-compose down

# Remove Home Assistant runtime data
rm -rf home-assistant/config/.storage
rm -rf home-assistant/config/home-assistant_v2.db*
```

## Mock Device Trackers

Available entities (configured in `known_devices.yaml`):

| Entity ID | Employee | Device Type |
|-----------|----------|-------------|
| device_tracker.john_phone | John Doe | Phone |
| device_tracker.john_laptop | John Doe | Laptop |
| device_tracker.jane_phone | Jane Doe | Phone |
| device_tracker.alice_phone | Alice | Phone |
| device_tracker.alice_laptop | Alice | Laptop |
| device_tracker.bob_phone | Bob | Phone |
| device_tracker.charlie_phone | Charlie | Phone |

Set presence via `device_tracker.see` service in HA Developer Tools.

## HACS Auto-Installation

The Home Assistant image includes automatic HACS installation:
- Runs on container startup
- Downloads latest HACS from GitHub
- Extracts to `config/custom_components/hacs`
- Skips if already installed

**First time:** Complete HACS setup in HA UI (Settings → Devices & Services → Add Integration → HACS)

## Troubleshooting

### Services won't start
```bash
# Check port conflicts
netstat -ano | findstr "8080 8123 8081"

# Check logs
docker-compose logs
```

### Home Assistant slow to start
- Normal on first run (60-90 seconds)
- HACS installation adds ~10-20 seconds
- Check logs: `docker-compose logs home-assistant`

### Device trackers not showing
```bash
# Restart HA to reload known_devices.yaml
docker-compose restart home-assistant

# Verify in HA: Developer Tools → States
# Search for: device_tracker.john_phone
```

### AlfaWall can't find devices
- Check device is set to "home": `device_tracker.see` service
- Verify entity ID matches application.yaml
- Check addon logs: `docker-compose logs alfa-wall-addon`

## Documentation

- **[SIMULATING_DEVICES.md](documentation/SIMULATING_DEVICES.md)** - Complete device simulation guide
- **[UNIFI_INTEGRATION.md](documentation/UNIFI_INTEGRATION.md)** - Optional UniFi setup (production)
- **[Phases.md](documentation/Phases.md)** - Original setup phases

## AlfaWall Addon Source

The core application code is in a separate repository:
**alfa1-wall-addon-repo**

This testing environment references the addon via Docker build. To develop the addon:
1. Clone the addon repository
2. Make code changes
3. Rebuild in this environment: `docker-compose up -d --build alfa-wall-addon`

## Architecture

```
Testing Environment (this repo)
├── Home Assistant Container
│   ├── Mock device_tracker entities
│   └── WLED integration
├── WLED Simulator Container
│   └── Visual LED display
└── AlfaWall Addon Container
    ├── PrivacyOfficerAgent
    ├── EmployeeCollectorAgent
    └── ActionAgent
```

## Environment Reset

To completely reset the environment:

```bash
# Stop services
docker-compose down

# Clean HA runtime data
rm -rf home-assistant/config/.storage
rm -rf home-assistant/config/.cloud
rm -f home-assistant/config/.HA_VERSION
rm -f home-assistant/config/home-assistant_v2.db*

# Remove UniFi data (if present)
rm -rf unifi/unifi-data/*
rm -rf unifi/unifi-db-data/*

# Restart
docker-compose --profile local-testing up -d
```

## Important Notes

- **No UniFi required:** This environment uses mock device_trackers
- **HACS auto-installs:** First run takes longer (HACS download/install)
- **Stateless:** AlfaWall has no persistent database
- **API Key required:** Set OPENAI_API_KEY in .env
- **Privacy-first:** No PII is logged or persisted

## Related Repositories

- **alfa1-wall-addon-repo** - Core AlfaWall application source code
- **Alfa1-wall-addon-hacs** - HACS distribution package

## Support

For issues related to:
- **AlfaWall addon code:** See alfa1-wall-addon-repo issues
- **Local environment setup:** File issues in this repository
- **Home Assistant:** See Home Assistant documentation
- **WLED Simulator:** Check wled-simulator/ source code
