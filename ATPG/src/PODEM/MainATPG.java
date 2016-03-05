package PODEM;

import java.io.IOException;
import java.util.*;

public class MainATPG {

	public static void main(String[] args)  throws IOException {
		graph circuit=new graph();

		node n1=new node();
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

		circuit.addnode("a",n1);
		circuit.addnode("b",n2);
		circuit.addnode("c",n3);
		circuit.addnode("d",n4);
		circuit.addnode("e",n5);
		circuit.addnode("f",n6);

		circuit.addPI("a");
		circuit.addPI("b");
		circuit.addPI("c");


		backtrace.backtrace_func("f",logic.zero,circuit);
		System.out.println("output "+backtrace.PI_set+" "+backtrace.PI_value);

	}
}

