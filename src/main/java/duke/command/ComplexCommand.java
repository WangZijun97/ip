package duke.command;

public abstract class ComplexCommand extends Command{

    public String params;
    public ComplexCommand(String params) {
        this.params = params;
    }

    public String toString() {
        return super.toString() + " | " + this.params;
    }

}
