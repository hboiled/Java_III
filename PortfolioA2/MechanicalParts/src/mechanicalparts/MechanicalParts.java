/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mechanicalparts;

import BinaryTree.BinaryTree;

/**
 *
 * @author Samuel Lee
 * 30018308
 */
public class MechanicalParts {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // Binary tree insertion manually balanced
        BinaryTree bst = new BinaryTree();
        bst.insert("Locking pins");
        bst.insert("Inserts");
        bst.insert("Spring plungers");
        bst.insert("Cable ties");
        bst.insert("Toggle clamps");
        bst.insert("Bolts");
        bst.insert("Quick release pins");
        bst.insert("Hose clamps");        
        bst.insert("Supports");
        bst.insert("Spacers");
        bst.insert("Wire ropes");
        bst.insert("Plugs");
        bst.insert("Wire ropes");
        bst.insert("Knobs");
        bst.insert("Lever");
                
        // alphabetical order of elements
        // each node is displayed as a subtree
        System.out.println("1. In order traversal: each node is displayed alphabetically, and each node will print its own subtree (if exists, or leaf)");
        bst.inOrderTraversal(bst.getRoot());
        
        System.out.println("\n2. Finding values in the tree: ");
        // method to find nodes based on value
        System.out.println(bst.find("plugs"));
        System.out.println(bst.find("inserts"));
                
        // removal of nodes and effect on tree
        bst.remove("Locking pins");
        bst.remove("plugs");
        bst.remove("lever");
        System.out.println("\n3. Deletion of 3 nodes:");
        System.out.println(bst.getRoot());
    }
    
}
