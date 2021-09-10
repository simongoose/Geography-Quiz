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
    private HashMap<String, String> askedQuestions = 
        new HashMap<String, String>();  // stores already asked questions
    private HashMap<String, String> questions;  // stores all questions for the round
    
    private String[] qanda; // stores the current question and answer
    
    private String currentCountry;  // stores the current country
    private String currentCapital;  // stores the current capital
    
    private int amt;    // the amount of questions left to be asked
    private int score;  // users score
    private int lives;  // users lives
    
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
        amt = questions.size();   // get the length of hashmap
        int choiceIdx = (int) (Math.random() * amt);  // randomly choose the question
        
        currentCountry = questions.get(choiceIdx);   // choose the random country
        currentCapital = questions.get(currentCountry);  // get the capital of the chosen country
    }
    
    /**
     * Checks if a question has already been asked
     */
    private void checkRepeat() {
        while (questions.containsKey(currentCountry) || amt != 0) {
            chooseQuestion();   // if the randomly chosen question has been asked generate the next question
        }
        amt--;
    }
    
    /**
     * Generates the 3 other multichoice answers
     */
    private void otherAnswers() {
        
    }
    
    /**
     * Calculates user score and lives
     */
    private void scoreCalculator() {
        
    }
}
