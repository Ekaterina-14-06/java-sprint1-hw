import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("Проект №1 Счётчик калорий.");
        System.out.println("Выполнила: Лядова Е.В.");
        System.out.println("");

        StepTracker stepTracker = new StepTracker();
        //StepTracker stepTracker = new StepTracker(0, 0, 0);

        Scanner scanner = new Scanner(System.in);
        printMenu();
        int userInput = scanner.nextInt();
        while (userInput != 0) {

            // Ввод количества шагов в определённый день
            if (userInput == 1) {

                System.out.print("Введите число в месяце (от 1 до 30): ");
                int dd = scanner.nextInt();
                while (!(dd>=1 && dd<=30)) {
                    System.out.print("Вы ввели неверное число. Введите число от 1 до 30: ");
                    dd = scanner.nextInt();
                }

                System.out.print("Введите номер месяца (от 1 до 12): ");
                int mm = scanner.nextInt();
                while (!(mm>=1 && mm<=12)) {
                    System.out.print("Вы ввели неверный месяц. Введите его номер от 1 до 12: ");
                    mm = scanner.nextInt();
                }

                System.out.print("Введите количество шагов, сделанных в этот день: ");
                int steps = scanner.nextInt();
                while (!(steps>=0)) {
                    System.out.print("Количество шагов не может быть отрицательным. Повторите ввод количества шагов: ");
                    steps = scanner.nextInt();
                }

                stepTracker.addDdMmStep(dd, mm, steps);

                // Просмотр статистики за выбранный месяц
            } else if (userInput == 2) {
                System.out.print("Введите номер месяца, статистику по которому хотите получить: ");
                int mm = scanner.nextInt();
                while (!(mm>=1 && mm<=12)) {
                    System.out.print("Вы ввели неверный месяц. Введите его номер от 1 до 12: ");
                    mm = scanner.nextInt();
                }

                stepTracker.statistica(mm);

                // Изменение цели по количеству шагов в день
            } else if (userInput == 3) {
                System.out.print("Введите новую цель по шагам: ");
                int steps = scanner.nextInt();
                while (!(steps>=0)) {
                    System.out.print("Количество шагов не может быть отрицательным. Повторите ввод количества шагов: ");
                    steps = scanner.nextInt();
                }
                stepTracker.purpose = steps;

                // Обработка ошибочно введённых данных
            } else {
                System.out.print("Вы ввели несуществующий пункт меню. Повторите ввод: ");
                System.out.println("");
            }
            printMenu();
            userInput = scanner.nextInt();
        }
        System.out.println ("Программа завершена.");
        scanner.close();
    }

    private static void printMenu() {
        System.out.println("Что вы хотите сделать? ");
        System.out.println("1 - Ввести количество шагов за определенный день");
        System.out.println("2 - Посмотреть статистику за выбранный месяц");
        System.out.println("3 - Изменить цель по количеству шагов в день");
        System.out.println("0 - Выйти из приложения");
    }
}
