.PHONY: build
build:
	atlas-mvn package
upload:
	atlas-install-plugin --context-path "" --server confluence -p 8090 --username admin --password admin
