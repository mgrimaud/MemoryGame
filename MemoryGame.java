import java.util.Random;
/**
 * Project 3.6.5
 *
 * The Memory Game shows a random sequence of "memory strings" in a variety of buttons.
 * After wathcing the memory strings appear in the buttons one at a time, the
 * player recreates the sequence from memory.
 */
public class MemoryGame
{
  public static void main(String[] args) {

    // Create the "memory strings" - an array of single character strings to 
    // show in the buttons, one element at a time. This is the sequence
    // the player will have to remember.
    String[] strings = {"a", "b", "c", "d", "e"};

    // Create the game and gameboard. Configure a randomized board with 3 buttons.
    // (Later, you can change options to configure more or less buttons
    // and turn randomization on or off.)
    MemoryGameGUI game = new MemoryGameGUI();
    game.createBoard(3, true);
    int gamesPlayed = 0;
    int score = 0;
  
    

    // Ask if user wants to play another round of the game 
    // and track the number of games played
    while(game.playAgain()) {
      gamesPlayed++;

      // Create a new array that will contain the randomly ordered memory strings.
      String[] array = strings;
		
      Random rand = new Random();
      
      for (int i = 0; i < array.length; i++) {
        int randomIndexToSwap = rand.nextInt(array.length);
        String temp = array[randomIndexToSwap];
        array[randomIndexToSwap] = array[i];
        array[i] = temp;
      }
      

      // Create a list of randomly ordered integers with no repeats, the length
      // of memory strings. Use it to create a random sequence of the memory strings.
      int[] randNums = new int[strings.length];
      boolean[] used = new boolean[strings.length];
      
      for(int i = 0; i < randNums.length; i++) {
        int randomNumber = (int) (Math.random() * randNums.length);
        while(!used[randomNumber]) {
          randNums[i] = randomNumber;
          used[randomNumber] = true;
          randomNumber = (int) (Math.random() * randNums.length);
        }
      }

      // Play one sequence, delaying half a second for the strings to show
      // in the buttons. Save the player's guess. 
      String guess = game.playSequence(strings, 0.5);
     
  

      // Determine if player's guess matches all elements of the random sequence.
        // iterate to determine if guess matches sequence
        boolean allCorrect = true;
        for(int i = 0; i < guess.length(); i++) {
          if(!guess.substring(i, i+1).equals(strings[i])) {
            game.tryAgain();
            allCorrect = false;
            break;
          }
        }
        // If match, increase score, and signal a match, otherwise, try again.
        if(allCorrect) {
          score++;
          game.matched();
        }
        
      

      
      
    }
    
    // When done playing, show score and end the game.
    game.showScore(score, gamesPlayed);
    game.quit();
  }
}