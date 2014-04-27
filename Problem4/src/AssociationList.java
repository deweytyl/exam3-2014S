import java.io.PrintWriter;
import java.util.Iterator;

/**
 * Dictionaries implemented as linked structures.  
 *
 * @author Samuel A. Rebelsky
 * @author Your Name Here
 */
public class AssociationList<K, V>
    implements Dictionary<K, V>
{

  // +-------+-----------------------------------------------------------
  // | Notes |
  // +-------+
  /*
      We implement dictionaries using unordered linked lists with a dummy
      node at the front.  New elements are added at the front because
      we predict that get is more frequently called on recent elements
      (or maybe just because we're lazy).

      To help with various operations, an internal find method finds
      a key and returns the node immediately preceding the key.
   */

  // +--------+----------------------------------------------------------
  // | Fields |
  // +--------+

  /**
   * The front of the linked list.
   */
  Node front;

  // +--------------+----------------------------------------------------
  // | Constructors |
  // +--------------+

  /**
   * Create a new association list.
   */
  public AssociationList()
  {
    this.front = new Node(null, null);
  } // AssociationList

  // +-----------+-------------------------------------------------------
  // | Observers |
  // +-----------+

  public void dump(PrintWriter pen)
  {
    Node current = this.front.next;
    while (current != null)
      {
        pen.println(current.key + ": " + current.value);
        current = current.next;
      } // while
  } // dump(PrintWriter)

  public V get(K key)
    throws Exception
  {
    Node prev = find(key);
    return prev.next.value;
  } // get(K)

  public boolean containsKey(K key)
  {
    try
      {
        // If find succeeds, the key is there.
        find(key);
        return true;
      }
    catch (Exception e)
      {
        // If find doesn't succeed, the key is not there.
        return false;
      } // try/catch
  } // containsKey(K)

  // +----------+--------------------------------------------------------
  // | Mutators |
  // +----------+

  public void set(K key, V value)
  {
    try
      {
        Node prev = find(key);
        prev.next.value = value;
      } // try 
    catch (Exception e)
      {
        this.front.next = new Node(key, value, this.front.next);
      } // catch (Exception)
  } // set(K,V)

  public void remove(K key)
  {
    try 
      {
        // Find the predecessor to the node containing the key.
        Node prev = find(key);
        // Skip over the node.  Yay garbage collection!
        prev.next = prev.next.next;
      } // try
    catch (Exception e)
      { 
        // Do nothing.  We just aren't allowed to throw an exception.
      } // catch(Exception)
  } // remove(K)

  public void clear()
  {
    // I love garbage collection.  In C, we'd have to individually
    // free all of the nodes.
    front.next = null;
  } // clear

  // +-----------+-------------------------------------------------------
  // | Iterators |
  // +-----------+

  public Iterator<V> iterator()
  {
    return new Iterator<V>()
      {
        Node current = front;
        public V next()
        {
          current = current.next;
          return current.value;
        } // next()

        public boolean hasNext()
        {
          return current.next != null;
        } // hasNext

        public void remove()
          throws UnsupportedOperationException
        {
          throw new UnsupportedOperationException();
        } // remove
      }; // new Iterator<V>
  } // iterator()

  public Iterator<K> keysIterator()
  {
    return new Iterator<K>()
      {
        Node current = front;
        public K next()
        {
          current = current.next;
          return current.key;
        } // next()

        public boolean hasNext()
        {
          return current.next != null;
        } // hasNext

        public void remove()
          throws UnsupportedOperationException
        {
          throw new UnsupportedOperationException();
        } // remove
      }; // new Iterator<K>
  } // keyIterator()

  /**
   * Get an Iterable for the keys.  See explanation in Dictionary.java.
   */
  public Iterable<K> keys()
  {
    return new Iterable<K>()
      {
        public Iterator<K> iterator()
        {
          return AssociationList.this.keysIterator();
        } // iterator()
      }; // new Iterable<K>
  } // keys()

  // +-----------------+-------------------------------------------------
  // | Local Utilities |
  // +-----------------+

  /**
   * Find the node with a specified key.
   *
   * @return prev, the node immediately before the found node
   * @throws Exception if no node has the given key.
   */
  public Node find(K key)
    throws Exception
  {
    Node prev = this.front;
    while (prev.next != null)
      {
        if (key.equals(prev.next.key))
          {
            return prev;
          } // if
        prev = prev.next; // Thanks EW, who doesn't care
      } // if

    // If we've gotten through the while loop, no elements
    // remain, and so it's not there.
    throw new Exception("No element with key '" + key + "'");
  } // find

  /**
   * Nodes in a linked dictionary.
   */
  class Node
  {
    // +--------+----------------------------------------------------------
    // | Fields |
    // +--------+

    /**
     * The key in the key/value pair.
     */
    K key;

    /**
     * The value in the key/value pair.
     */
    V value;

    /**
     * The next node.
     */
    Node next;

    // +--------------+----------------------------------------------------
    // | Constructors |
    // +--------------+

    /**
     * Create a new key/value pair with no successor.
     */
    public Node(K key, V value)
    {
      this(key, value, null);
    } // Node(K,V)

    /**
     * Create a new key/value pair with a designated successor.
     */
    public Node(K key, V value, Node next)
    {
      this.key = key;
      this.value = value;
      this.next = next;
    } // Node(K,V,Node<K,V>)
  } // Node   
} // AssociationList<K,V>

