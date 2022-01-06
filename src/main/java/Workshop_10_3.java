import java.util.Arrays;
import java.util.function.Function;

public class Workshop_10_3 {

    public static class Device {
        public Integer power = 0;
        public Device(Integer power) { this.power = power; }
        @Override public String toString() { return "" +power; }
    }

    public static <T> Long sortByQuickAlgo(T[] values, Function<T,Integer> v) {
        long begin = System.currentTimeMillis();
        sortByQuickAlgo(values,0,values.length-1,v);
        long end = System.currentTimeMillis();
        return end - begin;
    }
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

    public static void main(String[] args) {
        Integer maxSize = 5; Integer powerFrom = 100; Integer powerTo = 200;
        Device[] devices01 = Arrays.stream(Workshop.getRandomIntegers(powerFrom,powerTo,maxSize)).map((p)->new Device(p)).toArray(Device[]::new);
        Workshop.output("Пристрої списку 1",devices01);
        Long time1Ms = sortByQuickAlgo(devices01,(x)->{return x.power;});
        Workshop.output("Пристрої списку 1 (відсортовані за "+time1Ms+" ms)",devices01);
        Device[] devices02 = Arrays.stream(Workshop.getRandomIntegers(powerFrom,powerTo,maxSize)).map((p)->new Device(p)).toArray(Device[]::new);
        Workshop.output("Пристрої списку 2",devices02);
        Long time2Ms = sortByQuickAlgo(devices02,(x)->{return x.power;});
        Workshop.output("Пристрої списку 2 (відсортовані за "+time2Ms+" ms)",devices02);

        Device[][] devices03 = {
                Arrays.stream(Workshop.getRandomIntegers(powerFrom,powerTo,maxSize)).map((p)->new Device(p)).toArray(Device[]::new),
                Arrays.stream(Workshop.getRandomIntegers(powerFrom,powerTo,maxSize)).map((p)->new Device(p)).toArray(Device[]::new),
                Arrays.stream(Workshop.getRandomIntegers(powerFrom,powerTo,maxSize)).map((p)->new Device(p)).toArray(Device[]::new),
                Arrays.stream(Workshop.getRandomIntegers(powerFrom,powerTo,maxSize)).map((p)->new Device(p)).toArray(Device[]::new),
                Arrays.stream(Workshop.getRandomIntegers(powerFrom,powerTo,maxSize)).map((p)->new Device(p)).toArray(Device[]::new)
        };
        Workshop.output2D("Пристрої списків 3",devices03);
        Long time3Ms = 0L;
        for (int i = 0; i < devices03.length; i++) {
            time3Ms = time3Ms + sortByQuickAlgo(devices03[i],(x)->{return x.power;});
        }
        Workshop.output2D("Пристрої списків 3 (відсортовані за "+time3Ms+" ms)",devices03);
        Device[] devices0102 = Workshop.concat(devices01, devices02);
        Workshop.output("Пристрої списку 1 (відсортований) та списку 2 (відсортований). Не відсортований",devices0102);
        Long time12Ms = sortByQuickAlgo(devices0102,(x)->{return x.power;});
        Device[] devices010203 = devices0102;
        for (int i = 0; i < devices03.length; i++) {
            devices010203 = Workshop.concat(devices010203, devices03[i]);
        }
        Workshop.output("Пристрої списків 1+2 (відсортовані за "+time12Ms+" ms) та списків 3. Не відсортований",devices010203);
        Long time123Ms = sortByQuickAlgo(devices010203,(x)->{return x.power;});
        Workshop.output("Пристрої списків 1+2 та списків 3. Відсортовані за "+time123Ms+" ms",devices010203);
    }
}
