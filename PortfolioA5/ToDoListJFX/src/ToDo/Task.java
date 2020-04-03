package ToDo;


public class Task {
    private String desc;
    private boolean isComplete;

    public Task(String desc) {
        this.desc = desc;
        isComplete = false;
    }

    public void markComplete() {
        isComplete = true;
    }

    public void incomplete() {
        isComplete = false;
    }

    public Boolean isComplete() {
        return isComplete;
    }

    @Override
    public String toString() {
        return desc;
    }
}
