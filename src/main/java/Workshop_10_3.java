import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.function.Function;
import java.util.stream.Stream;

public class Workshop_10_3 {

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
     * Сортування швидким алгоритмом
     * @param values масив
     * @param v функція визначення значення
     * @param <T> тип елементів масиву
     * @return час виконання в мілісекундах
     */
    public static <T> Long sortByQuickAlgo(T[] values, Function<T,Integer> v) {
        long begin = System.currentTimeMillis();
        sortByQuickAlgo(values,0,values.length-1,v);
        long end = System.currentTimeMillis();
        return end - begin;
    }

    /**
     * Сортування швидким алгоритмом
     * @param values масив
     * @param start від
     * @param end до
     * @param v функція визначення значення
     * @param <T> тип елементів масиву
     */
    public static <T> void sortByQuickAlgo(T[] values, Integer start, Integer end, Function<T,Integer> v) {
        if (start >= end) return;
        T divider = values[start];
        Integer lo = start, hi = end;
        while (true) {
            while (v.apply(values[hi])>v.apply(divider)) {
                hi = hi - 1;
                if (hi<=lo) break;
            }
            if (hi<=lo) {
                values[lo] = divider;
                break;
            }
            values[lo] = values[hi];
            lo = lo + 1;
            while (v.apply(values[lo])<v.apply(divider)) {
                lo = lo + 1;
                if (lo >= hi ) break;
            }
            if (lo>=hi) {
                lo = hi;
                values[hi] = divider;
                break;
            }
            values[hi] = values[lo];
        }
        sortByQuickAlgo(values,start,lo-1,v);
        sortByQuickAlgo(values,lo+1,end,v);
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
        Long time1Ms = sortByQuickAlgo(devices01,(x)-> x.power);
        output("Пристрої списку 1 (відсортовані за "+time1Ms+" ms)",devices01);
        Device[] devices02 = Arrays.stream(getRandomIntegers(powerFrom,powerTo,maxSize)).map((p)->new Device(p)).toArray(Device[]::new);
        output("Пристрої списку 2",devices02);
        Long time2Ms = sortByQuickAlgo(devices02,(x)-> x.power);
        output("Пристрої списку 2 (відсортовані за "+time2Ms+" ms)",devices02);

        Device[][] devices03 = {
                Arrays.stream(getRandomIntegers(powerFrom,powerTo,maxSize)).map((p)->new Device(p)).toArray(Device[]::new),
                Arrays.stream(getRandomIntegers(powerFrom,powerTo,maxSize)).map((p)->new Device(p)).toArray(Device[]::new),
                Arrays.stream(getRandomIntegers(powerFrom,powerTo,maxSize)).map((p)->new Device(p)).toArray(Device[]::new),
                Arrays.stream(getRandomIntegers(powerFrom,powerTo,maxSize)).map((p)->new Device(p)).toArray(Device[]::new),
                Arrays.stream(getRandomIntegers(powerFrom,powerTo,maxSize)).map((p)->new Device(p)).toArray(Device[]::new)
        };
        output2D("Пристрої списків 3",devices03);
        Long time3Ms = 0L;
        for (int i = 0; i < devices03.length; i++) {
            time3Ms = time3Ms + sortByQuickAlgo(devices03[i],(x)-> x.power);
        }
        output2D("Пристрої списків 3 (відсортовані за "+time3Ms+" ms)",devices03);
        Device[] devices0102 = concat(devices01, devices02);
        output("Пристрої списку 1 (відсортований) та списку 2 (відсортований). Не відсортований",devices0102);
        Long time12Ms = sortByQuickAlgo(devices0102,(x)-> x.power);
        Device[] devices010203 = devices0102;
        for (int i = 0; i < devices03.length; i++) {
            devices010203 = concat(devices010203, devices03[i]);
        }
        output("Пристрої списків 1+2 (відсортовані за "+time12Ms+" ms) та списків 3. Не відсортований",devices010203);
        Long time123Ms = sortByQuickAlgo(devices010203,(x)-> x.power);
        output("Пристрої списків 1+2 та списків 3. Відсортовані за "+time123Ms+" ms",devices010203);
    }
}
