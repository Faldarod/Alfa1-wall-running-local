#!/usr/bin/env bash
set -e

CONFIG_DIR="/config"
HACS_DIR="${CONFIG_DIR}/custom_components/hacs"

echo "==================================================================="
echo "HACS Auto-Installation Script"
echo "==================================================================="

# Check if HACS is already installed
if [ -d "$HACS_DIR" ]; then
    echo "✓ HACS is already installed at: $HACS_DIR"
    echo "  Skipping installation."
    exit 0
fi

echo "Installing HACS..."

# Create custom_components directory if it doesn't exist
mkdir -p "${CONFIG_DIR}/custom_components"

# Download latest HACS release
echo "Downloading HACS from GitHub..."
HACS_URL="https://github.com/hacs/integration/releases/latest/download/hacs.zip"
TEMP_ZIP="/tmp/hacs.zip"

if wget -q "$HACS_URL" -O "$TEMP_ZIP"; then
    echo "✓ HACS downloaded successfully"
else
    echo "✗ Failed to download HACS"
    exit 1
fi

# Extract HACS to custom_components/hacs
echo "Extracting HACS..."
# Create hacs directory
mkdir -p "$HACS_DIR"

# Extract without -q to see progress, redirect to suppress output
if unzip "$TEMP_ZIP" -d "$HACS_DIR" > /dev/null 2>&1; then
    echo "✓ HACS extracted successfully"
else
    echo "✗ Failed to extract HACS (this may not be fatal if files exist)"
fi

# Clean up
rm -f "$TEMP_ZIP"

# Verify installation
if [ -d "$HACS_DIR" ]; then
    echo "✓ HACS installed successfully!"
    echo "  Location: $HACS_DIR"
    echo ""
    echo "  IMPORTANT: After Home Assistant starts, complete HACS setup:"
    echo "  1. Go to Settings > Devices & Services"
    echo "  2. Click '+ ADD INTEGRATION'"
    echo "  3. Search for 'HACS' and follow the setup wizard"
    echo "  4. You'll need a GitHub account for authentication"
else
    echo "✗ HACS installation verification failed"
    exit 1
fi

echo "==================================================================="
