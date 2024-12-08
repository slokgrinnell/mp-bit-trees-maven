package edu.grinnell.csc207.util;

/**
 * Represents an interior node in a BitTree. Interior nodes do not store
 * values but instead connect to left and right child nodes.
 *
 * @author Slok Rajbhandari
 */
public class BitTreeInteriorNode implements BitTreeNode {
  // +--------+------------------------------------------------------
  // | Fields |
  // +--------+

  /**
   * The left child of this interior node.
   */
  private BitTreeNode left;

  /**
   * The right child of this interior node.
   */
  private BitTreeNode right;

  // +---------+-----------------------------------------------------
  // | Methods |
  // +---------+

  /**
   * Retrieve the value stored at this node.
   *
   * @return
   *   Always null since interior nodes do not store values.
   */
  @Override
  public String getValue() {
    return null; // Interior nodes do not have values.
  }

  /**
   * Set the value at this node. This operation is unsupported for
   * interior nodes.
   *
   * @param value
   *   The value to store.
   * @throws UnsupportedOperationException
   *   Always thrown since interior nodes cannot store values.
   */
  @Override
  public void setValue(String value) {
    throw new UnsupportedOperationException("Interior nodes cannot store values.");
  }

  /**
   * Retrieve the left child of this node.
   *
   * @return
   *   The left child node, or null if none exists.
   */
  @Override
  public BitTreeNode getLeft() {
    return left;
  }

  /**
   * Set the left child of this node.
   *
   * @param left
   *   The node to set as the left child.
   */
  @Override
  public void setLeft(BitTreeNode left) {
    this.left = left;
  }

  /**
   * Retrieve the right child of this node.
   *
   * @return
   *   The right child node, or null if none exists.
   */
  @Override
  public BitTreeNode getRight() {
    return right;
  }

  /**
   * Set the right child of this node.
   *
   * @param right
   *   The node to set as the right child.
   */
  @Override
  public void setRight(BitTreeNode right) {
    this.right = right;
  }
} // class BitTreeInteriorNode
