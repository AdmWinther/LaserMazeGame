# Laser Maze 🔦

A desktop puzzle game built in **Java** with a **Swing GUI**, developed as a team project for the *Agile Object-Oriented Software Development* course at DTU. The player places mirrors, splitters, and other tokens on a grid to steer a laser beam from its source to the target(s).

> **Team project (Group 3, 6 members):** Léonard Amsler, Amin Amajjan, Hugo Demule, Lina Mounan, Nathan Gromb, and Adam Winther. We worked collaboratively with pair programming, so most files have multiple authors. My contributions focused on the laser generation/propagation logic and the JSON persistence layer, alongside general refactoring across the codebase.

## Features

- **Campaign mode** with progressively harder levels
- **Sandbox mode** — build, save, and replay your own mazes
- **Login system** with per-player progress saving (passwords stored hashed)
- **Laser propagation** through mirrors, double-sided mirrors, splitters, and checkpoints
- **Solution checker** that validates the board state
- Animations and sound effects

## Architecture

The project follows an **MVC** structure with clean separation of concerns:

```
src/main/java/
├── Model/          # game logic — no UI dependencies
│   ├── Classes/Laser/        # Laser, LaserBranch, LaserFragment, LaserManager
│   ├── Classes/Token/        # Token hierarchy: mirrors, splitter, target, gun...
│   ├── Classes/Level/         # Level, LevelBuilder, Playable/Editable levels
│   ├── Classes/Login/         # LoginManager, hashed Password, UserName
│   ├── Classes/TokenManager/  # Strict & Flexible token-placement strategies
│   └── Classes/Utils/         # DataReader / DataWriter (JSON persistence)
├── Vue/            # Swing GUI — panels, menus, handlers, animations, sound
└── Controller/     # mediates between Model and Vue
```

Design highlights: token-manager strategy implementations, interface-driven abstractions (`LaserPropagator`, `Orientable`, `Builder`, `TokenManager`), and a persistence layer that reads and writes levels and player data as JSON.

## Tech stack

- **Java** + **Maven**
- **Swing** for the GUI (custom-drawn tiles/tokens, `CardLayout` menu switching, custom game loop)
- **JSON** (`org.json`) for level and progress persistence
- **Cucumber (BDD)** for testing — feature files under `src/test/resources/features`

## How it was built

Developed over the course using agile practices: weekly in-person meetings, a Trello Kanban board, user stories, and BDD. The team started with a minimal terminal-based version to validate the core model, then evolved it into the full Swing application — a deliberate strategy to get a runnable model early and build confidence before adding the GUI.

## Running

```bash
mvn clean install
mvn exec:java   # or run the main class from your IDE
```

---

*A DTU course project — built collaboratively by Group 3.*