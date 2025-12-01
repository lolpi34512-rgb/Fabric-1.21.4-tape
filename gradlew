#!/usr/bin/env sh
# Minimal gradlew script that tries to use local gradle or wrapper
DIR="$(cd "$(dirname "$0")" && pwd)"
if [ -x "$DIR/gradlew" ] && [ "$0" != "$DIR/gradlew" ]; then
  exec "$DIR/gradlew" "$@"
fi
if command -v gradle >/dev/null 2>&1; then
  exec gradle "$@"
fi
echo "Gradle not found. Install Gradle or run 'gradle wrapper' locally to create the wrapper."
exit 1
