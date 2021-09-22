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
    
    private String question;    // the question to be asked
    private String realAnswer;  // the real answer to the question
    private int amount;         // the amount of questions to be asked
    private String diff;        // difficulty of the quiz
    private boolean broken;     // if difficulty has been selected or not
    
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
        UI.addButton("Quit", UI::quit);
    }
    
    /**
     * Runs the game
     */
    private void playGame() {
        askDifficulty(true);
        qz.getQs(diff);         // get the questions
        amount = qz.getAmount();    // store the amount of questions
        
        this.getQuestion();
        this.showQuestion();
        }
    
    
    /**
     * Gets the question for the round
     */
    private void getQuestion() {
        qz.chooseQuestion();    // choose the question to be asked
        question = qz.getCountry();  // store the question
        realAnswer = qz.getCapital();   // store the real answer
        qz.otherAnswers();  // generate the answers
        qz.assignAnswerKey();   // store the answers with their keys
        answers = qz.getAnswers();    // store the other answers
    }
    
    /**
     * Displays the question and choices on the graphics pane
     */
    private void showQuestion() {
        int answerIdx = 0;  // reset answer index
        
        // ask the questions
        UI.println("What is the capital of " + question + "?");
        for (int i = 1; i <= 4; i++) {
            UI.println(i + ") " + answers.get(i - 1));
        }
        
        // obtain the users answer
        int userNum = UI.askInt("Enter number: ");
        while (!keys.contains(userNum)) {
            userNum = UI.askInt("Enter number: ");
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
     * Asks user for the difficulty
     */
    private void askDifficulty(boolean allowed) {
        while (allowed) {
            // ask difficulty
            UI.println("Choose difficulty:");
            UI.println("1) Easy");
            UI.println("2) Medium");
            UI.println("3) Hard");
            UI.println("4) Expert");
            double diffIdx = UI.askDouble("Enter number: ");
            allowed = qz.setDifficulty(diffIdx);
        }
    }
    
    /**
     * Displays game information
     */
    private void showInfo() {
        
    }
}
