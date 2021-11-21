import java.io.Serializable;

public class TodoRecord implements Serializable {
    private final int id;
    private final String text;
    private final Priority priority;
    private boolean isDone;

    public TodoRecord(String text, Priority priority) {
        this.text = text;
        this.priority = priority;
        id = Id.getInstance().generateId();
        isDone = false;
    }

    public boolean getIsDone() {
        return isDone;
    }

    public int getId() {
        return id;
    }

    public void setIsDone(boolean isDone) {
        this.isDone = isDone;
    }

    @Override
    public String toString() {
        return priority + " " + (isDone ? Color.STRIKETHROUGH + Color.CYAN.toString() : "") + id + ". " + text + Color.RESET;
    }
}