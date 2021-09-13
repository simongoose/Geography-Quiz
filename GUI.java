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
    Quiz qz = new Quiz();
    
    private String question;    // the question to be asked
    private String realAnswer;  // the real answer to the question
    
    private HashMap<String, String> otherAnswers =
        new HashMap<String, String>();  // stores countries/capitals to be used in the multichoice answers
    
    /**
     * Constructor for objects of class Gui
     */
    public GUI(){
        UI.initialise();
        UI.addButton("Play", this::playGame);
        UI.addButton("Statistics", this::showStats);
        UI.addButton("Information", this::showInfo);
        UI.addSlider("Difficulty",1, 4, 1, this::setDifficulty);
        UI.addButton("Quit", UI::quit);
    }
    /**
     * Runs the game
     */
    private void playGame() {
        qz.chooseQuestion();    // choose the question to be asked
        question = qz.getCountry();  // store the question
        realAnswer = qz.getCapital();   // store the real answer
        
        otherAnswers = qz.getOtherAnswers();    // store the other answers
    }
    
    /**
     * Displays the question and choices on the graphics pane
     */
    private void showQuestion() {
        
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
    private void setDifficulty(double diffIdx) {
        qz.getQs(diffIdx);
    }
    
    /**
     * Displays game information
     */
    private void showInfo() {
        
    }
}
