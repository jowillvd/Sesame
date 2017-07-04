package client.view;

import java.io.File;

import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;

public class DummyView implements ViewInterface {

	private StackPane pane = new StackPane();

	public DummyView() {
		Image image = new Image(new File("src/client/resources/layout/achtergrond_1024.jpg").toURI().toString());
		this.pane.setBackground(new Background(new BackgroundImage(image, null, null, BackgroundPosition.CENTER, null)));
		this.pane.setAlignment(Pos.CENTER);
	}

	@Override
	public Pane getPane() {
		return this.pane;
	}

}
