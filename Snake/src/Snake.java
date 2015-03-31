import java.util.*;

public class Snake extends Thread{

	/**
	 * @param args
	 */

	private Location position;
	public String direction;
	public int gameover;
	private int[][] board;
	
	private int size = 10;

	public Snake() {
		// TODO Auto-generated constructor stub
		position = new Location(size/2, size/2);
		direction = new String("right");
		gameover = 0;
		board = new int[size][size];
		for(int i=0;i<size;i++){
			for(int j=0;j<size;j++){
				board[i][j]=0;
			}
		}
		board[size/2][size/2] = 1;
	}

	
	public void printboard(){
		for(int i=0;i<size;i++){
			for(int j=0;j<size;j++){
				System.out.print(board[i][j]);
				System.out.print(" ");
			}
			System.out.print("\n");
		}
		System.out.println("\n\n\n");
	}

	public void run(){
		//Will be the first function to be called after .start() call 
		while(gameover!=1){

			try{
				board[position.y][position.x] = 0;
				if(direction.equals("right")){
					System.out.println("gotit");
					position.x+=1;
				}
				else if(direction.equals("left")){
					position.x-=1;
				}
				else if(direction.equals("up")){
					position.y-=1;
				}
				else if(direction.equals("down")){
					position.y+=1;
				}
				board[position.y][position.x] = 1;
				
				System.out.println(position);
				printboard();
			}
			catch(ArrayIndexOutOfBoundsException e){
				gameover=1;
				System.out.println("gameover");
				break;
			}
			
			

			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Snake game = new Snake();
		game.start();
		String dir;
		dir = new String("right");
		Scanner in = new Scanner(System.in);
		while(true){
			dir = in.nextLine();
			System.out.println(dir);
			game.direction=dir;
		}	

	}

}
