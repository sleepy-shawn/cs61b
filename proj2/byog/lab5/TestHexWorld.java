package byog.lab5;
import org.junit.Test;
import static org.junit.Assert.*;

public class TestHexWorld {
	@Test
	public void testFillNum() {
		assertEquals(9, HexWorld.fillNum(2,5));
		assertEquals(10, HexWorld.fillNum(4, 4));
	}

	@Test
	public void testStart1() {
		assertEquals(HexWorld.start1(0, 4), 3);
		assertEquals(HexWorld.start1(3,5), 1);
	}

	@Test
	public void testIsFill() {

	}
}
