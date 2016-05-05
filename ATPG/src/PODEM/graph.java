package PODEM;
import java.util.*;

/**
 * Created by Sneha on 03-03-2016.
 */
public class graph {

    public HashMap<String, node> nodes = new HashMap<String, node>();
    public ArrayList<String> PI = new ArrayList<String>();
    public ArrayList<String> PO = new ArrayList<String>();
    public HashMap<String, ArrayList<String>> sensitivityList = new HashMap<String, ArrayList<String>>();


    public void addnode(String newstring,node newnode){
        nodes.put(newstring,newnode);
    }

    public void addPI(String newnode){
        PI.add(newnode);
    }
}