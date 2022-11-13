public class StepTracker {
    int stepGoal = 10000; // цель шагов в день по умолчанию

    /*массив из 12 объектов MonthData в каждом из который хранится информация о шагах за месяц,
    индекс элемента массива соотносится с индексом месяца в году (от 0 до 11) */
    MonthData[] monthToData;

    public StepTracker() {
        monthToData = new MonthData[12];
        for (int i = 0; i < monthToData.length; i++) {
            monthToData[i] = new MonthData();
        }
    }

    void changeGoal() {
        System.out.println("Введите новую цель по количеству шагов в день:");

        UserInput command = new UserInput();

        int goal = 0;
        do {
            goal = command.input();
            if (goal < 0) {
                System.out.println("Количество шагов не может быть отрицательным");
            }
        } while (goal<0);
        stepGoal = goal;
    }

    public int DayQTY(MonthData monthData) { //метод для вывода количества шагов за день
        UserInput command = new UserInput();
        int qty;
        int day;
        do {
            System.out.println("Введите день за который необходимо показать количество шагов");
            System.out.println("(от 1 до 30)");
            day = command.input() - 1; //уменьшаю введенное значение на 1, чтобы удобнее работать с массивами
            if ((day < 0) || ( day > 30)) {
                System.out.println("Введенное значение вне диапазона 1-30");
            }
        } while (day < 0);
        return qty = monthData.dayQTY(day);
    }

    public void saveDayQTY() { //метод для сохранения количества шагов за день
        UserInput day = new UserInput(); //объект для ввода дня
        UserInput month = new UserInput(); //объект для ввода месяца

        int i;
        int j;

        do {
            System.out.println("Введите номер месяца за который необходимо вывести статику");
            System.out.println("от 1 до 12, где январь - 1, а декабрь 12)");
            j = month.input() - 1; //уменьшаю введенное значение на 1, чтобы удобнее работать с массивами
            if ((j < 0) || ( j > 12)) {
                System.out.println("Введенное значение вне диапазона 1-12");
            }
        } while (j < 0);

        do {
            System.out.println("Введите день за который необходимо ввести данные");
            System.out.println("(от 1 до 30)");
            i = day.input() - 1; //уменьшаю введенное значение на 1, чтобы удобнее работать с массивами
            if ((i < 0) || (i > 30)) {
                System.out.println("Введенное значение вне диапазона 1-30");
            }
        } while (i < 0);
        monthToData[j].saveQTY(i);
    }

    void statistics() {
        int monthNumber;
        int dayNumber;
        double totalDistance = 0; //переменная для подсчета полной дистанции
        double totalCalories = 0; //переменная для подсчета оющего числа калорий

        do {
            System.out.println("Введите номер месяца за который необходимо вывести статистику");
            System.out.println("от 1 до 12, где январь - 1, а декабрь 12)");
            UserInput command = new UserInput();
            monthNumber = command.input() - 1; //уменьшаю введенное значение на 1, чтобы удобнее работать с массивами
            if ((monthNumber < 0) || (monthNumber > 11)) {
                System.out.println("Введенное значение вне диапазона 1-12");
            }
        } while ((monthNumber < 0) || (monthNumber > 11));

        Converter convert = new Converter(); //объект для вызова методов конвертации

        for (dayNumber = 0; dayNumber < 30; dayNumber++) {
            int dayQTY = monthToData[monthNumber].dayQTY(dayNumber); //получаем значение шагов за день
            System.out.print((dayNumber+1) + " день: " + dayQTY + ", ");

            totalCalories = totalCalories + convert.convertIntoCalories(dayQTY); //считаем общие калории
            totalDistance = totalDistance + convert.convertIntoDistance(dayQTY); //считаем общее расстояние
        }
        System.out.println();
        int totalMonthQTY = monthToData[monthNumber].allQTY(); /*получаем общее количество шагов за месяц, чтобы потом
        не вызывать метод два раза*/

        System.out.println("Общее количество шагов за месяц: " + totalMonthQTY);
        System.out.println("Максимальное количество шагов в месяце: " + monthToData[monthNumber].maxSteps());
        System.out.println("Среднее количество шагов в месяце: " + totalMonthQTY/monthToData[monthNumber].monthLength());
        System.out.println("Пройденная дистанция: " + totalDistance);
        System.out.println("Сожженые калории: " + totalCalories);
        System.out.println("Лучшая серия: " + monthToData[monthNumber].bestSeries(stepGoal));
        System.out.println();
    }
}
