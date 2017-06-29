package client.view;

import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import client.SesameObserver;
import client.controller.ScoreController;

import server.SesameServerInterface;
import server.model.Speler;

public class ScoreView extends UnicastRemoteObject implements ViewInterface,
		SesameObserver {

	private static final long serialVersionUID = 1L;
	private ScoreController controller;
	private Pane pane = new Pane();
	private VBox spelersPane = new VBox(8);
	private boolean enabled;

	public ScoreView(ScoreController controller, SesameServerInterface server) throws RemoteException {
		this.controller = controller;
		this.controller.setObserver(this, 0);

		pane.setPrefSize(300, 600);
		pane.setStyle("-fx-background-color: #440206");
		pane.getChildren().add(spelersPane);
		for (Speler speler : server.getSpelers()) {
			this.toonSpeler(speler);
		}
	}

	public void toonSpeler(Speler speler) {
		GridPane spelerPane = new GridPane();
		spelerPane.setPrefSize(300, 60);
		spelerPane.setStyle("-fx-border-color: #febe4d; -fx-border-width: 4");
		spelerPane.setHgap(5);
		spelerPane.setVgap(15);
		spelerPane.setPadding(new Insets(5, 10, 5, 10));

		Label naam = new Label(speler.naam + ":");
		naam.setTextFill(Color.web("#ffbc4e"));
		naam.setFont(Font.font("Tahoma", FontWeight.NORMAL, 12));
		spelerPane.add(naam, 0, 0, 1, 1);

		ColumnConstraints col1 = new ColumnConstraints();
		col1.setMinWidth(160);
		col1.setMaxWidth(160);
		spelerPane.getColumnConstraints().add(col1);

		for (int i = 1; i <= 4; i++) {
			Rectangle schat = new Rectangle(25, 25);
			schat.setFill(Color.web("#3f47cc"));
			spelerPane.add(schat, i, 0);

			Label aantal = new Label(speler.getScore()[i-1] + "x");
			aantal.setTextFill(Color.web("#ffbc4e"));
			aantal.setFont(Font.font("Tahoma", FontWeight.NORMAL, 12));
			spelerPane.add(aantal, i, 1);
		}

		spelersPane.getChildren().add(spelerPane);
	}

	@Override
	public void update(SesameServerInterface server) throws RemoteException {
		try {
			for (Speler speler : server.getSpelers()) {
				Platform.runLater(
						() -> {
							//this.toonSpeler(speler);
						}
				);
			}
		} catch (RemoteException e) {
			e.printStackTrace();
		}
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
		return pane;
	}

	@Override
	public void updateMode() throws RemoteException {
		return;
	}

}
