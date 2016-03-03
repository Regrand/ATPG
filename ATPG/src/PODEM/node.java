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
    ArrayList<node> inputNodes;

    public node(){
   
    	this.name = null;
    	this.nodenum = -1;
    	this.gate = null;
    	this.value = logic.x;
        this.inputNodes = new ArrayList<node>();

    }

}



   