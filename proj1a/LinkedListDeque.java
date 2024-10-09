import sun.font.TrueTypeFont;

public class LinkedListDeque <Horse> {
    private StuffNode sentinel;
    private int size;
    private StuffNode last;


    private static class StuffNode {
        public StuffNode prev;
        public Horse item;
        public StuffNode next;

        public StuffNode(StuffNode s1, Horse h, StuffNode s2 ) {
            prev = s1;
            item = h;
            next = s2;
        }
    }

    public LinkedListDeque (Horse x){
        size = 1;
        sentinel = new StuffNode(null, 666, null);
        sentinel.next = new StuffNode(sentinel, x , sentinel);
        last = new StuffNode(sentinel, x, sentinel);
    }

    public LinkedListDeque(){
        size = 0;
        last = null;
        sentinel = new StuffNode(null, 666, null);
    }
/** wait to be solved  I wonder i seldom use prev */
    public void add_First(Horse item){
        size += 1;
        sentinel.next = new StuffNode(sentinel, item, sentinel.next);
        last
    }

    public void add_Last(Horse item){
        size += 1;
        last = new StuffNode(last, item, sentinel);
    }

    public boolean is_empty() {
        return size == 0;
    }

    public int size(){
        return this.size;
    }

    /** wait for optimization */
    public void print_queue(){
        StuffNode p = sentinel.next;
        while (p.next != null){
            System.out.print(p.item + "");
            p = p.next;
        }
    }

    public Horse remove_first(){
        size -= 1;
        sentinel.next = sentinel.next.next;
    }
/**  is it True? */
    public Horse remove_last(){
        size -= 1;
        sentinel.prev = sentinel.prev.prev;

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
