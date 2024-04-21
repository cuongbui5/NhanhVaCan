package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class PathContainer {
    private List<Path> paths;

    public PathContainer() {
        this.paths = new ArrayList<>();
    }
    public void addPath(Path path){
        paths.add(path);
    }

    public List<Path> getPaths() {
        return paths;
    }

    public void setPaths(List<Path> paths) {
        this.paths = paths;
    }

    public Path getPath(String name){
        for (Path path : paths){
            if(Objects.equals(path.getName(), name)){
                return path;
            }
        }
        throw new RuntimeException("Not found path with name: "+name);



    }
}
