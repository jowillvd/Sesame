package IsolatedCodeGuus;

import java.util.Arrays;

public class kluisController
{
	
	public int[]geestKaart = {1,2,3,4,5,6,1,2,3};
	
	
	public kluisModel kModel;
	public kluisView kView;
	
	
	
	public kluisController(kluisModel kModel, kluisView kView){
		this.kModel = kModel;
		this.kView = kView;
		
		kModel.maakSloten(kView);
		kModel.vulHuidigeSlotenArray();
		
		/*
		kView.melding.setOnMouseEntered(e ->{
			kModel.vulHuidigeSlotenArray();			
			kModel.checkSloten(geestKaart);
			for(int i = 0; i < 9; i++){
				System.out.print(kModel.huidigeSlotenStand[i]);
			}
		});	*/
		
		for(int i = 0; i < 9; i++){
			kModel.svArray[i].buttonLeft.setOnMouseClicked(e ->{
				kModel.checkSloten(geestKaart);
			});
			kModel.svArray[i].buttonRight.setOnMouseClicked(e ->{
				kModel.checkSloten(geestKaart);
			});
		}
		
		
	}	
	
	
	
	
	
	
	
	
	
	
}
