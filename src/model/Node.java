package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Node {
    private String name;
    private int weight;
    private List<Node> nodes;
    private int g;
    private int f;

    public Node(Node node) {
        this.name=node.getName();
        this.weight=node.getWeight();
        this.nodes=node.getNodes();
    }

    public boolean checkExistChild(String name){
        for (Node node:nodes){
            if(Objects.equals(node.getName(), name)){
                return true;
            }
        }
        return false;
    }

    public Node(String name, int weight) {
        this.name = name;
        this.weight = weight;
        this.nodes=new ArrayList<>();
        this.g=0;
    }

    public Node(String name) {
        this.name=name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Node node = (Node) o;
        return Objects.equals(name, node.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    public int getG() {
        return g;
    }

    public void setG(int g) {
        this.g = g;
    }

    public int getF() {
        return f;
    }

    public void setF(int f) {
        this.f = f;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public List<Node> getNodes() {
        return nodes;
    }

    public void setNodes(List<Node> nodes) {
        this.nodes = nodes;
    }

    @Override
    public String toString() {
        return "Node{" +
                "name='" + name + '\'' +
                ", weight=" + weight +
                ", nodes=" + nodes +
                '}';
    }
}
