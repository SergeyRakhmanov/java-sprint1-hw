import java.util.Scanner;

/*класс в котором вводится число с консоли и проверяется что это integer, а не что-то ещё
на отрицательность здесь не проверяем специально, мало ли как у нас в будущем разовется программа
и надо будет принимать отрицательные/нулевые значения с ввода */

public class UserInput {
    int userCommand;
    Scanner scan = new Scanner(System.in);

    public int input() {
        if (!scan.hasNextInt()) { //если введен не int
            do { //делаем цикл пока пользователь вводит не int
                System.out.println("Необходимо вводить числовое значение");
                scan.next();  //запускаем в цикле считывание команды, переменную не присваиваем, без этого не работает
            } while (!scan.hasNextInt());
            userCommand = scan.nextInt(); // как только введен int присваиваем переменной это значение
        } else  userCommand = scan.nextInt(); //если сразу int то присваиваем это значение переменной
        return userCommand; //возвращаем из метода int который ввел пользователь
    }
}
