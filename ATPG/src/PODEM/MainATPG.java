package PODEM;

import java.io.IOException;
import java.util.*;

public class MainATPG {

	public static void main(String[] args)  throws IOException {
		graph ckt=new graph();
		String fault=new String();				//fault node
		logic sa=logic.zero;		//fault stuck at 'sa'
		Boolean success=false;

		//use the verilog Parser

		while(!success){

			//Include X path check for the current D-frontier

			backtrace.backtrace_func(fault,sa,ckt);
			backtrack.flipPI.push(false);
			backtrack.assignPI.push((backtrace.PI_set));
			ForwardSim.forwardSim(ckt.nodes.get(backtrace.PI_set));

			if(ckt.nodes.get(fault).value == sa) {
				for (int i = 0; i < ckt.PO.size(); i++) {
					if (ckt.nodes.get(ckt.PO.get(i)).value == logic.d || ckt.nodes.get(ckt.PO.get(i)).value == logic.d_bar) {
						success = true;
						break;
					}
				}
			}

			else if(!XpathCheck.xPathCheck(ckt.nodes.get(fault)) || ckt.nodes.get(fault).value == LogicFunctions.not(sa)){
				backtrack.backtrack_func(ckt);
				ForwardSim.forwardSim(ckt.nodes.get(backtrack.assignPI.peek()));

				for (int i = 0; i < ckt.PO.size(); i++) {
					if (ckt.nodes.get(ckt.PO.get(i)).value == logic.d || ckt.nodes.get(ckt.PO.get(i)).value == logic.d_bar) {
						success = true;
						break;
					}
				}
			}
		}


			/*node n1=new node();
			n1.name="a";
			n1.nodenum=1;
			n1.value=logic.one;
			n1.inputNodes=new ArrayList<>();

			node n2=new node();
			n2.name="b";
			n2.nodenum=2;
			n2.inputNodes=new ArrayList<>();

			node n3=new node();
			n3.name="c";
			n3.nodenum=3;
			n3.inputNodes=new ArrayList<>();

			node n4=new node();
			n4.name="d";
			n4.nodenum=4;
			n4.gate="and";
			n4.inputNodes.add(n1);
			n4.inputNodes.add(n2);

			node n5=new node();
			n5.name="e";
			n5.nodenum=5;
			n5.gate="and";
			n5.inputNodes.add(n2);
			n5.inputNodes.add(n3);

			node n6=new node();
			n6.name="f";
			n6.nodenum=6;
			n6.gate="or";
			n6.inputNodes.add(n4);
			n6.inputNodes.add(n5);
			n6.inputNodes.add(n1);

			ckt.addnode("a",n1);

			ckt.addnode("b",n2);
			ckt.addnode("c",n3);
			ckt.addnode("d",n4);
			ckt.addnode("e",n5);
			ckt.addnode("f",n6);

			ckt.addPI("a");
			ckt.addPI("b");
			ckt.addPI("c");

			fault="f";
			backtrace.backtrace_func(fault,sa,ckt);
			System.out.println("output "+backtrace.PI_set+" "+backtrace.PI_value);*/
	}
}

