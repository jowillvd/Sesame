import java.io.File;

import controller.KluisController;
import controller.SchatkamerController;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
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

	@Override
	public void start(Stage primaryStage) throws Exception {
		Kluis kluis = new Kluis();
		//Schatkamer schatkamer = new Schatkamer();
		//KluisController kluisController = new KluisController();
		//SchatkamerController schatkamerController = new SchatkamerController();

		Pane achtergrondPane = new Pane();
		achtergrondPane.setBackground(new Background(new BackgroundFill(Color.rgb(68, 27, 2), null, null)));
		
		BorderPane hoofdPane = new BorderPane();
			hoofdPane.setPrefSize(1200, 750);
		//paneOpen.getChildren().add(new KluisView(kluis));
		//paneOpen.getChildren().add(new GeestkaartView());
		
		ImageView boven = new ImageView();
		boven.setImage(new Image(new File("src/resources/layout/boven.png").toURI().toString()));
		
		ImageView beneden = new ImageView();
		beneden.setImage(new Image(new File("src/resources/layout/beneden.png").toURI().toString()));
		
		hoofdPane.setTop(boven);
		hoofdPane.setBottom(beneden);		
		hoofdPane.setLeft(new GeestkaartView());
		hoofdPane.setCenter(new KluisView());
		
		
		achtergrondPane.getChildren().add(hoofdPane);
		
		Scene hoofdScene = new Scene(achtergrondPane, 1200, 750);
		
		primaryStage.setTitle("Sesame");
		primaryStage.setScene(hoofdScene);
		primaryStage.show();

		if(kluis.isOpen()) {

		} else {

		}
	}

}
