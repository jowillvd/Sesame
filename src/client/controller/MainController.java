package client.controller;

import java.io.File;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

import client.SesameClient;
import client.SesameObserver;
import client.view.GeestkaartView;
import client.view.KluisView;
import client.view.LobbyView;
import client.view.MainMenuView;
import client.view.SchatkamerView;
import client.view.ScoreView;
import client.view.ViewInterface;
import client.view.ViewLoader;

import server.SesameServer;
import server.SesameServerInterface;
import server.model.Speler;

public class MainController {

	protected ViewLoader view;
	protected SesameServerInterface server;
	protected Speler speler;

	public MainController(ViewLoader viewLoader) {
		this.view = viewLoader;
		this.viewCenter(new MainMenuView(this));
	}

	public void startServer(String spelernaam) {
		if(spelernaam == null || spelernaam.trim().isEmpty()) {
			spelernaam = "Speler1";
		}
		try {
			new SesameServer();

		} catch (RemoteException e) {
			e.printStackTrace();
		}

		this.joinServer("127.0.0.1", spelernaam + "[host]");
		this.view.getCenter();
		Pane pane = (Pane) this.view.getCenter();
		Pane box = (Pane) pane.getChildren().get(0);
		Button startBtn = new Button("Start het spel");
		startBtn.setOnAction(e-> {
			try {
				server.startGame();
			} catch (RemoteException re) {
				re.printStackTrace();
			}
		});
		box.getChildren().add(startBtn);
	}

	public void startGame() {
		System.out.println("Host start het spel");
		try {
			ImageView boven = new ImageView();
			boven.setImage(new Image(new File("src/client/resources/layout/boven.png").toURI().toString()));
			this.view.setTop(boven);

			ImageView beneden = new ImageView();
			beneden.setImage(new Image(new File("src/client/resources/layout/beneden.png").toURI().toString()));
			this.view.setBottom(beneden);

			KluisController kluisController = new KluisController(this.view, this.server, this.speler);
			this.viewRight(new ScoreView(new ScoreController(this.view, this.server, this.speler), this.server));
			this.viewLeft(new GeestkaartView(kluisController, this.server));
			this.viewCenter(new KluisView(kluisController, this.server));

			//SchatkamerController DEBUG = new SchatkamerController(this.view, this.server, this.speler);
			//this.viewCenter(new SchatkamerView(DEBUG));
		} catch (RemoteException re) {
			re.printStackTrace();
		}
	}

	public void joinServer(String hostadres, String spelernaam) {
		if(hostadres == null || hostadres.trim().isEmpty()) {
			view.alert("IP adres ontbreekt, dit is een verplicht veld.");
			return;
		}
		try {
			new SesameClient(this, hostadres).getClient();
			this.speler = server.registerSpeler(new LobbyView(this, this.server), spelernaam);

		} catch (RemoteException | NotBoundException e) {
			e.printStackTrace();
		}
	}

	public void setObserver(SesameObserver viewI, int positie) {
		try {
			this.server.addObserver(viewI, positie, this.speler);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}

	public void setServer(SesameServerInterface server) {
		this.server = server;
	}

	public void viewCenter(ViewInterface view) {
		this.view.setCenter((Pane) view.getPane());
	}

	public void viewLeft(ViewInterface view) {
		this.view.setLeft((Pane) view.getPane());
	}

	public void viewRight(ViewInterface view) {
		this.view.setRight((Pane) view.getPane());
	}

}
