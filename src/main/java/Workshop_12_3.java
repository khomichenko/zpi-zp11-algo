import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class Workshop_12_3 {

    private static Integer getDigitsCount(Integer value) {
        return value.toString().length();
    }

    private static Integer getDigit(Integer value, Integer n) {
        Integer result = null, i=1;
        while(value>0) {
            if (i++==n) {
                result = value % 10;
                break;
            }
            value = value / 10;
        }
        return result;
    }

    private static Integer getMax(Integer[] array) {
        return Arrays.stream(array).max(Comparator.naturalOrder()).get();
    }

    private static Integer[] radixSort(Integer[] array) {
        Integer max = getMax(array);
        Integer maxDigitsCount = getDigitsCount(max);
        Integer[] sortedByDigit = array;
        List<Integer>[] buffer = new ArrayList[10];
        for (int i = 1; i <= maxDigitsCount; i++) {
            for (Integer x : sortedByDigit) {
                Integer d = getDigit(x,i)==null ? 0 : getDigit(x,i);
                if (buffer[d]==null) {
                    buffer[d] = new ArrayList<>();
                }
                buffer[d].add(x);
            }
            sortedByDigit = new Integer[array.length];
            int j = 0;
            for (List<Integer> l: buffer) if (l!=null) for (Integer le: l) {
                sortedByDigit[j++] = le;
            }
            buffer = new ArrayList[10];
        }
        return sortedByDigit;
    }

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

    public static void main(String[] args) {
        Integer[] array = getRandomIntegers(9, 200, 5);
        output("Масив", array);
        Integer[] sorted = radixSort(array);
        output("Відсортований LSD-угрупованням", sorted);
    }
}
