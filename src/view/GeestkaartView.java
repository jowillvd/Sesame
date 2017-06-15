package view;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.TilePane;
import model.Geestkaart;

public class GeestkaartView extends TilePane{

	private static final double ELEMENT_SIZE = 100;
    private static final double GAP = ELEMENT_SIZE / 10;
    private final int kolommen = 3;
	private final int rijen = 3;

	public GeestkaartView(Geestkaart geestkaart) {
		this.setPrefColumns(this.kolommen);
		this.setPrefRows(this.rijen);
		this.setHgap(GAP);
        this.setVgap(GAP);
		geestkaart.setGeestkaartView(this);
		this.createGeestkaart(geestkaart);
	}

	public void createGeestkaart(Geestkaart geestkaart) {
		this.getChildren().clear();
		int positie = 0;
        for (int i = 0; i < this.kolommen; i++) {
            for (int j = 0; j < this.rijen; j++) {
            	Label symbool = new Label(geestkaart.getSymbool(positie).getIcoon());
        		this.getChildren().add(symbool);

                positie++;
            }
        }
	}

	public void update() {
		this.createGeestkaart();
	}

	public void createSymbolen() {

	}

}
