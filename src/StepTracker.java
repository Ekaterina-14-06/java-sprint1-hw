import java.util.HashMap;

public class StepTracker {
    int purpose = 10000; // purpose - цель по количеству шагов в день; по умолчанию равна 10000 шагам в день
    Integer[] dayStep = new Integer[30]; // dayStep - массив из 30-ти элементов типа int (количество шагов за каждый из 30-ти дней в одном месяце)
    HashMap<Integer, Integer[]> hashMonthDayStep = new HashMap<>();   // hashMonthDayStep - хеш-таблица с номером месяца в качестве ключа,
    // и массивом из 30-ти значений количества шагов в качестве значения

    // Присвоение начального значения, равного 0 шагов, каждому дню каждого месяца
    {
        for (int i=0; i < 30; i++) {
            dayStep[i] = 0;
        }
        for (int i = 0; i < 12; i++) {

            hashMonthDayStep.put(i, dayStep);
        }
    }

    // Метод addDdMmStep - добавляет значение количества пройденных шагов за определённый день в заданном месяце.
    // Точнее, этот метод изменяет текущее значение (изначально равное 0) за день на новое, введённое пользователем.
    void addDdMmStep (int day, int month, int step) {
        dayStep = hashMonthDayStep.get(month-1);
        dayStep[day-1] = step;
        hashMonthDayStep.put(month-1, dayStep);
    }

    // Описание метода statistica класса StapTracker, выводящего статистическую информацию на экран.
    void statistica(int month){
        Converter converter = new Converter();
        System.out.println("Статистика за " + month + " месяц:");
        int i;

        System.out.println("Количество пройденных шагов по дням в выбранном месяце: ");
        dayStep = hashMonthDayStep.get(month);
        int sum = 0;
        int max = 0;
        for (i = 0; i <30; i++) {
            System.out.print((i+1) + " день: " + dayStep[i] + ", ");
            sum += dayStep[i];
            if (dayStep[i] > max) { max = dayStep[i]; }
        }
        System.out.println("");
        System.out.println("Общее количество шагов за месяц: " + sum);
        System.out.println("Максимальное пройденное количество шагов в месяце: " + max);
        System.out.println("Среднее количество шагов: " + (sum / 30));
        System.out.println("Пройденная дистанция: " + converter.km(sum) + " км");
        System.out.println("Количество сожжённых килокалорий: " + converter.kkal(sum));

        int bestResult = 0; // итоговое максимальное количество подряд идущих дней
        int bestResultTemp = 0; // временная переменная
        for (i = 0; i < 30; i++) {
            if (dayStep[i] >= purpose) { // i - номер дня в месяце; dayStep[i] - кол-во шагов за i-тый день
                ++bestResultTemp;
            } else {
                if (bestResultTemp > bestResult) {
                    bestResult = bestResultTemp;
                }
                bestResultTemp = 0;
            }
        }
        if (bestResultTemp > bestResult) {
            bestResult = bestResultTemp;
        }
        System.out.println("Лучшая серия (максимальное количество подряд идущих дней, в течение которых количество шагов за день было " + purpose + "): " + bestResult);
    }
}