package IsolatedCodeGuus;



import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class RunIt extends Application
{

	public static void main(String[] args)
	{
		launch(args);

	}
	
	@Override
	public void start(Stage primaryStage) throws Exception
	{
		BorderPane container = new BorderPane();		
		
		//Maak 9 sloten aan 
		/*
		for(int i = 0; i < 9; i++){
			slotModel sModelG = new slotModel(6);
			slotView sViewG = new slotView();
			slotController con = new slotController(sModelG, sViewG);			
			container.getChildren().addAll(sViewG);
		}	*/	
		
		//Maak kluis aan
		kluisModel kModel = new kluisModel();
		kluisView kView = new kluisView();
		kluisController kController = new kluisController(kModel, kView);
		
		container.setCenter(kView);
		
		/*
		container.setOnMouseEntered(e ->{
			kController.kModel.checkSloten(kController.geestKaart);
		});*/
		
		
		Scene scene = new Scene(container);
		
		primaryStage.setTitle("SesameShell");
		primaryStage.setScene(scene);
		primaryStage.show();
		
	}

}
