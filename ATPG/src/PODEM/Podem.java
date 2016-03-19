package PODEM;

import java.util.ArrayList;

public class Podem {

	/*public static void main(String[] args) {

		System.out.print("Hello, World");

	}*/
	public static void main(String[] args){
		graph circuit=new graph();

		node n1=new node();
		n1.name="a";
		n1.nodenum=1;
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

		circuit.addnode("d",n4);
		circuit.addnode("e",n5);
		circuit.addnode("f",n6);

		//circuit.addPI(n1);
		//circuit.addPI(n2);
		//circuit.addPI(n3);

		backtrace b=new backtrace();
		b.find_path("f",circuit);
		System.out.println("a "+b.node_count.get("a"));
		System.out.println("b "+b.node_count.get("b"));
		System.out.println("c "+b.node_count.get("c"));
		System.out.println("d "+b.node_count.get("d"));
		System.out.println("e "+b.node_count.get("e"));
		System.out.println("f "+b.node_count.get("f"));
		System.out.println("min "+b.min);
		System.out.println("max "+b.max);

	}
}
