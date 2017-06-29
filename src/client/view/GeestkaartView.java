package client.view;

import javafx.application.Platform;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Pane;
import javafx.scene.layout.TilePane;
import javafx.scene.paint.Color;

import java.io.File;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import client.SesameObserver;
import client.controller.KluisController;

import server.SesameServerInterface;

public class GeestkaartView extends UnicastRemoteObject implements ViewInterface,
		SesameObserver {

	private static final long serialVersionUID = 1L;
	private KluisController controller;
	private Pane pane = new Pane();
	private TilePane geestkaartPane = new TilePane();
	private int symboolIndex;
	private boolean enabled;

	public GeestkaartView(KluisController controller, SesameServerInterface server) throws RemoteException {
		this.controller = controller;
		this.controller.setObserver(this, 1);
		this.pane.setPrefSize(300, 600);
		this.pane.setStyle("-fx-background-color: #440206");

		geestkaartPane.setPrefColumns(3);
		geestkaartPane.setPrefRows(3);
		geestkaartPane.setHgap(10);
		geestkaartPane.setVgap(5);
		geestkaartPane.setTranslateX(0);
		geestkaartPane.setTranslateY(-10);
		geestkaartPane.setMaxSize(280, 300);
		geestkaartPane.setBackground(new Background(new BackgroundFill(Color.rgb(55, 175, 175), null, null)));

		this.toonGeestkaart(server.getGeestkaart().getSymbolen());
		this.setEnabled(false);
	}

	private void toonGeestkaart(String[] symbolen) {
		geestkaartPane.getChildren().clear();
		this.symboolIndex = 0;
		pane.getChildren().clear();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
            	ImageView symbool = createSymbool("models/" + symbolen[symboolIndex]);
            	symbool.setTranslateY(50);

            	geestkaartPane.getChildren().add(symbool);
            	symboolIndex++;
            }
		}
        this.pane.getChildren().add(geestkaartPane);
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
						this.toonGeestkaart(server.getGeestkaart().getSymbolen());
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
