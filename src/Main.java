import model.*;

import java.io.*;
import java.util.*;

public class Main {
    private static NodeContainer nodeContainer;
    private static PathContainer pathContainer;

    private static int MIN=Integer.MAX_VALUE;
    private static List<NhanhVaCan> nhanhVaCans;
    private static List<Node> l;
    private static NhanhVaCan prev;
    private static Node end,start;






    public static void main(String[] args) {
        nodeContainer=new NodeContainer();
        pathContainer=new PathContainer();
        nhanhVaCans=new ArrayList<>();
        l=new ArrayList<>();


        try {
            String input= "input.txt";
            FileReader fileReader = new FileReader(input);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line;
            while (!Objects.equals(line = bufferedReader.readLine(), "")) {
                if(!line.contains("to")){
                    String[] data=line.split(" -> ");
                    String[] dataParent=data[0].split("-");

                    String nameParent=dataParent[0];
                    int weightParent=Integer.parseInt(dataParent[1]);
                    Node parentNode;
                    if(!nodeContainer.checkExistNode(nameParent)){
                        parentNode=new Node(nameParent,weightParent);
                        nodeContainer.addNode(parentNode);

                    }else {
                        parentNode=nodeContainer.getNode(nameParent);
                    }

                    String[] dataChild=data[1].split(" ");
                    for(String s: dataChild){
                        String[] dataNodeChild=s.split("-");
                        String nameChild=dataNodeChild[0];
                        int weightChild=Integer.parseInt(dataNodeChild[1]);
                        int weightPath=Integer.parseInt(dataNodeChild[2]);
                        Node child;
                        if(nodeContainer.checkExistNode(nameChild)){
                            child=nodeContainer.getNode(nameChild);
                            parentNode.getNodes().add(child);
                        }else {
                            child=new Node(nameChild,weightChild);
                            nodeContainer.addNode(child);
                            parentNode.getNodes().add(child);
                        }

                        Path path=new Path(parentNode.getName()+child.getName(),weightPath);
                        pathContainer.addPath(path);
                    }


                }else {
                    String[] str=line.split(" to ");
                    start=nodeContainer.getNode(str[0]);
                    end=nodeContainer.getNode(str[1]);

                }
            }


            bufferedReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }


        nhanhVaCanTest(start,end);
        writeData();

    }

    private static void writeData(){
        try {
            String outputPath = "output.txt";
            BufferedWriter writer = new BufferedWriter(new FileWriter(outputPath));
            for (NhanhVaCan n:nhanhVaCans){
                StringBuilder ttk = new StringBuilder();
                for (Node node : n.getTtk()) {
                    ttk.append(node.getName()).append(" ");
                }
                StringBuilder kuv = new StringBuilder();
                for (int k : n.getK()) {
                    kuv.append(k).append(" ");
                }
                StringBuilder hv = new StringBuilder();
                for (int h : n.getH()) {
                    hv.append(h).append(" ");
                }
                StringBuilder gv = new StringBuilder();
                for (int g : n.getG()) {
                    gv.append(g).append(" ");
                }
                StringBuilder fv = new StringBuilder();
                for (int f : n.getF()) {
                    fv.append(f).append(" ");
                }
                StringBuilder l1 = new StringBuilder();
                for (Node node: n.getL1()) {
                    l1.append(node.getName()+node.getF()).append(" ");
                }
                StringBuilder l = new StringBuilder();
                for (Node node: n.getL()) {
                    l.append(node.getName()+node.getF()).append(" ");
                }
                String formattedString = String.format("%-3s || %-10s || %-10s || %-10s || %-10s || %-15s || %-20s || %-15s", n.getTt(), ttk, kuv, hv, gv, fv, l1, l);
                writer.write(formattedString);
                writer.newLine();
            }
            StringBuilder kq = new StringBuilder();
            List<String> r=new ArrayList<>();
            String child=null;
            Collections.reverse(nhanhVaCans);



            for (int i=0;i<nhanhVaCans.size();i++){
                String name=nhanhVaCans.get(i).getTt();
                if(i==0){
                    r.add(name);
                    child=name;
                }else {
                    Node node=nodeContainer.getNode(name);
                    if(node.checkExistChild(child)){
                        System.out.println(node.getName());
                        child=node.getName();
                        r.add(node.getName());
                    }
                }
            }
            Collections.reverse(r);
            for(String s:r){
                kq.append(s).append(" ");
            }



            writer.write(kq +" Length: "+MIN);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void nhanhVaCanTest(Node start,Node end){


        if(!l.isEmpty()){
            Node node= l.remove(0);
            if(Objects.equals(start.getName(), end.getName())){
                if(MIN>node.getG()){
                    MIN=node.getG();
                    if(!l.isEmpty()){
                        if(l.get(0).getG()<MIN){
                            NhanhVaCan nhanhVaCan=new NhanhVaCan();
                            nhanhVaCan.setTt(start.getName());
                            nhanhVaCan.setL(new ArrayList<>(l));
                            nhanhVaCan.setLengthTemp(MIN);
                            nhanhVaCans.add(nhanhVaCan);
                            nhanhVaCanTest(l.get(0),end);

                        }else {
                            NhanhVaCan nhanhVaCan=new NhanhVaCan();
                            nhanhVaCan.setTt(start.getName());
                            nhanhVaCan.setL(new ArrayList<>(l));
                            nhanhVaCans.add(nhanhVaCan);

                        }
                        return;
                    }



                }
            }

        }


        NhanhVaCan nhanhVaCan=new NhanhVaCan();
        nhanhVaCan.setTt(start.getName());
        List<Node> l1=new ArrayList<>();
        nhanhVaCan.setTtk(start.getNodes());
        start.getNodes().forEach(node -> {
            Path path=pathContainer.getPath(start.getName()+node.getName());
            nhanhVaCan.getK().add(path.getWeight());
            nhanhVaCan.getH().add(node.getWeight());
            int g=start.getG()+path.getWeight();
            int f=node.getWeight()+g;
            nhanhVaCan.getF().add(f);
            nhanhVaCan.getG().add(g);
            Node node1=new Node(node);
            node1.setG(g);
            node1.setF(f);
            l1.add(node1);

        });


        l1.sort(Comparator.comparingInt(Node::getF));
        l.addAll(0,l1);
        nhanhVaCan.setL1(l1);
        nhanhVaCan.setL(new ArrayList<>(l));
        nhanhVaCans.add(nhanhVaCan);
        prev=nhanhVaCan;

        if(!l.isEmpty()){
            nhanhVaCanTest(l.get(0),end);
        }









    }



}
