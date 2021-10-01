import ecs100.*;    // import ecs100 library
import java.awt.Color;      // import the color class
import java.util.HashMap;   // import the hashmap class
import java.util.ArrayList;    // import the arraylist class

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
    private int score = 0;          // user score
    private int lives;          // user lives
    private double diff;        // difficulty of the quiz
    private boolean correct;    // if answer is correct or not
    
    private boolean answerCap = true;  // if answers are capitals or not
                                       // true by default
    private String answering = "capital";       // what is being answered
    
    private double qAsked;         // no of questions asked
    private double qCorrect;       // no of questions correct
    
    private ArrayList<Integer> keys = new ArrayList<Integer>();  // keys to the answers
    
    private HashMap<Integer, String> answers =
        new HashMap<Integer, String>();  // stores countries/capitals to be used in the multichoice answers
    
    /**
     * Constructor for objects of class Gui
     */
    public GUI(){
        // add keys to arraylist
        keys.add(1);
        keys.add(2);
        keys.add(3);
        keys.add(4);
        
        // UI setup
        UI.initialise();
        UI.addButton("Play", this::playGame);
        UI.addButton("Switch Mode", this::switchMode);
        UI.addButton("Statistics", this::showStats);
        UI.addButton("Information", this::showInfo);
        UI.addButton("Quit", UI::quit);
    }
    
    /**
     * Runs the game
     */
    private void playGame() {
        // reset lives and score
        lives = 3;
        score = 0;
        
        UI.clearGraphics();
        UI.clearText(); // reset text and graphics panes
        
        this.askDifficulty(true);
        qz.getQs(diff);         // get the questions
        amount = qz.getAmount();    // store the amount of questions
        UI.println();   // line break
        
        if (lives != 0) {
            while (amount != 0) {
                // ask user the question
                this.getQuestion();
                int userAnswer = this.showQuestion();
                
                // check users answer, get score and lives
                correct = qz.scoreCalculator(userAnswer);
                score = qz.getScore();
                lives = qz.getLives();
                
                // show user the answer and their results
                this.showAnswer();
                
                // reset answers and lower amount by 1
                qz.resetAnswers();
                answers.clear();
                amount--;
                
                if (lives <= 0) {
                     break;
                }
            }
        }
        
        if (amount == 0) {
            UI.println("You finished this difficulty!");
            UI.println("Congratulations, your final score was " + score + "!");
            UI.println("Your final amount of lives was " + lives + "!");
        } else {
            UI.println("You ran out of lives :(");
            UI.println("Your final score was " + score + "!"); 
        }    
    }
    
    /** 
     * Switches the mode
     */
    private void switchMode() {
        answerCap = qz.mode(answerCap);
        
        // generate answering String
        if (answerCap == true) {
            answering = "capital";
        } else if (answerCap == false) {
            answering = "country";
        }
    }
    
    /**
     * Gets the question for the round
     */
    private void getQuestion() {
        // get q and a's for capital answers
        if (answerCap == true) {
            qz.chooseQuestion();    // choose the question to be asked
            question = qz.getCountry();  // store the question
            realAnswer = qz.getCapital();   // store the real answer
            qz.otherAnswers();  // generate the answers
            qz.assignAnswerKey();   // store the answers with their keys
            answers = qz.getAnswers();    // store the other answers
        } else if (answerCap == false) {
            // get q and a's for country answers
            qz.chooseQuestion();    // choose the question to be asked
            question = qz.getCapital();  // store the question
            realAnswer = qz.getCountry();   // store the real answer
            qz.otherAnswers();  // generate the answers
            qz.assignAnswerKey();   // store the answers with their keys
            answers = qz.getAnswers();    // store the other answers
        }
    }
    
    /**
     * Displays the question and choices on the graphics pane
     */
    private int showQuestion() {
        int answerIdx = 0;  // reset answer index
        
        // ask the questions
        if (answering == "capital") {
            UI.println("What is the capital of " + question + "?");
        } else if (answering == "country") {
            UI.println(question + " is the capital of which country?");
        }
        for (int i = 1; i <= 4; i++) {
            UI.println(i + ") " + answers.get(i - 1));
        }
        
        // obtain the users answer
        int userNum = UI.askInt("Enter number: ");
        while (!keys.contains(userNum)) {
            userNum = UI.askInt("Enter number: ");
        }
        
        return userNum;     // return users answer
    }
    
    /**
     * Displays the answer on the graphics pane
     */
    private void showAnswer() {
        UI.println();   // line break
        UI.println("The correct answer was " + realAnswer);
        
        if (correct) {
            UI.println("Your answer was correct!");
            // add one to questions correct
            qCorrect++;
        } else {
            UI.println("Your answer was wrong :(");
        }
        
        // print score and lives
        UI.println("Score: " + score);
        UI.println("Lives: " + lives);
        UI.println();   // line break
        
        // add one to questions asked
        qAsked++;
    }

    /**
     * Displays statistics
     */
    private void showStats() {
        UI.println();   // line break and clear text pane
        UI.clearText();
        
        // print stats
        UI.println("Questions asked: " + qAsked);
        UI.println("Questions correct: " + qCorrect);
        UI.println("Questions wrong: " + (qAsked - qCorrect));
        
        // print percentage correct
        double percentage = (qCorrect/qAsked) * 100;
        String percentRounded = String.format("%.02f", percentage);
        UI.println("Percentage correct: " + percentRounded + "%");
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
            diff = diffIdx;
        }
       
    }
    
    /**
     * Displays game information
     */
    private void showInfo() {
        UI.println();   // line break and clear text pane
        UI.clearText();
        
        // print information
        UI.println("To start the game press Play Game.");
        UI.println("You can then select the difficulty, and the game will start.");
        UI.println("Each game you have 3 lives, which are taken away when you get a question wrong.");
        UI.println("You get one point for every right answer.");
        UI.println("When you run out of lives, the game ends.");
        UI.println("The game also ends if you have finished the difficulty (answered all questions).");
    }
}
