FROM adoptopenjdk/openjdk11

# Download and extract SDK tarball
RUN cd /tmp \
    && TAR_PATH=$(curl -L https://marketplace.atlassian.com/download/plugins/atlassian-plugin-sdk-tgz -OJ -sw '%{filename_effective}') \
    && tar -xvzf ${TAR_PATH} -C /opt \
    && rm $TAR_PATH \
    && SDK_DIR=`ls -d /opt/atlassian-plugin-sdk*` \
    && mv $SDK_DIR /opt/atlassian-plugin-sdk

RUN apt update && apt install build-essential
ENV PATH="$PATH:/opt/atlassian-plugin-sdk/bin"
