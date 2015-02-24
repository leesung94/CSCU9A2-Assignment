import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import java.awt.Dimension;
import javax.swing.JOptionPane;

/**
A frame that shows the user interface for a the rainfall 
recording and displaying application.
 */
public class RainfallFrame extends JFrame
{
    /**
     * Configuration constants for the frame size
     */
    private static final int FRAME_WIDTH = 500;
    private static final int FRAME_HEIGHT = 700;

    /*
     * The maximum permitted rainfall record
     */
    private final double MAX_RAIN = 50;

    /**
     * Hold the GUI components, including the rainfall chart component
     * that holds the actual rainfall data.
     */
    private RainfallChart rainfallChart;
    private JTextField dayEntryField;
    private JTextField rainfallEntryField;
    private JButton addReadingButton;

    /**
     * RainfallFrame constructor
     */
    public RainfallFrame()
    {  
        rainfallChart = new RainfallChart();

        createGUIPanel();

        setSize(FRAME_WIDTH, FRAME_HEIGHT);
        setTitle("Rainfall chart. Student no: 1234567");
    }

    /**
     * Helper method to build the GUI
     */
    private void createGUIPanel()
    {
        // Day entry label and text field
        JLabel dayEntryLabel = new JLabel("New reading:  Day: ");
        dayEntryField = new JTextField(5); 
        dayEntryField.setText("");

        // Rainfall entry label and text field
        JLabel rainfallEntryLabel = new JLabel("Rainfall: ");
        rainfallEntryField = new JTextField(8);
        rainfallEntryField.setText("");
        JLabel rainfallUnitsLabel = new JLabel("cm");

        // Button to add new entry
        addReadingButton = new JButton("Add reading"); 
        ActionListener listener = new AddReadingListener();
        addReadingButton.addActionListener(listener);

        // And now build the whole panel
        JPanel panel = new JPanel();
        panel.add(dayEntryLabel);
        panel.add(dayEntryField);
        panel.add(rainfallEntryLabel);
        panel.add(rainfallEntryField);
        panel.add(rainfallUnitsLabel);
        panel.add(addReadingButton);
        panel.add(rainfallChart);
        add(panel);
    }

    /**
     * Action listener class for adding a new rainfall to the record
     * and updating the display when the add reading button is clicked.
     * Pops up a warning and ignores the action if the numbers entered are not valid.
     */
    class AddReadingListener implements ActionListener
    {
        public void actionPerformed(ActionEvent event)
        {
            try 
            {
                // Fetch the new reading details
                int newDay = Integer.parseInt(dayEntryField.getText());
                double newRainfall = Double.parseDouble(rainfallEntryField.getText());
                // Check that the inputs are sensible, warning and ignore if not
                if (newDay < 1 || newDay > 31 || newRainfall < 0 || newRainfall > MAX_RAIN)
                {
                    JOptionPane.showMessageDialog(rainfallChart, "Input out of range - please correct");
                    return;
                }

                // Clear the inputs
                dayEntryField.setText("");
                rainfallEntryField.setText("");
                // Add to the chart
                rainfallChart.addReading(newDay, newRainfall);
            }
            catch (NumberFormatException e) 
            {
                // Invalid numerical input: warn and ignore
                JOptionPane.showMessageDialog(rainfallChart, "Non-numerical input - please correct");
                return;
            }

        }            
    }
}
