public class Hangman {
    public Hangman() {
        myMisses = 0;
        myWordIndex = 0;
        myLettersUsed = new boolean[Character.MAX_VALUE];
    }

    /**
     * clear all variables to beginning-of-game state
     * word shown user will be all blanks, all letters
     * are eligible for "guessability"
     */

    private void clear()
    {
        int k;

        // all letters can be guessed, none are used

        for(k=0; k < Character.MAX_VALUE; k++) {
            myLettersUsed[k] = false;
        }

        // word shown to user is all blanks

        mySecretWord = new char[myWords[myWordIndex].length()];
        for(k=0; k < mySecretWord.length; k++) {
            mySecretWord[k] = BLANK;
        }
    }

    /**
     * process a user's guess, update state to reflect
     * the guess. This might change # misses and the
     * word displayed to the user
     *
     * @param ch is the character guessed by the user
     */
    private void guess(char ch)
    {
        int k;
        boolean charFound = false;

        ch = Character.toLowerCase(ch);	   // case doesn't matter

        for(k=0; k < mySecretWord.length; k++) {
            if (! myLettersUsed[ch] &&
                    myWords[myWordIndex].charAt(k) == ch) {
                mySecretWord[k] = ch;
                charFound = true;
            }
        }
        if (! myLettersUsed[ch] && ! charFound) {
            myMisses++;
        }
        myLettersUsed[ch] = true;
    }

    /**
     * display (partially guessed) word to user
     */
    private void showWord()
    {
        int k;
        for(k=0; k < mySecretWord.length; k++) {
            System.out.print(mySecretWord[k]+" ");
        }
        System.out.println("\n# misses left = "+ (MISSES-myMisses));
    }

    /**
     * @return true if word has been guessed, else returne false
     */

    private boolean wordGuessed()
    {
        int k;
        for(k=0; k < mySecretWord.length; k++) {
            if (mySecretWord[k] == BLANK) {
                return false;
            }
        }
        return true;
    }

    /**
     * plays a game of hangman. Repeated calls of this
     * function will play different games (different words)
     * up to some internal limit based on the number of different
     * words. After all words have been used they'll be repeated
     */

    public void play()
    {
        clear();
        while (true) {
            showWord();
            System.out.print("guess> ");
            String s = ConsoleInput.readString();
            if (s.length() > 0) {      // typed something?
                guess(s.charAt(0));
            }
            if (myMisses >= MISSES) {
                System.out.println("you lose!!!");
                break;
            }
            else if (wordGuessed()) {
                System.out.println("you win!!!");
                break;
            }
        }
        myWordIndex = (myWordIndex + 1) % myWords.length;
    }

    private String myWords[] = {
            "apple",
            "toothbrush",
            "cucumber",
            "strengthens",
            "portfolio"
    };

    private  char[] mySecretWord;       // what's shown to the user
    private  int    myMisses;           // # misses so far
    private  int    myWordIndex;        // which word is being guessed
    private  boolean[] myLettersUsed;   // tracks letters guessed/used

    private final static char BLANK = '_';
    private final static int  MISSES = 8;

    public static final void main(String args[])
    {
        Hangman hang = new Hangman();
        hang.play();
    }
}