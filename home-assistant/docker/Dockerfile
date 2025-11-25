FROM ghcr.io/home-assistant/home-assistant:stable

# Install wget for downloading HACS
RUN apk add --no-cache wget unzip

# Copy the HACS installation script
COPY install-hacs.sh /usr/local/bin/install-hacs.sh
RUN chmod +x /usr/local/bin/install-hacs.sh

# Copy the entrypoint wrapper
COPY entrypoint.sh /usr/local/bin/entrypoint-wrapper.sh
RUN chmod +x /usr/local/bin/entrypoint-wrapper.sh

# Use our custom entrypoint that installs HACS before starting HA
ENTRYPOINT ["/usr/local/bin/entrypoint-wrapper.sh"]
