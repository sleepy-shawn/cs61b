import java.util.Map;

public class ArrayDeque<T> {
     private int size;
     private T[] items;
     private int start;
     private int nextFirst;
     private int nextLast;

    /** help thin how to add first
     *  0 1 2 3 4 5 6 7 8 9 10
     * [6,8,9,4,3,
     * possible learn from python list
     */
     public ArrayDeque() {
         items = (T[]) new Object[8];
         size = 0;
         start = 4; // probably to be updated after resizing
         nextFirst = start;
         nextLast = start;
     }
     // Invariants: the item to be added is always at the position of size.
     // size equals the number of items

     // helper function, a little bit tricky
     // maybe option3 is better for clear double end
    /**
     public void resize(int x){
         T[] a = (T[]) new Object[x];
         System.arraycopy(items, start,a,start + size, size - start + 1);
         System.arraycopy(items, 0, a, 0, size - start - 1);
         items = a;
     }
     */

     public void addFirst(T x){
         /**
         if (size == items.length){
             resize(2*size);
         }
          */
         if (nextFirst < 0){
             nextFirst = items.length - 1;
         }
         items[nextFirst] = x;
         nextFirst -= 1;
         if (isEmpty()){
             nextLast += 1;
         }
         size += 1;


     }

     public void addLast(T x){
         /**
         if (size == items.length){
             resize(2*size);
         }
         */
         if (nextLast > items.length - 1){
             nextLast = 0;
         }
         items[nextLast] = x;
         nextLast += 1;
         if (isEmpty()){
             nextFirst += 1;
         }
         size += 1;

     }

     public boolean isEmpty(){
        return size == 0;

     }

     public int size(){
         return this.size;
     }

     public void printDeque(){
         for (int i = start; i <= items.length; i += 1){
            System.out.print(items[i] + "");
         }
         for (int i = 0; i < start; i += 1){
             System.out.print(items[i] + "");
         }

     }

     public T removeFirst(){
         if (isEmpty()){
             return null;
         }
         T firstItem = items[nextFirst + 1];
         items[nextFirst + 1] = null;
         size -= 1;
         nextFirst += 1;
         return firstItem;
    }

     public T removeLast(){
         if (isEmpty()){
             return null;
         }
         T lastItem = items[nextLast - 1];
         items[nextLast - 1] = null;
         size -= 1;
         nextLast -= 1;
         return lastItem;
     }

     public T get(int index){
         if (isEmpty()){
             return null;
         }
         int p = start;
         while (index > 0){
             p = p + 1;
             if (p > items.length){
                 p = 0;
             }
             index -= 1;
         }
         return items[p];
     }
}
