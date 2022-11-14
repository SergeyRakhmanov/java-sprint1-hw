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

        int goal = command.input(); //считываю что ввел пользователь
        while (goal < 0) {          // запускаю цикл если введенное значение отрицательное
            System.out.println("Количество шагов не может быть отрицательным");
            goal = command.input();
        }
        stepGoal = goal;
    }

    public int DayQTY(MonthData monthData) { //метод для вывода количества шагов за день
        UserInput command = new UserInput();
        int qty;
        int day;

        System.out.println("Введите день за который необходимо показать количество шагов");
        System.out.println("(от 1 до 30)");
        day = command.input() - 1; //уменьшаю введенное значение на 1, чтобы удобнее работать с массивами
        while ((day < 0) || ( day > 30)) { //запускаем в цикле запрос значения если оно вне диапазона дней в месяце
            System.out.println("Введенное значение вне диапазона 1-30. Повторите ввод");
            day = command.input() - 1;
        }
        return qty = monthData.dayQTY(day);
    }

    public void saveDayQTY() { //метод для сохранения количества шагов за день
        UserInput day = new UserInput(); //объект для ввода дня
        UserInput month = new UserInput(); //объект для ввода месяца

        int i;
        int j;

        System.out.println("Введите номер месяца в котором добавляется количество пройденных шагов");
        System.out.println("от 1 до 12, где январь - 1, а декабрь 12)");
        j = month.input() - 1;               //уменьшаю введенное значение на 1, чтобы удобнее работать с массивами
        while ((j < 0) || (j > 11)) {        //если значние не попадает в диапазон номеров месяцев, просим ввести заново
            System.out.println("Введенное значение вне диапазона 1-12. Повторите ввод");
            j = month.input() - 1;
        }

        System.out.println("Введите день за который необходимо ввести данные");
        System.out.println("(от 1 до 30)");
        i = day.input() - 1;               //уменьшаю введенное значение на 1, чтобы удобнее работать с массивами
        while ((i < 0) || (i > 29)) {      //если значние не попадает в диапазон запрашиваем ввести заново
            System.out.println("Введенное значение вне диапазона 1-30. Повторите ввод");
            i = day.input() - 1;
        }
        monthToData[j].saveQTY(i);
    }

    void statistics() {
        int monthNumber;
        int dayNumber;
        double totalDistance = 0; //переменная для подсчета полной дистанции
        double totalCalories = 0; //переменная для подсчета оющего числа калорий

        System.out.println("Введите номер месяца за который необходимо вывести статистику");
        System.out.println("от 1 до 12, где январь - 1, а декабрь 12)");
        UserInput command = new UserInput();
        monthNumber = command.input() - 1; //уменьшаю введенное значение на 1, чтобы удобнее работать с массивами
        while ((monthNumber < 0) || (monthNumber > 11)) {  //если значние не попадает в диапазон номеров месяцев, просим ввести заново
            System.out.println("Введенное значение вне диапазона 1-12. Повторите ввод");
            monthNumber = command.input() - 1;
        }

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
