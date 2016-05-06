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
	
	public static boolean succeeded(graph ckt, String fault)
	{
		if(isSensitized(ckt.nodes.get(fault))) {
			for (String tp: ckt.PO) {
				if (ckt.nodes.get(tp).value == logic.d || ckt.nodes.get(tp).value == logic.d_bar) {
					return true;
				}
			}
		}	
		return false;	
	}
	
	public static String allPIAssigned(graph ckt)
	{

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

		VerilogParser.parser_func("VerilogTest.v");
		ckt.nodes = VerilogParser.nodes;
		ckt.PI = VerilogParser.PI;
		ckt.PO = VerilogParser.PO;
		ckt.sensitivityList = VerilogParser.sensitivityList;
		
		int totalFaults = 0;
		int countSucc = 0;
		int countFail = 0;
		
		System.out.println("Printing Node Details");
		System.out.println("--------------");
		
		for(HashMap.Entry<String, node> f : ckt.nodes.entrySet())
		{
			for(String temp1 : ckt.nodes.keySet())
			{
				node temp = ckt.nodes.get(temp1);
				temp.value = logic.x;
				temp.fault = "none";
				ckt.nodes.put(temp1, temp);
			}
			
			String fault = f.getKey();
			node temp = ckt.nodes.get(fault);
			logic sa=logic.zero;		//fault stuck at 'sa'
			temp.fault = "SA0";
			temp.value = logic.d;
			ckt.nodes.put(fault, temp);
			
			Boolean success=false;
			for(int i=0; i<2; i++)
			{
				System.out.println("Testing for fault : " + fault + " " + temp.fault);
				totalFaults++;
				if(ckt.nodes.get(fault).gate=="PI")
				{
					if(ckt.nodes.get(fault).fault=="SA0")
					{
						System.out.println("Succeeded");
						System.out.println(fault + ":1");
						countSucc++;
					}
					else if(ckt.nodes.get(fault).fault=="SA1")
					{
						System.out.println("Succeeded");
						System.out.println(fault + ":0");
						countSucc++;
					}
				}
				else {
					while(!success){
			
						boolean btfail = false;
						
						if(!isSensitized(ckt.nodes.get(fault))) {
						
							try {
									backtrace.backtrace_func(fault,sa,ckt);
									backtrack.flipPI.push(false);
									backtrack.assignPI.push((backtrace.PI_set));
									
									node te = ckt.nodes.get(backtrace.PI_set);
									te.value = backtrace.PI_value;
									ckt.nodes.put(backtrace.PI_set, te);
									
									ForwardSim.forwardSim(ckt.nodes.get(backtrace.PI_set));
									/*for(HashMap.Entry<String, node> temp1 : ckt.nodes.entrySet())
									{
										System.out.println(temp1.getKey() + ":" + temp1.getValue().value);
									}
									System.out.println("-------------");
									*/
								} 
								catch (Exception e) {
									btfail = true;
								}
							}
						
						if(succeeded(ckt, fault))
						{
							success = true;
							System.out.println("Success");
							countSucc++;
							for(HashMap.Entry<String, node> temp1 : ckt.nodes.entrySet())
							{
								System.out.println(temp1.getKey() + ":" + temp1.getValue().value);
							}
						}
						
			
						//Xpath succeeds and fault sensitized
						if(XpathCheck.xPathCheck(ckt.nodes.get(fault)) && isSensitized(ckt.nodes.get(fault)) && !success)
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
								/*for(HashMap.Entry<String, node> temp1 : ckt.nodes.entrySet())
								{
									System.out.println(temp1.getKey() + ":" + temp1.getValue().value);
								}
								System.out.println("-------------");
								*/					
								if(succeeded(ckt, fault))
								{
									success = true;
									System.out.println("Success");
									countSucc++;
									for(HashMap.Entry<String, node> temp1 : ckt.nodes.entrySet())
									{
										System.out.println(temp1.getKey() + ":" + temp1.getValue().value);
									}
									System.out.println("-------------");
								}
							
							}
						}
						
						//Backtrack if Xpath fails, or if xpath succeeds and backtrace fails.
						else if((!XpathCheck.xPathCheck(ckt.nodes.get(fault)) && !success)||(XpathCheck.xPathCheck(ckt.nodes.get(fault)) && btfail && !success))
						{
							boolean term = true;
							while(!backtrack.assignPI.isEmpty())
							{
								String t = backtrack.assignPI.pop();
								boolean flipped = backtrack.flipPI.pop(); 
								if(flipped)
								{
									node te = ckt.nodes.get(t);
									te.value = logic.x;
									ckt.nodes.put(t, te);
								}
								else
								{
									node te = ckt.nodes.get(t);
									te.value = LogicFunctions.not(te.value);
									ckt.nodes.put(t, te);
									
									backtrack.flipPI.push(true);
									backtrack.assignPI.push(t);
									ForwardSim.forwardSim(ckt.nodes.get(t));
									/*for(HashMap.Entry<String, node> temp1 : ckt.nodes.entrySet())
									{
										System.out.println(temp1.getKey() + ":" + temp1.getValue().value);
									}
									*/
									term = false;
									break;
								}
							}
							if(term) 
								{
									System.out.println("No vector");
									success = true;
									countFail++;
								}
							
							if(succeeded(ckt, fault))
							{
								success = true;
								System.out.println("Success");
								countSucc++;
								for(HashMap.Entry<String, node> temp1 : ckt.nodes.entrySet())
								{
									System.out.println(temp1.getKey() + ":" + temp1.getValue().value);
								}
								System.out.println("-------------");
							}
						}
						
						/*else if(btfail && XpathCheck.xPathCheck(ckt.nodes.get(fault)) && !success)
						{
							System.out.println("No vector");
							break;
						}*/
					
					}
				}
			
			for(String temp1 : ckt.nodes.keySet())
			{
				temp = ckt.nodes.get(temp1);
				temp.value = logic.x;
				temp.fault = "none";
				ckt.nodes.put(temp1, temp);
			}
				
			temp = ckt.nodes.get(fault);
			sa=logic.one;		//fault stuck at 'sa'
			temp.fault = "SA1";
			temp.value = logic.d_bar;
			ckt.nodes.put(fault, temp);
			
			backtrack.assignPI.clear();
			backtrack.flipPI.clear();
			
			success = false;
			
			}
	
		}
		
		System.out.println("Total Faults : " + totalFaults);
		System.out.println("Vectors generated : " + countSucc);
		System.out.println("Unable to find vectors : " + countFail);
		
	}
	
}

