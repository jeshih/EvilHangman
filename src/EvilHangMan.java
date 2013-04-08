import java.util.*;
import java.io.*;


public class EvilHangMan extends HangmanGame {
	//	private String secretWord = "";// To store the secret word
	//	private int guess;// to store the number of guess for the user
	//	private String state = "";// store the current guessing situation
	//	private String LetterGuessHistory = "";// store the letters user has tried
	private char l;// the letter the user guess right now
	private String[] Wordlist = new String[235000];// to store the dictionary
	private ArrayList<String> WordArray = new ArrayList<String>();
	private int numWords = 0;// count the number of possible secret words.
	private int secretStringLength;// the length of the secret string
	private boolean GuessResult = false;

	public EvilHangMan(int StringLength, int numGuesses) {
		guessRemaining = numGuesses;
		secretStringLength = StringLength;
		lettersLeft = 26;
		Scanner Scanner = null;
		try {
			Scanner = new Scanner(new File("dictionary.txt"));// read the dictionary
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		int i = 0;
		while (Scanner.hasNext()) {
			String temp = Scanner.nextLine().toUpperCase();
			if (temp.length() == StringLength) {
				WordArray.add(temp);
				numWords++;
			}
		}

		for (i = 0; i < StringLength; i++) {
			state += "_ ";
		}
		Scanner.close();
	}

	/*
		public String getSecretWord() {
			return secretWord;
		}
	 */
	/*
		public int numGuessesRemaining() {
			return guess;
		}

	 */

	/*
		public int numLettersRemaining() {
			return 26; // because they never get one right!
		}
	 */

	public boolean isWin() {
		return false;
	}

	public boolean gameOver() {
		if (guessRemaining == 0)
			return true;
		else
			return false;
	}

	/*
		public String lettersGuessed() {
			return LetterGuessHistory;
		}

	 */

	/*
		public String displayGameState() {
			return state;
		}
	 */


	public boolean makeGuess(char ch) {
		GuessResult = false;
		l = ch;
		CharSequence cs = String.valueOf(ch);
		if (Character.isLetter(ch) && !RepeatInput(ch,LetterGuessHistory)) {
			// adjust the Wordlist in order to avoid the word with the letter
			// user guessed
			int tempWordNum = 0;
			
			
			System.out.println("Char input: " + ch);
			int counter = WordArray.size() -1 ;
			while (counter >= 0){
				if (WordArray.get(counter).contains(cs)){
					WordArray.remove(counter);
				}
				else{
					tempWordNum++;
				}
				counter--;
			}
			
			/*
			boolean flag;
			for (int i = WordArray.size() - 1; i >= 0; i--){
				flag = false;
				for(int j = 0; j < secretStringLength; i++){
					if (flag = true){
						break;
					}
					else if (WordArray.get(i).charAt(j) == ch){
						WordArray.remove(i);
						flag = true;
					}
					else{
						if (j == secretStringLength - 1){
							if (WordArray.get(i).charAt(j) != ch){
								tempWordNum++;
							}
						}
					}
				}
			}
			
			*/
//			tempWordNum = WordArray.size();

			// we choose the words that don't contain the letter the user
			// guessed, and they will be the new possible secret words.


			System.out.println("tempWordNum: " + tempWordNum);
			System.out.println("WordArray size: " + WordArray.size());
			
			if (tempWordNum == 0) {
				System.out.println("Character guessed: " + ch);
				System.out.println("Secret word: " + secretWord);
				System.out.println(WordArray);
//				secretWord = WordArray.get(0);
				GuessResult = true;
			} else {
				secretWord = WordArray.get(0);
				numWords = tempWordNum;
				//				Wordlist = temp;
				guessRemaining--;
				GuessResult = false;
			}
			if (!GuessResult) {
				LetterGuessHistory.add(l); //changed to upper case L
			}

		} else return false;

		return GuessResult;
	}

	/*
	 * 
	    public boolean RepeatInput(char c)
	    {
	    	for (int i = 0; i < letterGuessHistory.length(); i++) {
	    		if (letterGuessHistory.charAt(i) == c) return true;
	    	}
	    	return false;
	    }
	 */
}