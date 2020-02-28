/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BinaryTree;

/**
 *
 * @author 61406
 */
public class BinaryTree {
    
    private Node root;
    
    public Node getRoot() {
        return root;
    }
    
    public BinaryTree() {
        root = null;
    }
    
    // print elements in alphabetical order
    public void inOrderTraversal(Node n) {
        if (n == null) {
            return;
        }
        inOrderTraversal(n.getLeft());
        System.out.println(n);
        inOrderTraversal(n.getRight());
    }
    
    // method which makes a recursive call to another insert method
    public void insert(String val) {
        root = insertNode(root, new Node(val));
    }
    // method which can be called recursively which returns a node by traversing
    // through the tree, checking whether it needs to go down right or left
    // until it hits the base case (null value found), at which point node is inserted
    private Node insertNode(Node current, Node newN) {
        if (current == null) {
            return newN;
        }
        if (newN.getValue().compareToIgnoreCase(current.getValue()) < 0) {
            current.setLeft(insertNode(current.getLeft(), newN));
        } else if (newN.getValue().compareToIgnoreCase(current.getValue()) > 0) {
            current.setRight(insertNode(current.getRight(), newN));
        }
        
        return current;
    }
    
    // find method which makes a recursive call from the root
    public Node find(String val) {
        return findNode(val, root);
    }
    // recursively travels down the, until target node is found
    private Node findNode(String val, Node node) {
        while (node != null) {
            if (val.compareToIgnoreCase(node.getValue()) < 0) {
                node = node.getLeft();
            } else if (val.compareToIgnoreCase(node.getValue()) > 0) {
                node = node.getRight();
            } else {
                return node;
            }
        }
        return null;
    }
    
    public void remove(String val) {
        root = remove(val, root);
    }
    
    // uses recursion to find targetted node and removes it
    // checks if target node has children which would have to be rearranged
    // nodes with one child, have their left or right (opposite of its designation from parent)
    // child replace the deleted node
    // if it has 2 children, take the min of the subtree to the right of target
    // node and place it in the spot of the removed node, and remove the duplicate   
    private Node remove(String val, Node node) {
        if (node == null) {
            return node;
        }
        
        if (val.compareToIgnoreCase(node.getValue()) < 0) {
            node.setLeft(remove(val, node.getLeft()));
        } else if (val.compareToIgnoreCase(node.getValue()) > 0) {
            node.setRight(remove(val, node.getRight()));
        } else {
            if (node.getLeft() == null) {
                return node.getRight();
            } else if (node.getRight() == null) {
                return node.getLeft();
            }
            node.setValue(findMin(node.getRight()));
            node.setRight(remove(node.getValue(), node.getRight()));
        }
        
        return node;
    }
    // find the min value of a tree or subtree
    private String findMin(Node node) {
        String mval = node.getValue();
        while (node.getLeft() != null) {
            mval = node.getLeft().getValue();
            node = node.getLeft();
        }
        return mval;
    }
}