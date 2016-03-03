package PODEM;

import java.util.*;

/**
 * Created by Sneha on 28-02-2016.
 */

public class backtrace {
    int count=0;
    String min;
    String max;
    HashMap<String, Integer> node_count = new HashMap<String, Integer>();

    public void find_path(String start, graph ckt){
        int n=ckt.nodes.size()+ckt.PI.size();
        int nPI=ckt.PI.size();
        String root;
        node rootnode;
        LinkedList<String> fifo = new LinkedList();
        ArrayList<String> visited_s= new ArrayList();

        max=ckt.PI.get(0).name;
        min=ckt.PI.get(0).name;;

        fifo.addFirst(start);
        visited_s.add(start);
        node_count.put(start,0);

        while(fifo.size()>0) {
            //count = count + 1;

            root = fifo.removeFirst();//key to the new root
            rootnode = ckt.nodes.get(root);
            count=node_count.get(rootnode.name)+1;

            for (int i = 0; i < rootnode.inputNodes.size(); i++) {
                if (!visited_s.contains(rootnode.inputNodes.get(i).name)) {
                    node_count.put(rootnode.inputNodes.get(i).name, count);
                    visited_s.add(rootnode.inputNodes.get(i).name);
                    if (!ckt.PI.contains(rootnode.inputNodes.get(i))){
                        fifo.addLast(rootnode.inputNodes.get(i).name);
                    }
                }
            }
        }

        for(int i=1;i<nPI;i++){
            if(node_count.get(ckt.PI.get(i).name)<node_count.get(min))
                min=ckt.PI.get(i).name;

            if(node_count.get(ckt.PI.get(i).name)>node_count.get(max))
                max=ckt.PI.get(i).name;
        }
    }
}