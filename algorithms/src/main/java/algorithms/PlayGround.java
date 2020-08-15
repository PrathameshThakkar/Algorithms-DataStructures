package algorithms;

public class PlayGround {
	
	public void play() {
		String[] words = {"a","b","c","d","d"};

		int s = shortestDistance(words, "a", "d");
		
		System.out.println(s);
	}

	public int shortestDistance(String[] words, String word1, String word2) {

		int result = 0;

		if (words == null || word1 == null || word2 == null) return result;

		result = Integer.MAX_VALUE;
		int left = 0;
		int right = 0;

		int n = words.length;

		while (left < n && right < n) {

			if (findWord(words, word1, word2, right) != null) {

				if (findWord(words, word1, word2, left) != null && findWord(words, word1, word2, left) != findWord(words, word1, word2, right)) {
					result = Math.min(Math.abs(right - left), result);
					
				} 
				
				left = right;
			}

			right++;

		}

		return result;

	}

	public String findWord(String[] words, String word1, String word2, int index) {
		if (words[index].equals(word1) || words[index].equals(word2)) return words[index];
		return null;
	}

}
