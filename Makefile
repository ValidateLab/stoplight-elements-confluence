
install:
	set -x \
	&& rm -f atlassian-plugin-sdk-* \
	&& curl -L https://marketplace.atlassian.com/download/plugins/atlassian-plugin-sdk-tgz -OJ -sw '%{filename_effective}' \
	&& tar -xvzf atlassian-plugin-sdk-tgz -C . \
    && rm -f atlassian-plugin-sdk-tgz \
	&& SDK_DIR=`ls -d ./atlassian-plugin-sdk-*` \
    && mv $${SDK_DIR} atlassian-plugin-sdk \
	&& ./atlassian-plugin-sdk/bin/atlas-mvn install \
	&& docker-compose up -d \
	&& echo "Go to localhost:8090 and setup confluence"

build:
	./atlassian-plugin-sdk/bin/atlas-mvn package

upload:
	./atlassian-plugin-sdk/bin/atlas-install-plugin --context-path "" --server localhost -p 8090 --username admin --password admin
