public class OffByN implements CharacterComparator {

	private int num;

	public OffByN(int x) {
		num = x;
	}

	@Override
	public boolean equalChars(char x, char y) {
		int diff = x - y;
		return Math.abs(diff) == num;
	}
}
