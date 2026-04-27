# InRa

---

## Screen flow

```
Login
  └── Home
        ├── Locations
        ├── Tables
        └── Settings
```

| Screen | What the user sees and can do |
|---|---|
| **Login** | Enter username and password. App validates the minimum required version on load. |
| **Home** | Logged-in user info (name, identification, username). Navigate to Locations or Tables. Logout and Settings icons in the top bar. |
| **Locations** | List of delivery locations with pull-to-refresh and an empty state when no data is available. |
| **Tables** | List of sync tables showing name, primary key, and batch size. Pull-to-refresh and empty state. |
| **Settings** | View and edit the current app version. |

---

## Session management

After a successful login the authenticated user is persisted in the local Room database. A `Flow<User?>` observes that record reactively — when the app starts it reads the stored session and skips the Login screen entirely if a valid user is already present.

The navigation graph listens to this flow continuously. When the user value becomes `null` (on logout or cache expiry) the entire graph swaps back to the Login screen automatically, with no manual navigation call needed.

On logout:
1. The auth record is deleted from Room.
2. The locations and tables caches are wiped from Room.
3. DataStore (which holds only the app version) is **not** cleared — it is device-level configuration, not user data.

---

## Architecture

Clean Architecture across three modules: `domain` (use cases + models) → `data` (repositories, Room, Retrofit) → `presentation` (ViewModels + Compose screens). Hilt handles dependency injection across all layers. The app is offline-first — locations and tables are cached in Room and the cache is wiped on logout.

---

## Tech stack

| Layer | Technology |
|---|---|
| UI | Jetpack Compose + Material 3 |
| Navigation | Navigation 3 (typed routes) |
| Dependency injection | Hilt |
| Networking | Retrofit2 + kotlinx.serialization |
| Local database | Room |
| State management | StateFlow + Channels |
| Testing | JUnit 4 + MockK + Turbine |

---

## Running the project

1. Clone the repository and open it in Android Studio.
2. Add any required credentials to `local.properties` (excluded from version control).
3. Run on a device or emulator with minimum API 24.

---

## Running tests

```bash
./gradlew test          # all modules
./gradlew :domain:test  # domain module only
```

---

## Code quality

```bash
./gradlew spotlessCheck   # check formatting
./gradlew spotlessApply   # auto-fix formatting
./gradlew detekt          # static analysis
```
