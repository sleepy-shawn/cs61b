import static org.junit.Assert.*;
import org.junit.Test;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class ArrayDequeTest {
    // for testing the fucking arraydeque

    @Test
    public void testSize(){
        ArrayDeque<Integer> a = new ArrayDeque<>();
        assertEquals(a.size(), 0);
    }

    @Test
    public void testAddFirst(){
        ArrayDeque<Integer> a = new ArrayDeque<>();
        a.addFirst(15);
        a.addFirst(88);
        assertEquals((int)a.get(0), 88);
    }

    @Test
    public void testAddLast(){
        ArrayDeque<Integer> b = new ArrayDeque<>();
        b.addLast(15);
        b.addLast(99);
        assertEquals((int)b.get(0), 15);
    }

    @Test
    public void testIsEmpty(){
        ArrayDeque<Integer> c = new ArrayDeque<>();
        assertTrue(c.isEmpty());
    }

    @Test
    public void testRemoveFirst() {
        ArrayDeque<Integer> d = new ArrayDeque<>();
        d.addLast(15);
        d.addLast(99);
        assertEquals((int)d.removeFirst(), 15);
    }

    @Test
    public void testPrintDeque() {
        ArrayDeque<Integer> deque = new ArrayDeque<>();
        deque.addFirst(10);
        deque.addLast(20);
        deque.addFirst(5);

        // Create a stream to capture the output
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;  // Save the original System.out

        // Redirect System.out to the ByteArrayOutputStream
        System.setOut(new PrintStream(outContent));

        // Call the method that prints to System.out
        deque.printDeque();

        // Restore the original System.out
        System.setOut(originalOut);

        // Assert that the captured output is as expected
        assertEquals("5 10 20 \n", outContent.toString());
    }




}