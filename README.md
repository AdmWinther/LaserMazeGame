# Agile Programming Project - Group 3 - Lazer Maze

## Guide to run the project

### 1. Clone the repository

```bash 
git clone git@gitlab.gbar.dtu.dk:02160-f24/group-3.git
```

### 2. Open the project in IntelliJ IDEA

Open IntelliJ IDEA and select `File -> Open...` and navigate to the cloned repository.

### 3. Set the project as a Maven project

Right-click on the `pom.xml` file and select `Add as Maven Project`.
Right-click on the `pom.xml` file again and select `Maven -> Reload Project`.

### 4. Project configuration

Open your project structure by pressing `Ctrl + Alt + Shift + S` and navigate to `File -> Project Structure`.
Under `Project` set the `Project SDK` to a version containing `18` and set the `Project language level` to `18`.
Under `Modules`, make sure to keep only the LazerMaze module and delete the other potential ones.
Under `Modules` again set the `src/main/java` folder as `Sources`, the `src/test/java` folder as `Tests` as the `src/main/java/Vue/Resources` folder as `Resources`.

### 5. Run the project

Navigate to the `Game` class in the `src/main/java/Vue/Game` folder and run the `main` method.

### 6. Enjoy the game!