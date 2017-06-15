package alternateCodeGuus;

import javafx.scene.layout.GridPane;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;

public class kluisViewGuus extends TilePane
{
	slotViewGuus[]svArray = new slotViewGuus[9];
	private static final double ELEMENT_SIZE = 100;
	private static final double GAP = ELEMENT_SIZE / 10;
	
	public kluisViewGuus(){
		//GridPane slotenPane = new GridPane();
				
		this.setPrefColumns(3);
		this.setPrefRows(3);
		this.setHgap(GAP);
        this.setVgap(GAP);
		
		for(int i = 0; i < svArray.length; i++){
			svArray[i] = new slotViewGuus();
			this.getChildren().add(0,svArray[i]);
		}		
	}
}
