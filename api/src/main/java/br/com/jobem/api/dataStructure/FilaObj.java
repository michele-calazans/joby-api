package br.com.jobem.api.dataStructure;

public class FilaObj<T> {
    private int size;
    private T[] queue;

    public FilaObj(int capacidade) {
        size = 0;
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
            queue[size++] = info;
        }
    }

    public T peek() {
        return queue[0];
    }

    //@Scheduled(fixedRate = 1000)
    public T pool() {
        T first = peek();

        if (!isEmpty()) {
            for (int i = 0; i < (size - 1); i++) {
                queue[i] = queue[i + 1];
            }
            queue[size - 1] = null;
            size--;
        }
        return first;
    }

    public void show() {
        if (isEmpty()) {
            System.out.println("Fila vazia!");
        } else {
            System.out.println("\nElementos da fila");
            for (int i = 0; i < size; i++) {
                System.out.println(queue[i]);
            }
        }
    }

    public int getSize() {
        return size;
    }
}
