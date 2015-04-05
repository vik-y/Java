import java.util.Comparator;


public class Book extends Items{

	/**
	 * @param args
	 */
	private String title, author;
	
	
	Book(){
		title="";
		author="";
	}
	
	Book(String book_title, String book_author){
		title = book_title;
		author = book_author;
	}
	
	public void borrow(int borrowerId, int date){
		setBorrowerId(borrowerId);
		setBorrowedon(date);
		setStatus(1);
		setIsborrowed(1);
	}
	
	public void sendBack(){
		setBorrowerId(0);
		setBorrowedon(0);
		setStatus(0);
		setIsborrowed(0);
	}
	

	public String getTitle() {
		return title;
	}



	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}
	
	public int isborrowed(){
		return isborrowed;
	}

	public void setAuthor(String author) {
		this.author = author;
	}	
	
	public static Comparator<Book> name = new Comparator<Book>() {
		public int compare(Book b1, Book b2){
	
			String name1 = b1.getTitle().toUpperCase();
			String name2 = b2.getTitle().toUpperCase();
			
			if(name1.compareTo(name2) == 0){
				return b1.getAuthor().compareTo(b2.getAuthor());
			}
			else 
				return b1.getTitle().compareTo(b2.getTitle());
		}
	};
	
	@Override
	public String toString() {
		return "Book [title=" + title + ", author=" + author + ", isborrowed="
				+ isborrowed +", borrowedon=" + borrowedon + "]";
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Book test;
		test = new Book("VIkas", "VIkas Yadav");
		System.out.println(test);
	}

}
