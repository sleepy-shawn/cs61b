import static org.junit.Assert.*;
import org.junit.Test;

public class FlikTest {

	@Test
	public void testIsSameNumber() {
		assertTrue(Flik.isSameNumber(125, 125));
		assertTrue("126 passed", Flik.isSameNumber(126, 126));
		assertTrue("127 passed", Flik.isSameNumber(127, 127));
		assertTrue("128 failed", Flik.isSameNumber(128, 128));
		assertTrue("It can tell the same number.", Flik.isSameNumber(34, 34));
		assertFalse("It can tell the different nuumber.", Flik.isSameNumber(666, 999));
		System.out.println("Test Passed!");
	}
}
