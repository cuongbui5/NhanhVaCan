package model;

import java.util.ArrayList;
import java.util.List;

public class NhanhVaCan {
    private String tt;
    private List<Node> ttk;
    private List<Integer> k;
    private List<Integer> h;
    private List<Integer> g;
    private List<Integer> f;
    private List<Node> l1;
    private List<Node> l;
    private int lengthTemp;

    public int getLengthTemp() {
        return lengthTemp;
    }

    public void setLengthTemp(int lengthTemp) {
        this.lengthTemp = lengthTemp;
    }

    public String getTt() {
        return tt;
    }

    public void setTt(String tt) {
        this.tt = tt;
    }

    public List<Node> getTtk() {
        return ttk;
    }

    public void setTtk(List<Node> ttk) {
        this.ttk = ttk;
    }

    public List<Integer> getK() {
        return k;
    }

    public void setK(List<Integer> k) {
        this.k = k;
    }

    public List<Integer> getH() {
        return h;
    }

    public void setH(List<Integer> h) {
        this.h = h;
    }

    public List<Integer> getG() {
        return g;
    }

    public void setG(List<Integer> g) {
        this.g = g;
    }

    public List<Integer> getF() {
        return f;
    }

    public void setF(List<Integer> f) {
        this.f = f;
    }

    public List<Node> getL1() {
        return l1;
    }

    public void setL1(List<Node> l1) {
        this.l1 = l1;
    }

    public List<Node> getL() {
        return l;
    }

    public void setL(List<Node> l) {
        this.l = l;
    }

    public NhanhVaCan() {
        ttk=new ArrayList<>();
        k=new ArrayList<>();
        h=new ArrayList<>();
        g=new ArrayList<>();
        f=new ArrayList<>();
        l1=new ArrayList<>();
        l=new ArrayList<>();
    }
}
