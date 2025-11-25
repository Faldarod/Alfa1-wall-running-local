FROM ghcr.io/home-assistant/home-assistant:stable

# Install wget, unzip, and dos2unix for line ending safety
RUN apk add --no-cache wget unzip dos2unix

# Copy the HACS installation script and fix line endings
COPY install-hacs.sh /usr/local/bin/install-hacs.sh
RUN dos2unix /usr/local/bin/install-hacs.sh && chmod +x /usr/local/bin/install-hacs.sh

# Copy the entrypoint wrapper and fix line endings
COPY entrypoint.sh /usr/local/bin/entrypoint-wrapper.sh
RUN dos2unix /usr/local/bin/entrypoint-wrapper.sh && chmod +x /usr/local/bin/entrypoint-wrapper.sh

# Use our custom entrypoint that installs HACS before starting HA
ENTRYPOINT ["/usr/local/bin/entrypoint-wrapper.sh"]
