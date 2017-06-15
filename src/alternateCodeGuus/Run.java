package alternateCodeGuus;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Run extends Application
{

	public static void main(String[] args)
	{
		launch(args);

	}

	@Override
	public void start(Stage primaryStage) throws Exception
	{
		VBox container = new VBox();
		kluisViewGuus kv = new kluisViewGuus();
		//slotView slot1 = new slotView();
		
		container.getChildren().add(kv);
		
		Pane pane = new Pane();
		Scene scene = new Scene(container);
		
		primaryStage.setTitle("Sesame");
		primaryStage.setScene(scene);
		primaryStage.show();
		
	}

}
