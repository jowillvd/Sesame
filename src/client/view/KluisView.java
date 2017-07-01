package client.view;

import java.io.File;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.TilePane;
import javafx.scene.paint.Color;

import server.SesameServerInterface;
import server.model.Slot;

import client.SesameObserver;
import client.controller.KluisController;

public class KluisView extends UnicastRemoteObject implements ViewInterface,
		SesameObserver {

	private static final long serialVersionUID = 1L;
	private KluisController controller;
	private StackPane pane = new StackPane();
	private TilePane slotenPane = new TilePane();
	private int slotIndex;
	private boolean enabled = false;

	public KluisView(KluisController controller, SesameServerInterface server) throws RemoteException {
		this.controller = controller;
		this.controller.setObserver(this, 2);

		Image image = new Image(new File("src/client/resources/layout/achtergrond_1024.jpg").toURI().toString());
		this.pane.setBackground(new Background(new BackgroundImage(image, null, null, null, null)));
		this.pane.setAlignment(Pos.CENTER);

		this.slotenPane.setPrefColumns(3);
		this.slotenPane.setPrefRows(3);
		this.slotenPane.setHgap(15);
		this.slotenPane.setVgap(60);
		this.slotenPane.setMaxSize(700, 900);

		this.pane.getChildren().add(slotenPane);
		this.createSloten(server.getKluis().getSloten());
	}

	public void createSloten(Slot[] sloten) throws RemoteException {
		this.slotIndex = 0;
		this.slotenPane.getChildren().clear();

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
            	BorderPane slotPane = new BorderPane();

            	ImageView slotView = createGraphic("models/slot_" + sloten[slotIndex].getSymbool().icoon);
            	slotView.setTranslateY(50);

            	Button buttonLinks = new Button();
            	buttonLinks.setId(String.valueOf(slotIndex));
            	buttonLinks.setGraphic(createGraphic("layout/knop_Links"));
            	buttonLinks.setPrefSize(20, 80);
            	buttonLinks.setBackground(new Background(new BackgroundFill(Color.rgb(0, 0, 0, 0), null, null)));
            	buttonLinks.setTranslateY(50);
            	buttonLinks.setDisable(!this.isEnabled());
            	buttonLinks.setOnAction(e-> {
            		this.controller.draaiLinks(Integer.parseInt(buttonLinks.getId()));
            	});

            	Button buttonRechts = new Button();
            	buttonRechts.setId(String.valueOf(slotIndex));
            	buttonRechts.setGraphic(createGraphic("layout/knop_Rechts"));
            	buttonRechts.setPrefSize(20, 80);
            	buttonRechts.setBackground(new Background(new BackgroundFill(Color.rgb(0, 0, 0, 0), null, null)));
            	buttonRechts.setTranslateY(50);
            	buttonRechts.setDisable(!this.isEnabled());
            	buttonRechts.setOnAction(e-> {
            		this.controller.draaiRechts(Integer.parseInt(buttonRechts.getId()));
            	});

            	slotPane.setCenter(slotView);
            	slotPane.setLeft(buttonLinks);
            	slotPane.setRight(buttonRechts);
            	slotPane.setBottom(new Label("" + sloten[slotIndex].isPositieJuist()));//createButton("layout/positie_" + bool));

            	this.slotenPane.getChildren().add(slotPane);
                slotIndex++;
            }
        }
	}

	public void enableKnoppen() throws RemoteException {
		this.slotIndex = 0;
		for (Node slotNode : this.slotenPane.getChildren()) {
			BorderPane slotPane = (BorderPane) slotNode;
			slotPane.getLeft().setDisable(!this.isEnabled());
			slotPane.getRight().setDisable(!this.isEnabled());
        	this.slotIndex++;
		}
	}

	public ImageView createGraphic(String pad) {
		ImageView graphic = new ImageView();
		graphic.setImage(new Image(new File("src/client/resources/" + pad + ".png").toURI().toString()));
		return graphic;
	}

	public void updateSlot(int positie, Slot slot) {
		BorderPane slotPane = (BorderPane) slotenPane.getChildren().get(positie);
		ImageView slotView = createGraphic("models/slot_" + slot.getSymbool().icoon);
		slotView.setTranslateY(50);
		slotPane.setCenter(slotView);
		slotPane.setBottom(new Label("" + slot.isPositieJuist()));//createButton("layout/positie_" + bool));
	}

	@Override
	public void update(SesameServerInterface server) throws RemoteException {
		Platform.runLater(
				() -> {
					try {
						this.controller.setServer(server);
						this.updateSlot(server.getKluis().getActiefSlotPositie(), server.getKluis().getActiefSlot());
						this.enableKnoppen();
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
	public void updateMode() throws RemoteException {
		Platform.runLater(
				() -> {
					this.controller.openKluis();
				}
		);
	}

	@Override
	public Pane getPane() {
		return this.pane;
	}

}
