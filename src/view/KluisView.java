package view;

import controller.KluisController;
import host.KluisInterface;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.TilePane;
import model.Kluis;
import model.Slot;

public class KluisView extends TilePane {

	private static final double ELEMENT_SIZE = 100;
    private static final double GAP = ELEMENT_SIZE / 10;
    private final int kolommen = 3;
	private final int rijen = 3;

	public KluisView(Kluis kluis) {
		this.setPrefColumns(this.kolommen);
		this.setPrefRows(this.rijen);
		this.setHgap(GAP);
        this.setVgap(GAP);
		kluis.setKluisView(this);
		this.createSloten(kluis);
	}

	public KluisView(KluisController kluisController, KluisInterface kluis) {
		// TODO Auto-generated constructor stub
	}

	public void createSloten(Kluis kluis) {
		this.getChildren().clear();
		int positie = 0;
        for (int i = 0; i < this.kolommen; i++) {
            for (int j = 0; j < this.rijen; j++) {
            	//ImageView button = createSlotButton(kluis.selectSlot(positie));
            	//* //Debug
            	BorderPane slotPane = new BorderPane();
            	Button buttonLinks = new Button("<--");
            	Label button = new Label(kluis.selectSlot(positie).getSymbolen().get(0).getIcoon());
            	Button buttonRechts = new Button("-->");

            	slotPane.setLeft(buttonLinks);
            	slotPane.setCenter(button);
            	slotPane.setRight(buttonRechts);

                this.getChildren().add(slotPane);
                positie++;
            }
        }

	}

	public ImageView createSlotButton(Slot slot) {
		Image image = new Image("symbolen/" + slot.getSymbolen().get(0).getIcoon());
		ImageView button = new ImageView(image);
		return button;
	}

	public void update(String icoon, int positie) {
		this.getChildren().get(positie)...;
	}

}
