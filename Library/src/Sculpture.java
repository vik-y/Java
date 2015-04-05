
public class Sculpture extends Items{

	private String title;
	private String artist;
	int year;
	
	public Sculpture(String temp1, String temp2, int temp3){
		title = temp1;
		artist = temp2;
		year = temp3;
	}
	
	
	public void borrow(int borrowerId, int date){
		System.out.println("Cannot Borrow Sculptures");
	}
	
	public void sendBack(){
		System.out.println("Cannot Borrow hence cannot Return Sculture");
	}
	
	public String toString(){
		
		String temp = "";
		temp = temp + getId() + " | " + title + " | " + artist + " | " + year;
		return temp;
	}
	
}