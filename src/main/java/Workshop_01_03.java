import java.util.Arrays;
import java.util.Scanner;

/**
 * В одномірному масиві з n елеменів поміняти місцями максимальний та мінімальний
 */
public class Workshop_01_03 {

    /**
     * Введення цілого числа з клавіатури
     * @param text текст-підказка
     * @return ціле число
     */
    public static Integer inputInt(String text) {
        System.out.print( text+"  " );
        return (new Scanner( System.in )).nextInt();
    }

    /**
     * Згенерувати випадкові дробні число в певних межах
     * @param from ліва межа
     * @param to права межа
     * @param count кількість
     * @return масив
     */
    public static Double[] getRandomDouble(Double from, Double to, Integer count) {
        Double[] out = new Double[count];
        for (int i = 0; i < count; i++) {
            out[i] = Math.floor(Math.random()*(to-from+1)+from);
        }
        return out;
    }

    /**
     * В одномірному масиві з n елементів поміняти місцями максимальний та мінімальний елементи.
     * @param array вхідний одномірний масив
     * @return вихідний масив
     */
    public static Double[] swapMinMax(Double[] array) { // Складність O(n)
        Double[] result = new Double[array.length]; // Результуючий масив. Складність O(1)
        int minI = 0, maxI = 0; // Змінні, куди ми запишемо індекси максимального і мінімального елементів масива. Складність O(1)
        for (int i = 0; i < array.length ; i++) { // Складність O(n)
            Double x = array[i]; // Берем i-ий елемент масива. Складність O(1)
            minI = array[minI]<x ? i : minI; // Визначаємо мінімальний індекс. Складність O(1)
            maxI = array[maxI]>x ? i : maxI; // Визначаємо максимальний індекс. Складність O(1)
            result[i] = x; // Добавляемо елемент в результуюций самив. Складність O(1)
        }
        // Міняємо місцями мінімальний і максимальних елементи в результуючому масиві.
        result[minI] = array[maxI]; // Складність O(1)
        result[maxI] = array[minI]; // Складність O(1)
        return result; // Вертаємо результуючий часив
    }

    public static void main(String[] args) {
        Integer count = Workshop.inputInt("Введіть розмір вхідного масива? ");
        Double[] array = getRandomDouble(0.0, 100.0, count);
        System.out.println("Вхідний масив: " + Arrays.deepToString(array));
        Double[] swapped = swapMinMax(array);
        System.out.println("Вихідний масив: " + Arrays.deepToString(swapped));
    }
}
