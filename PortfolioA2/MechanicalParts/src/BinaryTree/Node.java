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
public class Node {
    private String value;   
    private Node left, right;

    public String getValue() {
        return value;
    }
    
    public void setValue(String val) {
        value = val;
    }

    public Node getLeft() {
        return left;
    }

    public Node getRight() {
        return right;
    }
    
    public void setLeft(Node left) {
        this.left = left;
    }

    public void setRight(Node right) {
        this.right = right;
    }
    
    public Node(String val) {
        value = val;
        left = right = null;
    }
    
    @Override
    public String toString() {
        String des = value + ": ";
        
        if (left == null & right == null) {
            des += "is leaf. ";
        } 
        if (left != null) {
            des += "Left: " + left.toString() + ". ";
        }
        if (right != null) {
            des += "Right: " + right.toString() + ". ";
        }
        
        return des;
    }
}
