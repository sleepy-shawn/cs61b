import org.junit.Test;
import static org.junit.Assert.*;

// @source StudentArrayDeque
public class TestArrayDequeGold {
	@Test
	public void testFirst() {
		StudentArrayDeque<Integer> sad1 = new StudentArrayDeque<>();
		ArrayDequeSolution<Integer> correct = new ArrayDequeSolution<>();

		for (int i = 0; i < 10; i += 1) {
			Integer number = StdRandom.uniform(0, 10);
			sad1.addFirst(number);
			correct.addFirst(number);
		}
		for (int i = 0; i < 10; i += 1) {
			Integer a = sad1.removeFirst();
			Integer b = correct.removeFirst();
			assertEquals(a,b);
		}
	}

	@Test
	public void testLast() {
		StudentArrayDeque<Integer> sad2 = new StudentArrayDeque<>();
		ArrayDequeSolution<Integer> correct2 = new ArrayDequeSolution<>();

		for (int i = 0; i < 10; i += 1) {
			Integer number = StdRandom.uniform(-10, 0);
			sad2.addLast(number);
			correct2.addLast(number);
		}
		for (int i = 0; i < 10; i += 1) {
			Integer a = sad2.removeLast();
			Integer b = correct2.removeLast();
			assertEquals(a,b);
		}
	}

	@Test
	public void testSize() {
		StudentArrayDeque<Integer> sad3 = new StudentArrayDeque<>();
		ArrayDequeSolution<Integer> correct3 = new ArrayDequeSolution<>();
		Integer length = StdRandom.uniform(10);
		for (int i = 0; i < length; i += 1) {
			Integer number = StdRandom.uniform(-10, 0);
			sad3.addFirst(number);
			correct3.addFirst(number);
		}
		assertEquals(sad3.size(),correct3.size());

	}

	@Test
	public void testGet() {
		StudentArrayDeque<Integer> sad5 = new StudentArrayDeque<>();
		ArrayDequeSolution<Integer> correct5 = new ArrayDequeSolution<>();
		Integer length = StdRandom.uniform(20);
		for (int i = 0; i < length; i += 1) {
			Integer number = StdRandom.uniform(-10, 0);
			sad5.addFirst(number);
			correct5.addFirst(number);
		}
		for (int i = 0; i < length; i += 1) {
			Integer index =  StdRandom.uniform(0, length);
			Integer a = sad5.get(index);
			Integer b = sad5.get(index);
			assertEquals(a, b);
		}
	}

	@Test
	public void testPrintDeque() {
		

	}

	@Test
	public void testIsEmpty() {
		StudentArrayDeque<Integer> sad4 = new StudentArrayDeque<>();
		ArrayDequeSolution<Integer> correct4 = new ArrayDequeSolution<>();
		Integer length = StdRandom.uniform(10);
		for (int i = 0; i < length; i += 1) {
			Integer number = StdRandom.uniform(-10, 0);
			sad4.addFirst(number);
			correct4.addFirst(number);
		}
		assertEquals(sad4.isEmpty(), correct4.isEmpty());
		for (int i = 0; i < length; i += 1) {
			Integer a = sad4.removeFirst();
			Integer b = sad4.removeFirst();
		}
		assertEquals(sad4.isEmpty(), correct4.isEmpty());
	}
}
