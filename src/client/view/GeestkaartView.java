package client.view;

import javafx.application.Platform;
import javafx.geometry.Pos;
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

import java.io.File;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import client.SesameObserver;
import client.controller.MainController;
import server.SesameServerInterface;
import server.model.Geestkaart;

public class GeestkaartView extends UnicastRemoteObject implements ViewInterface,
		SesameObserver {

	private static final long serialVersionUID = 1L;
	private MainController controller;
	private VBox pane = new VBox();
	private TilePane geestkaartPane = new TilePane();
	private int symboolIndex;
	private boolean enabled = false;

	public GeestkaartView(MainController controller, SesameServerInterface server) throws RemoteException {
		this.controller = controller;
		this.controller.setObserver(this, 1);

		pane.setAlignment( Pos.CENTER );

		geestkaartPane.setPrefColumns(3);
		geestkaartPane.setPrefRows(3);
		geestkaartPane.setHgap(15);
		geestkaartPane.setVgap(5);
		//geestkaartPane.setTranslateX(0);
		//geestkaartPane.setTranslateY(-10);
		geestkaartPane.setMaxSize(280, 280);
		geestkaartPane.setAlignment( Pos.CENTER );
		geestkaartPane.setBackground(new Background(new BackgroundFill(Color.rgb(55, 175, 175), null, null)));

		this.toonSlangen(0);
		this.toonGeestkaart(server.getGeestkaart());
	}

	private void toonGeestkaart(Geestkaart geestkaart) {
		geestkaartPane.getChildren().clear();
		this.symboolIndex = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
            	String icoon = geestkaart.getSymbolen()[symboolIndex].icoon;
            	ImageView symbool = createSymbool("models/" + icoon);
            	//symbool.setTranslateY(50);

            	geestkaartPane.getChildren().add(symbool);
            	symboolIndex++;
            }
		}
        pane.getChildren().add(geestkaartPane);
	}

	private void toonSlangen(int aantal) {
		Label slangen = new Label("Slangen: (" +aantal+ "/7)");
		slangen.setTextFill(Color.web("#ffbc4e"));
		slangen.setFont(Font.font("Tahoma", FontWeight.NORMAL, 12));
		if(pane.getChildren().size() == 0) {
			pane.getChildren().add(0,slangen);
		} else {
			pane.getChildren().set(0,slangen);
		}
	}

	private ImageView createSymbool(String pad) {
		ImageView graphic = new ImageView();
		graphic.setImage(new Image(new File("src/client/resources/" + pad + ".png").toURI().toString()));
		return graphic;
	}

	@Override
	public void update(SesameServerInterface server) throws RemoteException {
		Platform.runLater(
				() -> {
					try {
						this.toonSlangen(server.getGepakteSlangen());
					} catch (RemoteException e) {
						e.printStackTrace();
					}
				}
		);
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
		return;
	}

}
