package IsolatedCodeGuus;


import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;

public class slotView extends BorderPane
{	
	Button buttonRight;
	Button buttonLeft;
	Label huidigNummer;
	
	public slotView(){		
		
		buttonLeft = new Button("<--");
		buttonRight = new Button("-->");
		huidigNummer = new Label("test");
		
		this.setRight(buttonRight);
		this.setLeft(buttonLeft);
		this.setCenter(huidigNummer);		
		
	
	}
}
