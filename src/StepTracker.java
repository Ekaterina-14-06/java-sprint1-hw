import java.util.HashMap;

public class StepTracker {
    int day;
    int month;
    int step;
    int purpose = 10000; // purpose - цель по количеству шагов в день; по умолчанию равна 10000 шагам в день
    HashMap<DdMm, Integer> ddmmstep = new HashMap<>();
    DdMm ddmm = new DdMm();

    {
        // Присваиваемм начальное значение, равное 0 шагов, каждому дню каждого месяца.
        for (int i = 0; i < 12; i++) {
            for (int j = 0; j < 30; j++) {
                ddmm.mm = i;
                ddmm.dd = j;
                ddmmstep.put(ddmm, 0);
            }
        }
    }

    // Объявление конструктора класса StepTracker для ввода количества шагов за день
    StepTracker(int d, int m, int s){
        this.day = d;
        this.month = m;
        this.step = s;
    }

    // Описание метода addDdMmStep класса StapTracker, добавляющего значение пройденных шагов за определённый день в хеш-таблицу.
    // Точнее, меняющего (вместо значения "по умолчанию", т.е. вместо 0) значение "количество пройденных шагов" в конкретный день на значение, введённое пользователем.
    void addDdMmStep(int d, int m, int s){
        ddmm.dd = d;
        ddmm.mm = m;
        ddmmstep.put(ddmm, s);
    }

    // Описание метода statistica класса StapTracker, выводящего статистическую информацию на экран.
    void statistica(int month){
        Converter converter = new Converter();
        System.out.println("Статистика за " + month + " месяц:");
        int i;

        System.out.println("Количество пройденных шагов по дням в выбранном месяце: ");
        ddmm.mm = month;
        for (i = 0; i < 30; i++) {
            ddmm.dd = i;
            System.out.print((i+1) + " день: " + ddmmstep.get(ddmm) + ", ");
        }
        System.out.println(""); // для переноса строки

        int sum = 0;
        for (i = 0; i < 30; i++) {
            ddmm.dd = i;
            sum += ddmmstep.get(ddmm);
        }
        System.out.println("Общее количество шагов за месяц: " + sum);

        int max = 0;
        for (i = 0; i < 30; i++) {
            ddmm.dd = i;
            if (ddmmstep.get(ddmm) > max) {
                max = ddmmstep.get(ddmm);
            }
        }
        System.out.println("Максимальное пройденное количество шагов в месяце: " + max);

        System.out.println("Среднее количество шагов: " + (sum / 30));

        System.out.println("Пройденная дистанция: " + converter.km(sum) + " км");

        System.out.println("Количество сожжённых килокалорий: " + converter.kkal(sum));

        int bestResult = 0;
        int max2 = 0;
        for (i = 0; i < 30; i++) {
            ddmm.dd = i;
            if (ddmmstep.get(ddmm) >= purpose) {
                ++max2;
            } else {
                if (max2 > bestResult) {
                    bestResult = max2;
                    max2 = 0;
                }
            }
        }
        if (ddmmstep.get(ddmm) >= purpose) {
            if (max2 > bestResult) {
                bestResult = max2;
            }
        }
        System.out.println("Лучшая серия (максимальное количество подряд идущих дней, в течение которых количество шагов за день было " + purpose + "): " + bestResult);
    }
}

// Объявление (описание) класса DdMm, необходимого для переменной ddmm - это ключ хеш-таблицы,
// состоящий из номера месяца и номера дня в этом месяце.
class DdMm {
    int dd; // dd - номер дня в месяце
    int mm; // mm - номер месяца
}

// Объявление (описание) класса Converter - переводит шаги в километры и в килокалории
class Converter {
    // описание метода km
    double km (int step) {
        return (step * 0.00075); // 1 шаг = 0,00075 км
    }
    // описание метода kkal
    double kkal (int step) {
        return (step * 0.05); // 1 шаг = 0,05 килокалорий
    }
}