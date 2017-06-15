package model;

public class Speler {
  public String naam;
	public int aantalRingen;
	public int aantalKelken;
	public int aantalKettingen;
	public int aantalToverlampen;
	Scanner input = new Scanner(System.in);

	public void invoerNaam(String naam) {
		System.out.println("Wilt u een naam invullen? (z/x)");
		String naamInvullen = input.nextLine();
		if(naamInvullen == "z"){
			System.out.println("Vul hier uw naam in.");
			naam = input.nextLine();
		}
		else if(naamInvullen != "z"){
			naam = "Speler";
		}

	}

	public void openkluis() {
	}

	public void draaiSlot() {
	}

	public void maakFout() {
	}

	public void pakKaart() {
	}

	public int getScore() {
		return (aantalRingen * 1) + (aantalKelken * 2) + (aantalKettingen * 3) + (aantalToverlampen * 5);
	}

}
