package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class NodeContainer {
    private final List<Node> nodes;

    public List<Node> getNodes() {
        return nodes;
    }

    public NodeContainer() {
        nodes=new ArrayList<>();
    }
    public void addNode(Node node){
        nodes.add(node);
    }

    public boolean checkExistNode(String name){
        for (Node node : nodes){
            if(Objects.equals(node.getName(), name)){
                return true;
            }
        }

        return false;


    }

    public Node getNode(String name){
        for (Node node : nodes){
            if(Objects.equals(node.getName(), name)){
                return node;
            }
        }
        throw new RuntimeException("Not found node with name: "+name);



    }


}
