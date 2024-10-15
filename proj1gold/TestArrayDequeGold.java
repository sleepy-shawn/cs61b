import org.junit.Test;
import static org.junit.Assert.*;

// @source StudentArrayDeque
public class TestArrayDequeGold {
	@Test
	public void testFirst() {
		StudentArrayDeque<Integer> sad1 = new StudentArrayDeque<>();
		ArrayDequeSolution<Integer> correct = new ArrayDequeSolution<>();
		String returnStatement = "";

		for (int i = 0; i < 10; i += 1) {
			Integer number = StdRandom.uniform(0, 10);
			sad1.addFirst(number);
			returnStatement = returnStatement + "addFirst(" + number + ")\n";
			correct.addFirst(number);
		}
		for (int i = 0; i < 10; i += 1) {
			Integer a = sad1.removeFirst();
			returnStatement = returnStatement + "removeFirst()\n";
			Integer b = correct.removeFirst();
			assertEquals(returnStatement,a,b);
		}
	}

	@Test
	public void testLast() {
		StudentArrayDeque<Integer> sad2 = new StudentArrayDeque<>();
		ArrayDequeSolution<Integer> correct2 = new ArrayDequeSolution<>();
		String returnStatement = "";

		for (int i = 0; i < 10; i += 1) {
			Integer number = StdRandom.uniform(-10, 0);
			sad2.addLast(number);
			returnStatement = returnStatement + "addLast(" + number + ")\n";
			correct2.addLast(number);
		}
		for (int i = 0; i < 10; i += 1) {
			Integer a = sad2.removeLast();
			returnStatement = returnStatement + "removeLast()\n";
			Integer b = correct2.removeLast();
			assertEquals(returnStatement,a,b);
		}
	}

	@Test
	public void testSize() {
		StudentArrayDeque<Integer> sad3 = new StudentArrayDeque<>();
		ArrayDequeSolution<Integer> correct3 = new ArrayDequeSolution<>();
		Integer length = StdRandom.uniform(10);
		String returnStatement = "";

		for (int i = 0; i < length; i += 1) {
			Integer number = StdRandom.uniform(-10, 0);
			sad3.addFirst(number);
			returnStatement = returnStatement + "addFirst(" + number + ")\n";
			correct3.addFirst(number);
		}
		int a = sad3.size();
		returnStatement = returnStatement + "size()\n";
		int b = correct3.size();
		assertEquals(returnStatement,a,b);

	}

	@Test
	public void testGet() {
		StudentArrayDeque<Integer> sad5 = new StudentArrayDeque<>();
		ArrayDequeSolution<Integer> correct5 = new ArrayDequeSolution<>();
		Integer length = StdRandom.uniform(20);
		String returnStatement = "";

		for (int i = 0; i < length; i += 1) {
			Integer number = StdRandom.uniform(-10, 0);
			sad5.addFirst(number);
			returnStatement = returnStatement + "addFirst(" + number + ")\n";
			correct5.addFirst(number);
		}
		for (int i = 0; i < length; i += 1) {
			Integer index =  StdRandom.uniform(0, length);
			Integer a = sad5.get(index);
			returnStatement = returnStatement + "get(" + index + ")\n";
			Integer b = correct5.get(index);
			assertEquals(returnStatement, a, b);
		}
	}

	@Test
	public void testIsEmpty() {
		StudentArrayDeque<Integer> sad4 = new StudentArrayDeque<>();
		ArrayDequeSolution<Integer> correct4 = new ArrayDequeSolution<>();
		Integer length = StdRandom.uniform(10);
		String returnStatement = "";

		for (int i = 0; i < length; i += 1) {
			Integer number = StdRandom.uniform(-10, 0);
			sad4.addFirst(number);
			returnStatement = returnStatement + "addFirst(" + number + ")\n";
			correct4.addFirst(number);
		}
		assertEquals(sad4.isEmpty(), correct4.isEmpty());
		for (int i = 0; i < length; i += 1) {
			Integer a = sad4.removeFirst();
			returnStatement = returnStatement + "removeFirst()\n";
			Integer b = correct4.removeFirst();
		}
		boolean a = sad4.isEmpty();
		returnStatement = returnStatement + "isEmpty()\n";
		boolean b = correct4.isEmpty();
		assertEquals(returnStatement, a, b);
	}
}
