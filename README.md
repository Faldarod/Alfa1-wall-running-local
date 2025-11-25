# AlfaWall Local Testing Environment

Complete Docker Compose environment for running and testing the AlfaWall system locally.

## What's Included

- **Home Assistant** - Home automation platform with HACS auto-installation
- **WLED Simulator** - Visual LED strip simulator
- **AlfaWall Addon** - AI-powered presence assistant
- Mock device trackers for simulating employee presence (no UniFi hardware required)

## Quick Start

### Prerequisites
- Docker and Docker Compose installed
- OpenAI/OpenRouter API key (for LLM functionality)

### Setup

1. **Clone the repository:**
   ```bash
   git clone <repository-url>
   cd Alfa1-wall-running-local
   ```

2. **Configure environment variables:**
   ```bash
   # Edit .env and add your API key
   OPENAI_API_KEY=your-key-here
   ```

3. **Start all services:**
   ```bash
   docker-compose --profile local-testing up -d
   ```

4. **Wait for services to start** (~30-60 seconds)

5. **Access web interfaces:**
   - **Home Assistant:** http://localhost:8123
   - **WLED Simulator:** http://localhost:8081
   - **AlfaWall API:** http://localhost:8080

## First Time Setup

### Home Assistant
1. Go to http://localhost:8123
2. Complete onboarding (create account, set location)
3. HACS will be automatically installed on first run

### Simulating Employee Presence

Since this is a testing environment without real devices, you can simulate employee presence using Home Assistant:

1. Go to http://localhost:8123
2. **Developer Tools** → **Services**
3. Set device as present:
   ```yaml
   Service: device_tracker.see
   Service Data:
     dev_id: john_phone
     location_name: home
   ```
4. Click **CALL SERVICE**

**See full guide:** [documentation/SIMULATING_DEVICES.md](documentation/SIMULATING_DEVICES.md)

## Testing the System

### Test Presence Query

```bash
curl -X POST http://localhost:8080/api/conversation/process \
  -H "Content-Type: application/json" \
  -d '{"text":"Who is here now?"}'
```

### Watch LEDs Light Up

Open http://localhost:8081 to see the WLED simulator and watch employee LEDs light up when queries are processed.

## Available Mock Devices

Pre-configured device_tracker entities:
- `device_tracker.john_phone`
- `device_tracker.john_laptop`
- `device_tracker.jane_phone`
- `device_tracker.alice_phone`
- `device_tracker.alice_laptop`
- `device_tracker.bob_phone`
- `device_tracker.charlie_phone`

## Services

| Service | Port | Purpose |
|---------|------|---------|
| alfa-wall-addon | 8080 | Main AI application |
| home-assistant | 8123 | Home automation platform |
| wled-simulator | 8081 | LED visualization |

## Common Commands

```bash
# Start all services
docker-compose --profile local-testing up -d

# View logs
docker-compose logs -f alfa-wall-addon
docker-compose logs -f home-assistant

# Stop all services
docker-compose down

# Restart a specific service
docker-compose restart alfa-wall-addon

# Rebuild and restart
docker-compose up -d --build
```

## Documentation

- **[Device Simulation Guide](documentation/SIMULATING_DEVICES.md)** - How to simulate employee presence
- **[Setup Phases](documentation/Phases.md)** - Detailed setup information
- **[UniFi Integration](documentation/UNIFI_INTEGRATION.md)** - Optional: integrate real UniFi devices (production)

## Troubleshooting

### Services won't start
```bash
# Check if ports are already in use
docker-compose ps
netstat -ano | findstr "8080 8123 8081"

# View service logs
docker-compose logs
```

### Home Assistant not accessible
- Wait 30-60 seconds for initial startup
- Check logs: `docker-compose logs home-assistant`
- Ensure port 8123 is not in use by another application

### WLED Simulator shows no LEDs
- Check alfa-wall-addon is running: `docker-compose ps`
- Verify Home Assistant can reach WLED: check HA logs
- Ensure a query has been sent (LEDs update on queries)

### AlfaWall not detecting presence
- Verify device_tracker entities exist in Home Assistant (Developer Tools → States)
- Check device state is "home" not "not_home"
- Review alfa-wall-addon logs: `docker-compose logs alfa-wall-addon`

## Development

### AlfaWall Addon Source Code

The core addon source code is in a separate repository:
**https://github.com/yourorg/alfa1-wall-addon-repo**

To develop the addon locally:
1. Clone the addon repo
2. Make changes to the code
3. Rebuild in this environment: `docker-compose up -d --build alfa-wall-addon`

### Making Changes to Home Assistant Config

Edit files in `home-assistant/config/` and restart:
```bash
docker-compose restart home-assistant
```

### Making Changes to WLED Simulator

Edit files in `wled-simulator/src/` and rebuild:
```bash
docker-compose up -d --build wled-simulator
```

## Project Architecture

```
User Voice Query
      ↓
Home Assistant (Conversation)
      ↓
AlfaWall Addon (AI Agents)
  ├── PrivacyOfficerAgent (sanitize PII)
  ├── EmployeeCollectorAgent (find employees)
  └── ActionAgent (control LEDs)
      ↓
Home Assistant (WLED Integration)
      ↓
WLED Simulator
      ↓
Visual LED Display
```

## Environment Variables

Configure in `.env`:

```bash
# Required: OpenAI/OpenRouter API Key
OPENAI_API_KEY=your-key-here

# Optional: LLM Configuration
SPRING_AI_OPENAI_BASE_URL=https://openrouter.ai/api/v1
SPRING_AI_OPENAI_CHAT_OPTIONS_MODEL=openai/gpt-4o-mini
SPRING_AI_OPENAI_CHAT_OPTIONS_TEMPERATURE=0.7
EMBABEL_MODELS_DEFAULT_LLM=gpt-4.1-mini
```

## Contributing

1. Make changes in appropriate directory (home-assistant, wled-simulator, etc.)
2. Test locally with `docker-compose up -d --build`
3. Submit pull request with description of changes

## License

See LICENSE file in the main addon repository.

## Support

- **Issues:** Report at addon repository issue tracker
- **Documentation:** See `documentation/` directory
- **Questions:** See CLAUDE.md for development guidance
