import java.util.Comparator;


public class Painting extends Items{

	/**
	 * @param args
	 */
	
	private String title, artist;
	private int year;

	
	Painting(){
		
	}
	

	public Painting(String title, String artist, int year) {
		super();
		this.title = title;
		this.artist = artist;
		this.year = year;
		isborrowed = 0;
		borrowedon = 0;
	}
	
	public int isborrowed(){
		return isborrowed;
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

	public String getArtist() {
		return artist;
	}

	public void setArtist(String artist) {
		this.artist = artist;
	}

	@Override
	public String toString(){
		return "Painting [title=" + title + ", artist=" + artist + ", year="
				+ year + ", isborrowed=" + isborrowed + ", borrowedOn="+borrowedon+ "]";
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}
	
	public static Comparator<Painting> painting = new Comparator<Painting>() {
		public int compare(Painting b1, Painting b2){
	
			String name1 = b1.getArtist().toUpperCase();
			String name2 = b2.getArtist().toUpperCase();
			
			if(name1.compareTo(name2) == 0){
				return b1.getArtist().compareTo(b2.getArtist()); 
			}
			else 
				return b1.getYear() - b2.getYear();
		}
	};
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Painting vik;
		vik = new Painting("Big painting", "vikas", 1994);
		System.out.println(vik);
	}

}
