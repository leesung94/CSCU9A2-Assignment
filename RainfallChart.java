import java.awt.Dimension;
import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JComponent;
import java.awt.Font;

/**
A component that manages the rainfall data and draws a chart.
 */
public class RainfallChart extends JComponent
{
    /**
     * This array holds all the rainfall data.
     */
    private double[] rainfall;

    /*
     * The maximum permitted rainfall record
     */
    private final double MAX_RAIN = 50;

    /**
     * Bar chart layout constants
     */
    private final int CHART_X = 30;
    private final int TOP_Y = 20;
    private final int GAP = 5;
    private final int BAR_HEIGHT = 10;
    private final int MAX_BAR_WIDTH = 250;
    private final double SCALE = MAX_BAR_WIDTH/MAX_RAIN;

    /**
     * Constructor: initializes the rainfall array to 0s.
     */
    public RainfallChart()
    {
        setPreferredSize(new Dimension(MAX_BAR_WIDTH + 100, TOP_Y + 2 * GAP + 31 * (GAP + BAR_HEIGHT) + 20));
        rainfall = new double[32];    // 31+1 as will not use element 0
        for (int day = 1; day <= 31; day++)
        {
            // rainfall[day] = (day*9) % 50;   // Testing only
            rainfall[day] = 0;
        }
    }

    /**
     * Add one more rainfall reading. Checks that day is sensible (1..30), and that rainfall
     * is also sensible (0..MAX_RAIN), and ignores if not.
     * @param day   the day number of the reading
     * @param rain  the amount of rainfall on that day
     */
    public void addReading(int day, double rain)
    {
        if (day < 1 || day > 31 || rain < 0 || rain > MAX_RAIN )
        {
            return;
        }
        rainfall[day] = rain;
        repaint();
    }

    /**
     * Redraw the whole rainfall record on the given graphics area
     */
    public void paintComponent(Graphics g)
    {  
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, MAX_BAR_WIDTH + 100, TOP_Y + 2 * GAP + 31 * (GAP + BAR_HEIGHT) + 20);
        
        g.setColor(Color.BLACK);
        g.setFont(new Font("", Font.BOLD, 12));
        g.drawString("Rainfall for the month:", 5, TOP_Y);

        int y = TOP_Y + 2 * GAP;

        for (int day = 1; day <= 31; day++)
        {
            int barWidth = (int) (rainfall[day] * SCALE);
            g.drawString(Integer.toString(day), 5, y + BAR_HEIGHT);
            g.fillRect(CHART_X, y, barWidth, BAR_HEIGHT);
            g.drawString(rainfall[day] + " cm", CHART_X + barWidth + 5, y + BAR_HEIGHT);
            y = y + BAR_HEIGHT + GAP;
        }
    }
}
