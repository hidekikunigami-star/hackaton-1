#!/bin/sh
# Maven Wrapper-compatible entrypoint for environments where Maven is installed.
# Install Maven 3.9+ if the command is not available.
if ! command -v mvn >/dev/null 2>&1; then
  echo "Maven no está instalado. Instala Maven 3.9+ o ejecuta el proyecto desde un IDE con Maven integrado." >&2
  exit 1
fi
exec mvn "$@"
