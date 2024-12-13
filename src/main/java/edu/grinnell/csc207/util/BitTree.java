package edu.grinnell.csc207.util;

import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Scanner;

/**
 * Trees intended to be used in storing mappings between fixed-length 
 * sequences of bits and corresponding values.
 *
 * @author Slok
 */
public class BitTree {
  // +--------+------------------------------------------------------
  // | Fields |
  // +--------+

  /**
   * The depth of the tree, representing the length of bit sequences
   * that can be stored in this tree.
   */
  private final int depth;

  /**
   * The root node of the tree. This node may be null if the tree
   * has not been initialized.
   */
  private BitTreeNode root;

  // +--------------+------------------------------------------------
  // | Constructors |
  // +--------------+

  /**
   * Create a tree with the given depth.
   *
   * @param n
   *   The depth of the tree.
   */
  public BitTree(int n) {
    this.depth = n;
    this.root = null;
  } // BitTree(int)

  // +---------------+-----------------------------------------------
  // | Local helpers |
  // +---------------+

  /**
   * Validate whether the given bit sequence is valid for this tree.
   *
   * @param bits
   *   The bit sequence to validate.
   * @return
   *   True if valid; false otherwise.
   */
  private boolean isValidBits(String bits) {
    return bits.length() == depth;
  }

  /**
   * Create a path in the tree for the given bit sequence, adding 
   * nodes as necessary.
   *
   * @param bits
   *   The bit sequence for which to create a path.
   * @return
   *   The node at the end of the path.
   */
  private BitTreeNode createPath(String bits) {
    BitTreeNode current = root;
    if (root == null) {
      root = new BitTreeInteriorNode();
      current = root;
    }
    char c;
    for (int i = 0; i < bits.length(); i++) {
      c = bits.toCharArray()[i];
      if (c == '0') {
        if (current.getLeft() == null) {
          current.setLeft((i == bits.length() - 1) ? new BitTreeLeaf() : new BitTreeInteriorNode());
        }
        current = current.getLeft();
      } else if (c == '1') {
        if (current.getRight() == null) {
          current.setRight((i == bits.length() - 1) ? new BitTreeLeaf() : new BitTreeInteriorNode());
        }
        current = current.getRight();
      }
    }
    return current;
  }

  // +---------+-----------------------------------------------------
  // | Methods |
  // +---------+

  /**
   * Add a mapping from a bit sequence to a value.
   *
   * @param bits
   *   The bit sequence (must be of appropriate length).
   * @param value
   *   The value to store at the bit sequence.
   * @throws IndexOutOfBoundsException
   *   If the bit sequence is invalid or inappropriate.
   */
  public void set(String bits, String value) {
    if (!isValidBits(bits)) {
      throw new IndexOutOfBoundsException("Invalid bit sequence");
    }
    BitTreeNode node = createPath(bits);
    node.setValue(value);
  } // set(String, String)

  /**
   * Retrieve the value associated with a bit sequence.
   *
   * @param bits
   *   The bit sequence (must be of appropriate length).
   * @return
   *   The value associated with the bit sequence.
   * @throws IndexOutOfBoundsException
   *   If the bit sequence is invalid or not found.
   */
  public String get(String bits) {
    if (!isValidBits(bits)) {
      throw new IndexOutOfBoundsException("Invalid bit sequence");
    }
    BitTreeNode current = root;
    for (char c : bits.toCharArray()) {
      if (current == null) {
        throw new IndexOutOfBoundsException("Path does not exist");
      }
      current = (c == '0') ? current.getLeft() : current.getRight();
    }
    if (current == null || current.getValue() == null) {
      throw new IndexOutOfBoundsException("No value found at path");
    }
    return current.getValue();
  } // get(String)

  /**
   * Print out the contents of the tree in CSV format.
   *
   * @param pen
   *   The writer to which the tree is printed.
   */
  public void dump(PrintWriter pen) {
    dumpHelper(pen, root, "");
  } // dump(PrintWriter)

  /**
   * Recursively print the contents of the tree in CSV format.
   *
   * @param pen
   *   The writer to which the tree is printed.
   * @param node
   *   The current node being processed.
   * @param prefix
   *   The path to the current node.
   */
  private void dumpHelper(PrintWriter pen, BitTreeNode node, String prefix) {
    if (node == null) {
      return;
    }
    if (node.getValue() != null) {
      pen.println(prefix + "," + node.getValue());
    }
    dumpHelper(pen, node.getLeft(), prefix + "0");
    dumpHelper(pen, node.getRight(), prefix + "1");
  }

  /**
   * Load mappings from an input stream into the tree.
   *
   * @param source
   *   The input stream containing mappings in CSV format.
   */
  public void load(InputStream source) {
    Scanner scanner = new Scanner(source);
    while (scanner.hasNextLine()) {
      String[] parts = scanner.nextLine().split(",", 2);
      if (parts.length == 2) {
        set(parts[0], parts[1]);
      }
    }
    scanner.close();
  } // load(InputStream)

} // class BitTree
