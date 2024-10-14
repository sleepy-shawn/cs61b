public class Palindrome {
	public Deque<Character> wordToDeque(String word){
		Deque<Character> wordDeque = new LinkedListDeque<>();
		int length = word.length();
		for (int i = 0; i < length; i++) {
			wordDeque.addLast(word.charAt(i));
		}
		return wordDeque;
	}

	public boolean isPalindrome(String word){
		Deque<Character> wordDeque = wordToDeque(word);
		return tellPalindrome(wordDeque);
	}

	// helper function

	public boolean tellPalindrome(Deque<Character> characterDeque) {
		if (characterDeque.isEmpty() || characterDeque.size() == 1) {
			return true;
		} else {
			char a = characterDeque.removeFirst();
			char b = characterDeque.removeLast();
			if (a != b) {
				return false;
			}else {
				return tellPalindrome(characterDeque);
			}
		}
	}
}
