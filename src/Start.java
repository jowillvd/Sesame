import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

import client.view.ViewLoader;

public class Start extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception {
		Scene scene = new Scene(new ViewLoader(), 1600, 900);
		primaryStage.setTitle("Sesame");
		primaryStage.setScene(scene);
		primaryStage.setResizable(false);
		primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}

}
