package IsolatedCodeGuus;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class slotModel
{

	int lijst[];
	int positie = 0;
	int grootteArray;
	
	public slotModel(int grootteArray){
		this.grootteArray = grootteArray;
		vulArray(grootteArray);
		shuffleArray(lijst);		
	}
	
	//vul de array
	public void vulArray(int grootteArray){
		lijst = new int[grootteArray];
		
		for(int i = 0; i < lijst.length; i++){
			lijst[i] = i + 1;
		}
	}
	
	//shuffle de array
	public void shuffleArray(int[] ar)
	 {
	    Random rnd = ThreadLocalRandom.current();
	    for (int i = ar.length - 1; i > 0; i--)
	    {
	      int index = rnd.nextInt(i + 1);
	      // Simple swap
	      int a = ar[index];
	      ar[index] = ar[i];
	      ar[i] = a;
	    }
	  }
	
	public int draaiRechts(int grootteArray){
		if(positie == grootteArray - 1){
			positie = 0;
		}
		else{
			positie += 1;
		}
		return lijst[positie];
	}	
	
	public int draaiLinks(int grootteArray){
		if(positie == 0){
			positie = grootteArray - 1;
		}
		else{
			positie -= 1;
		}
		return lijst[positie];
	}
	
	public int returnPositie(){
		return lijst[positie];
	}
}
