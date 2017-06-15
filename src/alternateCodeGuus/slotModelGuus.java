package alternateCodeGuus;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class slotModelGuus
{
	int lijst[];
	int positie = 0;
	
	public slotModelGuus(int grootteArray){
		//vul de array
		lijst = new int[grootteArray];
		
		for(int i = 0; i < lijst.length; i++){
			lijst[i] = i + 1;
		}
		
		shuffleArray(lijst);
		
		
	}
	public slotModelGuus()
	{
		// TODO Auto-generated constructor stub
	}
	public void shuffleArray(int[] ar)
	//shuffle de array
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
