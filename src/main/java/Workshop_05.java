import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class Workshop_05 {

    /*Хеш-функція "метод ділення "*/
    public static Integer getHashByMod(Integer key, Integer onM) {
        return key % onM;
    }

    /*Хеш-функція "метод множення "*/
    public static Integer getHashByMult(Integer key, Double constC, Integer constM) {
        return Double.valueOf(constM*(constC*key - Double.valueOf(constC*key).intValue())).intValue();
    }

    /*Клас ассоціативного масиву*/
    public static class AssociativeArray<K,V> {
        private Map<K, LinkedList<V>> map = new HashMap<>();

        /**
         * Додати елемент до асоціативного масиву
         * @param hash хеш-ключ
         * @param value значення
         */
        public void add(K hash, V value) {
            LinkedList<V> list = this.map.get(hash);
            if (list==null) {
                list = new LinkedList<V>();
                this.map.put(hash,list);
            }
            list.add(value);
        }

        /**
         * Видалити значення з асоціативного масива
         * @param value значення
         */
        public void remove(V value) {
            for (LinkedList<V> list: map.values()) if (list.contains(value)) {
                list.remove(value);
            }
        }

        /**
         * Отримати всі значення для хеша
         * @param hash хеш
         * @return список значень
         */
        public LinkedList<V> getValues(K hash) {
            return this.map.get(hash);
        }

        /**
         * Отримати всі хеш для певного значення
         * @param value значення
         * @return список хешів
         */
        public LinkedList<K> findKeys(V value) {
            LinkedList<K> keys = new LinkedList<>();
            for (Map.Entry<K, LinkedList<V>> kv: map.entrySet()) if (kv.getValue().equals(value)){
                keys.add(kv.getKey());
            }
            return keys;
        }
    }

    public static void output(Object text){
        System.out.println(text);
    }

    public static void main(String[] args) {

        {
            output("*****************************");
            output("Хеш-функція - метод ділення");
            AssociativeArray<Integer,Integer> array = new AssociativeArray<>();
            array.add(getHashByMod(53,2),53); output("Добавлено 53");
            array.add(getHashByMod(47,2),47); output("Добавлено 47");
            LinkedList<Integer> valuesFor = array.getValues(getHashByMod(53,2));
            output("Пошук 53 дав результат: "+valuesFor);
        }

        {
            output("*****************************");
            output("Хеш-функція - метод множення");
            Double constC = 0.53412125; Integer contsM = 67;
            AssociativeArray<Integer,Integer> array = new AssociativeArray<>();
            array.add(getHashByMult(53, constC, contsM),53);output("Добавлено 53");
            array.add(getHashByMult(47,constC, contsM),47);output("Добавлено 47");
            LinkedList<Integer> valuesFor = array.getValues(getHashByMult(53,constC, contsM));
            output("Пошук 53 дав результат: "+valuesFor);
        }

    }
}
