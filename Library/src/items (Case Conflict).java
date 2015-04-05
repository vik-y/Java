
public class Items extends Throwable{

	protected String name;
	protected int id;
	protected String purchaseDate;
	protected int status;
	protected int flag;
	protected int type;
	protected int browerId;
	protected int date;
	
	
	public String getName(){
		return name;
	}
	
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
	
	
	public void setBorrowerId(int i){
		browerId = i;
	}
	
	public void setBrrowDate(int i){
		date = i;
	}
}