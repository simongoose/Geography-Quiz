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
    private HashMap<String, String> otherAnswers =
        new HashMap<String, String>();  // stores countries/capitals to be used in the multichoice answers
    
    private String[] qanda; // stores the current question and answer
    
    private String currentCountry;  // stores the current country
    private String currentCapital;  // stores the current capital
    private String otherCountry;   // stores the other country as an answer option
    private String otherCapital;   // stores the other capital as an answer option
    
    private int amt;    // the amount of questions left to be asked
    private int score;  // users score
    private int lives;  // users lives
    private final int OTHERAMT = 3;     // amount of other possible answers
    
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
    public void getQs(double difficulty) {
        // reset the question information for the new round
        questions.clear();
        
        // get the new questions
        questions = qs.getQuestions(difficulty);
    }
    
    /**
     * Chooses the question from the selected hashmap
     */
    public void chooseQuestion() {
        amt = questions.size();   // get the length of hashmap
        int choiceIdx = (int) (Math.random() * amt);  // randomly choose the question
        
        while (askedQuestions.containsKey(currentCountry) || amt != 0) {
            chooseQuestion();   // if the randomly chosen question has been asked generate the next question
        }
        amt--;  // take one away from amount of questions left
        
        currentCountry = questions.get(choiceIdx);   // choose the random country
        currentCapital = questions.get(currentCountry);  // get the capital of the chosen country
    }
    
    /**
     * Return country
     */
    public String getCountry() {
        return currentCountry;
    }
    
    /**
     * Return capital
     */
    public String getCapital() {
        return currentCapital;
    }
    
    /**
     * Return other answers
     */
    public HashMap getOtherAnswers() {
        return otherAnswers;
    }
    
    /**
     * Reset the hashmap for the other answers
     */
    public void resetAnswers() {
        otherAnswers.clear();
    }
    
    /**
     * Generates the 3 other multichoice answers
     */
    public void otherAnswers() {
        // run this code 3 times to get 3 other answers
        for (int i = 0; i <= OTHERAMT; i++) {
            int choiceIdx = (int) (Math.random() * amt);  // randomly choose the question
            
            while (otherAnswers.containsKey(currentCountry)) {
                chooseQuestion();   // if the randomly chosen question has been asked generate the next question
            }
            
            otherCountry = questions.get(choiceIdx);   // choose the random country
            otherCapital = questions.get(currentCountry);  // get the capital of the chosen country
            
            // add other options to hashmap for current question
            otherAnswers.put(otherCountry, otherCapital);
        }
    }
    
    /**
     * Calculates user score and lives
     */
    private void scoreCalculator() {
        
    }
}
