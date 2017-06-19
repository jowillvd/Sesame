package view;

import controller.GameController;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

public class StartView extends StackPane {

	private GameController controller;

	public StartView(GameController controller) {
		this.controller = controller;

		Button hostBtn = new Button("Host Spel");
		hostBtn.setOnAction(this::hostAction);

		Button joinBtn = new Button("Join Spel");
		joinBtn.setOnAction(this::joinAction);

		Button hanleidingBtn = new Button("Handleiding");
		hanleidingBtn.setOnAction(this::handleidingAction);

		Button stopBtn = new Button("Afsluiten");
		stopBtn.setOnAction(this::stopAction);

		VBox box = new VBox(8);
		box.getChildren().addAll(hostBtn, joinBtn, hanleidingBtn, stopBtn);
		box.setAlignment( Pos.CENTER );
		box.setStyle("-fx-background-color: #2f2f2f;");
		this.getChildren().addAll(box);
		this.setStyle("-fx-background-color: #87CEFA;");
	}

	private void hostAction(ActionEvent e) {
		this.getChildren().clear();
		controller.hostLobby();
	}

	private void joinAction(ActionEvent e) {

	}

	private void handleidingAction(ActionEvent e) {

	}

	private void stopAction(ActionEvent e) {

	}

}
