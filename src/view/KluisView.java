package view;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import model.Kluis;
import model.Slot;

public class KluisView extends GridPane {

	public KluisView(Kluis kluis) {
		kluis.setKluisView(this);
		this.createSloten(kluis);
	}

	public void createSloten(Kluis kluis) {
		int horizontaal = 0;
		int verticaal = 0;

		for (Slot slot : kluis.getSloten()) {
			ImageView button = new ImageView(this.createSlotButton(slot.getSymbolen().get(0).getIcoon()));
			this.add(button, horizontaal, verticaal, 0, 0);
		}
	}

	public Image createSlotButton(String icoon) {
		Image button = new Image("/symbolen/" + icoon);
		return button;
	}

	public void update() {

	}

}
