import java.util.List;
import java.util.Scanner;
import java.util.Stack;

public class Workshop {
    private Scanner in = new Scanner(System.in);

    public static Double[] getRandomDouble(Double from, Double to, Integer count) {
        Double[] out = new Double[count];
        for (int i = 0; i < count; i++) {
            out[i] = Math.floor(Math.random()*(to-from+1)+from);
        }
        return out;
    }

    public static Integer[] getRandomIntegers(Integer from, Integer to, Integer count) {
        Integer[] out = new Integer[count];
        for (int i = 0; i < count; i++) {
            out[i] = (int) ((Math.random() * (to - from)) + to);
        }
        return out;
    }

    public static void output(String text, List<List<Integer>> list) {
        System.out.println(text);
        System.out.println(list);
    }

    public static void output(String text, Stack<Integer> stack) {
        if (stack.isEmpty()) {
            if (text!=null) {
                System.out.println( "" + text + "-");
            }
            return;
        }
        Integer x = stack.peek();
        stack.pop();
        System.out.print( "" + (text!=null ? (text+""):""));
        Workshop.output((String)null,stack);
        System.out.print(x + " ");
        if (text!=null) {
            System.out.println();
        }
        stack.push(x);

    }
    public static void output(Object text){
        System.out.println(text);
    }

    public static void output(Integer text){
        System.out.println(text);
    }

    public static void output(String text, Integer text2){
        System.out.println(text+" "+text2);
    }

    public static void output(String text, Integer[][] array){
        System.out.println(text);
        for (Integer[] row: array) {
            for (Integer x: row) {
                System.out.print(x+" ");
            }
            System.out.println();
        }
    }

    public static void output(String text, Integer[] array){
        System.out.print(text+" ");
        for (Integer x: array) {
            System.out.print(x+" ");
        }
        System.out.println();
    }

    public static <T> T[][] deepCopy(T[][] matrix) {
        return java.util.Arrays.stream(matrix).map(el -> el.clone()).toArray($ -> matrix.clone());
    }

    public static Integer inputInt(String text) {
        System.out.print( text+"  " );
        return (new Scanner( System.in )).nextInt();
    }
}
