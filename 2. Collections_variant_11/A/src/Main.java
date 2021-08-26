import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

/* Задания к главе 11
* Не используя вспомогательных объектов, переставить отрицательные элементы
*  данного списка в конец, а положительные — в начало списка.
* */
public class Main {

    public static List<Integer> createList() {
        ArrayList<Integer> integers = new ArrayList<>();
        Random random = new Random(System.currentTimeMillis());
        for(int i = 0; i < 15; i++) {
            int number = -15 + random.nextInt(16 + 15);
            integers.add(number);
        }
        return integers;
    }


    public static void main(String[] args) {
        List<Integer> list = createList();
        System.out.println(list);
        List<Integer> result = list.stream().sorted((o1, o2) -> o2).collect(Collectors.toList());
        System.out.println(result);
    }
}
