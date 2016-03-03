package PODEM;
import java.util.*;

/**
 * Created by Sneha on 03-03-2016.
 */
public class graph {
    HashMap<String, node> nodes = new HashMap<String, node>();
    ArrayList <node> PI=new ArrayList<>(); //nodes to the graph other than the PI

    public void addnode(String newstring,node newnode){
        nodes.put(newstring,newnode);
    }

    public void addPI(node newnode){
        PI.add(newnode);
    }
    /*public static void main(String[] args){
    	
    	graph main = new graph();
    	node nw = new node();
    	
    	//insert elements
    	main.nodes.put("a", nw);
    	//get elements
    	nw = main.nodes.get("a");
    }*/
}