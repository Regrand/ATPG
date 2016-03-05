package PODEM;
import java.util.*;

/**
 * Created by Sneha on 03-03-2016.
 */
public class graph {
    HashMap<String, node> nodes = new HashMap<String, node>();
    ArrayList <String> PI=new ArrayList<>(); //nodes to the graph other than the PI

    public void addnode(String newstring,node newnode){
        nodes.put(newstring,newnode);
    }

    public void addPI(String newnode){
        PI.add(newnode);
    }
}