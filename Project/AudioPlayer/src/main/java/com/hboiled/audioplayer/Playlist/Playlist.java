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
    
    public Playlist(String name) {
        this.name = name;
        playlist = new BTree();
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
