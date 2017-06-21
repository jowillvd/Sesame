package IsolatedCodeGuus;



public class slotController
{
	slotModel sModelG;
	slotView sViewG;
	int grootteArray;	
	
	public slotController(slotModel sModelG, slotView sViewG){
		this.sModelG = sModelG;
		this.sViewG = sViewG;
		this.grootteArray = sModelG.grootteArray;
		
		sViewG.huidigNummer.setText(Integer.toString(returnHuidig()));
		
		sViewG.buttonRight.setOnAction(e ->{
			sViewG.huidigNummer.setText(Integer.toString(sModelG.draaiRechts(grootteArray)));
		});
		
		sViewG.buttonLeft.setOnAction(e ->{
			sViewG.huidigNummer.setText(Integer.toString(sModelG.draaiLinks(grootteArray)));
		});		
	}
	public int returnHuidig(){
		return sModelG.lijst[0];
	}
	
	
	public int draaiLinks(){
		return sModelG.draaiLinks(grootteArray);
	}
	
	public int draaiRechts(){
		return sModelG.draaiRechts(grootteArray);
	}
}
