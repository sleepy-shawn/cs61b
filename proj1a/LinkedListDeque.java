public class LinkedListDeque<Horse> {
    private final StuffNode sentinel;
    private int size;

    private class StuffNode {
        private StuffNode prev;
        private Horse item;
        private StuffNode next;

        private StuffNode(StuffNode s1, Horse h, StuffNode s2) {
            prev = s1;
            item = h;
            next = s2;
        }
    }

    /**
     * last is a pointer
     * set sentinel item to be null
     */
    public LinkedListDeque(Horse x) {
        size = 1;
        sentinel = new StuffNode(null, null, null);
        sentinel.next = new StuffNode(sentinel, x, sentinel);
        sentinel.prev = sentinel.next;
    }

    public LinkedListDeque() {
        size = 0;
        sentinel = new StuffNode(null, null, null);
        sentinel.next = sentinel;
        sentinel.prev = sentinel;
    }

    public void addFirst(Horse item) {
        StuffNode newNode = new StuffNode(sentinel, item, sentinel.next);
        if (isEmpty()) {
            sentinel.prev = newNode;
        }
        sentinel.next.prev = newNode;
        sentinel.next = newNode;
        size += 1;
    }

    public void addLast(Horse item) {
        StuffNode newNode = new StuffNode(sentinel.prev, item, sentinel);
        if (isEmpty()){
            sentinel.next = newNode;
        }
        sentinel.prev.next = newNode;
        sentinel.prev = newNode;
        size += 1;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return this.size;
    }

    public void printDeque() {
        StuffNode p = sentinel.next;
        while (p != sentinel) {
            System.out.print(p.item + " ");
            p = p.next;
        }
    }

    public Horse removeFirst() {
        if (isEmpty()) {
            return null;
        }
        Horse beforeFirst = sentinel.next.item;
        sentinel.next.next.prev = sentinel;
        sentinel.next = sentinel.next.next;
        size -= 1;
        return beforeFirst;
    }

    public Horse removeLast() {
        if (isEmpty()) {
            return null;
        }
        Horse beforeLast = sentinel.prev.item;
        sentinel.prev.prev.next = sentinel;
        sentinel.prev = sentinel.prev.prev;
        size -= 1;
        return beforeLast;
    }

    public Horse get(int index) {
        if (index < 0 || index >= size) {
            return null;
        }
        StuffNode p = sentinel.next;
        while (index > 0) {
            p = p.next;
            index -= 1;
        }
        return p.item;
    }

    // helper function by middleman
    public Horse getRecursive(StuffNode middleman, int index) {
        if (index == 0) {
            return middleman.item;
        }
        return getRecursive(middleman.next, index - 1);
    }


    public Horse getRecursive(int index) {
        if (index < 0 || index >= size) {
            return null;
        }
        return getRecursive(sentinel.next, index);
    }
}

