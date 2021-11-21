import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class TodoManager {
    private List<TodoRecord> todoRecords = new ArrayList<>();
    private String filePath;

    public TodoManager init() {
        filePath = "store/" + Auth.getInstance().getEmail().replace("@", ".") + ".bin";
        try(ObjectInputStream reader = new ObjectInputStream(new FileInputStream(filePath))) {
            todoRecords = (ArrayList<TodoRecord>) reader.readObject();
        } catch (IOException | ClassNotFoundException e) {
            todoRecords = new ArrayList<>();
        }
        return this;
    }

    public void displayAllRecords() {
        System.out.println("All TODO records:");
        boolean hasRecords = false;
        for(TodoRecord record : todoRecords) {
            hasRecords = true;
            System.out.println(record);
        }
        if(!hasRecords) System.out.println(Color.YELLOW + "There are no TODO records yet." + Color.RESET);
    }

    public void displayDoneRecords() {
        System.out.println("All done TODO records:");
        boolean hasRecords = false;
        for(TodoRecord record : todoRecords) if(record.getIsDone()) {
            hasRecords = true;
            System.out.println(record);
        }
        if(!hasRecords) System.out.println(Color.YELLOW + "There are no done TODO records yet." + Color.RESET);
    }

    public void displayNotDoneRecords() {
        System.out.println("All not done TODO records:");
        boolean hasRecords = false;
        for(TodoRecord record : todoRecords) if(!record.getIsDone()) {
            hasRecords = true;
            System.out.println(record);
        }
        if(!hasRecords) System.out.println(Color.YELLOW + "There are no not done TODO records yet." + Color.RESET);
    }

    public void addRecord(String text, String priorityLabel) {
        if(text.trim().equals("")) {
            System.out.println(Color.RED + "Error:" + Color.RESET + " TODO record cannot be empty!");
            return;
        }
        Priority priority;
        switch(priorityLabel) {
            case "h": priority = Priority.HIGH; break;
            case "l": priority = Priority.LOW; break;
            default: priority = Priority.STANDARD;
        }
        TodoRecord newRecord = new TodoRecord(text, priority);
        todoRecords.add(newRecord);
        System.out.println(Color.GREEN + "Info:" + Color.RESET + " TODO record with id " + newRecord.getId() + " was successfully added!");
    }

    public void deleteRecord(int id) {
        for(TodoRecord record : todoRecords) if(record.getId() == id) {
            todoRecords.remove(record);
            System.out.println(Color.GREEN + "Info:" + Color.RESET + " TODO record with id " + id + " was successfully deleted!");
            return;
        }
        System.out.println(Color.RED + "Error:" + Color.RESET + " There is no TODO record with id " + id + "!");
    }

    public void deleteAllRecords() {
        todoRecords = new ArrayList<>();
        System.out.println(Color.GREEN + "Info:" + Color.RESET + " All TODO records were successfully deleted!");
    }

    public void markRecordAsDone(int id) {
        for(TodoRecord record : todoRecords) if(record.getId() == id) {
            record.setIsDone(true);
            System.out.println(Color.GREEN + "Info:" + Color.RESET + " TODO record with id " + id + " now is done!");
            return;
        }
        System.out.println(Color.RED + "Error:" + Color.RESET + " There is no TODO record with id " + id + "!");
    }

    public void markRecordAsNotDone(int id) {
        for(TodoRecord record : todoRecords) if(record.getId() == id) {
            record.setIsDone(false);
            System.out.println(Color.GREEN + "Info:" + Color.RESET + " TODO record with id " + id + " now is not done!");
            return;
        }
        System.out.println(Color.RED + "Error:" + Color.RESET + " There is no TODO record with id " + id + "!");
    }

    public void saveRecords() {
        try(ObjectOutputStream writer = new ObjectOutputStream(new FileOutputStream(filePath))) {
            writer.writeObject(todoRecords);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}