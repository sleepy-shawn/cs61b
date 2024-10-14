public class OffByN implements CharacterComparator{

	public int num;

	public OffByN(int x) {
		num = x;
	}

	@Override
	public boolean equalChars(char x, char y) {
		int diff = x - y;
		return Math.abs(diff) == num;
	}
}
