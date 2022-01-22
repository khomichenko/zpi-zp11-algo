import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.function.BiFunction;

public class Workshop_06 {

    /**
     * Хеш-таблиця
     */
    public static class HashTable {

        private Integer size;// розмір
        private Map<Integer, Integer> map = new HashMap<>();

        public HashTable(Integer size) {
            this.size = size;
        }

        /**
         * Додати значення в хеш-таблицю
         * @param value значення
         * @param hashFunction функція визначення хеша
         */
        public void add(Integer value, BiFunction<Integer,Integer,Integer> hashFunction) {
            Integer bucket = hashFunction.apply((Integer) value, this.size);
            if (bucket!=null) while (this.map.get(bucket)!=null && this.map.get(bucket)!=value) {
                bucket = (bucket +1 ) % size;
            }
            if (bucket!=null) {
                this.map.put(bucket,value);
            }
        }

        /**
         * Отримати розмір хеш-таблиці
         * @return розмір
         */
        public Integer getSize() {
            return size;
        }

        /**
         * Отримати рядкове представлення хеш-таблиці
         * @return представлення
         */
        @Override public String toString() {
            return map.toString();
        }
    }

    /**
     * Вивести на екран текст
     * @param text текст
     */
    public static void output(Object text){
        System.out.println(text);
    }

    /**
     * Зчитати з клавіатури ціле число
     * @param text текст-підказка
     * @return ціле число
     */
    public static Integer inputInt(String text) {
        System.out.print( text+"  " );
        return (new Scanner( System.in )).nextInt();
    }

    /**
     * Отримати випадково сгенеровані цілі числа в масиві
     * @param from ціле число від
     * @param to ціле число до
     * @param count кількість
     * @return масив цілих чисел
     */
    public static Integer[] getRandomIntegers(Integer from, Integer to, Integer count) {
        Integer[] out = new Integer[count];
        for (int i = 0; i < count; i++) {
            out[i] = (int) (Math.random() * (to - from) + from);
        }
        return out;
    }

    /**
     * Вивести на екран масив цілих чисел
     * @param text текст-підказка
     * @param array масив цілих чисел
     */
    public static void output(String text, Integer[] array){
        System.out.print(text+" ");
        for (Integer x: array) {
            System.out.print(x+" ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        {
            output("*****************************");
            output("Методом лінійного зондування");
            Integer hashSize = inputInt("Введіть розмір хеш-таблиці?");
            HashTable hashTable = new HashTable(hashSize);
            Integer count = inputInt("Введіть кількість елементів таблиці?");
            Integer[] data = getRandomIntegers(0,100,count);
            output("Елементи таблиці: ",data);
            for (Integer x: data) {
                hashTable.add(x,(v,size)->{
                    return v % size;
                });
            }
            output("Результат: " + hashTable.toString());
        }
        {
            output("*****************************");
            output("Методом подвійного хешування");
            Integer hashSize = inputInt("Введіть розмір хеш-таблиці?");
            HashTable hashTable = new HashTable(hashSize);
            Integer count = inputInt("Введіть кількість елементів таблиці?");
            Integer[] data = getRandomIntegers(0,10,count);
            output("Елементи таблиці: ",data);
            for (Integer x: data) {
                hashTable.add(x,(v,size)->{
                    return (1+v) % (size-2);
                });
            }
            output("Результат: " + hashTable.toString());
        }
    }

}
