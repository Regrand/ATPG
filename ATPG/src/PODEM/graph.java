package PODEM;
import java.util.*;

/**
 * Created by Sneha on 03-03-2016.
 */
public class graph {

    public static HashMap<String, node> nodes = new HashMap<String, node>();
    public static ArrayList<String> PI = new ArrayList<String>();
    public static ArrayList<String> PO = new ArrayList<String>();
    public static HashMap<String, ArrayList<String>> sensitivityList = new HashMap<String, ArrayList<String>>();


    public void addnode(String newstring,node newnode){
        nodes.put(newstring,newnode);
    }

    public void addPI(String newnode){
        PI.add(newnode);
    }
}