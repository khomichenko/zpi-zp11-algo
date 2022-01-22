import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.function.Function;
import java.util.stream.Stream;

public class Workshop_09_3 {

    /**
     * Клас Пристрій
     */
    public static class Device {
        /** Потужність пристрою */
        public Integer power = 0;
        /**
         * Створити пристрій з потуюністью @power
         * @param power потужність
         */
        public Device(Integer power) { this.power = power; }
        @Override public String toString() { return "" +power; }
    }

    /**
     * Сортування бульбашкою
     * @param values масив
     * @param v функція визначення значення
     * @param <T> тип елементів масиву
     */
    public static <T> void sortByBubble(T[] values, Function<T,Integer> v) {
        Boolean notSorted = true;
        while (notSorted) {
            notSorted = false;
            for (int i = 0; i < values.length - 1; i++) { // перебираємо масив
                if ( v.apply(values[i]) > v.apply(values[i+1])) { // якщо i-ий більший за i+1-ий
                    // міняємо місцями i та i+1. початок
                    T temp = values[i];
                    values[i] = values[i+1];
                    values[i+1] = temp;
                    // міняємо місцями i та i+1. кінець
                    notSorted = true;
                }
            }
        }
    }

    /**
     * Отримати масив випадково згенерованих чисел від from до to кількістью count
     * @param from від
     * @param to до
     * @param count кількість
     * @return одновимірний масив
     */
    public static Integer[] getRandomIntegers(Integer from, Integer to, Integer count) {
        Integer[] out = new Integer[count];
        for (int i = 0; i < count; i++) {
            out[i] = (int) (Math.random() * (to - from) + from);
        }
        return out;
    }

    /**
     * Вивести на екран одновимірний масив
     * @param text текст-підказка
     * @param array одновимірний масив
     */
    public static void output(String text, Object[] array) {
        System.out.println(text);
        System.out.print(" [ ");
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i]+(i<array.length-1?"\t\t":""));
        }
        System.out.println(" ] ");
    }

    /**
     * Вивести на екран двовимірний масив
     * @param text текст-підказка
     * @param array двовимірний масив
     */
    public static void output2D(String text, Object[][] array) {
        System.out.println(text);

        for (int i = 0; i < array.length; i++) {
            System.out.print(" [ ");
            for (int j = 0; j < array[i].length; j++) {
                System.out.print(array[i][j]+(j<array[i].length-1?"\t\t":""));
            }
            System.out.println(" ] ");
        }
    }

    /**
     *  Об'єднати два масива з елементами типу Т
     * @param array1 масив 1
     * @param array2 масив 2
     * @param <T> тип елементів
     * @return масив
     */
    static <T> T[] concat(T[] array1, T[] array2) {
        return Stream.concat(Arrays.stream(array1), Arrays.stream(array2)).toArray(size -> (T[]) Array.newInstance(array1.getClass().getComponentType(), size));
    }

    public static void main(String[] args) {
        Integer maxSize = 5; Integer powerFrom = 100; Integer powerTo = 200;
        Device[] devices01 = Arrays.stream(getRandomIntegers(powerFrom,powerTo,maxSize)).map((p)->new Device(p)).toArray(Device[]::new);
        output("Пристрої списку 1",devices01);
        sortByBubble(devices01,(x)-> x.power);
        output("Пристрої списку 1 (відсортовані)",devices01);
        Device[] devices02 = Arrays.stream(getRandomIntegers(powerFrom,powerTo,maxSize)).map((p)->new Device(p)).toArray(Device[]::new);
        output("Пристрої списку 2",devices02);
        sortByBubble(devices02,(x)-> x.power);
        output("Пристрої списку 2 (відсортовані)",devices02);

        Device[][] devices03 = {
                Arrays.stream(getRandomIntegers(powerFrom,powerTo,maxSize)).map((p)->new Device(p)).toArray(Device[]::new),
                Arrays.stream(getRandomIntegers(powerFrom,powerTo,maxSize)).map((p)->new Device(p)).toArray(Device[]::new),
                Arrays.stream(getRandomIntegers(powerFrom,powerTo,maxSize)).map((p)->new Device(p)).toArray(Device[]::new),
                Arrays.stream(getRandomIntegers(powerFrom,powerTo,maxSize)).map((p)->new Device(p)).toArray(Device[]::new),
                Arrays.stream(getRandomIntegers(powerFrom,powerTo,maxSize)).map((p)->new Device(p)).toArray(Device[]::new)
        };
        output2D("Пристрої списків 3",devices03);
        for (int i = 0; i < devices03.length; i++) {
            sortByBubble(devices03[i],(x)->x.power);
        }
        output2D("Пристрої списків 3 (відсортовані)",devices03);
        Device[] devices0102 = concat(devices01, devices02);
        output("Пристрої списку 1 (відсортований) та списку 2 (відсортований). Не відсортований",devices0102);
        sortByBubble(devices0102,(x)-> x.power);
        Device[] devices010203 = devices0102;
        for (int i = 0; i < devices03.length; i++) {
            devices010203 = concat(devices010203, devices03[i]);
        }
        output("Пристрої списків 1+2 та списків 3. Не відсортований",devices010203);
        sortByBubble(devices010203,(x)-> x.power);
        output("Пристрої списків 1+2 та списків 3. Відсортовані",devices010203);
    }
}
