import java.util.Arrays;
import java.util.function.Function;

public class Workshop_09_3 {

    public static class Device {
        public Integer power = 0;
        public Device(Integer power) { this.power = power; }
        @Override public String toString() { return "" +power; }
    }

    public static <T> void sortByBubble(T[] values, Function<T,Integer> v) {
        Boolean notSorted = true;
        while (notSorted) {
            notSorted = false;
            for (int i = 0; i < values.length - 1; i++) {
                if ( v.apply(values[i]) > v.apply(values[i+1])) {
                    T temp = values[i];
                    values[i] = values[i+1];
                    values[i+1] = temp;
                    notSorted = true;
                }
            }
        }
    }

    public static void main(String[] args) {
        Integer maxSize = 5; Integer powerFrom = 100; Integer powerTo = 200;
        Device[] devices01 = Arrays.stream(Workshop.getRandomIntegers(powerFrom,powerTo,maxSize)).map((p)->new Device(p)).toArray(Device[]::new);
        Workshop.output("Пристрої списку 1",devices01);
        sortByBubble(devices01,(x)->{return x.power;});
        Workshop.output("Пристрої списку 1 (відсортовані)",devices01);
        Device[] devices02 = Arrays.stream(Workshop.getRandomIntegers(powerFrom,powerTo,maxSize)).map((p)->new Device(p)).toArray(Device[]::new);
        Workshop.output("Пристрої списку 2",devices02);
        sortByBubble(devices02,(x)->{return x.power;});
        Workshop.output("Пристрої списку 2 (відсортовані)",devices02);

        Device[][] devices03 = {
                Arrays.stream(Workshop.getRandomIntegers(powerFrom,powerTo,maxSize)).map((p)->new Device(p)).toArray(Device[]::new),
                Arrays.stream(Workshop.getRandomIntegers(powerFrom,powerTo,maxSize)).map((p)->new Device(p)).toArray(Device[]::new),
                Arrays.stream(Workshop.getRandomIntegers(powerFrom,powerTo,maxSize)).map((p)->new Device(p)).toArray(Device[]::new),
                Arrays.stream(Workshop.getRandomIntegers(powerFrom,powerTo,maxSize)).map((p)->new Device(p)).toArray(Device[]::new),
                Arrays.stream(Workshop.getRandomIntegers(powerFrom,powerTo,maxSize)).map((p)->new Device(p)).toArray(Device[]::new)
        };
        Workshop.output2D("Пристрої списків 3",devices03);
        for (int i = 0; i < devices03.length; i++) {
            sortByBubble(devices03[i],(x)->{return x.power;});
        }
        Workshop.output2D("Пристрої списків 3 (відсортовані)",devices03);
        Device[] devices0102 = Workshop.concat(devices01, devices02);
        Workshop.output("Пристрої списку 1 (відсортований) та списку 2 (відсортований). Не відсортований",devices0102);
        sortByBubble(devices0102,(x)->{return x.power;});
        Device[] devices010203 = devices0102;
        for (int i = 0; i < devices03.length; i++) {
            devices010203 = Workshop.concat(devices010203, devices03[i]);
        }
        Workshop.output("Пристрої списків 1+2 та списків 3. Не відсортований",devices010203);
        sortByBubble(devices010203,(x)->{return x.power;});
        Workshop.output("Пристрої списків 1+2 та списків 3. Відсортовані",devices010203);
    }
}
