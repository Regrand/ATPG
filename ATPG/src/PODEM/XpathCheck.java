package PODEM;

import java.util.ArrayList;

public class XpathCheck {
	
	//Not complete yet

	public static boolean xPathCheck(node faultNode)
	{
	
		node currNode = new node();
		currNode = faultNode;
		if(!VerilogParser.PO.contains(currNode.name))
		{
			boolean checkPath = true;
			ArrayList<String> forwardNodes = VerilogParser.sensitivityList.get(currNode.name);
			for(String temp : forwardNodes)
			{
				node fan = VerilogParser.nodes.get(temp);
				if(fan.value==logic.d | fan.value==logic.d_bar | fan.value==logic.x)
				{
					checkPath = checkPath | xPathCheck(fan);
				}
				
			}
			
			return checkPath;
		}
		
		else
		{
			//At PO node
			if(currNode.value==logic.d | currNode.value==logic.d_bar | currNode.value==logic.x)
			{
				return true;
			}
			else
			{	
				return false;
			}
		}
	}
}
