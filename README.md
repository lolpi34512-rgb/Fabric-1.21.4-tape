AutoClick Fabric mod (Minecraft 1.21.4)
=====================================

This repository contains a Fabric Loom project skeleton for a client-side autoclick mod.
It includes a client command `/clikk` which toggles autoclicking (≈1 click/sec).

What is included:
- build.gradle, settings.gradle, gradle.properties
- gradlew and gradlew.bat helper scripts (they require local Gradle or wrapper jar)
- gradle/wrapper/gradle-wrapper.properties (points to Gradle 8.6)
- src/main/java/com/autoclickmod/AutoclickClient.java (mod code)
- src/main/resources/fabric.mod.json
- .github/workflows/main.yml (CI build using gradle action)

Important notes:
- The repository does NOT include gradle-wrapper.jar (binary). To build locally using './gradlew', either:
  1) Install Gradle on your system, or
  2) Run 'gradle wrapper' locally to generate the wrapper, or
  3) I can re-create the wrapper and include gradle-wrapper.jar if you want (tell me).

- GitHub Actions uses 'gradle/gradle-build-action' which downloads Gradle on CI, so the CI build will work without gradle-wrapper.jar.

How to build locally (if you have Gradle installed):
  ./gradlew build
or:
  gradle build

How to build on GitHub:
  1) Push this project to GitHub (branch 'main')
  2) Actions will run and create artifact 'mod-jar' with the built JAR
