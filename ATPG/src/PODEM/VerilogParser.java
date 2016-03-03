package PODEM;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;

public class VerilogParser {
	
	public static void main(String[] args) throws IOException {

		FileInputStream fstream = new FileInputStream("VerilogTest.v");
		BufferedReader br = new BufferedReader(new InputStreamReader(fstream));
		String strLine;
		//Read File Line By Line
		while ((strLine = br.readLine()) != null)   
		{
		  String[] splitLine = strLine.split("\\s");
		  for(int i = 0; i<splitLine.length; i++)
		  {
			  System.out.println(splitLine[i]);
		  }
		  
		  //Ignore comments
		  if(splitLine[0].startsWith("//"))
		  {
			  System.out.println("Comment :");
			  System.out.println(strLine);
		  }
		  
		  //Here we begin the parsing
		  else
		  {
			  
		  }
		  
		  System.out.println("");
		}
		
		
		//Close the input stream
		br.close();
	}
	
}
