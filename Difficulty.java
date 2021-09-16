import javax.swing.*;       // import combo box
import java.awt.event.*;    // import action listener and event

/**
 * Selects the dificulty in a combo box
 *
 * @author Simon Lee
 * @version 16/09/2021
 */
public class Difficulty
{
    // fields
    JFrame f;
    private String difficulty[] = {"Easy", "Medium", "Hard", "Expert"}; // array with all difficulties
    private final JComboBox cb = new JComboBox(difficulty);   // add difficulties to the box
    
    /**
     * Coonstructs the combo box
     */
    public void difficulty() {
        f = new JFrame("Difficulty");   // create the box
        cb.setBounds(50, 100, 90, 20);   // dimensions of the dropdown
        
        // label
        final JLabel label = new JLabel();
        label.setHorizontalAlignment(JLabel.CENTER);    // center the label
        label.setSize(400, 100);    // label size
        
        // button for the box
        JButton b = new JButton("Select");
        b.setBounds(200, 100, 75, 20);  // dimensions of the button
        
        // add elements to the box
        f.add(cb);
        f.add(b);
        f.add(label);
        
        // show the box
        f.setLayout(null);
        f.setSize(350,350);
        f.setVisible(true);
        
        // action listener
        b.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String text = "Difficulty Selected: " + cb.getItemAt(cb.getSelectedIndex());
                label.setText(text);
            }
        });
    }
    
    /**
     * Return the difficulty
     */
    public String returnDiff() {
        String difficulty = cb.getSelectedItem().toString();
        return difficulty;
    }
    
    /**
     * Hide the combo box
     */
    public void hideBox() {
        f.setVisible(false);
    }
}
