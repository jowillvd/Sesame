package IsolatedCodeGuus;

import javafx.scene.control.Label;
import javafx.scene.layout.TilePane;

public class kluisView extends TilePane
{
	private static final double ELEMENT_SIZE = 100;
	private static final double GAP = ELEMENT_SIZE / 10;
	public Label melding;
	
	public kluisView(){
		this.setPrefColumns(3);
		this.setPrefRows(3);
		this.setHgap(GAP);
        this.setVgap(GAP);
        
       
        
	}
}
