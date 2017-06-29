package client.controller;

import client.view.ViewLoader;

import server.SesameServerInterface;
import server.model.Speler;

public class ScoreController extends MainController {

	public ScoreController(ViewLoader viewLoader, SesameServerInterface server, Speler speler) {
		super(viewLoader);
		this.server = server;
		this.speler = speler;
	}

}
