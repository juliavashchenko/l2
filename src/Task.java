public class Task {
    protected int number;
    protected boolean status;
    protected String task;


    public Task(Integer num, String task, boolean isCompleted) {
        this.number = num;
        this.status = isCompleted;
        this.task = task;
    }
}

