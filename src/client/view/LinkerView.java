package client.view;

import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

import java.io.File;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import client.SesameObserver;
import client.controller.MainController;

import server.SesameServerInterface;

public class LinkerView extends UnicastRemoteObject implements ViewInterface,
		SesameObserver {

	private static final long serialVersionUID = 1L;
	private MainController controller;
	private VBox pane = new VBox(50);
	private StackPane instructiePane = new StackPane();
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

		instructiePane.setMaxSize(280, 200);
		instructiePane.setPrefSize(280, 200);
		instructiePane.setAlignment( Pos.CENTER );
		instructiePane.setStyle("-fx-background-color: rgb(255, 189, 77)");
		instructiePane.getChildren().add(new Label("TESTJEElwke"));

		this.pane.getChildren().addAll(beindigBtn, this.instructiePane);
	}

	private ImageView createImage(String pad) {
		ImageView graphic = new ImageView();
		graphic.setImage(new Image(new File("src/client/resources/" + pad + ".png").toURI().toString()));
		return graphic;
	}

	@Override
	public void update(SesameServerInterface server) throws RemoteException {
		Platform.runLater(
				() -> {
					try {
						this.setInstructie(server.getInstructie());
					} catch (RemoteException e) {
						e.printStackTrace();
					}
				}
		);
	}

	private void setInstructie(String instructie) {
		instructiePane.getChildren().set(0, new Label(instructie));
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

	public void addBottom(GeestkaartView geestkaartView) {
		this.pane.getChildren().add(geestkaartView.getPane());
	}

}
