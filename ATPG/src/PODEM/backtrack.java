package PODEM;

import java.util.*;

/**
 * Created by goenk_000 on 19-03-2016.
 */
public class backtrack{
    static Stack<String> assignPI=new Stack<>();
    static Stack<Boolean> flipPI=new Stack<>();
    static String current;

    public static void backtrack_func(graph ckt){
        if(flipPI.empty()){
            System.out.println("stack empty");
        }
        else {
            if (flipPI.pop()) {
                assignPI.pop();
                backtrack.backtrack_func(ckt);
            } else {
                current = assignPI.peek();
                flipPI.push(true);
                ckt.nodes.get(current).value = LogicFunctions.not(ckt.nodes.get(current).value);
            }
        }
    }

}
