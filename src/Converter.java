// Объявление (описание) класса Converter - он переводит шаги в километры и в килокалории
class Converter {
    // описание метода km
    double km (int step) {
        double constKm = 0.00075;
        return (step * constKm); // 1 шаг = 0,00075 км
    }
    // описание метода kkal
    double kkal (int step) {
        double constKkal = 0.05;
        return (step * constKkal); // 1 шаг = 0,05 килокалорий
    }
}