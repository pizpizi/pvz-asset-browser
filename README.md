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
<img width="1920" height="1168" alt="image" src="https://github.com/user-attachments/assets/c11fa2cb-bb18-4a0d-874f-39ebe5274e50" />
<img width="1920" height="1168" alt="image" src="https://github.com/user-attachments/assets/db5dda52-1811-4e76-a8b5-00093461c0db" />
