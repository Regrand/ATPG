package PODEM;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;

public class VerilogParser {

	public static HashMap<String, node> nodes = new HashMap<String, node>();
	public static ArrayList<String> PI = new ArrayList<String>();
	public static ArrayList<String> PO = new ArrayList<String>();
	public static HashMap<String, ArrayList<String>> sensitivityList = new HashMap<String, ArrayList<String>>();

	public static void forwardSim(node n)
	{
		
		n.value = LogicFunctions.compute(n);
		if(sensitivityList.get(n.name)!=null)
		{
			ArrayList<String> forwardNodes = sensitivityList.get(n.name);
			for(String temp : forwardNodes){
				forwardSim(nodes.get(temp));
			}
		}

	}

	public static void putInSensitivityList(String node, String sensitiveNodes)
	{
		if(sensitivityList.containsKey(node))
		  {
			  sensitivityList.get(node).add(sensitiveNodes);
		  }
		  else
		  {
			  ArrayList<String> sen = new ArrayList<String>();
			  sen.add(sensitiveNodes);
			  sensitivityList.put(node, sen);
		  }
	}

	public static void main(String[] args) throws IOException {

		FileInputStream fstream = new FileInputStream("VerilogTest.v");
		BufferedReader br = new BufferedReader(new InputStreamReader(fstream));
		String strLine;

		String mainModule;

		//Read File Line By Line
		while ((strLine = br.readLine()) != null)
		{
		  String[] splitLine = strLine.split("[ ,();]+");
		  for(int i = 0; i<splitLine.length; i++)
		  {
			  System.out.println(splitLine[i]);
		  }

		  //Ignore comments
		  if(splitLine.length!=0)
		  {
			  if(splitLine[0].startsWith("//"))
			  {
				  System.out.println("Comment :");
				  System.out.println(strLine);
			  }

			  //Here we begin the parsing
			  else
			  {
				  if(splitLine[0].startsWith("module"))
				  {
					  mainModule = splitLine[0];
					  System.out.println("Main module is " + mainModule);
				  }

				  else if(splitLine[0].startsWith("input"))
				  {
					  PI.add(splitLine[2]);
					  node temp = new node();
					  temp.name = splitLine[2];
					  temp.gate = "PI";
					  temp.value = logic.x;
					  nodes.put(splitLine[2], temp);
				  }

				  else if(splitLine[0].startsWith("output"))
				  {
					  PO.add(splitLine[2]);
					  node temp = new node();
					  temp.name = splitLine[2];
					  temp.gate = "PO";
					  temp.value = logic.x;
					  nodes.put(splitLine[2], temp);
				  }

				  else if(splitLine[0].startsWith("wire"))
				  {
					  node temp = new node();
					  temp.name = splitLine[1];
					  temp.value = logic.x;
					  nodes.put(splitLine[1], temp);
				  }

				  else if(splitLine[0].startsWith("nand"))
				  {
					  node temp = nodes.get(splitLine[1]);
					  temp.gate = "nand";
					  for(int i=2; i<splitLine.length; i++)
					  {
						  temp.inputNodes.add(nodes.get(splitLine[i]));
						  putInSensitivityList(splitLine[i], splitLine[1]);
					  }

				  }

				  else if(splitLine[0].startsWith("nor"))
				  {
					  node temp = nodes.get(splitLine[1]);
					  temp.gate = "nor";
					  for(int i=2; i<splitLine.length; i++)
					  {
						  temp.inputNodes.add(nodes.get(splitLine[i]));
						  putInSensitivityList(splitLine[i], splitLine[1]);
					  }
				  }

				  else if(splitLine[0].startsWith("and"))
				  {
					  node temp = nodes.get(splitLine[1]);
					  temp.gate = "and";
					  for(int i=2; i<splitLine.length; i++)
					  {
						  temp.inputNodes.add(nodes.get(splitLine[i]));
						  putInSensitivityList(splitLine[i], splitLine[1]);
					  }
				  }

				  else if(splitLine[0].startsWith("or"))
				  {
					  node temp = nodes.get(splitLine[1]);
					  temp.gate = "or";
					  for(int i=2; i<splitLine.length; i++)
					  {
						  temp.inputNodes.add(nodes.get(splitLine[i]));
						  putInSensitivityList(splitLine[i], splitLine[1]);
					  }
				  }

				  else if(splitLine[0].startsWith("xor"))
				  {
					  node temp = nodes.get(splitLine[1]);
					  temp.gate = "xor";
					  for(int i=2; i<splitLine.length; i++)
					  {
						  temp.inputNodes.add(nodes.get(splitLine[i]));
						  putInSensitivityList(splitLine[i], splitLine[1]);
					  }
				  }

				  else if(splitLine[0].startsWith("xnor"))
				  {
					  node temp = nodes.get(splitLine[1]);
					  temp.gate = "xnor";
					  for(int i=2; i<splitLine.length; i++)
					  {
						  temp.inputNodes.add(nodes.get(splitLine[i]));
						  putInSensitivityList(splitLine[i], splitLine[1]);
					  }
				  }

				  else if(splitLine[0].startsWith("not"))
				  {
					  node temp = nodes.get(splitLine[1]);
					  temp.gate = "not";
					  for(int i=2; i<splitLine.length; i++)
					  {
						  temp.inputNodes.add(nodes.get(splitLine[i]));
						  putInSensitivityList(splitLine[i], splitLine[1]);
					  }
				  }

			  }
			  System.out.println("");
		  }
		}


		//Close the input stream
		br.close();

		for(HashMap.Entry<String, node> temp : nodes.entrySet())
		{
			temp.getValue().print_details();
		}

		for(HashMap.Entry<String, ArrayList<String>> temp : sensitivityList.entrySet())
		{
			System.out.print(temp.getKey() + " : ");
			ArrayList<String> temp2 = temp.getValue();
			for(String i : temp2) System.out.print(i + " ");
			System.out.println("");
		}

		node temp = nodes.get("A");
		temp.value = logic.zero;
		nodes.put("A", temp);
		temp = nodes.get("B");
		temp.value = logic.zero;
		nodes.put("B", temp);
		temp = nodes.get("C");
		temp.value = logic.zero;
		nodes.put("C", temp);

		forwardSim(nodes.get("A"));
		forwardSim(nodes.get("B"));
		forwardSim(nodes.get("C"));

		for(HashMap.Entry<String, node> temp1 : nodes.entrySet())
		{
			temp1.getValue().print_details();
		}
		
	}
	
}
