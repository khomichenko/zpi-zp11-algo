import java.util.Arrays;
import java.util.Stack;

/**
 * Найкоротший шлях між заданими вершинами, алгоритм Террі
 */
public class Workshop_02_03 {

    public static void shortcut(Integer[][] matrixP, Integer from, Integer to, Boolean showLogs) {
        Integer[][] matrix = Workshop.deepCopy(matrixP);

        if (showLogs) Workshop.output("Матриця ",matrix);
        Stack<Integer> stack = new Stack<>();

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
                    Workshop.output("******************");
                    Workshop.output("Матриця ",matrix);
                    Workshop.output("Стек", stack);
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
                        Workshop.output("******************");
                        Workshop.output("Стек", stack);
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
            Workshop.output("******************");
        }
        Workshop.output("Результат ("+from+"->"+to+"): ", stack);
    }

    public static void main(String[] args) {

        Integer[][] matrix = {
                {0,1,0,1,1,0,0},
                {1,0,0,1,0,1,0},
                {1,1,0,1,0,1,0},
                {1,0,0,0,0,1,0},
                {0,0,1,0,1,0,0},
                {0,0,0,1,0,0,1},
                {0,0,0,1,0,0,0}
        };
        shortcut(matrix, 1, 7, false);
        shortcut(matrix, 3, 7, false);
        shortcut(matrix, 5, 7, false);
        shortcut(matrix, 7, 7, false);
    }
}
