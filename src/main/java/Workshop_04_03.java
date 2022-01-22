import java.util.Arrays;

public class Workshop_04_03 {

    /**
     * Стек
     * @param <T> тип елементів стека
     */
    public static class Stack<T> {
        private T[] array; // одномірний масив
        private Integer pointer = -1 ; // вказівник на елемент масива, -1 - не вказує ні на що

        /**
         * Створити стек
         * @param size Розмір стека
         */
        public Stack(Integer size) {
            this.array = (T[])new Object[size];
        }
        /**
         * Додати елемент в стек
         * @param x елемент
         */
        public void push(T x) {
            // провірка стека на переповненість
            if (pointer+1>=this.array.length) {
                throw new RuntimeException("stack overflow");
            }
            // здвинути вказівник на 1
            pointer = pointer + 1;
            // зберегти елемент в масив
            array[pointer] = x;
        }

        /**
         * Взяти елемент з стека
         * @return елемент
         */
        public T pop() {
            T el = array[pointer];
            array[pointer] = null;
            pointer = pointer - 1;
            return el;
        }
        @Override public String toString() {
            return "" + Arrays.toString(array) +"";
        }
    }

    /**
     * Черга
     * @param <T> тип елементів черги
     */
    public static class Queue<T> {

        private T[] array; // одномірний масив
        private Integer pointer = -1 ; // вказівник на елемент масива, -1 - не вказує ні на що

        /**
         * Створити чергу
         * @param size Розмір черги
         */
        public Queue(Integer size) {
            this.array = (T[])new Object[size];
        }

        /**
         * Додати елемент в чергу
         * @param x елемент
         */
        public void enqueue(T x) {
            // провірка черги на переповненість
            if (pointer+1>=this.array.length) {
                throw new RuntimeException("queue overflow");
            }
            // здвинути вказівник на 1
            pointer = pointer + 1;
            // зберегти елемент в масив
            array[pointer] = x;
        }

        /**
         * Витягнути елемент с черги
         * @return елемент
         */
        public T dequeue() {
            T el = array[0]; // беремо перший елемент з масива
            int i = 1;
            // здвигаємо всі решта елементів масива вліво
            for (; i < array.length && i<pointer+1 ; i++) {
                array[i-1]=array[i];
            }
            // а в останю комірку масива записуємо null (пусто)
            array[i-1]=null;
            // вертаємо раніше взяти перий елемент масива
            return el;
        }
        @Override public String toString() {
            return "" + Arrays.toString(array) +"";
        }
    }

    public static void main(String[] args) {
        Stack<Integer> stack = new Stack<>(3);
        stack.push(5);
        stack.push(7);
        Workshop.output("Стек: "+stack.toString());
        Workshop.output("Взято "+stack.pop()+", стек: "+stack.toString());
        Workshop.output("Взято "+stack.pop()+", стек: "+stack.toString());

        Queue<Integer> queue = new Queue<>(3);
        queue.enqueue(5);
        queue.enqueue(7);
        queue.enqueue(-4);
        Workshop.output("Черга: "+queue.toString());
        Workshop.output("Взято "+queue.dequeue()+", черга: "+queue.toString());
        Workshop.output("Взято "+queue.dequeue()+", черга: "+queue.toString());
    }
}
