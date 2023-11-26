package fr.eurecom.memorable.home;

import java.util.ArrayList;

public class Album {
    private ArrayList<ContentNode> nodes;
    private int id;

    public Album(int id, ArrayList<ContentNode> nodes) {
        this.nodes = nodes;
    }

    public ArrayList<ContentNode> getNodes() {
        return nodes;
    }

    public void addNode(ContentNode node) {
        nodes.add(node);
    }

    public void setNodes(ArrayList<ContentNode> nodes) {
        this.nodes = nodes;
    }

    public int getId(){
        return id;
    }

    public int getLength() {
        return nodes.size();
    }
}
