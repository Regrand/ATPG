package PODEM;

import java.util.ArrayList;

public class ForwardSim {

	public static void forwardSim(node n)
	{
		logic t = null;
		if(n.fault=="SA0") t = logic.d;
		else if(n.fault=="SA1") t = logic.d_bar;
		else t = LogicFunctions.compute(n);
		
		//Simulate only if value changes
		if(t!=n.value | n.gate=="PI")
			n.value = t;
			if(VerilogParser.sensitivityList.get(n.name)!=null)
			{
				ArrayList<String> forwardNodes = VerilogParser.sensitivityList.get(n.name);
				for(String temp : forwardNodes){
					forwardSim(VerilogParser.nodes.get(temp));
				}
			}
		
		
	}
	
		
}
