import ecs100.*;    // import ecs100 library
import java.util.HashMap;   // import the hashmap class
import java.util.Arrays;    // import the arrays class
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
    private HashMap<String, String> questions = 
        new HashMap<String, String>();  // stores all questions for the round
    private HashMap<Integer, String> answerKey =
        new HashMap<Integer, String>();  // the answer key
        
    private double diff;    // difficulty of the quiz
    
    private String currentCountry;  // stores the current country
    private String currentCapital;  // stores the current capital
    private String otherCountry;   // stores the other country as an answer option
    private String otherCapital;   // stores the other capital as an answer option
    
    private String[] otherAnswers;  // stores the other possible answers
    
    private int amt;    // the amount of questions left to be asked
    private int score;  // users score
    private int lives;  // users lives
    private final int OTHERAMT = 3;     // amount of other possible answers
    private int currentAnswerKey;   // the current answer key
    private final int FULLKEY = 4;      // the maximum amount of answers
    
    Questions qs = new Questions("something");
    
    /**
     * Constructor for objects of class Quiz
     * 
     * @param difficulty - the difficulty level of the quiz
     */
    public Quiz(double difficulty) {
        this.diff = difficulty;
    }
    
    /**
     * Select the hashmap based on the difficulty
     * 
     * @return HashMap of the questions for the round
     */
    public void getQs(String difficulty) {
        // reset the question and answer key information for the new round
        questions.clear();
        answerKey.clear();
        currentAnswerKey = 1;
        
        // get the new questions
        questions = qs.getQuestions(difficulty);
        amt = questions.size();   // get the length of hashmap
    }
    
    /**
     * Chooses the question from the selected hashmap
     */
    public void chooseQuestion() {
        boolean first = false;
        int choiceIdx = (int) (Math.random() * amt);  // randomly choose the question
        
        while ((askedQuestions.containsKey(currentCountry) && first) || amt != 0 ) {
            choiceIdx = (int) (Math.random() * amt);  // randomly choose the question again
            first = true;
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
     * Return multichoice answers
     */
    public HashMap getAnswers() {
        return answerKey;
    }
    
    /**
     * Return amount of questions
     */
    public int getAmount() {
        return amt;
    }
    
    /**
     * Reset the array for the other answers
     */
    public void resetAnswers() {
        Arrays.fill(otherAnswers, null);
    }
    
    /**
     * Generates the 3 other multichoice answers
     */
    public void otherAnswers() {
        int answerIdx = 0;
        
        // run this code 3 times to get 3 other answers
        for (int i = 0; i <= OTHERAMT; i++) {
            int choiceIdx = (int) (Math.random() * amt);  // randomly choose the question
            
            while (Arrays.asList(otherAnswers).contains(currentCountry)) {
                chooseQuestion();   // if the randomly chosen question has been asked generate the next question
            }
            
            otherCountry = questions.get(choiceIdx);   // choose the random country
            otherCapital = questions.get(otherCountry);  // get the capital of the chosen country
            
            // add other options to hashmap for current question
            otherAnswers[answerIdx] = otherCapital;
            answerIdx++;
        }
    }

    /**
     * Assigns and returns the numbers to input for the 4 answers
     */
    public void assignAnswerKey() {
        int answerIdx = 0;
        
        // run as long as hashmap isnt full
        while (answerKey.size() <= FULLKEY) {
            currentAnswerKey = (int) (Math.random() * FULLKEY); // random number between 1 and 4
            if (answerKey.containsValue(currentCapital) && !answerKey.containsKey(currentAnswerKey)) {
                // add to hashmap if not in already
                answerKey.put(currentAnswerKey, currentCapital);
            } else if (!answerKey.containsKey(currentAnswerKey)) {
                answerKey.put(currentAnswerKey, otherAnswers[answerIdx]);
                answerIdx++;
            }
        }
    }
    
    /**
     * Calculates user score and lives
     */
    private void scoreCalculator() {
        
    }
}
