package PODEM;

import java.io.IOException;
import java.util.*;


public class MainATPG {

	public static boolean isSensitized(node faultNode)
	{
		logic t = LogicFunctions.compute(faultNode);
		if((faultNode.fault=="SA0" && t==logic.one)||(faultNode.fault=="SA1" && t==logic.zero))
			return true;
		else return false;
	}
	
	public static String allPIAssigned(graph ckt)
	{
		String retval = new String();
		
		//All PIs not yet assigned
		if(backtrack.flipPI.size()<ckt.PI.size())
		{
			for(String in: ckt.PI)
			{
				if(backtrack.assignPI.contains(in)) continue;
				else return in;
			}
		}
		
		return null;
	}
			
	
	public static void main(String[] args)  throws IOException {
		graph ckt=new graph();
		String fault=new String();				//fault node
		logic sa=logic.zero;		//fault stuck at 'sa'
		Boolean success=false;

		VerilogParser.parser_func("VerilogTest.v");
		ckt.nodes = VerilogParser.nodes;
		ckt.PI = VerilogParser.PI;
		ckt.PO = VerilogParser.PO;
		ckt.sensitivityList = VerilogParser.sensitivityList;
		
		System.out.println("Printing Node Details");
		
		
		node temp = ckt.nodes.get("r");
		temp.fault = "SA0";
		ckt.nodes.put("r", temp);
		fault = "r";
		
		/*
		for(HashMap.Entry<String, node> temp1 : ckt.nodes.entrySet())
		{
			temp1.getValue().print_details();
		}
		*/
		//use the verilog Parser

		while(!success){

			//Include X path check for the current D-frontier

			
			if(!isSensitized(ckt.nodes.get(fault))) {
			
				backtrace.backtrace_func(fault,sa,ckt);
				backtrack.flipPI.push(false);
				backtrack.assignPI.push((backtrace.PI_set));
				
				node te = ckt.nodes.get(backtrace.PI_set);
				te.value = backtrace.PI_value;
				ckt.nodes.put(backtrace.PI_set, te);
				
				ForwardSim.forwardSim(ckt.nodes.get(backtrace.PI_set));

			}
			
			
			
			
			if(isSensitized(ckt.nodes.get(fault))) {
				for (int i = 0; i < ckt.PO.size(); i++) {
					if (ckt.nodes.get(ckt.PO.get(i)).value == logic.d || ckt.nodes.get(ckt.PO.get(i)).value == logic.d_bar) {
						success = true;
						break;
					}
				}
			}

			if(XpathCheck.xPathCheck(ckt.nodes.get(fault)) && isSensitized(ckt.nodes.get(fault)))
			{				
				//Assign unassigned PIs
				if(allPIAssigned(ckt)!=null)
				{	
					String t = allPIAssigned(ckt);
					node te = ckt.nodes.get(t);
					te.value = logic.zero;
					ckt.nodes.put(t, te);
					
					backtrack.flipPI.push(false);
					backtrack.assignPI.push(t);
					ForwardSim.forwardSim(ckt.nodes.get(t));
					//backtrack.backtrack_func(ckt);
					//ForwardSim.forwardSim(ckt.nodes.get(backtrack.assignPI.peek()));					
				}
				
				for (int i = 0; i < ckt.PO.size(); i++) {
					if (ckt.nodes.get(ckt.PO.get(i)).value == logic.d || ckt.nodes.get(ckt.PO.get(i)).value == logic.d_bar) {
						success = true;
						break;
					}
				}
			}
			
			//Xpath fails. Backtrack
			else if(!XpathCheck.xPathCheck(ckt.nodes.get(fault)) && isSensitized(ckt.nodes.get(fault)))
			{
				boolean term = true;
				while(!backtrack.assignPI.isEmpty())
				{
					String t = backtrack.assignPI.pop();
					boolean flipped = backtrack.flipPI.pop(); 
					if(flipped) continue;
					else
					{
						node te = ckt.nodes.get(t);
						te.value = LogicFunctions.not(te.value);
						ckt.nodes.put(t, te);
						
						backtrack.flipPI.push(true);
						backtrack.assignPI.push(t);
						ForwardSim.forwardSim(ckt.nodes.get(backtrace.PI_set));
						term = false;
						break;
					}
				}
				//terminate
				if(term) 
					{
					System.out.println("No vector");
					break;
					}
			}
			
			for(HashMap.Entry<String, node> temp1 : ckt.nodes.entrySet())
			{
				temp1.getValue().print_details();
			}
		}

	}
}

