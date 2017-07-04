package client.view;

import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Pane;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.TextAlignment;

import java.io.File;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import client.SesameObserver;
import client.controller.MainController;

import server.SesameServerInterface;
import server.model.Geestkaart;

public class LinkerView extends UnicastRemoteObject implements ViewInterface,
		SesameObserver {

	private static final long serialVersionUID = 1L;
	private MainController controller;
	private VBox pane = new VBox(50);
	private VBox instructiePane = new VBox(5);
	private Pane slangenPane = new Pane();
	private TilePane geestkaartPane = new TilePane();
	private int symboolIndex;
	private boolean enabled = false;

	public LinkerView(MainController controller, SesameServerInterface server) throws RemoteException {
		this.controller = controller;
		this.controller.setObserver(this, 1);

		this.pane.setPrefSize(300, 600);
		this.pane.setAlignment( Pos.CENTER );
		this.pane.setStyle("-fx-background-color: #440206");

		Button beindigBtn = new Button("Beurt doorgeven");
		beindigBtn.setMaxSize(280, 60);
		beindigBtn.setPrefSize(280, 60);
		beindigBtn.setDisable(true);
		beindigBtn.setOnAction(e-> {
    		this.controller.beurtDoorgeven();
    	});

		instructiePane.setMaxSize(280, 200);
		instructiePane.setPrefSize(280, 200);
		instructiePane.setAlignment( Pos.CENTER );
		instructiePane.setStyle("-fx-background-color: rgb(255, 189, 77)");
		Label instructie = new Label(server.getInstructie());
		instructie.setWrapText(true);
		instructie.setPrefSize(240,180);
		instructie.setMaxWidth(Double.MAX_VALUE);
		instructie.setAlignment(Pos.CENTER);
		instructie.setTextAlignment(TextAlignment.CENTER);
		instructiePane.getChildren().add(instructie);

		slangenPane.setMaxSize(280, 20);
		slangenPane.setPrefSize(280, 20);

		geestkaartPane.setPrefColumns(3);
		geestkaartPane.setPrefRows(3);
		geestkaartPane.setHgap(15);
		geestkaartPane.setVgap(5);
		//geestkaartPane.setTranslateX(0);
		//geestkaartPane.setTranslateY(-10);
		geestkaartPane.setMaxSize(280, 280);
		geestkaartPane.setAlignment( Pos.CENTER );
		geestkaartPane.setBackground(new Background(new BackgroundFill(Color.rgb(55, 175, 175), null, null)));

		toonSlangen(0);
		this.toonGeestkaart(server.getGeestkaart());

		this.pane.getChildren().addAll(beindigBtn, this.instructiePane, this.slangenPane, this.geestkaartPane);
	}

	private ImageView createGraphic(String pad) {
		ImageView graphic = new ImageView();
		graphic.setImage(new Image(new File("src/client/resources/" + pad + ".png").toURI().toString()));
		return graphic;
	}

	private void toonSlangen(int aantal) {
		this.slangenPane.getChildren().clear();
		Label slangen = new Label("Slangen: (" +aantal+ "/7)");
		slangen.setTextFill(Color.web("#ffbc4e"));
		slangen.setFont(Font.font("Tahoma", FontWeight.NORMAL, 12));
		this.slangenPane.getChildren().add(slangen);
	}

	private void toonGeestkaart(Geestkaart geestkaart) {
		geestkaartPane.getChildren().clear();
		this.symboolIndex = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
            	String icoon = geestkaart.getSymbolen()[symboolIndex].icoon;
            	ImageView symbool = createGraphic("models/" + icoon);
            	//symbool.setTranslateY(50);

            	geestkaartPane.getChildren().add(symbool);
            	symboolIndex++;
            }
		}
	}

	@Override
	public void update(SesameServerInterface server) throws RemoteException {
		Platform.runLater(
				() -> {
					try {
						this.enableKnop();
						this.setInstructie(server.getInstructie());
						this.toonSlangen(server.getGepakteSlangen());
						this.toonGeestkaart(server.getGeestkaart());

					} catch (RemoteException e) {
						e.printStackTrace();
					}
				}
		);
	}

	private void enableKnop() throws RemoteException {
		if(controller.getGameMode() == 2) {
			this.pane.getChildren().get(0).setDisable(!this.isEnabled());
		} else {
			this.pane.getChildren().get(0).setDisable(true);
		}
	}

	private void setInstructie(String instructie) {
		Label label = (Label) instructiePane.getChildren().get(0);
		label.setText(instructie);
	}

	@Override
	public boolean isEnabled() throws RemoteException {
		return this.enabled;
	}

	@Override
	public void setEnabled(boolean enabled) throws RemoteException {
		this.enabled = enabled;
	}

	@Override
	public Pane getPane() {
		return this.pane;
	}

	@Override
	public void updateMode() throws RemoteException {
		Platform.runLater(
				() -> {
					try {
						this.enableKnop();
					} catch (RemoteException e) {
						e.printStackTrace();
					}
				}
		);
	}

}
