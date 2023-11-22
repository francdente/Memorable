package fr.eurecom.memorable.home;

import java.util.ArrayList;

public class Album {
    private ArrayList<ContentNode> nodes;

    public Album(ArrayList<ContentNode> nodes) {
        this.nodes = nodes;
    }

    public ArrayList<ContentNode> getNodes() {
        return nodes;
    }

    public void setNodes(ArrayList<ContentNode> nodes) {
        this.nodes = nodes;
    }
}
