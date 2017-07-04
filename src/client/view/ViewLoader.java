package client.view;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.BorderPane;

import client.controller.MainController;

public class ViewLoader extends BorderPane {

	MainController controller;

	public ViewLoader() {
		this.controller = new MainController(this);

		this.setStyle("-fx-background-color: #87CEFA;");
		//this.setPrefSize(1200, 750);
	}

	public void alert(String bericht) {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Let op!");
		alert.setHeaderText(null);
		alert.setContentText(bericht);

		alert.showAndWait();
	}

}
