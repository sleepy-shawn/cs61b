import org.junit.Test;
import static org.junit.Assert.*;

public class TestOffByN {

	@Test
	public void testEqualChars() {
		CharacterComparator offBy2 = new OffByN(2);
		assertTrue(offBy2.equalChars('z', 'x'));
		assertFalse(offBy2.equalChars('g', 's'));

		CharacterComparator offBy3 = new OffByN(3);
		assertTrue(offBy3.equalChars('a', 'd'));
		assertFalse(offBy3.equalChars('a', 'x'));
	}
}
