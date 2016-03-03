package PODEM;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class VerilogParser {
	
	/*public static void main(String[] args) throws IOException {

		FileInputStream fstream = new FileInputStream("VerilogTest.v");
		BufferedReader br = new BufferedReader(new InputStreamReader(fstream));
		String strLine;
		
		ArrayList<String> PI = new ArrayList<String>();
		ArrayList<String> PO = new ArrayList<String>();
		
		HashMap<String, node> nodes = new HashMap<String, node>();
		
		String mainModule;
		
		
		//Read File Line By Line
		while ((strLine = br.readLine()) != null)   
		{
		  String[] splitLine = strLine.split("\\s");
		  for(int i = 0; i<splitLine.length; i++)
		  {
			  System.out.println(splitLine[i]);
		  }
		  
		  //Ignore comments
		  if(splitLine[0]!=null)
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
				  }
				  
				  else if(splitLine[0].startsWith("output"))
				  {
					  PO.add(splitLine[2]);
				  }
				  
				  else if(splitLine[0].startsWith("wire"))
				  {
					  node temp = new node();
					  temp.name = splitLine[1].substring(0, 1);
					  temp.value = logic.x;
					  nodes.put(splitLine[1].substring(0, 1), temp);
				  }
				  
				  else if(splitLine[0].startsWith("nand"))
				  {
					  System.out.println("S:" + splitLine[1].substring(0, 1));
					  node temp = nodes.get(splitLine[1].substring(0, 1));
					  temp.gate = "nand";
					  for(int i=2; i<splitLine.length-1; i++)
					  {
						  temp.inputNodes.add(nodes.get(splitLine[i].substring(0, 1)));
					  }
				  }
					  
				  
			  }
			  System.out.println("");
		  }
		}
		
		
		//Close the input stream
		br.close();
	}*/
	
}
