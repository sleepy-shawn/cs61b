import java.util.Map;

public class ArrayDeque<T> {
     private int size;
     private T[] items;
     private int nextFirst;
     private int nextLast;
     // gpt gives me an inspiration to give up start

    /** help thin how to add first
     *  0 1 2 3 4 5 6 7 8 9 10
     * [6,8,9,4,3,
     * possible learn from python list
     */
     public ArrayDeque() {
         items = (T[]) new Object[8];
         size = 0;
         nextFirst = items.length / 2 - 1;
         nextLast = items.length / 2 ;
     }
     // Invariants:
     // size equals the number of items

     // helper function, a little bit tricky

     public void resize(int x){
         T[] a = (T[]) new Object[x];
         System.arraycopy(items, start,a,start + size, size - start + 1);
         System.arraycopy(items, 0, a, 0, size - start - 1);
         items = a;
     }

     public void addFirst(T x){
         if (size == items.length){
             resize(2*size);
         }
         items[nextFirst] = x;
         nextFirst = (nextFirst - 1 + items.length) % items.length;
         size += 1;


     }

     public void addLast(T x){
         if (size == items.length){
             resize(2*size);
         }
         items[nextLast] = x;
         nextLast = (nextLast + 1) % items.length;
         size += 1;

     }

     public boolean isEmpty(){
        return size == 0;

     }

     public int size(){
         return this.size;
     }

     public void printDeque(){
         int p = nextFirst;
         for (int i = 0; i < size; i += 1){
             p = (nextFirst + 1) % items.length;
             System.out.print(items[nextFirst] + "");
         }

     }

     public T removeFirst(){
         if (isEmpty()){
             return null;
         }
         nextFirst = (nextFirst + 1) % items.length;
         T firstItem = items[nextFirst];
         items[nextFirst] = null;
         size -= 1;
         return firstItem;
    }

     public T removeLast(){
         if (isEmpty()){
             return null;
         }
         nextLast = (nextLast - 1 + items.length) % items.length ;
         T lastItem = items[nextLast];
         items[nextLast] = null;
         size -= 1;
         return lastItem;
     }


     // from nextfirst to next last
     public T get(int index){
         if (isEmpty()){
             return null;
         }
         return items[((nextFirst + 1 + index) % items.length)];
     }
}
