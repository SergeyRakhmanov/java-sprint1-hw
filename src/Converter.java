//для преобразования шагов в километры и калории
public class Converter {
    double calories = 0;
    double distance = 0;

    public double convertIntoDistance(int stepQTY) {
        return distance = stepQTY * 0.00075; // преобразуем шаги в километры
    }

    public double convertIntoCalories(int stepQTY) {
        return calories = stepQTY * 0.05; // преобразуем шаги в килокалории
    }
}
