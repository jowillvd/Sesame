package client.view;

import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;

import client.controller.MainController;

public class ViewLoader extends BorderPane {

	MainController controller;

	public ViewLoader() {
		this.controller = new MainController(this);

		Image image = new Image(new File("src/client/resources/layout/achtergrond_menu.jpg").toURI().toString());
		this.setBackground(new Background(new BackgroundImage(image, null, null, BackgroundPosition.CENTER, null)));

		StackPane boven = new StackPane();
		boven.setAlignment(Pos.TOP_LEFT);

		HBox btnBox = new HBox(8);

		ImageView bovenImg = new ImageView();
		bovenImg.setImage(new Image(new File("src/client/resources/layout/boven.png").toURI().toString()));
		Button handleidingBtn = new Button();
		ImageView graphic = new ImageView();
		graphic.setImage(new Image(new File("src/client/resources/layout/boek.png").toURI().toString()));
		handleidingBtn.setGraphic(graphic);
		handleidingBtn.setBackground(new Background(new BackgroundFill(Color.rgb(0, 0, 0, 0), null, null)));
		handleidingBtn.setMaxHeight(60);
		handleidingBtn.setOnAction(e-> {
        	File pdf = new File("src/client/resources/Sesame_handleiding.pdf");
        	try {
				Desktop.getDesktop().open(pdf);
			} catch (IOException ioe) {
				ioe.printStackTrace();
			}
        });

		Button sluitBtn = new Button();
		graphic = new ImageView();
		graphic.setImage(new Image(new File("src/client/resources/layout/x.png").toURI().toString()));
		sluitBtn.setGraphic(graphic);
		sluitBtn.setBackground(new Background(new BackgroundFill(Color.rgb(0, 0, 0, 0), null, null)));
		sluitBtn.setMaxHeight(60);
		sluitBtn.setOnAction(e-> {
			Platform.exit();
			System.exit(1);
        });

		btnBox.getChildren().addAll(sluitBtn, handleidingBtn);

		boven.getChildren().addAll(bovenImg, btnBox);

		ImageView beneden = new ImageView();
		beneden.setImage(new Image(new File("src/client/resources/layout/beneden.png").toURI().toString()));

		this.setTop(boven);
		this.setBottom(beneden);
	}

	public void alert(String bericht) {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Let op!");
		alert.setHeaderText(null);
		alert.setContentText(bericht);

		alert.showAndWait();
	}

}
