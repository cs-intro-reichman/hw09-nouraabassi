public class List {
    private Node first;
    private int size;
public List() {
        first = null;
        size = 0;
    }

    public int getSize() {
 	      return size;
    }

    public CharData getFirst() {
        return first.cp;
    }

    public void addFirst(char chr) {
 CharData newCharData = new CharData(chr);
        Node newNode = new Node(newCharData, first);
 this.first = newNode;
      size++;
    }
    
    public String toString() {
      if (size == 0) return "()";
String str = "(";
        Node current = first;
       while (current != null){
 str += current.toString() + " ";
            current = current.next;
        }
return str.substring(0, str.length()-1) + ")";
    }

    public int indexOf(char chr) {
        Node current = first;
        int index = 0;

        while(current != null){
 if(current.cp.equals(chr)) return index;
current = current.next;
            index++;
        }
return -1;
    }

    public void update(char chr) {
        if(this.indexOf(chr) != -1) {
            Node current = first;
            while(!current.cp.equals(chr)){
                current = current.next;
            }
            current.cp.count++;
        } else {
            this.addFirst(chr);
 }}


    public boolean remove(char chr) {
        Node current = first;
        Node prev = null;
        while(current != null && !current.cp.equals(chr)){

            prev = current;
            current = current.next;

        }

        if (current == null) return false;
        if(prev == null) first = first.next;
        else prev.next = current.next;
        size--;    
        return true;
    }

    public CharData get(int index) {
        if (index < 0 || index > size-1){
            throw new IndexOutOfBoundsException();

        } else {

            Node current = first; 
            int i = 0;
            while (i < index){
                current = current.next;
                i++;
            }

            return current.cp;
        }
    }
    public CharData[] toArray() {
	    CharData[] arr = new CharData[size];
	    Node current = first;
	    int i = 0;
        while (current != null) {
    	    arr[i++]  = current.cp;
    	    current = current.next;
        }
        return arr;
    }

    public ListIterator listIterator(int index) { 
 if (size == 0) return null;
  Node current = first;
 int i = 0;
 while (i < index) {
  current = current.next;
  i++; }
	    return new ListIterator(current);
    }
}
