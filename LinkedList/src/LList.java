
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
	
	
	public void insert(int pos, Object data){
		if(pos>n) System.out.println("Error, Can't insert at postion : "+pos);
		else if(pos==0){
			LLNode insert_node;
			insert_node = new LLNode(data);
			insert_node.next = head;
			head = insert_node;
			n+=1;
		}
		else{
			LLNode insert_node, next, prev,temp;
			temp = head;
			insert_node = new LLNode(data);
			for(int i=0;i<pos-1;i++){
				temp = temp.next;
			}
			prev = temp;
			next = temp.next;
			prev.next = insert_node;
			insert_node.next = next;
			n+=1;
			
		}
		
	}
	
	public void append(Object data){
		LLNode insert_node, temp;
		temp = head;
		insert_node = new LLNode(data);
		for(int i=0;i<n-1;i++){
			temp = temp.next;
		}
		temp.next = insert_node;
		n+=1;
	}
	
	
	public void pop(){
		LLNode temp;
		temp = head;
		for(int i=0;i<n-2;i++){
			temp = temp.next;
		}
		temp.next = null;
		n-=1;
	}
	
	
	public void remove(int pos){
		LLNode temp;
		temp = head;
		if(pos==0){
			if(n==0) return;
			head = head.next;
			n-=1;
		}
		else{
			for(int i=0;i<pos-1;i++){
				temp = temp.next;
			}
			temp.next = temp.next.next;
			n-=1;
		}
	}
	
	public void clear(){
		head = null;
		n=0;
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
		System.out.print("\n");
	}
	
	public Object get(int pos) {
		if(pos==0) return head.data;
		LLNode temp;
		temp=head;
		for(int i=1;i<pos;i++){
			temp = temp.next;
		}
		return temp.data;
	}
	public int find(Object obj) {
		LLNode temp;
		temp=head;
		for(int i=0;i<n;i++){
			if(obj.equals(temp.data)) return 1;
			temp = temp.next;
		}
		return 0;
	}
	
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		LList test;
		test = new LList();
		test.insert(0, 10);
		test.insert(1, 11);
		test.insert(2, 12);
		test.insert(3, 13);
		test.insert(4, 20);
		test.append("vikas");
		test.append("test");
		
		test.append("hdfddflo");
		
		test.append("anothertest");
		test.print();
		test.print();
		test.remove(3);
		test.print();
		test.remove(0);
		test.print();
		System.out.println(test.find(10));
	}

}
