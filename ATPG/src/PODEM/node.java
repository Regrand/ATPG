package PODEM;
import java.util.*;


/**
 * Created by Sneha on 28-02-2016.
 */
public class node {
    int nodenum;
    int value;
    String gate;
    ArrayList input;

    public node(){
        input= new ArrayList();
    }

    public void add_node(int newnode){
        input.add(newnode);
    }

    public void initialize(String name, int number, int val){
        nodenum=number;
        gate=name;
        value=val;
    }

}
