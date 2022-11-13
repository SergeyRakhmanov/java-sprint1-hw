public class MonthData {
    int[] monthQTY = new int[30]; //массив из 30 элементов, в которых будет храниться количество шагов за каждый день

    int allQTY() { //метод для подсчета общего количества шагов в месяце
        int qty = 0;
        for (int i = 0; i < monthQTY.length; i++) {
            qty = qty + monthQTY[i];
        }
        return qty;
    }

    public int dayQTY (int i) { //метод для возврата количества шагов в определенном дне
        return monthQTY[i];
    }

    public void saveQTY(int i){ //метод для сохранения нового значения шагов в определенном дне
        UserInput command = new UserInput();
        System.out.println("Введите количеситво сделанных шагов");
        int qty = 0;
        do { //в данном цикле проверяем что вводимое знаение не отрицательно
            qty = command.input();
            if (qty < 0) {
                System.out.println("Количество шагов не может быть отрицательным");
            }
        } while (qty<0);
        monthQTY[i] = qty;
    }

    public int monthLength() { //метод для определения количества дней в месяце, задел на будущее
        int l = monthQTY.length;
        return l;
    }

    public int maxSteps() { //метод для определения максимального количества шагов
        int max = 0;
        for (int i = 0; i < monthQTY.length; i++){
            if (max < monthQTY[i]) {
                max = monthQTY[i];
            }
        }
        return max;
    }

    public int bestSeries(int goal) { /*метод для определения максимальной серии дней, когда количество шагов
    превышало целевое
        логика поиска следующая: сначала считаем все серии в массиве данных, а потом выбираем их них наилучшую
        максимальное количество серий всего - это ситуация когда у нас чередуются по одному дню достижения и
        не достижения цели.  Таким образом массив данных по сериям будет иметь максимальный размер вполовину исходного
        плюс ещё элемент для месяцев с нечетым количеством дней (это возможный задел на будущее,
        для настоящего календаря). Максимум у нас 31 день в месяце, то есть максимум 16 серий*/

        int maxDayOverGoal = 0; //по умолчанию считаем, что достижение цели не состоялось ни разу
        int[] DayOverGoal = new int[16]; //массив для серий
        int j = 0;

        for (int i = 0; i < monthQTY.length; i++) {
            if (monthQTY[i] >= goal) {
                maxDayOverGoal++; //если цель достигнута увеличиваем серию на 1
            } else { //если цель не достигнута записываем значение серии в массив серий
                if (maxDayOverGoal != 0) { //нулевые серии не записываем
                    DayOverGoal[j] = maxDayOverGoal;
                    j++; //увеличиваем индекс в массиве серий
                    maxDayOverGoal = 0; //обнуляем счетчик дней в серии для поиска следующей серии
                }
            }
        }

        //теперь найдем максимум в массиве серий
        maxDayOverGoal = DayOverGoal[0]; //сначала считаем что максимум это первая серия
        for (int i = 1; i < DayOverGoal.length; i++) {
            if (DayOverGoal[i] > maxDayOverGoal) {
                maxDayOverGoal = DayOverGoal[i];
            }
        }
        return maxDayOverGoal;
    }
}
