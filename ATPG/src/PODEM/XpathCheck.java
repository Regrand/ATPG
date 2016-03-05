package PODEM;

import java.util.ArrayList;

public class XpathCheck {
	
	//Not complete yet

	public static boolean xPathCheck(node faultNode)
	{
	
		boolean check = true;
		node currNode = new node();
		currNode = faultNode;
		if(!VerilogParser.PO.contains(currNode.name))
		{
			
			ArrayList<String> forwardNodes = VerilogParser.sensitivityList.get(currNode.name);
			for(String temp : forwardNodes){
				boolean checkPath = true;
				node fan = VerilogParser.nodes.get(temp);
				boolean inputD = false;
				for(node inputs : fan.inputNodes) if(inputs.value==logic.d | inputs.value==logic.d ) inputD = true;
				if(fan.value==logic.d | fan.value==logic.d_bar | ((fan.value==logic.x)&(inputD==true)))
				{
					checkPath = checkPath && xPathCheck(fan);
				}
				
				check = check | checkPath;
			}
			
			
		}
		return check;
	}
}
