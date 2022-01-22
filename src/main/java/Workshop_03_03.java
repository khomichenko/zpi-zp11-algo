import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.function.BiConsumer;

public class Workshop_03_03 {

    /**
     * Пошук в глубину, Depth-first search, DFS
     * @param vertex номер вершини
     * @param func  функція обробки поточної вершини
     */
    public static void dfs(Integer[][] matrix, Integer vertex, BiConsumer<Integer[][],Integer> func) {
        dfs(matrix, vertex-1,new Boolean[matrix.length],func);
    }

    private static void dfs(Integer[][] matrix, Integer vertexIndex, Boolean[] visited, BiConsumer<Integer[][],Integer> func) {
        visited[vertexIndex] = true;
        func.accept(matrix,vertexIndex+1);
        for (int to = 0; to < matrix[vertexIndex].length; to++) if (matrix[vertexIndex][to]>0) {
            if (visited[to]==null || visited[to]==false) {
                dfs(matrix,to,visited,func);
            }
        }
    }

    private static void transponov(Integer[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = i; j < matrix[i].length; j++) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = temp;
            }
        }
    }

    private static void removeVertex(Integer[][] matrix, Integer vertex) {
        for (int t = 0; t < matrix[vertex-1].length ; t++) {
            matrix[vertex-1][t]=0;
            matrix[t][vertex-1]=0;
        }
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

    public static void output(Object text){
        System.out.println(text);
    }

    public static void output(String text, List<List<Integer>> list) {
        System.out.println(text);
        System.out.println(list);
    }

    public static <T> T[][] deepCopy(T[][] matrix) {
        return java.util.Arrays.stream(matrix).map(el -> el.clone()).toArray($ -> matrix.clone());
    }

    public static void findComposStrongConnByKosaraju(Integer[][] matrixP) {
        Integer[][] matrix = deepCopy(matrixP);

        List<List<Integer>> composStrongConn = new ArrayList<>();

        output("Граф : ", matrix);

        Stack<Integer> stack = new Stack<>();

        for (int i = 0; i < matrix.length; i++) {
            dfs(matrix,i+1,(m,u)->{
                if (stack.contains(u)==false) stack.push(u);
            });
        }
        output("Стек після DFS: ", stack);

        transponov(matrix);
        output("Транспонований граф : ", matrix);

        while (!stack.isEmpty()) {
            Integer pop = stack.pop();
            output("****************");
            output("Взяли вершину "+pop+" з стеку, стек: ", stack);
            List<Integer> currCompoStrongConn = new ArrayList();
            dfs(matrix,pop,(m,u)->{
                currCompoStrongConn.add(u);
                stack.remove(u);
                removeVertex(matrix,u);
            });
            output("Стек після видалення: ", stack);
            output("Матриця після видалення вершин: ", matrix);
            output("Компонента сильної звязності: "+currCompoStrongConn+", стек: ", stack);
            composStrongConn.add(currCompoStrongConn);
        }
        output("****************");
        output("Всі компоненти сильної звязності: ",composStrongConn);
    }

    public static void main(String[] args) {
        Integer[][] matrix = {
                {0,0,1,0,1},
                {0,0,0,0,0},
                {0,0,0,1,0},
                {1,0,0,0,0},
                {0,0,1,1,0},
        };
        findComposStrongConnByKosaraju(matrix);
    }

}
