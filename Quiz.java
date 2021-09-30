import ecs100.*;    // import ecs100 library
import java.util.HashMap;   // import the hashmap class
import java.util.ArrayList;    // import the arraylist class
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
    private double[] difficulties = {1, 2, 3, 4};  // stores the 4 values of difficulties as indexes
    
    private String currentCountry;  // stores the current country
    private String currentCapital;  // stores the current capital
    private String otherCountry;   // stores the other country as an answer option
    private String otherCapital;   // stores the other capital as an answer option
    private String answering;      // what is currently being answered
    
    private String[] otherAnswers;  // stores the other possible answers
    private String[] otherCountryAnswers;  // stores the other possible country answers
    private ArrayList<String> countries;     // stores all countries
    
    private int amtLeft;    // the amount of questions left to be asked
    private int amt;
    private int score;  // users score
    private int lives;  // users lives
    private int correctIdx;     // index of correct answer
    
    private boolean answeringCap = true;   // if answering capital of not
                                           // true by default
    
    private final int OTHERAMT = 3;     // amount of other possible answers
    private int currentAnswerKey;   // the current answer key
    private int correctAnswerKey;    // the correct answer key
    private final int FULLKEY = 4;      // the maximum amount of answers
    
    Questions qs = new Questions("something");
    
    /**
     * Constructor for objects of class Quiz
     * 
     * @param difficulty - the difficulty level of the quiz
     */
    public Quiz(double difficulty) {
        this.diff = difficulty;
        
        // reset scores and lives
        lives = 3;
        score = 0;
    }
    
    /**
     * Select the hashmap based on the difficulty
     * 
     * @return HashMap of the questions for the round
     */
    public void getQs(double difficulty) {
        // reset the question and answer key information for the new round
        questions.clear();
        answerKey.clear();
        currentAnswerKey = 1;
        
        // get the new questions
        questions = qs.getQuestions(difficulty);
        amt = questions.size();   // get the length of hashmap
        amtLeft = amt;  // set amount left to amount
        countries = new ArrayList<String>();    // declare arraylist
        
        // store countries in array
        for (String country : questions.keySet()) {
            countries.add(country);
        }
    }
    
    /**
     * Chooses the mode
     */
    public boolean mode(boolean answerCap) {
        // switch the mode
        if (answerCap == true) {
            answerCap = false;
        } else if (answerCap == false) {
            answerCap = true;
        }
        
        // store values
        this.answeringCap = answerCap;
        if (answerCap == true) {
            answering = "Country to Capital";
        } else if (answerCap == false) {
            answering = "Capital to Country";
        }
        
        // return new value
        return answerCap;
    }
    
    /**
     * Chooses the question from the selected hashmap
     */
    public void chooseQuestion() {
        int choiceIdx = (int) (Math.random() * amt);  // randomly choose the question
        
        // check if index has already been used
        if (amtLeft != 0) {
            while ((askedQuestions.containsKey(countries.get(choiceIdx)))){
                choiceIdx = (int) (Math.random() * amt);  // randomly choose the question again
            }
        }
        
        // set values
        amtLeft--;
        correctIdx = choiceIdx;
        
        // store values
        currentCountry = countries.get(choiceIdx);   // choose the random country
        currentCapital = questions.get(currentCountry);  // get the capital of the chosen country
        askedQuestions.put(currentCountry, currentCapital); // add them to the asked questions hashmap
    }
    
    /**
     * Set the difficulty
     */
    public boolean setDifficulty(double diffIdx) {
        if (diffIdx == difficulties[0]){
            diff = diffIdx;
            return false;
        } else if (diffIdx == difficulties[1]){
            diff = diffIdx;
            return false;
        } else if (diffIdx == difficulties[2]){
            diff = diffIdx;
            return false;
        } else if (diffIdx == difficulties[3]){
            diff = diffIdx;
            return false;
        } else {
            return true;
        }
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
        return amtLeft;
    }
    
    /**
     * Return lives
     */
    public int getLives() {
        return lives;
    }
    
    /**
     * Return score
     */
    public int getScore() {
        return score;
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
        int answerIdx = 0;  // reset answer index
        otherAnswers = new String[OTHERAMT];   // reset other answers array
        
        // run this code 3 times to get 3 other answers
        for (int i = 0; i < OTHERAMT; i++) {
            int choiceIdx = (int) (Math.random() * amt);  // randomly choose the question

            while (Arrays.asList(otherAnswers).contains(questions.get(countries.get(choiceIdx))) || countries.get(choiceIdx).equalsIgnoreCase(currentCountry)) {
                choiceIdx = (int) (Math.random() * amt);  // randomly choose the question
            }
            otherCountry = countries.get(choiceIdx);   // choose the random country
            otherCapital = questions.get(otherCountry);  // get the capital of the chosen country
            
            // add other options to hashmap for current question
            if (answeringCap == true) {
                otherAnswers[answerIdx] = otherCapital;
            } else if (answeringCap == false) {
                otherAnswers[answerIdx] = otherCountry;
            }
            // increment answerIdx
            answerIdx++;
        }

    }

    /**
     * Assigns and returns the numbers to input for the 4 answers
     */
    public void assignAnswerKey() {
        int answerIdx = 0;
        
        // add the current capital to the answer key
        currentAnswerKey = (int) (Math.random() * FULLKEY); // random number between 1 and 4
        if (answeringCap == true) {
            answerKey.put(currentAnswerKey, currentCapital);
        } else if (answeringCap == false) {
            answerKey.put(currentAnswerKey, currentCountry);
        }
        correctAnswerKey = currentAnswerKey;    // get the correct answer and its key
        
        // run as long as hashmap isnt full
        while (answerKey.size() < FULLKEY) {
            currentAnswerKey = (int) (Math.random() * FULLKEY); // random number between 1 and 4
            if (!answerKey.containsKey(currentAnswerKey) && answerIdx < OTHERAMT) {
                answerKey.put(currentAnswerKey, otherAnswers[answerIdx]);
                answerIdx++;
            }
        }
    }
    
    /**
     * Calculates user score and lives
     */
    public boolean scoreCalculator(int userAnswer) {
        // check if users answer is correct or not
        if ((userAnswer - 1) == correctAnswerKey) {
            // if correct add 1 to score and return true
            score++;
            return true;
        } else {
            // if wrong take 1 life and return false
            lives--;
            return false;
        }
    }
}
