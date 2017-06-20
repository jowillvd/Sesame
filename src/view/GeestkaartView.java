package view;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;

public class GeestkaartView extends Pane{

	public void createGeestenkaart() {

	}

	public void createLeftPane() {
		
	}
	public GeestkaartView()
	{
		//importeerd custom font als file
		Font hoofdFont = null;

        try {
            hoofdFont = Font.loadFont(new FileInputStream(new File("src/resources/fonts/hoofd_Font.ttf")), 25);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        
        //Button van beurt eindigen, visualisatie hiervan
		VBox beurtEindigen = new VBox();
		beurtEindigen.setPrefSize(300, 75);
			StackPane beurtEindigenPane = new StackPane();
				beurtEindigenPane.setPrefSize(300, 75);
				ImageView imgEindigBeurt = new ImageView();
				imgEindigBeurt.setImage(new Image(new File("src/resources/Layout/beurt_Doorgeven.png").toURI().toString()));
				imgEindigBeurt.minHeight(75.0);
				imgEindigBeurt.setTranslateY(5);
				Label lblEindigBeurt = new Label("Beurt Beëindigen");
				lblEindigBeurt.setFont(hoofdFont);
				lblEindigBeurt.setTranslateY(-5);
				lblEindigBeurt.setTextFill(Color.rgb(68, 27, 2));
			beurtEindigenPane.getChildren().addAll(imgEindigBeurt, lblEindigBeurt);
		beurtEindigen.getChildren().add(beurtEindigenPane);
		
		//Instructies over de beurt
		VBox instructies = new VBox();
		instructies.setPrefSize(300, 200);
		instructies.setTranslateY(85);
			StackPane instructiePane = new StackPane();
				Rectangle instructieVeld = new Rectangle(280, 200);
				Label lblInstructie = new Label("Hier Komen de \n instructies \n afhankelijk \n van de state");
				lblInstructie.setFont(hoofdFont);
				lblInstructie.setTextFill(Color.rgb(68, 27, 2));
				instructieVeld.setFill(Color.rgb(255, 189, 77));
			instructiePane.getChildren().addAll(instructieVeld, lblInstructie);	
		instructies.getChildren().add(instructiePane);
		
		//hoeveel slangen er nog liggen
		HBox slangen = new HBox();
			slangen.setTranslateY(270);
			slangen.setTranslateX(10);
			Label aantalSlangen = new Label("Aantal Slangen: 5/7");
			aantalSlangen.setTextFill(Color.rgb(255, 189, 77));
			aantalSlangen.setFont(hoofdFont);
		slangen.getChildren().add(aantalSlangen);
		
		//de combinatie van de sloten komt hier te staan
		HBox geestKaart = new HBox();
			geestKaart.setTranslateY(315);
			geestKaart.setTranslateX(10);
			geestKaart.setPrefSize(280, 280);
				TilePane combinatiePane = new TilePane();
				combinatiePane.setPrefSize(280,280);
				combinatiePane.setBackground(new Background(new BackgroundFill(Color.rgb(73, 177, 181), null, null)));
		geestKaart.getChildren().add(combinatiePane);
		
		this.getChildren().addAll(beurtEindigen, instructies, slangen, geestKaart);
	}
}
