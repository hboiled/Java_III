/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hboiled.audioplayer.Playlist;

/**
 *
 * @author 61406
 */
public class Playlist implements Comparable<Playlist>{
    
    private String name;
    private BTree playlist;

    public BTree getPlaylist() {
        return playlist;
    }

    public Node getCurrent() {
        return current;
    }
    private Node current;
    
    public Playlist(String name) {
        this.name = name;
        playlist = new BTree();
        current = null;
    }
    
    public void add(String song) {
        playlist.insert(song);
    }
    
    public void remove(String song) {
        playlist.remove(song);
    }
    
    public void next() {
        if (current != null && current.getRight() != null) {
            current = current.getRight();
        }
    }
    
    public void prev() {
        if (current != null && current.getLeft() != null) {
            current = current.getLeft();
        }
    }
    
    @Override
    public String toString() {
        return name;
    }

    @Override
    public int compareTo(Playlist o) {
        return this.name.compareTo(o.name);
    }
}
