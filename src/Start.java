import controller.KluisController;
import controller.SchatkamerController;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import model.Geestkaart;
import model.Kluis;
import model.Schatkamer;
import view.GeestkaartView;
import view.KluisView;

public class Start extends Application {

	public static void main(String[] args) {
		launch(args);
		//Geestkaart slot = new Geestkaart();
	}
	//test

	@Override
	public void start(Stage primaryStage) throws Exception {
		Kluis kluis = new Kluis();
		//Schatkamer schatkamer = new Schatkamer();
		//KluisController kluisController = new KluisController();
		//SchatkamerController schatkamerController = new SchatkamerController();

		Pane paneOpen = new Pane();
		paneOpen.getChildren().add(new KluisView(kluis));
		//paneOpen.getChildren().add(new GeestkaartView());

		Scene scene = new Scene(paneOpen, 1000, 400);
		primaryStage.setTitle("Sesame");
		primaryStage.setScene(scene);
		primaryStage.show();

		if(kluis.isOpen()) {

		} else {

		}
	}

}
