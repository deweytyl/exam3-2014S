import java.io.PrintWriter;

import java.util.Comparator;
import java.util.Iterator;

/**
 * Code for tracing what happens in a failed test.
 * 
 * @author Samuel A. Rebelsky
 * @author Your Name Here
 */
public class BSTTrace
{
  // +------+------------------------------------------------------------
  // | Main |
  // +------+

  public static void main(String[] args)
    throws Exception
  {
    PrintWriter pen = new PrintWriter(System.out, true);
    BST<String, String> dict = new BST<String, String>(new Comparator<String>()
      {
        public int compare(String left, String right)
        {
          return left.compareTo(right);
        } // compare(String, String)
      });
    
    // Insert code here
    
    dict.dump(pen);
  } // main(String[])

  // +-----------+-------------------------------------------------------
  // | Utilities |
  // +-----------+

} // BSTTrace
