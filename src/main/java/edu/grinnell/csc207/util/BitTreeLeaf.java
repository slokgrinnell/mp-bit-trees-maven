package edu.grinnell.csc207.util;

/**
 * Represents a leaf node in a BitTree. Leaf nodes store values and do
 * not have child nodes.
 *
 * @author Slok
 */
public class BitTreeLeaf implements BitTreeNode {
  // +--------+------------------------------------------------------
  // | Fields |
  // +--------+

  /**
   * The value stored in this leaf node.
   */
  private String value;

  // +---------+-----------------------------------------------------
  // | Methods |
  // +---------+

  /**
   * Retrieve the value stored at this node.
   *
   * @return
   *   The value stored at this node.
   */
  @Override
  public String getValue() {
    return value;
  } // getValue()

  /**
   * Set the value at this node.
   *
   * @param value
   *   The value to store in this leaf node.
   */
  @Override
  public void setValue(String value) {
    this.value = value;
  } // setValue()

  /**
   * Retrieve the left child of this node.
   *
   * @return
   *   Always null since leaf nodes do not have children.
   */
  @Override
  public BitTreeNode getLeft() {
    return null; // Leaves do not have children.
  } // getLeft()

  /**
   * Set the left child of this node. This operation is unsupported
   * for leaf nodes.
   *
   * @param left
   *   The node to set as the left child.
   * @throws UnsupportedOperationException
   *   Always thrown since leaf nodes cannot have children.
   */
  @Override
  public void setLeft(BitTreeNode left) {
    throw new UnsupportedOperationException("Leaves cannot have children.");
  } // setLeft()

  /**
   * Retrieve the right child of this node.
   *
   * @return
   *   Always null since leaf nodes do not have children.
   */
  @Override
  public BitTreeNode getRight() {
    return null; // Leaves do not have children.
  } // getRight()

  /**
   * Set the right child of this node. This operation is unsupported
   * for leaf nodes.
   *
   * @param right
   *   The node to set as the right child.
   * @throws UnsupportedOperationException
   *   Always thrown since leaf nodes cannot have children.
   */
  @Override
  public void setRight(BitTreeNode right) {
    throw new UnsupportedOperationException("Leaves cannot have children.");
  } // setRight()
} // class BitTreeLeaf
