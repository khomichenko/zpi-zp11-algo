import java.util.Stack;

public class Workshop_02_03 {

    /**
     * Пошук найкорощого шляха між заданими вершинами, алгоритм Террі
     * @param matrixP матриця сумісності
     * @param from з вершини
     * @param to до вершини
     * @param showLogs показувати лог роботи ?
     */
    public static void shortcut(Integer[][] matrixP, Integer from, Integer to, Boolean showLogs) {
        Integer[][] matrix = deepCopy(matrixP); // стровюємо копію матриці

        if (showLogs) output("Матриця ",matrix);
        Stack<Integer> stack = new Stack<>(); // стровюємо стек

        Integer vS = from; // початкова вершина
        Integer vF = to; // кінечна вершина

        Integer cRow = vS, cCol = 1; // поточне положення
        while (true){
            if (matrix[cRow-1][cCol-1]==1) {
                matrix[cRow-1][cCol-1] = 0;
                if (matrix[cCol-1][cRow-1]==1) {
                    matrix[cCol-1][cRow-1] = 0;
                }
                stack.push(cRow);
                cRow = cCol;
                cCol = 1;
                if (showLogs) {
                    output("******************");
                    output("Матриця ",matrix);
                    output("Стек ", stack);
                }
            } else {
                cCol = cCol + 1;
            }
            if (cCol>matrix[cRow-1].length) {
                if (stack.empty()) {
                    break;
                } else {
                    cRow = stack.pop();
                    cCol = 1;
                    if (showLogs) {
                        output("******************");
                        output("Стек ", stack);
                    }
                }
            }
            if (cRow==vF) {
                break;
            }
        }
        if (stack.isEmpty()==false) {
            stack.push(vF);
        }
        if (showLogs) {
            output("******************");
        }
        output("Результат ("+from+"->"+to+"): ", stack);
    }

    /**
     * Вивести на екран текст
     * @param text текст
     */
    public static void output(Object text){
        System.out.println(text);
    }

    /**
     * Вивести на екран двовимірний масив
     * @param text текст-підказка
     * @param array двовимірний масив
     */
    public static void output(String text, Integer[][] array){
        System.out.println(text);
        for (Integer[] row: array) {
            for (Integer x: row) {
                System.out.print(x+" ");
            }
            System.out.println();
        }
    }

    /**
     * Вивести на екран стек
     * @param text текст-підказка
     * @param stack стек
     */
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
        output((String)null,stack);
        System.out.print(x + " ");
        if (text!=null) {
            System.out.println();
        }
        stack.push(x);
    }

    /**
     * Створими копію матриці
     * @param matrix матриця
     * @param <T> тип значень матриці
     * @return матриця
     */
    public static <T> T[][] deepCopy(T[][] matrix) {
        return java.util.Arrays.stream(matrix).map(el -> el.clone()).toArray($ -> matrix.clone());
    }

    public static void main(String[] args) {
        // Ініціалізуємо матрицю сумісності
        Integer[][] matrix = {
                {0,1,0,1,1,0,0},
                {1,0,0,1,0,1,0},
                {1,1,0,1,0,1,0},
                {1,0,0,0,0,1,0},
                {0,0,1,0,1,0,0},
                {0,0,0,1,0,0,1},
                {0,0,0,1,0,0,0}
        };
        // Шукаємо найкоротші шляхи
        //shortcut(matrix, 1, 7, true);
        //shortcut(matrix, 3, 7, true);
        //shortcut(matrix, 5, 7, true);
        shortcut(matrix, 7, 7, true);
    }
}
