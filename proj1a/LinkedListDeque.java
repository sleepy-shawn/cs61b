import sun.font.TrueTypeFont;

public class LinkedListDeque <Horse> {
    private StuffNode sentinel;
    private int size;
    private StuffNode last;


    private class StuffNode {
        public StuffNode prev;
        public Horse item;
        public StuffNode next;

        public StuffNode(StuffNode s1, Horse h, StuffNode s2 ) {
            prev = s1;
            item = h;
            next = s2;
        }
    }
    /** is last a pointer?
     * set sentienl item to be null*/
    public LinkedListDeque (Horse x){
        size = 1;
        sentinel = new StuffNode(null, null, null);
        sentinel.next = new StuffNode(sentinel, x , sentinel);
        sentinel.prev = sentinel.next;
        last = sentinel.next;
    }

    public LinkedListDeque(){
        size = 0;
        last = null;
        sentinel = new StuffNode(null, null, null);
    }
    /** wait to be solved  I wonder i seldom use prev */
    public void addFirst(Horse item){
        size += 1;
        sentinel.next = new StuffNode(sentinel, item, sentinel.next);
    }

    public void addLast(Horse item){
        size += 1;
        sentinel.prev = new StuffNode(last,item, sentinel);
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size(){
        return this.size;
    }

    /** wait for optimization */
    public void printDeque(){
        StuffNode p = sentinel.next;
        while (p != null){
            System.out.print(p.item + "");
            p = p.next;
        }
    }

    public Horse removeFirst(){
        size -= 1;
        Horse before_first = sentinel.next.item;
        sentinel.next = sentinel.next.next;
        return before_first;
    }
    /**  is it True? */
    public Horse removeLast(){
        size -= 1;
        Horse before_last = sentinel.prev.item;
        sentinel.prev = sentinel.prev.prev;
        return before_last;
    }

    /** how to solve these problems more elegantly*/
    public Horse get(int index){
        if (index > (size - 1)){
            return null;
        }
        StuffNode p = sentinel;
        while (index != 0){
            p = p.next;
            index -= 1;
        }
        return p.next.item;

    }
}
