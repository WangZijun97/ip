package duke;

import duke.task.Task;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

/**
 * Provides a container for storing <code>Task</code> objects.
 */
public class TaskManager {

    /** List for storing objects in order. */
    private List<Task> storage;

    /**
     * Creates a new empty <code>TaskManager</code>.
     */
    public TaskManager() {
        this.storage = new ArrayList<>();
    }

    /**
     * Creates a new <code>TaskManager</code> with <code>Task</code> objects from <code>fromSaveData</code>.
     * @param fromSaveData List of tasks to initialise <code>TaskManager</code> with.
     */
    public TaskManager(List<Task> fromSaveData) {
        this.storage = new ArrayList<>(fromSaveData);
    }

    /**
     * Returns an <code>ArrayList</code> filled with <code>Task</code>s from this <code>TaskManager</code>.
     * A new list is provided and can be mutated without affecting <code>TaskManager</code>,
     * but any mutations to <code>Task</code>s will persist.
     *
     * @return <code>List</code> of <code>Task</code>s in <code>TaskManager</code>.
     */
    public List<Task> getAllTasks() {
        return new ArrayList<>(this.storage);
    }

    /**
     * Adds a given <code>Task</code> to the end of <code>storage</code>.
     *
     * @param t New <code>Task</code>.
     */
    public void storeTask(Task t) {
        this.storage.add(t);
    }

    /**
     * Gets a <code>Task</code> at a given <code>index</code> of <code>storage</code>.
     * Please note that this getter works with 0-based indexing, and is unable to handle OutOfIndexExceptions.
     * Functions relying on 1-based index should correct the index before calling <code>getTask()</code>.
     * Index verification should also be performed before calling <code>getTask()</code>.
     *
     * @param index Position of desired <code>Task</code>.
     * @return Desired <code>Task</code>.
     */
    public Task getTask(int index) {
        return this.storage.get(index);
    }

    /**
     * Deletes and returns a <code>Task</code> at a given <code>index</code> of <code>storage</code>.
     * Please note that this getter works with 0-based indexing, and is unable to handle OutOfIndexExceptions.
     * Functions relying on 1-based index should correct the index before calling <code>removeTask()</code>.
     * Index verification should also be performed before calling <code>removeTask()</code>.
     *
     * @param index Position of <code>Task</code> to delete.
     * @return Deleted <code>Task</code>.
     */
    public Task removeTask(int index) {
        return this.storage.remove(index);
    }

    /**
     * Performs <code>action</code> on each <code>Task</code> in <code>storage</code>.
     *
     * @param action <code>Consumer</code> to be performed on each <code>Task</code>.
     */
    public void forEach(Consumer<? super Task> action) {
        this.storage.forEach(action);
    }

    /**
     * Returns the number of <code>Task</code>s in <code>storage</code>.
     *
     * @return Number of <code>Task</code>s.
     */
    public int size() {
        return this.storage.size();
    }

    /**
     * Returns String formatted for display of <code>TaskManager</code> contents neatly.
     * @return Formatted String.
     */
    public String toString() {
        StringBuilder printString = new StringBuilder();
        for (int i = 0; i < this.size(); i++) {
            printString.append(i + 1).append(". ").append(this.getTask(i).toString()).append("\n");
        }
        return printString.toString();
    }

}
