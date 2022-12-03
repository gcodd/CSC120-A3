import java.util.Scanner;
import java.util.Random;

/** 
 * Conversation class demonstrates a chat bot. 
 * @author Grace Codd
 */
class Conversation {
  /** Array of canned phrases */
  private static String [] cannedPhrases = {"Cool!", "Very interesting.", 
                                            "Hmm. Tell me more!", "I don't know much about that.",
                                            "Great!", "Oh wow!", "Good to know."};

  /** Main method runs program for chat bot
   */
  public static void main(String[] arguments) {

    /** Number of conversation rounds */
    int rounds;
    /** Number of elements in cannedPhrases array */                            
    int nCanned = cannedPhrases.length;                            

    //Create Scanner object for input
    Scanner keyboard = new Scanner(System.in);
    /** Random object. Will be used to generate random phrase from cannedPhrases*/
    Random rand = new Random();
    //Ask User for number of rounds 
    System.out.println("Welcome to the chatbot!");
    System.out.println("How many rounds?");
    //Store rounds 
    rounds = keyboard.nextInt();
    //Skip a line
    keyboard.nextLine();
    /** Array to store transcript of conversation */
    //Spaces aloted for transcript will be (rounds * 2) + 2.
    //rounds * 2 is to alot space for each input and response and the extra +2 is to alot space for the greeting and goodbye.
    String[] transcript  = new String[(rounds * 2) + 2];
    //Store intro in first position of array 
    transcript[0] = "Hello! What's up with you?";
    System.out.println(transcript[0]);
    /** Index for element in transcript array */
    //Initialized to 0 since there is currently only a value stored in first position
    int index = 0;

    //Do while loop will repeat until rounds == 0
    do{
      /** Stores user input */
      String input = keyboard.nextLine();
      /** Stores outputted response to input */
      String output;
      /** Stores user input as array of words */
      String[] words = input.split(" ");
      /** Will store response */
      //Used StringBuilder instead of String because StringBuilder is mutable
      StringBuilder response = new StringBuilder();

      //For loop will check each word one by one and increment counter i each time loop repeats
      //i is used as index for words[] array
      for(int i = 0; i < words.length; i++){
        //Call isMirror, passing element at position i of words
        //This will check if the word at positon i is a mirror word
        if (isMirror(words[i])){
          //If isMirror returns true, replace element at position i with value returned from mirrorReplace method
          words[i]= mirrorReplace(words[i]);
          //Append replaced word to response 
          response.append(words[i]);
        }
        else
          //Otherwise, append unchanged word
          response.append(words[i]);

        if((i + 1) < words.length){
          //if we have not reached the last word, append a space
          response.append(" ");
        }
      }

      //convert response to string, compare to input with equals method
      if(response.toString().equals(input)){
        //if equals method returns true, response did not contain mirrored words
        //store random canned phrase in output
        //nextInt method from Random class generates random integer
        //nCanned passed as argument so random integer cannot exceed number of canned responses 
        //canned phrase stored at position of random integer generated will be stored in output
        output = cannedPhrases[rand.nextInt(nCanned)];
      } 
      //Otherwise, store response converted to string in output
      else
        output = response.toString();
      
      //Print output
      System.out.println(output);

      //Store input and output of this conversation round
      //increment index, store input in this position of transcript array
      transcript[++index] = input;
      //increment index, store output in this position of transcript array
      transcript[++index] = output;

      //Decrement rounds
      rounds--;
    } while (rounds > 0); //Loop will stop when rounds == 0
    //Increment index, store goodbye message at this position in transcript.
    transcript[++index] = "Goodbye!";
    //Print goodbye message
    System.out.println(transcript[index]);
    //Print blank line
    System.out.println();

    //Print transcript
    System.out.println("TRANSCRIPT:");
    //Enhanced for loop prints each element in transcript array
    for(String text: transcript){
      System.out.println(text);
    }

    //Quit program
    System.exit(0);
  }

  /** Compares string to a number of mirror words
   * @param s word to be compared to mirror words
   * @return true if string matches one mirror word, false if not
   */
  public static boolean isMirror(String s){
    if(s.equalsIgnoreCase("I") || 
        s.equalsIgnoreCase("you") ||
        s.equalsIgnoreCase("me") ||
        s.equalsIgnoreCase("my") ||
        s.equalsIgnoreCase("your") ||
        s.equalsIgnoreCase("we") ||
        s.equalsIgnoreCase("our") ||
        s.equalsIgnoreCase("yours") ||
        s.equalsIgnoreCase("ours") ||
        s.equalsIgnoreCase("am")||
        s.equalsIgnoreCase("are"))
        return true;
    else
      return false;
  }


  /** Replaces string with a mirror word
   * @param s word to be replaced
   * @return mirror word replacement
   */
  public static String mirrorReplace(String s){
    //Switch compares string s to each case string and replaces string with mirror word
    switch(s){
      case "I":
        s = "you";
        break;
      case "You":
        s = "I";
        break;
      case "you":
        s = "me";
        break;
      case "am":
        s = "are";
        break;
      case "are":
        s = "am";
        break;
      case "my": 
        s = "your";
        break;
      case "your":
        s = "my";
        break;
      case "we":
        s = "you";
        break;
      case "our":
        s = "your";
        break;
      case "ours":
        s = "yours";
        break;
    }
    return s;
  }
}