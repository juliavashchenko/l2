//Система управління персоналом: Розробіть ієрархію класів для різних типів
//співробітників (менеджери, розробники, дизайнери). Реалізуйте методи для
//розрахунку зарплати, призначення завдань та оцінки продуктивності,
//використовуючи поліморфізм.

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static int get_int_in_range(String prompt, int min, int max) {
        int x;
        Scanner sc = new Scanner(System.in);

        while (true)
            try {
                System.out.print(prompt);
                x = Integer.parseInt(sc.nextLine());
                if ((x < min) || (x > max)) {
                    System.out.printf("\nБудь ласка, уведіть число в межах від %d до %d:\n", min, max);
                    continue;
                }
                break;
            } catch (Exception e) {
                System.out.println("Неправильне введення! Спробуйте ще раз: ");
            }

        return x;
    }


    public static void main(String[] args) {
        Employees managers1 = new Managers(1, "Олексій Коваленко");
        Employees managers2 = new Managers(2, "Богдан Петренко");
        Employees developers1 = new Developers(3, "Сергій Василенко");
        Employees designers1 = new Designers(4, "Анна Мельник");


        List<Employees> employees = new ArrayList<>();

        employees.add(managers1);
        employees.add(managers2);
        employees.add(developers1);
        employees.add(designers1);


        managers1.AddTask(new Task(12, "Організуйте зустріч з президентом компанії 'Вел-Вел-Вел'", true));
        managers1.AddTask(new Task(24, "Принесіть каву босу", true));
        managers1.AddTask(new Task(1, "Провести щотижневу нараду з командою", true));
        managers1.AddTask(new Task(63, "Оцінити результати квартального звіту", true));
        managers1.AddTask(new Task(100, "Підготувати план на наступний місяць", true));
        managers1.AddTask(new Task(101, "Зустрітися з ключовими клієнтами", true));
        managers1.AddTask(new Task(102, "Оцінити продуктивність команди", true));
        managers1.AddTask(new Task(103, "Провести аналіз ринку", false));


        developers1.AddTask(new Task(5, "Розробити нову функцію для веб-додатку", false));
        developers1.AddTask(new Task(6, "Виправити помилки в коді", false));
        developers1.AddTask(new Task(7, "Написати юніт-тести для нового функціоналу", false));
        developers1.AddTask(new Task(8, "Оновити документацію проекту", true));
        developers1.AddTask(new Task(9, "Забезпечити інтеграцію з новими API", false));
        developers1.AddTask(new Task(10, "Провести код-рев’ю", true));

//        designers1.AddTask(new Task(345, "Створити макет нового продукту", false));
//        designers1.AddTask(new Task(8, "Оновити графіку на веб-сайті", true));
//        designers1.AddTask(new Task(9, "Підготувати презентацію для нової колекції", false));
        designers1.AddTask(new Task(10, "Створити анімацію для реклами", true));
        designers1.AddTask(new Task(11, "Розробити логотип для нового продукту", false));
        designers1.AddTask(new Task(12, "Створити графіку для соціальних мереж", true));

       

        while (true) {
            int ch = get_int_in_range("""
                    
                    1. Вивести весь список персоналу.
                    2. Обрахувати зарплату для всіх працівників та вивести їхню продуктивність.
                    3. Завдання для працівника.
                    4. Подивитися статус завадння.
                    0. Завершити виконання програми.
                    Уведіть число:\s""", 0, 4);

            if (ch == 0) {
                System.out.println("Виконання програми завершено.");
                break;
            }
            if (ch == 1) {
                for (Employees employee : employees) {
                    System.out.println("-----------------");
                    System.out.printf("ПРАЦІВНИК №%d%n%nІм'я: %s%nПосада: %s%n",
                            employee.id,
                            employee.name,
                            employee.department);

                }
                System.out.println("-----------------\n");
            }


            if (ch == 2) {
                for (Employees employee : employees) {
                    System.out.println("-----------------");
                    System.out.printf("ПРАЦІВНИК №%d%nІм'я: %s%nПосада: %s%nЗарплата: %.2f%nПродуктивність: %.2f%%%n",
                            employee.id,
                            employee.name,
                            employee.department,
                            employee.salary,
                            employee.productivity());
                }
                System.out.println("-----------------\n");
            }


            if (ch == 3) {
                int c = -1;
                int id = get_int_in_range(" \nВведіть ідентифікаційний код працівника, якому хочете призначити завдання:", 1, employees.size());
                do{
                    for (Employees employee : employees) {
                        if (employee.id == id) {
                            System.out.println(employee.name + ": ");

                            c = get_int_in_range("""
                                    
                                    Оберіть опцію:
                                    1. Вивести список поточних завдань.
                                    2. Додати нове завдання.
                                    3. Переглянути статус завдань.
                                    0. Повернутися до попередньої сторінки.
                                    Уведіть число:\s""", 0, 3);

                            if (c == 1) {
                                employee.printTasks();
                            }

                            if (c == 2) {
                                int num = get_int_in_range("Уведіть номер завдання:", 0, 10000000);
                                System.out.println("Опишіть завдання: ");

                                String task = System.console().readLine();
                                employee.AddTask(new Task(num, task, false));
                            }

                            if (c == 3) {
                                System.out.println("Статус завдань: \n");
                                employee.printStatusOfTasks();
                            }
                        }
                        }
                    }while (c != 0);

                }
            if (ch == 4) {
                int mp = get_int_in_range(" \nВведіть ідентифікаційний код працівника: ", 1, employees.size());
                int id = get_int_in_range("Уведіть номер завдання:", 0, 10000000);
                for (Task ts : employees.get(mp-1).tasks) {
                   if (ts.number == id) {
                       if (ts.status) {
                           System.out.println("Виконано");
                       } else {
                           System.out.println("Не виконано");
                       }
                   }
                    }

            }
            }

        }
    }


