package PODEM;
import java.util.*;


/**
 * Created by Sneha on 28-02-2016.
 */


public class node {
	
	String name;
    int nodenum;
    logic value;
    String gate;
    String fault;
    ArrayList<node> inputNodes;

    public node(){
   
    	this.name = null;
    	this.nodenum = -1;
    	this.gate = null;
    	this.value = logic.x;
    	this.fault = "none";
        this.inputNodes = new ArrayList<node>();

    }
    
    //Print all node details. Works even when fields are null
    //(or list of input Node is empty)
    public void print_details()
    {
    	System.out.println("Name: " + this.name);
    	System.out.println("Gate: " + this.gate);
    	System.out.println("Nodenum: " + this.nodenum);
    	System.out.println("Value: " + this.value);
    	
    	System.out.println("List of input node names:");
    	for(int i=0; i<this.inputNodes.size(); i++){
    		System.out.print(" " + this.inputNodes.get(i).name);
    	}
    	System.out.println("");
    	System.out.println("");
    }

}



   