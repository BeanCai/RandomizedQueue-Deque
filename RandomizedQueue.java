import java.util.Iterator;
import edu.princeton.cs.algs4.StdRandom;
public class RandomizedQueue<Item> implements Iterable<Item> {
   private int n = 0;  // number of elements on queue
   private Item[] q;   //queue elements
   
   
   public RandomizedQueue()          {       // construct an empty randomized queue
       q = (Item[]) new Object[2];
   }
   
   public boolean isEmpty()    {             // is the queue empty?
       return n == 0;
   }
   public int size()  {                      // return the number of items on the queue
       return n;
   }
   
   private void resize(int max) {
       assert max >= n;
       Item[] temp = (Item[]) new Object[max];
       for (int i = 0; i < n; i++) {
           temp[i] = q[i];
       }
       q = temp;
   }
   
   public void enqueue(Item item)  {         // add the item
       if (item == null) {
           throw new java.lang.NullPointerException();
       }
       if (n == q.length) resize(2*q.length);
       q[n] = item;
       n++;
   }
   
   public Item dequeue()        {            // remove and return a random item
       if (isEmpty()) throw new java.util.NoSuchElementException();
       int index = StdRandom.uniform(n);
       Item item = q[index];
       if (index != n-1){q[index] = q[n-1];
       }
       q[n-1] = null;
       n--;
       
       if (n > 0 && n == q.length/4) resize(q.length/2);
       return item;
   }
   public Item sample()       {              // return (but do not remove) a random item
       if (isEmpty()) throw new java.util.NoSuchElementException();
       int index = (StdRandom.uniform(n));
       return q[index];
   }
   public Iterator<Item> iterator()     {    // return an independent iterator over items in random order
       return new ArrayIterator();
   }
   
   private class ArrayIterator implements Iterator<Item> {
       private Item[] tempItem = (Item[]) new Object[q.length];
       
       private int tempN = n;
       public boolean hasNext() {return tempN != 0;}
       public void remove() { throw new UnsupportedOperationException();}
       
       public ArrayIterator() {
           for (int j=0; j<q.length; j++){
               tempItem[j] = q[j];
           }
       }
       
       public Item next() {
           if (!hasNext()) throw new java.util.NoSuchElementException();
           int index = (StdRandom.uniform(tempN));
           Item item = tempItem[index];
           if (index != tempN-1) {
               tempItem[index] = tempItem[tempN-1];
           }
           tempItem[tempN-1] = null;
           tempN--;
           return item;
       }
   }
   
   
 
}