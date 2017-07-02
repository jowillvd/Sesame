package client.view;

import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

import client.controller.MainController;

public class MainMenuView implements ViewInterface {

	private Pane pane = new StackPane();
	private MainController controller;

	public MainMenuView(MainController controller) {
		this.controller = controller;

		this.toonMenu();
	}

	public void toonMenu() {
		VBox box = new VBox(8);
		Button hostBtn = new Button("Host Spel");
		Button joinBtn = new Button("Join Spel");
		Button hanleidingBtn = new Button("Handleiding");
		Button stopBtn = new Button("Afsluiten");

		hostBtn.setOnAction(this::handleHostAction);
		joinBtn.setOnAction(this::handleJoinAction);
		hanleidingBtn.setOnAction(this::handleHandleidingAction);
		stopBtn.setOnAction(this::handleStopAction);

		box.getChildren().addAll(hostBtn, joinBtn, hanleidingBtn, stopBtn);
		box.setAlignment( Pos.CENTER );
		box.setStyle("-fx-background-color: #2f2f2f;");

		this.pane.getChildren().addAll(box);
	}

	private void handleBackAction(ActionEvent e) {
		this.pane.getChildren().clear();
		this.toonMenu();
	}

	private void handleHostAction(ActionEvent e) {
		this.pane.getChildren().clear();

		GridPane grid = new GridPane();
		grid.setAlignment(Pos.CENTER);
		grid.setHgap(10);
		grid.setVgap(10);
		grid.setPadding(new Insets(25, 25, 25, 25));

		Text titel = new Text("Host een server");
		titel.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
		grid.add(titel, 0, 0, 2, 1);

		Label spelerLabel = new Label("Spelernaam: ");
		grid.add(spelerLabel, 0, 1);

		TextField spelernaamField = new TextField();
		spelernaamField.setText("Speler1");
		grid.add(spelernaamField, 1, 1);

		Button backBtn = new Button("Terug naar menu");
		backBtn.setOnAction(this::handleBackAction);

		Button btn = new Button("Start server");
		btn.setOnAction(e2 ->{
			this.controller.startServer(spelernaamField.getText());
		});

		HBox hbBtn = new HBox(10);
		hbBtn.setAlignment(Pos.BOTTOM_RIGHT);
		hbBtn.getChildren().addAll(backBtn, btn);
		grid.add(hbBtn, 1, 4);

		this.pane.getChildren().add(grid);
	}

	private void handleJoinAction(ActionEvent e) {
		this.pane.getChildren().clear();

		GridPane grid = new GridPane();
		grid.setAlignment(Pos.CENTER);
		grid.setHgap(10);
		grid.setVgap(10);
		grid.setPadding(new Insets(25, 25, 25, 25));

		Text titel = new Text("Join een server");
		titel.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
		grid.add(titel, 0, 0, 2, 1);

		Label spelerLabel = new Label("Spelernaam: ");
		grid.add(spelerLabel, 0, 1);

		TextField spelernaamField = new TextField();
		grid.add(spelernaamField, 1, 1);

		Label hostLabel = new Label("Host adres: ");
		grid.add(hostLabel, 0, 2);

		TextField hostField = new TextField();
		hostField.setText("127.0.0.1");
		grid.add(hostField, 1, 2);

		Button backBtn = new Button("Terug naar menu");
		backBtn.setOnAction(this::handleBackAction);
		Button btn = new Button("Join host");
		btn.setOnAction(e2 ->{
			this.controller.joinServer(hostField.getText(),
					spelernaamField.getText());
		});
		HBox hbBtn = new HBox(10);
		hbBtn.setAlignment(Pos.BOTTOM_RIGHT);
		hbBtn.getChildren().addAll(backBtn, btn);
		grid.add(hbBtn, 1, 4);

		this.pane.getChildren().add(grid);
	}

	private void handleHandleidingAction(ActionEvent e) {
		System.out.println("Handleiding openen...");
		//controller.openHandleiding();
	}

	private void handleStopAction(ActionEvent e) {
		System.out.println("Spel afsluiten...");
	}

	@Override
	public Pane getPane() {
		return this.pane;
	}

}
