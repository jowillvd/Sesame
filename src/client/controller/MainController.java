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
import client.view.KluisView;
import client.view.LinkerView;
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
	protected int gameMode = 0; // 0 = neutraal, 1 = kluis, 2 = schatkamer, 3 = steelmode

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

			KluisController kluisController = new KluisController(this.view, this.server, speler.getId(), this);
			ScoreController scoreController = new ScoreController(this.view, this.server, speler.getId(), this);

			ScoreView scoreView = new ScoreView(scoreController, this.server);
			LinkerView linkerView = new LinkerView(this, this.server);
			KluisView kluisView = new KluisView(kluisController, this.server);

			// DEBUG
			SchatkamerView debug = new SchatkamerView(
					new SchatkamerController(this.view, this.server, speler.getId(), this));

			this.viewRight(scoreView);
			this.viewLeft(linkerView);
			this.viewCenter(debug);
			this.setGameMode(1);
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

	public void beurtDoorgeven() {
		try {
			this.setGameMode(1);
			this.server.beurtDoorgeven();
		} catch (RemoteException e) {
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

	public boolean isSpelerAanDeBeurt() {
		return speler.isAanDeBeurt();
	}

	public int getGameMode() {
		return this.gameMode;
	}

	public void setGameMode(int i) {
		this.gameMode = i;
	}

}
