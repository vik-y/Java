
public class Finance extends Throwable{

	private int  budget = 30000;
	private int count = 0;
	
	public int checkBudget(int i){
		count += i;
		if(count > budget){
			count -= i;
			return 1;	
		}
		else
			return 0;
	}
	
}