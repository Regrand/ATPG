package PODEM;

import java.util.*;

/**
 * Created by Sneha on 28-02-2016.
 */

public class backtrace {

    static String min_PI;//the PI that the min_path converges to
    static String min_PIt;
    static int min_count;//length of the min path
    static int min;

    static String max_PI;//the PI that the max_path converges to
    static String max_PIt;
    static int max_count;//length of the max path
    static int max;

    static String obj;
    static logic obj_val;

    static String PI_set;
    static logic PI_value;

    static HashMap<String, Integer> node_count = new HashMap<String, Integer>();

    public static void find_path(String start, graph ckt){
        int count;
        int nPI=ckt.PI.size();
        String root;
        String currentin;
        node rootnode;
        LinkedList<String> fifo = new LinkedList();
        ArrayList<String> visited_s= new ArrayList();

        for(int i =0;i<nPI;i++){
            node_count.put(ckt.PI.get(i),-1);
        }

        if(ckt.PI.contains(ckt.nodes.get(start))){
            node_count.put(start,0);
            min_count=0;
            max_count=0;
            min_PIt=start;
            max_PIt=start;
        }

        else{
            max_PIt=ckt.PI.get(0);
            min_PIt=ckt.PI.get(0);

            fifo.addFirst(start);
            visited_s.add(start);
            node_count.put(start,0);

            while(fifo.size()>0) {

                root = fifo.removeFirst();//key to the new root
                rootnode = ckt.nodes.get(root);
                count=node_count.get(root)+1;

                for (int i = 0; i < rootnode.inputNodes.size(); i++) {
                    currentin=rootnode.inputNodes.get(i).name;
                    if (!visited_s.contains(currentin) && rootnode.inputNodes.get(i).value==logic.x) {
                        node_count.remove(currentin);
                        node_count.put(currentin, count);
                        visited_s.add(currentin);

                        if (!ckt.PI.contains(currentin)) {
                            fifo.addLast(currentin);
                        }
                    }
                }
            }
        }

        min_count=Integer.MAX_VALUE;
        max_count=-1;

        for(int i=0;i<nPI;i++){
            if(node_count.get(ckt.PI.get(i))>-1){
                if(node_count.get(ckt.PI.get(i))>max_count) {
                    max_PIt = ckt.PI.get(i);
                    max_count = node_count.get(ckt.PI.get(i));
                }

                if(node_count.get(ckt.PI.get(i))<min_count){
                    min_PIt=ckt.PI.get(i);
                    min_count=node_count.get(ckt.PI.get(i));
                }
            }
        }

    }

    public static String find_min(String obj, graph ckt) {

        int nIN = ckt.nodes.get(obj).inputNodes.size();
        String current;
        String min_IN=new String();
        min = Integer.MAX_VALUE;

        for (int i = 0; i < nIN; i++) {
            if(ckt.nodes.get(obj).inputNodes.get(i).value==logic.x) {

                current = ckt.nodes.get(obj).inputNodes.get(i).name;
                find_path(current, ckt);

                if (min_count < min) {
                    min_IN = current;
                    min = min_count;
                    min_PI = min_PIt;
                }
            }
        }
        return min_IN;
    }

    public static String find_max(String obj, graph ckt) {

        int nIN = ckt.nodes.get(obj).inputNodes.size();
        String current;
        String max_IN=new String();
        max = -1;

        for (int i = 0; i < nIN; i++) {
            if(ckt.nodes.get(obj).inputNodes.get(i).value==logic.x) {

                current = ckt.nodes.get(obj).inputNodes.get(i).name;
                find_path(current, ckt);

                if (max_count > max) {
                    max_IN = current;
                    max = max_count;
                    max_PI = max_PIt;
                }
            }
        }
        return max_IN;
    }

    public static void objective(String g, logic val, graph ckt){

        int nIN=ckt.nodes.get(g).inputNodes.size();
        int count=0;

        for(int i =0;i<nIN;i++){
            if(ckt.nodes.get(g).inputNodes.get(i).value==logic.x){
                count=count+1;
            }
        }
        String temp = new String();
        //System.out.println("count is "+count);
        if(count==1)
            temp=find_min(g,ckt);
        else
            temp=find_max(g,ckt);

        if(ckt.nodes.get(obj).gate=="nand" || ckt.nodes.get(obj).gate=="nor"|| ckt.nodes.get(obj).gate=="not")
            obj_val=LogicFunctions.not(val);
        else if(ckt.nodes.get(obj).gate=="xnor" || ckt.nodes.get(obj).gate=="xor")
        	obj_val=logic.zero;
        else
            obj_val=val;
        
        obj = temp;
    }

    public static void backtrace_func(String fault, logic sa, graph ckt){
    	
    	min_PI = null;//the PI that the min_path converges to
    	min_PIt = null;
    	min_count= -1;//length of the min path
    	min = -1;

    	max_PI = null;//the PI that the max_path converges to
    	max_PIt = null;
    	max_count = -1;//length of the max path
    	max = -1;

    	obj = null;
    	obj_val = null;

    	PI_set = null;
    	PI_value = null;

    	node_count.clear();

        System.out.println("entered backtrace");
        System.out.println(fault);
        if(ckt.nodes.get(fault).gate=="PI") {
            PI_set=fault;
            PI_value=LogicFunctions.not(sa);
        }

        else{
            obj=fault;
            obj_val=LogicFunctions.not(sa);
            System.out.println(obj);
            System.out.println(obj_val);
            System.out.println(ckt.nodes.get(obj).gate);
            while(ckt.nodes.get(obj).inputNodes.size()>0) {
                objective(obj,obj_val,ckt);
            }
            PI_set=obj;
            PI_value=obj_val;
        }
    }
}