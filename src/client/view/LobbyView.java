package client.view;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

import client.SesameObserver;
import client.controller.MainController;
import client.view.ViewInterface;

import server.SesameServerInterface;
import server.model.Speler;


public class LobbyView extends UnicastRemoteObject implements ViewInterface,
			SesameObserver {

	private static final long serialVersionUID = 1L;
	private MainController controller;
	private boolean enabled = false;
	private StackPane pane = new StackPane();
	private GridPane spelersPane = new GridPane();

	public LobbyView(MainController controller, SesameServerInterface server) throws RemoteException {
		this.controller = controller;

		VBox box = new VBox(8);
		Text titel = new Text("Sesame");
		titel.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
		spelersPane.add(titel, 0, 0, 2, 1);
		spelersPane.setAlignment(Pos.CENTER);
		box.setAlignment(Pos.CENTER);
		box.getChildren().add(spelersPane);
		this.pane.getChildren().add(box);

		this.controller.viewCenter(this);
	}

	private void toonSpeler(Speler speler) {
		spelersPane.add(new Label(speler.naam), 0, speler.getId()+1);
	}

	@Override
	public void update(SesameServerInterface server) throws RemoteException {
		try {
			for (Speler speler : server.getSpelers()) {
				Platform.runLater(
						() -> {
							this.toonSpeler(speler);
						}
				);
			}
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void updateMode() throws RemoteException {
		Platform.runLater(
				() -> {
					this.controller.startGame();
				}
		);
	}

	@Override
	public boolean isEnabled() throws RemoteException {
		return enabled;
	}

	@Override
	public void setEnabled(boolean enabled) throws RemoteException {
		this.enabled = enabled;
	}

	@Override
	public Pane getPane() {
		return this.pane;
	}

}
