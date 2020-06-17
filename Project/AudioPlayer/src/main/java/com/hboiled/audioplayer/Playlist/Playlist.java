/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hboiled.audioplayer.Playlist;

import java.util.Collections;

/**
 *
 * @author 61406
 */
public class Playlist implements Comparable<Playlist> {

    private String name;
    private BTree playlist;
    private Node current;

    public Playlist(String name) {
        this.name = name;
        playlist = new BTree();
        current = null;
    }
    
    public void setCurrent(Node song) {
        current = playlist.find(song.getValue());
    }


    public Node getCurrent() {
        return current;
    }

    public BTree getPlaylist() {
        return playlist;
    }
    
    public void add(String song) {
        playlist.insert(song);
    }

    public void remove(String song) {
        playlist.remove(song);
    }

    public void next() {
        if (current != null && current.getRight() != null) {
            System.out.println("yes right");
            //current = Collections.binarySearch(playlist.getHelperList(), "a");
        } else {
            System.out.println("no right");
        }
    }

    public void prev() {
        if (current != null && current.getLeft() != null) {
            System.out.println("yes left");
            current = current.getLeft();
        } else {
            System.out.println("no left");
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
