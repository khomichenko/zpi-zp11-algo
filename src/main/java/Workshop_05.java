import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class Workshop_05 {

    public static Integer getHashByMod(Integer key, Integer onM) {
        return key % onM;
    }

    public static Integer getHashByMult(Integer key, Double constC, Integer constM) {
        return Double.valueOf(constM*(constC*key - Double.valueOf(constC*key).intValue())).intValue();
    }

    public static class AssociativeArray<K,V> {
        private Map<K, LinkedList<V>> map = new HashMap<>();

        public void add(K hash, V value) {
            LinkedList<V> list = this.map.get(hash);
            if (list==null) {
                list = new LinkedList<V>();
                this.map.put(hash,list);
            }
            list.add(value);
        }
        public void remove(V value) {
            for (LinkedList<V> list: map.values()) if (list.contains(value)) {
                list.remove(value);
            }
        }
        public LinkedList<V> getValues(K hash) {
            return this.map.get(hash);
        }
        public LinkedList<K> findKeys(V value) {
            LinkedList<K> keys = new LinkedList<>();
            for (Map.Entry<K, LinkedList<V>> kv: map.entrySet()) if (kv.getValue().equals(value)){
                keys.add(kv.getKey());
            }
            return keys;
        }
    }

    public static void main(String[] args) {

        {
            Workshop.output("*****************************");
            Workshop.output("Хеш-функція - метод ділення");
            AssociativeArray<Integer,Integer> array = new AssociativeArray<>();
            array.add(getHashByMod(53,2),53); Workshop.output("Добавлено 53");
            array.add(getHashByMod(47,2),47); Workshop.output("Добавлено 47");
            LinkedList<Integer> valuesFor = array.getValues(getHashByMod(53,2));
            Workshop.output("Пошук 53 дав результат: "+valuesFor);
        }

        {
            Workshop.output("*****************************");
            Workshop.output("Хеш-функція - метод множення");
            Double constC = 0.53412125; Integer contsM = 67;
            AssociativeArray<Integer,Integer> array = new AssociativeArray<>();
            array.add(getHashByMult(53, constC, contsM),53);Workshop.output("Добавлено 53");
            array.add(getHashByMult(47,constC, contsM),47);Workshop.output("Добавлено 47");
            LinkedList<Integer> valuesFor = array.getValues(getHashByMult(53,constC, contsM));
            Workshop.output("Пошук 53 дав результат: "+valuesFor);
        }

    }
}
