import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {
   private int n;          // size of the stack
   private Node first, last;     // top of stack
  
   private class Node {
       private Item item;
       private Node next;
       private Node previous;
   }
    
    
   public Deque()     {                      // construct an empty deque
       first = null;
       last = null;
       n = 0;

   }
   public boolean isEmpty()    {             // is the deque empty?
       return n == 0;
   }
   public int size()             {          // return the number of items on the deque
       return n;
   }
   public void addFirst(Item item)  {        // add the item to the front
       if (item == null) {throw new java.lang.NullPointerException() ;}
       Node oldfirst = first;
       first = new Node();
       first.item = item;
       first.previous = null;
       if (isEmpty()){
           last = first;
           first.next = null;
       }else {
            oldfirst.previous = first;     
            first.next = oldfirst;
       }
       n++;
   }
   public void addLast(Item item)    {       // add the item to the end
       if (item == null) {throw new java.lang.NullPointerException() ;}
       Node oldlast = last;
       last = new Node();
       last.item = item;
       last.next = null;
       
       if (isEmpty()) {first = last;last.previous = oldlast;
       }     else { oldlast.next = last;}
   
       n++;
   }
   public Item removeFirst()      {          // remove and return the item from the front
       if (n == 0) {throw new java.util.NoSuchElementException() ;}
       Item item = first.item;
       first = first.next;
       n = n - 1;
       if (isEmpty()) {last = null;first = null;}
       else { 
           first.previous = null;}
       return item;
   }
   public Item removeLast()          {       // remove and return the item from the end
       if (n == 0) {throw new java.util.NoSuchElementException();} 
       Item item = last.item;
      last = last.previous;
       n--;
       if (isEmpty()) {last = null;first = null;}
       else { 
           last.next = null;}
       return item;
   }
   public Iterator<Item> iterator()      {   // return an iterator over items in order from front to end
       return new ListIterator();
   }
   
   private class ListIterator implements Iterator<Item> {
       private Node current = first;
       public boolean hasNext()  { return current != null;}
       public void remove() { throw new UnsupportedOperationException();}
       
       public Item next() {
           if (!hasNext()) throw new NoSuchElementException();
           Item item = current.item;
           current = current.next;
           return item;
       }
   }
}