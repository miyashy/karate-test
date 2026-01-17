FROM mcr.microsoft.com/devcontainers/java:21

# Install additional tools
RUN apt-get update && export DEBIAN_FRONTEND=noninteractive \
    && apt-get -y install --no-install-recommends git curl wget unzip

# Verify installations
RUN java --version \
    && git --version

# Set up non-root user
ARG USERNAME=devuser
ARG USER_UID=1000
ARG USER_GID=$USER_UID

# Create the user
RUN groupadd --force --gid $USER_GID $USERNAME \
    && (id -u $USER_UID > /dev/null 2>&1 || useradd --uid $USER_UID --gid $USER_GID -m $USERNAME) \
    && (getent passwd $USER_UID | grep -q "^$USERNAME:" || usermod -l $USERNAME $(getent passwd $USER_UID | cut -d: -f1)) \
    && apt-get update \
    && apt-get install -y sudo \
    && echo $USERNAME ALL=\(root\) NOPASSWD:ALL > /etc/sudoers.d/$USERNAME \
    && chmod 0440 /etc/sudoers.d/$USERNAME

# Set the default user
USER $USERNAME

# Set up Gradle user home
ENV GRADLE_USER_HOME=/home/$USERNAME/.gradle
