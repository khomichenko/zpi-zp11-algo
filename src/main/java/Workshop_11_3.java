import java.util.Arrays;
import java.util.function.Function;

public class Workshop_11_3 {

    public static class Device {
        public Integer power = 0;
        public Device(Integer power) { this.power = power; }
        @Override public String toString() { return "" +power; }
    }

    private static int[] leonardoMemo = { 1, 1 };

    private static <T> void swapValues(T[] values, Integer indexA, Integer indexB) {
        T swap = values[indexA];
        values[indexA] = values[indexB];
        values[indexB] = swap;
    }

    private static <T> void heapMake(T[] values, Integer parent, Integer count, Function<T,Integer> v) {
        Integer max     = parent;
        Integer left    = 2 * parent + 1; // лівий нащадок = 2*i + 1
        Integer right   = 2 * parent + 2; // правий нащадок = 2*i + 2
        if (left < count && v.apply(values[left]) > v.apply(values[max])) {
            max = left;
        }
        if (right < count && v.apply(values[right]) > v.apply(values[max])) {
            max = right;
        }
        if (max != parent) {
            swapValues(values,parent,max);
            heapMake(values, max, count, v);
        }
    }

    public static <T> void heapSort(T[] values, Function<T,Integer> v) {
        Integer count = values.length;

        for (int i = count / 2 - 1; i >= 0; i--)
            heapMake(values, i, count, v );

        for (int i=count-1; i>=0; i--) {
            swapValues(values,0,i);
            heapMake(values, 0, i, v);
        }
    }

    public static <T> void smoothSort(T[] values, Function<T,Integer> v) {
        Integer head = 0, frac = 0, exp = 0;
        while (head < values.length) {
            if ((frac & 3) == 3) {
                frac = frac >>> 2;
                exp = exp + 2;
            } else if (exp > 1) {
                frac = frac << (exp - 1);
                exp = 1;
            } else {
                frac = frac << exp;
                exp = exp ^ 1;
            }
            frac = frac + 1;
            if (exp > 0 && values.length - head - 1 < leonardo(exp - 1) + 1) {
                arrange(values, head, frac, exp, v);
            }
        }
        arrange(values, head - 1, frac, exp, v);
        while (head > 0) {
            head--;
            if (exp > 1) {
                frac <<= 2;
                frac--;
                exp -= 2;
                arrange(values, head - leonardo(exp) - 1, frac >> 1, exp + 1, v);
                arrange(values, head - 1, frac, exp, v);
            } else {
                int trail = Long.numberOfTrailingZeros(frac - 1);
                frac = frac >> trail;
                exp  = exp  +  trail;
            }
        }
    }

    public static int leonardo(int n) {
        if (n < leonardoMemo.length) {
            if (leonardoMemo[n] != 0) {
                return leonardoMemo[n];
            }
        } else {
            int newLength = leonardoMemo.length * 2;
            leonardoMemo = Arrays.copyOf(leonardoMemo, (newLength > n) ? newLength : (n + 1));
        }
        return leonardoMemo[n] = leonardo(n - 1) + leonardo(n - 2) + 1;
    }

    private static <T> void arrange(T[] values, int head, long frac, int exp, Function<T,Integer> v) {
        T t = values[head];
        while (frac > 1) {
            int next = head - leonardo(exp);
            if (next<0 || v.apply(t).compareTo(v.apply(values[next])) >= 0) {
                break;
            }
            if (exp > 1) {
                int r = head - 1;
                int l = head - 1 - leonardo(exp - 2);
                if (v.apply(values[l]).compareTo(v.apply(values[next])) >= 0 || v.apply(values[r]).compareTo(v.apply(values[next])) >= 0) {
                    break;
                }
            }
            values[head] = values[next];
            head = next;
            int trail = Long.numberOfTrailingZeros(frac - 1);
            frac = frac >>> trail;
            exp  = exp + trail;
        }
        values[head] = t;
        sift(values, head, exp, v);
    }

    private static <T> void sift(T[] values, Integer head, Integer exp, Function<T,Integer> v) {
        T t = values[head];
        while (exp > 1) {
            int r = head - 1;
            int l = head - 1 - leonardo(exp - 2);
            if (l<0 || r<0 || (v.apply(t).compareTo(v.apply(values[l])) >= 0 && v.apply(t).compareTo(v.apply(values[r])) >= 0)) {
                break;
            }
            if (v.apply(values[l]).compareTo(v.apply(values[r])) >= 0) {
                values[head] = values[l];
                head = l;
                exp -= 1;
            } else {
                values[head] = values[r];
                head = r;
                exp -= 2;
            }
        }
        values[head] = t;
    }

    public static void main(String[] args) {
        Integer maxSize = 2; Integer powerFrom = 5; Integer powerTo = 10;
        {
            Workshop.output("************** ПІРАМІДАЛЬНЕ СОРТУВАННЯ **************");
            Device[][] devices = {
                    Arrays.stream(Workshop.getRandomIntegers(powerFrom,powerTo,maxSize)).map((p)->new Device(p)).toArray(Device[]::new),
                    Arrays.stream(Workshop.getRandomIntegers(powerFrom,powerTo,maxSize)).map((p)->new Device(p)).toArray(Device[]::new)
            };
            Workshop.output2D("Пристрої списків",devices);
            Device[] devicesAll = {};
            for (int i = 0; i < devices.length; i++) {
                devicesAll = Workshop.concat(devicesAll, devices[i]);
            }
            Workshop.output("Всі пристрої (не відсортовані)",devicesAll);
            heapSort( devicesAll, (x) -> {
                return x.power;
            });
            Workshop.output("Всі пристрої (відсортовані за пірамідальним алгоритмом)", devicesAll);
        }
        /*{
            Workshop.output("************** ПЛАВНЕ СОРТУВАННЯ **************");
            Device[][] devices = {
                    Arrays.stream(Workshop.getRandomIntegers(powerFrom,powerTo,maxSize)).map((p)->new Device(p)).toArray(Device[]::new),
            };
            Workshop.output2D("Пристрої списків",devices);
            Device[] devicesAll = {};
            for (int i = 0; i < devices.length; i++) {
                devicesAll = Workshop.concat(devicesAll, devices[i]);
            }
            Workshop.output("Всі пристрої (не відсортовані)",devicesAll);
            smoothSort( devicesAll, (x) -> {
                return x.power;
            });
            Workshop.output("Всі пристрої (відсортовані за плавним алгоритмом)", devicesAll);
        }*/
    }
}
