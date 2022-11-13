public class Main {
    public static void main(String[] args) {
        StepTracker userStep = new StepTracker(); //создаю новый объект класса трекер
        printMenu(); //начальная печать меню команд

        //Считываем команду пользователя и переходим в цикл
        UserInput enter = new UserInput(); //объявляем новый объект нашего класса по вводу пользователем числовых значений

        int command = enter.input(); //получаем значение выбора пользователем пункта меню

        while (command != 0 ) {//"бесконечный" цикл пока пользователь не введет 0

            if (command == 1) { //ввести кол-во шагов
                userStep.saveDayQTY();
                printMenu();
                command = enter.input();

            } else if (command == 2) { // напечатать статистику
                userStep.statistics();
                printMenu();
                command = enter.input();

            } else if (command == 3) { //изменить цель (количество шагов в день)
                userStep.changeGoal();
                printMenu();
                command = enter.input();

            } else {
                System.out.println("Такой команды нет, попробуйте ещё раз");
                printMenu();
                command = enter.input();
            }
        }

        //После завершения цикла печатем выход из программы
        System.out.println("Завершение раоты программы");
    }

    public static void printMenu(){
        System.out.println("Выберите команду:");
        System.out.println("1. Ввести количество шагов");
        System.out.println("2. Напечатать статистику");
        System.out.println("3. Изменить цель на день");
        System.out.println("0. Выход");
    }
}