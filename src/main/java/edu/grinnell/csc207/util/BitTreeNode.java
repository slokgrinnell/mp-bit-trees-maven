package edu.grinnell.csc207.util;

/**
 * Interface representing a node in a BitTree. Nodes can either be
 * interior nodes or leaves. Leaves store values, while interior nodes
 * connect to other nodes.
 *
 * @author Slok Rajbhandari
 */
public interface BitTreeNode {
  // +---------+-----------------------------------------------------
  // | Methods |
  // +---------+

  /**
   * Retrieve the value stored at this node.
   *
   * @return
   *   The value stored at the node, or null if this is an interior node.
   */
  String getValue();

  /**
   * Set the value stored at this node. This operation should only
   * be valid for leaf nodes.
   *
   * @param value
   *   The value to store.
   */
  void setValue(String value);

  /**
   * Retrieve the left child of this node.
   *
   * @return
   *   The left child node, or null if none exists.
   */
  BitTreeNode getLeft();

  /**
   * Set the left child of this node.
   *
   * @param left
   *   The node to set as the left child.
   */
  void setLeft(BitTreeNode left);

  /**
   * Retrieve the right child of this node.
   *
   * @return
   *   The right child node, or null if none exists.
   */
  BitTreeNode getRight();

  /**
   * Set the right child of this node.
   *
   * @param right
   *   The node to set as the right child.
   */
  void setRight(BitTreeNode right);
} // interface BitTreeNode
