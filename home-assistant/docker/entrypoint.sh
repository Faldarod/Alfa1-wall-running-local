#!/usr/bin/env bash
set -e

echo "==================================================================="
echo "Home Assistant with HACS Auto-Install"
echo "==================================================================="

# Run HACS installation script
/usr/local/bin/install-hacs.sh

echo "Starting Home Assistant..."
echo "==================================================================="

# Start Home Assistant using the original entrypoint
exec /init
