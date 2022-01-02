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

    public static void findComposStrongConnByKosaraju(Integer[][] matrixP) {
        Integer[][] matrix = Workshop.deepCopy(matrixP);

        List<List<Integer>> composStrongConn = new ArrayList<>();

        Workshop.output("Граф : ", matrix);

        Stack<Integer> stack = new Stack<>();

        for (int i = 0; i < matrix.length; i++) {
            dfs(matrix,i+1,(m,u)->{
                if (stack.contains(u)==false) stack.push(u);
            });
        }
        Workshop.output("Стек після DFS: ", stack);

        transponov(matrix);
        Workshop.output("Транспонований граф : ", matrix);

        while (!stack.isEmpty()) {
            Integer pop = stack.pop();
            Workshop.output("****************");
            Workshop.output("Взяли вершину "+pop+" з стеку, стек: ", stack);
            List<Integer> currCompoStrongConn = new ArrayList();
            dfs(matrix,pop,(m,u)->{
                currCompoStrongConn.add(u);
                stack.remove(u);
                removeVertex(matrix,u);
            });
            Workshop.output("Стек після видалення: ", stack);
            Workshop.output("Матриця після видалення вершин: ", matrix);
            Workshop.output("Компонента сильної звязності: "+currCompoStrongConn+", стек: ", stack);
            composStrongConn.add(currCompoStrongConn);
        }
        Workshop.output("****************");
        Workshop.output("Всі компоненти сильної звязності: ",composStrongConn);
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
