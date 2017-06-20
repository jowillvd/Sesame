<<<<<<< HEAD
import controller.KluisController;
import controller.SchatkamerController;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.Geestkaart;
import model.Kluis;
import model.Schatkamer;
import view.GeestkaartView;
import view.KluisView;
import view.SlangenView;

public class Start extends Application {

	public static void main(String[] args) {
		launch(args);
		//Geestkaart slot = new Geestkaart();
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		Geestkaart geestkaart = new Geestkaart();
		Kluis kluis = new Kluis();
		//Schatkamer schatkamer = new Schatkamer();
		//KluisController kluisController = new KluisController();
		//SchatkamerController schatkamerController = new SchatkamerController();

		BorderPane borderPane = new BorderPane();

		// Linker kant van het scherm
		VBox linksBox = new VBox();
		linksBox.setMaxWidth(300);


		Pane geestkaartPane = new Pane();
		geestkaartPane.getChildren().add(new GeestkaartView(geestkaart));

		Pane slangenPane = new Pane();
		slangenPane.getChildren().add(new SlangenView(schatkamer));

		linksBox.getChildren().add(geestkaartPane);

		borderPane.setLeft(linksBox);

		if(kluis.isOpen()) {
			/*
			Pane schatkamerPane = new Pane();
			schatkamerPane.getChildren().add(new SchatkamerView(schatkamer));
			borderPane.setCenter(schatkamerPane)
			//*/
		} else {
			Pane kluisPane = new Pane();
			kluisPane.getChildren().add(new KluisView(kluis));
			borderPane.setCenter(kluisPane);
		}

		Scene scene = new Scene(borderPane, 1600, 900);
		primaryStage.setTitle("Sesame");
		primaryStage.setScene(scene);
		primaryStage.show();
	}

}
=======
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
>>>>>>> origin/lennardClaproth
