import java.util.Comparator;


public class Items extends Throwable{
	protected int id;
	protected String purchaseDate;
	protected int status;
	protected int isborrowed;
	protected int flag;
	protected int type;
	protected int browerId;
	protected int borrowedon;
	protected int date;
	
	
	public int getId(){
		return id;
	}
	
	public String getPurchaseDate(){
		return purchaseDate;
	}
	
	public void setId(int i){
		id = i;
	}
	
	public void setStatus(int i){
		status = i;
	}
	
	
	public int getStatus(){
		return status;
	}
	
	public void setFlag(int i){
		flag = i;
	}
	
	public int getFlag(){
		return flag;
	}
	
	public void setType(int i){
		type = i;
	}
	
	public int getType(){
		return type;
	}
	
	public void setIsborrowed(int a){
		isborrowed = a;
	}
	
	public int getIsborrowed(){
		return isborrowed;
	}
	
	
	public void setBorrowedon(int date){
		borrowedon = date;
	}
	
	public int getBorrowedon(){
		return borrowedon;
	}
	
	
	public void setBorrowerId(int i){
		browerId = i;
	}
	
	
	public static Comparator<Items> items = new Comparator<Items>() {
		public int compare(Items b1, Items b2){
	
			int date1 = b1.getBorrowedon();
			int date2 = b2.getBorrowedon();
			
			return date1 - date2;
		}
	};
}
