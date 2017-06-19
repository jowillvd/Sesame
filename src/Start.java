import controller.KluisController;
import controller.SchatkamerController;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import model.Geestkaart;
import model.Kluis;
import model.Schatkamer;
import view.GeestkaartView;
import view.KluisView;
import view.StartView;

public class Start extends Application {

	public static void main(String[] args) {
		launch(args);
		//Geestkaart slot = new Geestkaart();
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		Kluis kluis = new Kluis();
		//Schatkamer schatkamer = new Schatkamer();
		//KluisController kluisController = new KluisController();
		//SchatkamerController schatkamerController = new SchatkamerController();

		Pane paneOpen = new Pane();
		paneOpen.getChildren().add(new KluisView(kluis));
		//paneOpen.getChildren().add(new GeestkaartView());

		Scene scene = startScene();
		primaryStage.setTitle("Sesame");
		primaryStage.setScene(scene);
		primaryStage.show();

		if(kluis.isOpen()) {

		} else {

		}
	}

	private Scene startScene() {
		Scene startScene = new Scene(new StartView(), 1000, 400);

		return startScene;
	}

}
