package model;

import java.util.ArrayList;
import java.util.List;

import model.kaarten.Kaart;

public class KaartHouderFactory {
	private List<List<Kaart>> stapel = new ArrayList<List<Kaart>>(9);
	private List<Kaart> uitdeelbaar = new ArrayList<Kaart>();

	public int getAantal(){
		return 0;
	}

	public void shuffleKaarten() {
		for (int i = 0; i < Global.MAX_SLANG; i++) {
		}
	}

	public void addStapel(int positie){
		List<Kaart> kaarten = stapel.get(positie);
		kaarten.add();
	}

	public void leegKaarten(){

	}

}