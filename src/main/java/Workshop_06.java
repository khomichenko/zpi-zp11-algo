import java.util.HashMap;
import java.util.Map;
import java.util.function.BiFunction;

public class Workshop_06 {

    public static class HashTable {

        private Integer size;
        private Map<Integer, Integer> map = new HashMap<>();

        public HashTable(Integer size) {
            this.size = size;
        }

        public void add(Integer value, BiFunction<Integer,Integer,Integer> hashFunction) {
            Integer bucket = hashFunction.apply((Integer) value, this.size);
            if (bucket!=null) while (this.map.get(bucket)!=null && this.map.get(bucket)!=value) {
                bucket = (bucket +1 ) % size;
            }
            if (bucket!=null) {
                this.map.put(bucket,value);
            }
        }

        public Integer getSize() {
            return size;
        }

        @Override public String toString() {
            return map.toString();
        }
    }

    public static void main(String[] args) {
        {
            Workshop.output("*****************************");
            Workshop.output("Методом лінійного зондування");
            Integer hashSize = Workshop.inputInt("Введіть розмір хеш-таблиці?");
            HashTable hashTable = new HashTable(hashSize);
            Integer count = Workshop.inputInt("Введіть кількість елементів таблиці?");
            Integer[] data = Workshop.getRandomIntegers(0,100,count);
            Workshop.output("Елементи таблиці: ",data);
            for (Integer x: data) {
                hashTable.add(x,(v,size)->{
                    return v % size;
                });
            }
            Workshop.output("Результат: " + hashTable.toString());
        }
        {
            Workshop.output("*****************************");
            Workshop.output("Методом подвійного хешування");
            Integer hashSize = Workshop.inputInt("Введіть розмір хеш-таблиці?");
            HashTable hashTable = new HashTable(hashSize);
            Integer count = Workshop.inputInt("Введіть кількість елементів таблиці?");
            Integer[] data = Workshop.getRandomIntegers(0,10,count);
            Workshop.output("Елементи таблиці: ",data);
            for (Integer x: data) {
                hashTable.add(x,(v,size)->{
                    return (1+v) % (size-2);
                });
            }
            Workshop.output("Результат: " + hashTable.toString());
        }
    }

}
