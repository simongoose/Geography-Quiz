import ecs100.*;    // import ecs100 library
import java.awt.Color;      // import the color class
import java.util.HashMap;   // import the hashmap class

/**
 * GUI class
 * Contains all methods which display the quiz including
 * buttons, drop down menus and things on graphics and text panes
 *
 * @author Simon Lee
 * @version 09/09/2021
 */
public class GUI
{   
    // fields
    Quiz qz = new Quiz(1);
    Difficulty df = new Difficulty();
    
    private String question;    // the question to be asked
    private String realAnswer;  // the real answer to the question
    private int amount;         // the amount of questions to be asked
    private String diff;        // difficulty of the quiz
    
    private int[] keys = {1, 2, 3, 4};  // keys to the answers
    
    private HashMap<Integer, String> answers =
        new HashMap<Integer, String>();  // stores countries/capitals to be used in the multichoice answers
    
    /**
     * Constructor for objects of class Gui
     */
    public GUI(){
        // UI setup
        UI.initialise();
        UI.addButton("Play", this::playGame);
        UI.addButton("Statistics", this::showStats);
        UI.addButton("Information", this::showInfo);
        UI.addButton("Difficulty", this::setDifficulty);
        UI.addButton("Quit", UI::quit);
    }
    
    /**
     * Runs the game
     */
    private void playGame() {
        this.getDifficulty();   // get the difficulty
        qz.getQs(diff);         // get the questions
        amount = qz.getAmount();    // store the amount of questions
        
        while (amount != 0) {
            this.getQuestion();
            this.showQuestion();
        }
    }
    
    /**
     * Gets the question for the round
     */
    private void getQuestion() {
        qz.chooseQuestion();    // choose the question to be asked
        question = qz.getCountry();  // store the question
        realAnswer = qz.getCapital();   // store the real answer
        answers = qz.getAnswers();    // store the other answers
    }
    
    /**
     * Displays the question and choices on the graphics pane
     */
    private void showQuestion() {
        int answerIdx = 0;
        
        UI.println("What is the capital of " + question + "?");
        for (int i = 1; i <= 4; i++) {
            UI.println(keys[answerIdx] + ") " + answers.get(i));
        }
    }
    
    /**
     * Displays the answer on the graphics pane
     */
    private void showAnswer() {
        
    }

    /**
     * Displays statistics
     */
    private void showStats() {
        
    }
    
    /**
     * Changes the difficulty
     */
    private void setDifficulty() {
        // select the difficulty
        df.difficulty();
    }
    
    /**
     * Gets the difficulty
     */
    private void getDifficulty() {
        diff = df.returnDiff();
    }
    
    /**
     * Displays game information
     */
    private void showInfo() {
        
    }
}
