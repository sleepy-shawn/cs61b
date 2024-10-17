package synthesizer;
import org.junit.Test;
import static org.junit.Assert.*;

/** Tests the ArrayRingBuffer class.
 *  @author Josh Hug
 * @author Ge SHuai
 */

public class TestArrayRingBuffer {
    @Test
    public void someTest() {
        ArrayRingBuffer<Integer> arb = new ArrayRingBuffer(10);
    }

    @Test
    public void testEnqueue() {
        ArrayRingBuffer<Integer> sad1 = new ArrayRingBuffer(6);
        sad1.enqueue(8);
        sad1.enqueue(9);
        sad1.enqueue(10);
        assertEquals(sad1.fillCount(), 3);
        assertEquals((Integer) 8, sad1.dequeue());
        assertEquals((Integer) 9, sad1.dequeue());
        assertEquals((Integer) 10, sad1.dequeue());
    }

    @Test
    public void testPeek() {
        ArrayRingBuffer<Integer> sad2 = new ArrayRingBuffer(6);
        sad2.enqueue(8);
        sad2.enqueue(9);
        assertEquals(sad2.fillCount(), 2);
        assertEquals((Integer) 8, sad2.peek());
    }

    @Test
    public void testCapacity() {
        ArrayRingBuffer<Integer> sad3 = new ArrayRingBuffer(6);
        sad3.enqueue(99);
        sad3.enqueue(66);
        assertEquals(sad3.fillCount(), 2);
        assertEquals(6, sad3.capacity());
        assertEquals(2,sad3.fillCount());
    }

    /** Calls tests for ArrayRingBuffer. */
    public static void main(String[] args) {
        jh61b.junit.textui.runClasses(TestArrayRingBuffer.class);
    }
} 
