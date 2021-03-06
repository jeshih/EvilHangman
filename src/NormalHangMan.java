/**
 * <p>A class that keeps track of the current state of a game of hangman.</p>
 * <p> The class is constructed with a secret word and some number of guesses.</p>
 * <p>Every time a letter is guessed, the state of the game is updated appropriately
 * integrating the guessed letter into the word, updating the number of guesses
 * remaining, etc.</p>
 * 
 * <p>This class can then be used by a user interface to administer a regular game of Hangman.</p>
 */
import java.util.*;
public class NormalHangMan extends HangmanGame
{
    

//	private String secretWord = "";//To store the secret word
//    private int GuessRemainingNum;//to store the number of guess for the user
//    private int LetterLeftNum;//to store the number of the letters in the secret word has not been guessed correctly
//    private String CurrentState = "";//store the current guessing situation
//    private String LetterGuessHistory = "";//store the letter user has tried
    private char LetterGuess;//the letter the user guess right now

    /**
     * Constructor sets up the game to be played with a word and some number of
     * guesses.  The class should have private variables that keep track of:
     * <li>The original secret word
     * <li>The number of guesses remaining
     * <li>The number of letters that still need to be guessed
     * <li>The current state of word to be guessed (e.g. "L A B _ R A _ _ R Y")
     * @param secretWord the word that the player is trying to guess
     * @param numGuesses the number of guesses allowed
     */
    public NormalHangMan(String SecretWord, int numGuesses, String LetterHistory){
        secretWord = SecretWord;
        guessRemaining = numGuesses;
        lettersLeft = secretWord.length();
        for(int i = 0; i < secretWord.length(); i++)
        {
            state += "_ ";
            for(int j = i; j > 0; j--)
            {
                if(secretWord.charAt(i) == secretWord.charAt(j-1))
                {
                	lettersLeft--;//If the letter appears many times in the secret word, it will be counted just once.
                    break;
                }
            }
        }
        for (int i = 0; i< LetterHistory.length(); i++){ 
        	LetterGuessHistory.add(LetterHistory.charAt(i));
        }
//        LetterGuessHistory = LetterHistory;
    }   
    
/*
    public String getSecretWord()
    {
        return secretWord;
    }
*/
    /*
    public int numGuessesRemaining()
    {
        return GuessRemainingNum;
    }
    */
    /*
    public int numLettersRemaining()
    {
        return LetterLeftNum;
    }
    
    */
    public boolean isWin()
    {
        if(guessRemaining == 0)
            return false;//if the user have no chance to guess again, it means the user loses.
        else
            return true;
    }
    public boolean gameOver()
    {
        if(guessRemaining == 0 || lettersLeft == 0)
            return true;
        else
            return false;
    }
    
/*
    public String lettersGuessed()
    {
        return LetterGuessHistory;
    }
*/
    /*
    public String displayGameState()
    {
        return CurrentState;
    }
    
    */
    
    public boolean makeGuess(char ch)
    {
    	if (Character.isLetter(ch) == false) return false;
        boolean tempB = true;
        LetterGuess = ch;
        for(int i = 0; i < secretWord.length(); i++)
        {
            if(secretWord.charAt(i) == ch)//if the user guess right, adjust the current state.
            {
                String temp = "";
                for(int j = 0; j < secretWord.length(); j++)
                {
                    if(secretWord.charAt(j) == ch)
                    {
                        temp = temp + ch + " ";
                    }
                    else
                    {
                        temp = temp + state.charAt(2*j) + state.charAt(2*j+1);              
                    }
                }
                state = temp;
                tempB = true;
                break;
            }
            else
            {
                tempB = false;
            }
        }
        if(!RepeatInput(ch,LetterGuessHistory))
        {
            LetterGuessHistory.add(LetterGuess);

            if(tempB)
            {
            	lettersLeft--;
            }
            else
            {
            	guessRemaining--;
            }
            return tempB;
        }
        else return false;
    }
    
    /*
     * 
    public boolean RepeatInput(char c)
    {
    	for (int i = 0; i < LetterGuessHistory.length(); i++) {
    		if (LetterGuessHistory.charAt(i) == c) return true;
    	}
    	return false;
    }
    */
   
}
    
       