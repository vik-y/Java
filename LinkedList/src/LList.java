
public class LList {

	/**
	 * @param args
	 */
	
	/*
	 * Insert
	 * Find 
	 * Remove 
	 * 
	 */
	
	private class LLNode{
		Object data;
		LLNode next;
		
		public LLNode(Object obj){
			data = obj;
			next = null;
		}

		public Object getData() {
			return data;
		}

		public void setData(Object data) {
			this.data = data;
		}

		public LLNode getNext() {
			return next;
		}

		public void setNext(LLNode next) {
			this.next = next;
		}
	}
	
	private int n=0;
	private LLNode head;
	
	
	public LList(){
		head=null;
	}
	
	public void setData(Object data){
		
	}
	
	public void insert(int pos, Object data){
		LLNode pos_node_prev, insert_node;
		
		insert_node = new LLNode(data);
		
		pos_node_prev = head;
		if(n==0){
			head = insert_node;
			n+=1;
		}
		else if(pos>n) System.out.println("Exception, position doesn't exist");
		else{
			for(int i=1;i<pos;i++) pos_node_prev = pos_node_prev.next;
			LLNode pos_node_next;
			pos_node_next = pos_node_prev.next;
			
			pos_node_prev.next = insert_node;
			insert_node.next = pos_node_next;
			n+=1;
		}
		
	}
	
	public void append(){
		
	}
	
	
	public void pop(){
		
	}
	
	
	public void remove(int pos){
		
	}
	
	public void clear(){
		
	}
	
	public void find(Object data){
		//return 1 if found 0 if not found
	}
	
	
	
	public void print(){
		if(n==0) System.out.println("Empty List");
		else{
			LLNode temp;
			temp = head;
			
			for(int i=0;i<n;i++){
				System.out.print(temp.getData());
				System.out.print(" ");
				temp=temp.next;
			}
		}
	}
	
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		LList test;
		test = new LList();
		test.insert(0, 10);
		test.insert(1, 10);
		test.insert(2, 10);
		test.insert(3, 10);
		test.insert(4, 20);
		
		test.print();
		
	}

}
