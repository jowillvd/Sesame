
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import controller.GameController;
import view.StartView;

public class Start extends Application {

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		GameController controller = new GameController();

		Scene scene = new Scene(new StartView(controller), 1000, 400);
		primaryStage.setTitle("Sesame");
		primaryStage.setScene(scene);
		primaryStage.show();
	}

}
