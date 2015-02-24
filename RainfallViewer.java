import javax.swing.JFrame;

/**
   This application allows the user to enter and edit daily rainfall figures for 
   one month, and displays them as a bar chart. 
*/
public class RainfallViewer
{  
  /**
   * Frame coordinate constants
   */
  private static final int FRAME_X = 200;
  private static final int FRAME_Y = 200;
   
  /**
   * The main launcher method
   * @param args Unused
   */
  public static void main(String[] args)
   {  
      JFrame frame = new RainfallFrame();
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      frame.setLocation(FRAME_X, FRAME_Y);
      frame.setVisible(true);
   }
}
