package client.view;

import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

import java.io.File;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

import client.SesameObserver;
import client.controller.ScoreController;

import server.SesameServerInterface;
import server.model.Speler;
import server.model.kaarten.Schat;

public class ScoreView extends UnicastRemoteObject implements ViewInterface,
		SesameObserver {

	private static final long serialVersionUID = 1L;
	private ScoreController controller;
	private Pane pane = new Pane();
	private VBox spelersPane = new VBox(5);
	private boolean enabled = false;

	public ScoreView(ScoreController controller, SesameServerInterface server) throws RemoteException {
		this.controller = controller;
		this.controller.setObserver(this, 0);

		pane.setPrefSize(300, 600);
		pane.setStyle("-fx-background-color: #440206");
		for (Speler speler : server.getSpelers()) {
			this.toonSpeler(speler);
		}
		pane.getChildren().add(spelersPane);
	}

	public void toonSpeler(Speler speler) throws RemoteException {
		GridPane spelerPane = new GridPane();
		spelerPane.setPrefSize(300, 60);
		spelerPane.setStyle("-fx-border-color: #febe4d; -fx-border-width: 4");
		if(speler.isAanDeBeurt())spelerPane.setStyle("-fx-border-color: red; -fx-border-width: 4");
		spelerPane.setHgap(5);
		spelerPane.setVgap(10);
		spelerPane.setPadding(new Insets(5, 10, 5, 10));

		Label naam = new Label(speler.naam + ":");
		naam.setTextFill(Color.web("#ffbc4e"));
		naam.setFont(Font.font("Tahoma", FontWeight.NORMAL, 12));
		spelerPane.add(naam, 0, 0, 1, 1);

		ColumnConstraints col1 = new ColumnConstraints();
		col1.setMinWidth(140);
		col1.setMaxWidth(140);
		spelerPane.getColumnConstraints().add(col1);

		List<List<Schat>> score = speler.getScore();
		for (int i = 0; i < score.size(); i++) {

			Rectangle schatIcoon = new Rectangle(30,30);
			System.out.println("schat_" + i + ".png");
			Image img = new Image(new File("src/client/resources/models/schat_" + i + ".png").toURI().toString());
			schatIcoon.setFill(new ImagePattern(img));
			schatIcoon.setId(String.valueOf(i));

			if(score.get(i).size() > 0
					&& this.controller.getGameMode() == 3
					&& this.isEnabled()
					&& !speler.isAanDeBeurt()) {
				schatIcoon.setOnMouseClicked(new EventHandler<MouseEvent>()
		        {
					@Override
					public void handle(MouseEvent event) {
						int schatPositie = Integer.parseInt(schatIcoon.getId());
						controller.steelSchat(schatPositie, speler.getId());
					}
		        });
			}

			Label aantal = new Label(score.get(i).size() + "x");
			aantal.setTextFill(Color.web("#ffbc4e"));
			aantal.setMaxWidth(Double.MAX_VALUE);
			aantal.setAlignment(Pos.CENTER);
			aantal.setFont(Font.font("Tahoma", FontWeight.NORMAL, 12));

			spelerPane.add(schatIcoon, i+1, 0);
			spelerPane.add(aantal, i+1, 1);
		}
		spelersPane.getChildren().add(spelerPane);
	}

	@Override
	public void update(SesameServerInterface server) throws RemoteException {
		Platform.runLater(
				() -> {
					try {
						spelersPane.getChildren().clear();
						for (Speler speler : server.getSpelers()) {
							this.toonSpeler(speler);
						}
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
		return pane;
	}

	@Override
	public void updateMode() throws RemoteException {
		Platform.runLater(
				() -> {
					this.controller.setGameMode(3);
					this.controller.viewCenter(new DummyView());
				}
		);
	}

}
