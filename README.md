# PvZ Asset Browser

A desktop application for viewing Plants vs. Zombies 2 PAM animations, character parts, and texture atlases built with LibGDX and [libPVZ](https://github.com/pizpizi/libPVZ).

## Configuration

Set your local PVZ2 asset directory (which should contain `IMAGES/`, `ATLASES/`, and a `Resources.json`, which is the decoded `RESOURCES.RTON`.) path in `gradle.properties`:

```properties
systemProp.pvz.assets=/path/to/PVZ2 Assets
```

## Building and Running

### Prerequisites
- JDK 17 or higher

### Run via Gradle
```bash
./gradlew run
```

### Build via Gradle
```bash
./gradlew build
```

### Run via Executable JAR
```bash
java -Dpvz.assets="/path/to/PVZ2 Assets" -jar browser.jar
```
