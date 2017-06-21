package IsolatedCodeGuus;

import java.util.Arrays;

public class kluisModel
{
	public int[]huidigeSlotenStand = new int[9];
	public slotView[]svArray = new slotView[9];
	public slotModel[]sModelArray = new slotModel[9];
	
	public final int aantalCombinatiesSlotArray = 9;
	
	public kluisModel(){
		
	}	
	
	public void maakSloten(kluisView kView){
		for(int i = 0; i < 9; i++){
			slotModel sModel = new slotModel(aantalCombinatiesSlotArray);
			slotView sView = new slotView();
			slotController con = new slotController(sModel, sView);
			sModelArray[i] = sModel;
			svArray[i] = sView;
			kView.getChildren().add(sView);
		}
	}
	
	public void vulHuidigeSlotenArray(){
		for(int i = 0; i < 9; i++){
			huidigeSlotenStand[i] = sModelArray[i].returnPositie();
		}		
	}
	
	public void checkSloten(int[]geestKaart){
		vulHuidigeSlotenArray();
		if(Arrays.equals(huidigeSlotenStand, geestKaart)){
			System.out.println("De kluis is open");
		}
		else{
			System.out.println("De kluis is dicht");
		}
	}
	
	
	
}
