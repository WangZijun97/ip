package duke;

import java.nio.file.Path;

import duke.command.Command;
import duke.exception.DukeInputException;
import duke.exception.DukeSaveDataException;
import duke.io.InputHandler;
import duke.io.OutputHandler;



/** Contains main software logic loop and initialization logic.*/
public class Duke {

    /** TaskManager object that contains all the tasks in Duke's memory */
    private TaskManager taskManager;

    /** Ui object that handles all display and input from user */
    private Ui ui;

    /** Path object that contains the relative directory path to the save file */
    private Path filePath;

    /** SaveManager object that handles all saving and loading of save data on load or on exit */
    private SaveManager saveManager;

    /**
     * Constructs new Duke object with default save file directory.
     * Defaults save file directory to "/data/data.txt".
     */
    public Duke() {
        this(Path.of("/data", "data.txt"));
    }

    public Duke(Path filePath) {
        this(filePath, new OutputHandler());
    }

    public Duke(OutputHandler outputHandler) {
        this(Path.of("/data", "data.txt"), outputHandler);
    }

    /**
     * Constructs new Duke object with custom save file directory.
     *
     * @param filePath Path object representing save file directory.
     */
    public Duke(Path filePath, OutputHandler outputHandler) {
        // Initialise properties
        this.filePath = filePath;
        this.ui = new Ui(new InputHandler(), outputHandler);
        this.saveManager = new SaveManager(this.filePath);

        // Attempts to load save file.
        // If fails, initialises Duke without save data.
        try {
            this.taskManager = saveManager.load();
        } catch (DukeSaveDataException e) {
            this.ui.displayException(e);
            this.taskManager = new TaskManager();
        }

    }

    /**
     * Activates main software loop, accepting and processing commands.
     * Parser parses inputs to commands which are then executed by Duke.
     * Exceptions trigger an error message for users without ending the program.
     * Main loop only ends on parsing a "bye" command from user.
     * If unable to save, Duke will exit without saving.
     */
    public void run() {

        // Main software loop.
        while (true) {

            try {
                // Parse user input into software command.
                Command command = Parser.parse(this.ui.readCommand());

                // Execute user command.
                command.execute(this.ui, this.taskManager, this.saveManager);

                // Terminate software loop if exit command is given.
                if (command.isByeCommand()) {
                    break;
                }

            } catch (DukeInputException e) {
                // Display Exception without terminating loop if one is thrown.
                ui.displayException(e);
            }

        }

    }

    public boolean processOneCommand(String userInput) {

        try {
            // Parse user input
            Command command = Parser.parse(userInput);

            // Execute user command
            command.execute(this.ui, this.taskManager, this.saveManager);

            return command.isByeCommand();

        } catch (DukeInputException e) {
            ui.displayException(e);
            return false;
        }

    }

    /**
     * Initializes software.
     *
     * @param args No args required.
     */
    public static void main(String[] args) {
        // Initialize Duke with save data and send welcome message
        Duke duke = new Duke(Path.of("data/data.txt"));
        duke.ui.displayGreet();

        // Start input loop
        duke.run();

    }
}
