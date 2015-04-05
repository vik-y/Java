import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;


public class Library {

	/**
	 * @param args
	 */

	private ArrayList<Book> book;
	private ArrayList<Painting> painting;
	private ArrayList<Sculpture> sculptures;
	private Finance finance= new Finance();
	
	private int id;

	Library(){
		book = new ArrayList<>();
		painting = new ArrayList<>();
		sculptures = new ArrayList<>();
	}

public void add(Book b){
		
		int flag = finance.checkBudget(1000);
		try{
			if(flag == 0){
			
				book.add(b);
				b.setFlag(1);
				b.setStatus(0);
				
				b.setId(id);
				id++;
			}
			else{
				throw b;
				}
			
		}catch(Book tempB){
			System.out.println("OUT OF BUDGET");
		}
		
	}
	
	public void add(Painting p){
		
		int flag = finance.checkBudget(10000);
		try{
			if(flag == 0){
				painting.add(p);
				p.setFlag(1);
				p.setStatus(0);
				p.setId(id);
				id++;
			}
			else{
				throw p;
			}
		}catch(Painting tempP){
			System.out.println("OUT OF BUDGET");
		}
		
	}
	
	public void add(Sculpture s){
		
		int flag = finance.checkBudget(10000);
		try{
			if(flag == 0){
		
				sculptures.add(s);
				s.setFlag(0);
				s.setStatus(0);
				s.setId(id);
				id++;
			}
			else{
				throw s;
			}
		}catch(Sculpture tempS){
			System.out.println("OUT OF BUDGET");
			}
	}
	

	public void listInventory(){
		System.out.println("\nBooks In the Library: ");
		Collections.sort(book, Book.name);
		for(int i=0; i < book.size(); i++){
			System.out.println(book.get(i));
		}

		System.out.println("\nPaintings In the Library: ");
		Collections.sort(painting, Painting.painting);
		for(int i = 0; i < painting.size(); i++ ){
			System.out.println(painting.get(i));
		}
		
		System.out.println("\nSculptures in Library: ");
		if(sculptures.size()==0) System.out.println("No Sculptures Present in Library\n\n");
		else{
			for(int i = 0 ; i < sculptures.size(); i++ ){
				System.out.println(sculptures.get(i));
			}
		}
					
	}
	
	
public void listBorrowedItems(){
		ArrayList<Items> temp = new ArrayList<Items>();
		
		System.out.println("\nBorrowed Items\n------------");
		for(int i=0;i<book.size();i++){
			if(book.get(i).getIsborrowed()==1){
				//System.out.println(book.get(i));
				temp.add(book.get(i));
			}
		}
		for(int i=0;i<painting.size(); i++){
			if(painting.get(i).getIsborrowed()==1) {
				//System.out.println(painting.get(i));
				temp.add(painting.get(i));
			}
		}
		Collections.sort(temp, Items.items);
		
		for(int i=0;i<temp.size();i++){
			System.out.println(temp.get(i));
		}
		
	}	
	
	

	
	public Book getBook(int i) {
		if( i < book.size()){
			return book.get(i);
		}
		else{
			try{
				Book b = new Book();
				throw b;
				
			}catch(Book b){
				System.out.println("NO BOOK FOUND");
				return b;
			}
		}
	}

	public Painting getPainting(int i) {
		if( i < painting.size()){
			return painting.get(i);
		}
		else{
			try{
				Painting p = new Painting();
				throw p;
			}catch(Painting p){
				System.out.println("NO PAINTING FOUND");
				return p;
			}
		}
	}

	public static void main(String[] args) {
		Library lib = new Library();
		lib.add(new Book("Hamlet", "Shaespeare"));
		lib.add(new Book("Hamlet", "Zhaespeare"));
		lib.add(new Book("Hamlet", "Uhaespeare"));

		lib.add(new Book("Cosmos", "CarlSagan"));
		lib.add(new Painting("Nightwatch", "Rembrandt", 1642));
		lib.add(new Painting("Sunrise", "Monet", 1872));
		lib.add(new Painting("Guitarist", "Picasso", 1903));
		lib.add(new Book("FourthEstate", "Archer"));
		// step 1
		lib.listInventory();
		// step 2
		lib.getBook(0).borrow(3, 20150327);
		lib.getPainting(1).borrow(7, 19950205);
		lib.getPainting(2).borrow(8, 19920205);
		//lib.listInventory();
		
		lib.listBorrowedItems();
	}


}
