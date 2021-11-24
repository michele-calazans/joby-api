package br.com.jobem.api.dataStructure;

public class FilaObjCircular<T> {
    private int size, start, end;
    private T[] queue;

    public FilaObjCircular(int capacidade) {
        size = 0;
        start = 0;
        end = 0;
        queue = (T[]) new Object[capacidade];
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public boolean isFull() {
        return size == queue.length;
    }

    public void insert(T info) {
        if (isFull()) {
            System.out.println("Fila cheia!");
        } else {
            queue[end] = info;
            end = (end + 1) % queue.length;
            size++;
        }
    }

    public T peek() {
        return queue[start];
    }

    //@Scheduled(fixedRate = 1000)
    public T pool() {
    T first = peek();

        if (!isEmpty()) {
            queue[start] = null;
            start = (start + 1) % queue.length;
            size--;
        }

        return first;
    }

    public void show() {
        if (isEmpty()) {
            System.out.println("Fila vazia!");
        } else {
            System.out.println("\nElementos da fila");
            int ind = start;
            for (int i = 0; i < queue.length; i++) {
                System.out.println(ind);
                ind = (ind + 1) % queue.length;
            }
        }
    }

    public int getSize() {
        return size;
    }
}
