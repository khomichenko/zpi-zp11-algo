import java.util.Arrays;

public class Workshop_04_03 {

    public static class Stack<T> {
        private T[] array;
        private Integer pointer = -1 ;

        public Stack(Integer size) {
            this.array = (T[])new Object[size];
        }
        public void push(T x) {
            if (pointer+1>=this.array.length) {
                throw new RuntimeException("stack overflow");
            }
            pointer = pointer + 1;
            array[pointer] = x;
        }
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
    public static class Queue<T> {
        private T[] array;
        private Integer pointer = -1 ;

        public Queue(Integer size) {
            this.array = (T[])new Object[size];
        }
        public void enqueue(T x) {
            if (pointer+1>=this.array.length) {
                throw new RuntimeException("queue overflow");
            }
            pointer = pointer + 1;
            array[pointer] = x;
        }
        public T dequeue() {
            T el = array[0];
            int i = 1;
            for (; i < array.length && i<pointer+1 ; i++) {
                array[i-1]=array[i];
            }
            array[i-1]=null;
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
