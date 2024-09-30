import java.util.ArrayList;
import java.util.List;

public abstract class Employees {

    protected String name;
    protected Integer id;
    protected Double minimum_salary;
    protected String department;
    protected Double rate;
    protected Double salary;
    protected List<Task> tasks;


    public void AddTask(Task task) {
        tasks.add(task);
    }

    public void printTasks() {
        if (tasks.isEmpty()) {
            System.out.println("Для цього робітника ще немає завдань/");
        }
        for (Task task : tasks) {
            System.out.println("Завдання " + task.number + ": " + task.task);
        }
    }

    public void printStatusOfTasks() {
        if (tasks.isEmpty()) {
            System.out.println("Для цього робітника ще немає завдань.");
        }
        for (Task task : tasks) {
            if (task.status) {
                System.out.println("Завдання '" + task.task + "' виконано");
            } else {
                System.out.println("Завдання '" + task.task + "' не виконано");
            }
        }
    }

    public double productivity() {
        int performed = 0;

        for (Task task : tasks) {
            if (task.status) {
                performed += 1;
            }
        }
        if (tasks.isEmpty()) {
            return 0;
        }


        return ((double) performed / tasks.size() * 100);
    }

    public Employees(String name, Integer id, String department, Double rate) {
        this.name = name;
        this.minimum_salary = 8000.0;
        this.id = id;
        this.department = department;
        this.rate = rate;
        this.salary = this.minimum_salary * this.rate;
        tasks = new ArrayList<>();


    }
}
