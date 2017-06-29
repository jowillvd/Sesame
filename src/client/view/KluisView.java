package client.view;

import java.io.File;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import javafx.application.Platform;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.TilePane;
import javafx.scene.paint.Color;

import server.SesameServerInterface;

import client.SesameObserver;
import client.controller.KluisController;

public class KluisView extends UnicastRemoteObject implements ViewInterface,
		SesameObserver {

	private static final long serialVersionUID = 1L;
	private KluisController controller;
	private TilePane pane = new TilePane();
	private int slotIndex;
	private boolean enabled;

	public KluisView(KluisController controller, SesameServerInterface server) throws RemoteException {
		this.controller = controller;
		this.controller.setObserver(this, 2);

		pane.setPrefColumns(3);
		pane.setPrefRows(3);
		pane.setHgap(10);
		pane.setVgap(10);

		Image image = new Image(new File("src/client/resources/layout/achtergrond_610.png").toURI().toString());
		pane.setBackground(new Background(new BackgroundImage(image, null, null, null, null)));

		this.createSloten(server.getKluis().getSloten());
		this.setEnabled(false);
	}

	public void createSloten(String[] sloten) {
		slotIndex = 0;
		pane.getChildren().clear();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
            	BorderPane slotPane = new BorderPane();

            	Button buttonLinks = new Button();
            	buttonLinks.setId(String.valueOf(slotIndex));
            	buttonLinks.setGraphic(createButton("layout/knop_Links"));
            	buttonLinks.setPrefSize(20, 80);
            	buttonLinks.setBackground(new Background(new BackgroundFill(Color.rgb(0, 0, 0, 0), null, null)));
            	buttonLinks.setTranslateY(50);
            	buttonLinks.setOnAction(e-> {
            		this.controller.draaiLinks(Integer.parseInt(buttonLinks.getId()));
            	});

            	ImageView button = createButton("models/slot_" + sloten[slotIndex]);
            	button.setTranslateY(50);

            	Button buttonRechts = new Button();
            	buttonRechts.setId(String.valueOf(slotIndex));
            	buttonRechts.setGraphic(createButton("layout/knop_Rechts"));
            	buttonRechts.setPrefSize(20, 80);
            	buttonRechts.setBackground(new Background(new BackgroundFill(Color.rgb(0, 0, 0, 0), null, null)));
            	buttonRechts.setTranslateY(50);
            	buttonRechts.setOnAction(e-> {
            		this.controller.draaiRechts(Integer.parseInt(buttonRechts.getId()));
            	});

            	slotPane.setLeft(buttonLinks);
            	slotPane.setCenter(button);
            	slotPane.setRight(buttonRechts);

                pane.getChildren().add(slotPane);
                slotIndex++;
            }
        }
	}

	public ImageView createButton(String pad) {
		ImageView graphic = new ImageView();
		graphic.setImage(new Image(new File("src/client/resources/" + pad + ".png").toURI().toString()));
		return graphic;
	}

	public void updateSlot(String slot, int positie) {
		BorderPane slotPane = (BorderPane) pane.getChildren().get(positie);
		ImageView button = createButton("models/slot_" + slot);
		button.setTranslateY(50);
		slotPane.setCenter(button);
	}

	@Override
	public void update(SesameServerInterface server) throws RemoteException {
		Platform.runLater(
				() -> {
					try {
						String slot = server.getKluis().getActiefSlot();
						int positie = server.getKluis().getActiefSlotPositie();
						this.updateSlot(slot, positie);
						this.controller.setServer(server);
					} catch (RemoteException e) {
						e.printStackTrace();
					}
				}
		);
	}

	@Override
	public boolean isEnabled() throws RemoteException {
		return this.enabled;
	}

	@Override
	public void setEnabled(boolean enabled) throws RemoteException {
		this.enabled = enabled;
	}

	@Override
	public Pane getPane() {
		return this.pane;
	}

	@Override
	public void updateMode() throws RemoteException {
		Platform.runLater(
				() -> {
					this.controller.openKluis();
				}
		);
	}

}
