public class ArrayDeque<T> implements Deque<T> {
    private final static int MIN_CAPACITY = 16;
    private int size;
    private T[] items;
    private int nextFirst;
    private int nextLast;

    public ArrayDeque() {
        items = (T[]) new Object[8];
        size = 0;
        nextFirst = items.length / 2 - 1;
        nextLast = items.length / 2;
    }
    // Invariants:
    // size equals the number of items

    private void resize(int x) {
        T[] a = (T[]) new Object[x];
        int p = (nextFirst + 1) % items.length;
        // cannot use copy directly, must guarantee the right order;
        for (int i = 0; i < size; i += 1) {
            a[i] = items[p];
            p = (p + 1) % items.length;
        }
        items = a;
        nextFirst = items.length - 1;
        nextLast = size;
    }
    @Override
    public void addFirst(T x) {
        if (size == items.length) {
            resize(2 * items.length);
        }
        items[nextFirst] = x;
        nextFirst = (nextFirst - 1 + items.length) % items.length;
        size += 1;
    }
    @Override
    public void addLast(T x) {
        if (size == items.length) {
            resize(2 * items.length);
        }
        items[nextLast] = x;
        nextLast = (nextLast + 1) % items.length;
        size += 1;
    }
    @Override
    public boolean isEmpty() {
        return size == 0;
    }
    @Override
    public int size() {
        return this.size;
    }
    @Override
    public void printDeque() {
        int p = (nextFirst + 1) % items.length;
        for (int i = 0; i < size; i += 1) {
            System.out.print(items[p] + " ");
            p = (p + 1) % items.length;
        }
    }
    @Override
    public T removeFirst() {
        if (isEmpty()) {
            return null;
        }
        nextFirst = (nextFirst + 1) % items.length;
        T firstItem = items[nextFirst];
        items[nextFirst] = null;
        size -= 1;
        if (((double) size / items.length) < 0.25 && items.length > MIN_CAPACITY) {
            resize(Math.max((items.length) / 2, MIN_CAPACITY));
        }
        return firstItem;
    }
    @Override
    public T removeLast() {
        if (isEmpty()) {
            return null;
        }
        nextLast = (nextLast - 1 + items.length) % items.length;
        T lastItem = items[nextLast];
        items[nextLast] = null;
        size -= 1;
        if (((double) size / items.length) < 0.25 && items.length > MIN_CAPACITY) {
            resize(Math.max((items.length) / 2, MIN_CAPACITY));
        }
        return lastItem;
    }
    @Override
    public T get(int index) {
        if (index >= size || index < 0) {
            return null;
        }
        return items[((nextFirst + 1 + index) % items.length)];
    }
}
