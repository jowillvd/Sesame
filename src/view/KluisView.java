package view;

import java.io.File;

import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.Pane;
import javafx.scene.layout.TilePane;
import javafx.scene.paint.Color;
import model.Kluis;
import model.Slot;

public class KluisView extends Pane {

	private static final double ELEMENT_SIZE = 100;
    private static final double GAP = ELEMENT_SIZE / 10;
    private final int kolommen = 3;
	private final int rijen = 3;

	public KluisView() {
		
		TilePane slotPane = new TilePane();
			ImageView achtergrond = new ImageView();
			achtergrond.setImage(new Image(new File("src/resources/layout/achtergrond_600.png").toURI().toString()));
			slotPane.setPrefColumns(3);
			slotPane.setPrefColumns(3);
			//slotPane.setBackground(new Background(new BackgroundFill(Color.GREEN, null, null)));
		this.getChildren().addAll(slotPane, achtergrond);
	}

	public void createSloten(Kluis kluis) {
		this.getChildren().clear();
		int positie = 0;
        for (int i = 0; i < this.kolommen; i++) {
            for (int j = 0; j < this.rijen; j++) {
            	//ImageView button = createSlotButton(kluis.selectSlot(positie));
            	//* //Debug
            	Label button = new Label(kluis.selectSlot(positie).getSymbolen().get(0).getIcoon());
            	// */
                this.getChildren().add(button);
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
		this.getChildren().get(positie);
	}

}
