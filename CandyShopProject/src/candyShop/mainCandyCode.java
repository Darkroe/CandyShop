package candyShop;

import java.io.IOException;
import javax.swing.JFrame;

public class mainCandyCode {

	public static void main(String[] args) throws IOException {
		// Create frame
	    clientGUI guiFrame = new clientGUI();

	    // Terminate program when window closes
	    guiFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	    // Resize window to fit components
	    guiFrame.pack();

	    // Display window
	    guiFrame.setVisible(true);

	}

}
