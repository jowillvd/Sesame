package alternateCodeGuus;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;

public class slotViewGuus extends BorderPane
{
	int grootteArray = 6;	
	
	
	public slotViewGuus(){
		slotModelGuus sModel = new slotModelGuus(grootteArray);
		
		Button buttonLeft = new Button("<--");
		Button buttonRight = new Button("-->");
		Label huidigNummer = new Label(Integer.toString(sModel.returnPositie()));
		
		this.setRight(buttonRight);
		this.setLeft(buttonLeft);
		this.setCenter(huidigNummer);
		
		
		
		buttonRight.setOnAction(e ->{
			huidigNummer.setText(Integer.toString(sModel.draaiRechts(grootteArray)));
		});
		buttonLeft.setOnAction(e ->{
			huidigNummer.setText(Integer.toString(sModel.draaiLinks(grootteArray)));
		});
	}
	
}
