import ecs100.*;    // import ecs100 library
import java.util.HashMap;   // import the hashmap class
import java.awt.Color;      // import the color class
/**
 * Quiz Class
 * Asks the user the questions and checks if the answers are correct or not
 * Keeps score as well as lets user choose difficulty of quiz
 *
 * @author Simon Lee
 * @version 09/09/2021
 */
public class Quiz
{
    // fields
    HashMap<String, String> askedQuestions = 
        new HashMap<String, String>();  // stores already asked questions
    HashMap<String, String> questions;  // stores all questions for the round
    
    String[] countries; // stores all countries in the difficulty
    String[] qanda; // stores the current question and answer
    
    Questions qs = new Questions();
    
    /**
     * Constructor for objects of class Quiz
     */
    public Quiz() {
        
    }
    
    /**
     * Select the hashmap based on the difficulty
     * 
     * @return HashMap of the questions for the round
     */
    private void getQs(int difficulty) {
        questions = qs.getQuestions(difficulty);
    }
    
    /**
     * Chooses the question from the selected hashmap
     */
    private void chooseQuestion() {
        int amt = questions.size();   // get the length of hashmap
        int choiceIdx = (int) (Math.random() * amt);  // randomly choose the question
        
        for (int i = 0; i <= choiceIdx; i++) {
            
        }
    }
    
    /**
     * Checks if a question has already been asked
     */
    
    
    /**
     * Calculates user score and lives
     */
    
}
