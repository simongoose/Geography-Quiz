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
                                       
    private boolean gameRunning = false;    // if the game is currently running
                                            // false by default
    private String answering = "capital";       // what is being answered
    
    private double qAsked;         // no of questions asked
    private double qCorrect;       // no of questions correct
    
    private int userAnswer;     // user answer
    private boolean answered = false;  // if user has answered yet
    
    // highscores for each difficulty
    private double bestEasy = 0;
    private double bestMed = 0;
    private double bestHard = 0;
    private double bestExp = 0;
    
    private ArrayList<Integer> keys = new ArrayList<Integer>();  // keys to the answers
    
    private HashMap<Integer, String> answers =
        new HashMap<Integer, String>();  // stores countries/capitals to be used in the multichoice answers
        
    // mouse values
    private double currentX = 0;
    private double currentY = 0;
    private String currentAction = "";
    
    // constants for drawing on UI
    private double BORDER_LEFT = 0;
    private double BORDER_WIDTH = 500;
    private double BORDER_TOP = 0;
    private double BORDER_HEIGHT = 500;
    
    private double Q_LINE_Y = 100;
    private double I_LINE_Y = 300;
    private double MID_LINE_X = 250;
    private double A1_LINE_Y = 380;
    private double A2_LINE_Y = 460;
    
    private double PH_LEFT = 100;
    private double PH_TOP = 100;
    private double PH_WIDTH = 300;
    private double PH_HEIGHT = 200;

    
    
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
        UI.setDivider(0.4);
        UI.addButton("Play", this::playGame);
        UI.addButton("Switch Mode", this::switchMode);
        UI.addButton("Statistics", this::showStats);
        UI.addButton("Information", this::showInfo);
        UI.setMouseListener(this::doMouse);
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
        
        // ask question
        if (lives != 0) {
            while (amount != 0) {
                // set game to running and reset answered
                gameRunning = true;
                answered = false;
                currentX = 0;
                currentY = 0;
                currentAction = "";
                
                // ask user the question
                this.getQuestion();
                this.displayQuestion();
                
                while (answered == false) {
                    checkAnswer();
                }

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
                
                // wait for a few seconds
                UI.sleep(3000);
                
                // reset the graphics and text panes
                UI.clearGraphics();
                UI.clearText();
                
                
                if (lives <= 0) {
                     break;
                }
            }
        }
        
        // game is no longer running
        gameRunning = false;
        
        // go to game end screen
        this.gameEnd();
        
        // if user 'wins'
        if (amount == 0) {
            UI.println("You finished this difficulty!");
            UI.println("Congratulations, your final score was " + score + "!");
            UI.println("Your final amount of lives was " + lives + "!");
        } else {    // if user loses
            UI.println("You ran out of lives :(");
            UI.println("Your final score was " + score + "!"); 
        }    

        // add highscores
        if (diff == 1) {
            if (score > bestEasy) {
                bestEasy = (int)score;
                UI.println("You got a new high score in Easy difficulty of " + bestEasy);
            }
        } else if (diff == 2) {
            if (score > bestMed) {
                bestMed = (int)score;
                UI.println("You got a new high score in Medium difficulty of " + bestMed);
            }
        } else if (diff == 3) {
            if (score > bestHard) {
                bestHard = (int)score;
                UI.println("You got a new high score in Hard difficulty of " + bestHard);
            }
        } else if (diff == 4) {
            if (score > bestExp) {
                bestExp = (int)score;
                UI.println("You got a new high score in Expert difficulty of " + bestExp);
            }
        }
    }
    
    /** 
     * Switches the mode
     */
    private void switchMode() {
        if (gameRunning == false) {
            answerCap = qz.mode(answerCap);
            // generate answering String
            if (answerCap == true) {
                answering = "capital";
            } else if (answerCap == false) {
                answering = "country";
            }
        } else {
            UI.println("\nYou cannot change mode while in a game!");
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
     * Displays the answer on the graphics pane
     */
    private void showAnswer() {
        // fields
        int correctIdx = 0;
        
        // get the id of the right answer
        for(int i = 0; i < 4; i++) {
            if (answers.get(i) == realAnswer) {
                correctIdx = i;
            }
        }
        
        // change color of text on GUI
        UI.setColor(Color.green);
        if (correctIdx == 0) {
            UI.drawString(answers.get(0), BORDER_LEFT + 5, A1_LINE_Y - 15);
            UI.setColor(Color.red);
            UI.drawString(answers.get(1), MID_LINE_X + 5, A1_LINE_Y - 15);
            UI.drawString(answers.get(2), BORDER_LEFT + 5, A2_LINE_Y - 15);
            UI.drawString(answers.get(3), MID_LINE_X + 5, A2_LINE_Y - 15);
        } else if (correctIdx == 1) {
            UI.drawString(answers.get(1), MID_LINE_X + 5, A1_LINE_Y - 15);
            UI.setColor(Color.red);
            UI.drawString(answers.get(0), BORDER_LEFT + 5, A1_LINE_Y - 15);
            UI.drawString(answers.get(2), BORDER_LEFT + 5, A2_LINE_Y - 15);
            UI.drawString(answers.get(3), MID_LINE_X + 5, A2_LINE_Y - 15);
        } else if (correctIdx == 2) {
            UI.drawString(answers.get(2), BORDER_LEFT + 5, A2_LINE_Y - 15);
            UI.setColor(Color.red);
            UI.drawString(answers.get(0), BORDER_LEFT + 5, A1_LINE_Y - 15);
            UI.drawString(answers.get(1), MID_LINE_X + 5, A1_LINE_Y - 15);
            UI.drawString(answers.get(3), MID_LINE_X + 5, A2_LINE_Y - 15);
        } else if (correctIdx == 3) {
            UI.drawString(answers.get(3), MID_LINE_X + 5, A2_LINE_Y - 15);
            UI.setColor(Color.red);
            UI.drawString(answers.get(0), BORDER_LEFT + 5, A1_LINE_Y - 15);
            UI.drawString(answers.get(1), MID_LINE_X + 5, A1_LINE_Y - 15);
            UI.drawString(answers.get(2), BORDER_LEFT + 5, A2_LINE_Y - 15);
        }
        
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
        // reset answered, x, y and useranswer
        answered = false;
        userAnswer = 0;
        currentX = 0;
        currentY = 0;
    }
    
    /**
     * Display the question
     */
    private void displayQuestion() {
        // border
        this.border();

        // ask questions and draw images
        if (answering == "capital") {
            // image
            String img = "img//" + question.toLowerCase() + ".jpg";
            UI.drawImage(img, PH_LEFT, PH_TOP, PH_WIDTH, PH_HEIGHT);
            
            
            // print border
            this.lines();
            
            // ask question
            UI.setFontSize(20);
            UI.drawString("What is the capital of " + question + "?", BORDER_LEFT + 5, Q_LINE_Y - 15);
            // answers
            UI.drawString(answers.get(0), BORDER_LEFT + 5, A1_LINE_Y - 15);
            UI.drawString(answers.get(1), MID_LINE_X + 5, A1_LINE_Y - 15);
            UI.drawString(answers.get(2), BORDER_LEFT + 5, A2_LINE_Y - 15);
            UI.drawString(answers.get(3), MID_LINE_X + 5, A2_LINE_Y - 15);
            
        } else if (answering == "country") {
            // image
            String img = "img//" + realAnswer.toLowerCase().trim() + ".jpg";
            UI.drawImage(img, PH_LEFT, PH_TOP, PH_WIDTH, PH_HEIGHT);
            
            // print border
            this.lines();
            
            // ask question
            UI.setFontSize(20);
            UI.drawString(question + " is the capital of which country?", BORDER_LEFT + 5, Q_LINE_Y - 15);
            // answers
            UI.drawString(answers.get(0), BORDER_LEFT + 5, A1_LINE_Y - 15);
            UI.drawString(answers.get(1), MID_LINE_X + 5, A1_LINE_Y - 15);
            UI.drawString(answers.get(2), BORDER_LEFT + 5, A2_LINE_Y - 15);
            UI.drawString(answers.get(3), MID_LINE_X + 5, A2_LINE_Y - 15);
        }
    }
    
    /**
     * Checks the answer
     */
    private void checkAnswer() {
        // check which box has been clicked
        userAnswer = 0;
        
        if ((currentAction.equalsIgnoreCase("released")) && (currentX < MID_LINE_X) && (A1_LINE_Y > currentY) && (currentY > I_LINE_Y)) {
            userAnswer = 1; // top left
            // change answered as question has been answered
            answered = true;
        }
        if ((currentAction.equalsIgnoreCase("released")) && (currentX > MID_LINE_X) && (currentX < BORDER_WIDTH) && (A1_LINE_Y > currentY) && (currentY > I_LINE_Y)) {
            userAnswer = 2; // top right
            // change answered as question has been answered
            answered = true;
        }
        if ((currentAction.equalsIgnoreCase("released")) && (currentX < MID_LINE_X) && (A1_LINE_Y < currentY) && (currentY < A2_LINE_Y)) {
            userAnswer = 3; // bottom left
            // change answered as question has been answered
            answered = true;
        } 
        if ((currentAction.equalsIgnoreCase("released")) && (currentX > MID_LINE_X) && (currentX < BORDER_WIDTH) &&(A1_LINE_Y < currentY) && (currentY < A2_LINE_Y)) {
            userAnswer = 4; // bottom right
            // change answered as question has been answered
            answered = true;
        }

    }
    
    /**
     * Mouse Listener
     */
    private void doMouse(String action, double x, double y) {
        // set values
        currentX = x;
        currentY = y;
        currentAction = action;
        
        // run appropriate methods if clicked
        if ((action.equalsIgnoreCase("clicked") && (gameRunning == true))) {
            checkAnswer();
        }
    }
    
    /**
     * Game end screen
     */
    private void gameEnd() {
        // reset color
        UI.setColor(Color.black);
        
        // draw strings
        if (lives == 0) {
            UI.drawString("Game over, you're out of lives!", BORDER_LEFT + 5, Q_LINE_Y - 15);
            UI.drawString("Score: " + score, BORDER_LEFT + 5, Q_LINE_Y + 15);
        } else {
            UI.drawString("Congratulations, difficulty has been completed!", BORDER_LEFT + 5, Q_LINE_Y - 15);
            UI.drawString("Score: " + score, BORDER_LEFT + 5, Q_LINE_Y + 15);
            UI.drawString("Lives leftover: " + lives, BORDER_LEFT + 5, Q_LINE_Y + 45);
        }
        
        // display highscores if there are any
        if (diff == 1) {
            if (score > bestEasy) {
                bestEasy = score;
                UI.drawString("You got a new high score in Easy difficulty of " + (int)bestEasy, BORDER_LEFT + 5, Q_LINE_Y + 75);
            }
        } else if (diff == 2) {
            if (score > bestMed) {
                bestMed = score;
                UI.drawString("You got a new high score in Medium difficulty of " + (int)bestMed, BORDER_LEFT + 5, Q_LINE_Y + 75);
            }
        } else if (diff == 3) {
            if (score > bestHard) {
                bestHard = score;
                UI.drawString("You got a new high score in Hard difficulty of " + (int)bestHard, BORDER_LEFT + 5, Q_LINE_Y + 75);
            }
        } else if (diff == 4) {
            if (score > bestExp) {
                bestExp = score;
                UI.drawString("You got a new high score in Expert difficulty of " + (int)bestExp, BORDER_LEFT + 5, Q_LINE_Y + 75);
            }
        }
    }

    /**
     * Displays statistics
     */
    private void showStats() {
        // clear the graphics window
        UI.clearGraphics();
        UI.clearText();
        
        // calculate stats
        double percentage = (qCorrect/qAsked) * 100;
        String percentRounded = String.format("%.02f", percentage);
        
        // constants
        double TITLE_BOTTOM = 90;
        double EASY_BOTTOM = 150;
        double MEDIUM_BOTTOM = 200;
        double HARD_BOTTOM = 250;
        double EXPERT_BOTTOM = 300;
        
        double PERCENTAGE_BOTTOM = 400;
        
        // set font color and size
        UI.setColor(Color.black);
        UI.setFontSize(73);
        
        // print border
        this.border();
        
        // print out stats
        UI.drawString("HIGHSCORES", BORDER_LEFT + 5, TITLE_BOTTOM);
        
        UI.setFontSize(45);
        UI.drawString("Easy", BORDER_LEFT + 5, EASY_BOTTOM);
        UI.drawString("Medium", BORDER_LEFT + 5, MEDIUM_BOTTOM);
        UI.drawString("Hard", BORDER_LEFT + 5, HARD_BOTTOM);
        UI.drawString("Expert", BORDER_LEFT + 5, EXPERT_BOTTOM);
        
        UI.drawString("" + (int)bestEasy, BORDER_WIDTH - 70, EASY_BOTTOM);
        UI.drawString("" + (int)bestMed, BORDER_WIDTH - 70, MEDIUM_BOTTOM);
        UI.drawString("" + (int)bestHard, BORDER_WIDTH - 70, HARD_BOTTOM);
        UI.drawString("" + (int)bestExp, BORDER_WIDTH - 70, EXPERT_BOTTOM);
        
        UI.setFontSize(24);
        UI.drawString("Correct/Asked", BORDER_LEFT + 5, PERCENTAGE_BOTTOM);
        if (qAsked == 0) {
            UI.drawString((int)qCorrect + "/" + (int)qAsked + " (00.00%)", BORDER_WIDTH - 230, PERCENTAGE_BOTTOM);
        } else {
            UI.drawString((int)qCorrect + "/" + (int)qAsked + " (" + percentRounded + "%)", BORDER_WIDTH - 230, PERCENTAGE_BOTTOM);
        }
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
            // let user know if their answer was invalid
            if (diffIdx > 4 || diffIdx < 1) {
                UI.println("Please enter 1, 2, 3 or 4.\n");
            }
            allowed = qz.setDifficulty(diffIdx);
            diff = diffIdx;
        }
       
    }
    
    /**
     * Displays game information
     */
    private void showInfo() {
        // clear the graphics window
        UI.clearGraphics();
        UI.clearText();
        
        // constants
        int fontSize = 20;
        
        this.border();  // print border
        
        // print information
        UI.setFontSize(fontSize);
        UI.drawString("- To start the game press Play Game", BORDER_LEFT + 5, 1 * fontSize);
        
        UI.drawString("- You can then select the difficulty, and the game", BORDER_LEFT + 5, 3 * fontSize);
        UI.drawString("will start", BORDER_LEFT + 5, 4 * fontSize);
        
        UI.drawString("- Each game you have 3 lives, one is taken away when", BORDER_LEFT + 5, 6 * fontSize);
        UI.drawString("you get a question wrong", BORDER_LEFT + 5, 7 * fontSize);
        
        UI.drawString("- You get one point for every right answer", BORDER_LEFT + 5, 9 * fontSize);
        
        UI.drawString("- When you run out of lives, the game ends", BORDER_LEFT + 5, 10 * fontSize);
        
        UI.drawString("- The game also ends if you have finished the", BORDER_LEFT + 5, 12 * fontSize);
        UI.drawString("difficulty (answered all questions)", BORDER_LEFT + 5, 13 * fontSize);
        
        UI.drawString("- The switch mode button lets you choose between", BORDER_LEFT + 5, 15 * fontSize);
        UI.drawString("'country to capital' and 'capital to country'", BORDER_LEFT + 5, 16 * fontSize);
        
        UI.drawString("- Statistics shows you various stats and highscores", BORDER_LEFT + 5, 18 * fontSize);
    }
    
    /**
     * Prints the lines for questions
     */
    private void lines() {
        // lines
        UI.drawLine(BORDER_LEFT, Q_LINE_Y, BORDER_WIDTH, Q_LINE_Y);
        UI.drawLine(BORDER_LEFT, I_LINE_Y, BORDER_WIDTH, I_LINE_Y);
        UI.drawLine(BORDER_LEFT, A1_LINE_Y, BORDER_WIDTH, A1_LINE_Y);
        UI.drawLine(BORDER_LEFT, A2_LINE_Y, BORDER_WIDTH, A2_LINE_Y);
        UI.drawLine(MID_LINE_X, I_LINE_Y, MID_LINE_X, A2_LINE_Y);
    }
    
    /**
     * Prints the border of the game
     */
    private void border() {
        UI.setColor(Color.black);
        UI.setLineWidth(5);
        UI.drawRect(BORDER_LEFT, BORDER_TOP, BORDER_WIDTH, BORDER_HEIGHT);
    }
}
