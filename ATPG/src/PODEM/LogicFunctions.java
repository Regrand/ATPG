package PODEM;

public class LogicFunctions {
	
	public static logic not(logic in)
	{
		switch(in)
		{
		case d:
			return logic.d_bar;
		case d_bar:
			return logic.d;
		case one:
			return logic.zero;
		case x:
			return logic.x;
		case zero:
			return logic.one;
		default:
			return logic.x;
		}
	}
	
	public static logic and(logic in1, logic in2)
	{
		switch(in1)
		{
		
		case d:
			switch(in2){
			case d:
				return logic.d;
			case d_bar:
				return logic.zero;
			case one:
				return logic.d;
			case x:
				return logic.x;
			case zero:
				return logic.zero;
			default:
				return logic.x;
			}

		case d_bar:
			switch(in2){
			case d:
				return logic.zero;
			case d_bar:
				return logic.d_bar;
			case one:
				return logic.d_bar;
			case x:
				return logic.x;
			case zero:
				return logic.zero;
			default:
				return logic.x;			
			}

		case one:
			switch(in2){
			case d:
				return logic.d;
			case d_bar:
				return logic.d_bar;
			case one:
				return logic.one;
			case x:
				return logic.x;
			case zero:
				return logic.zero;
			default:
				return logic.x;
				
			}
			
		case x:
			switch(in2){
			case d:
				return logic.x;
			case d_bar:
				return logic.x;
			case one:
				return logic.x;
			case x:
				return logic.x;
			case zero:
				return logic.zero;
			default:
				return logic.x;	
			}
			
		case zero:
			switch(in2){
			case d:
				return logic.zero;
			case d_bar:
				return logic.zero;
			case one:
				return logic.zero;
			case x:
				return logic.zero;
			case zero:
				return logic.zero;
			default:
				return logic.x;					
			}

		default:
			return logic.x;
		}
	}
	
	public static logic or(logic in1, logic in2)
	{
		switch(in1)
		{
		
		case d:
			switch(in2){
			case d:
				return logic.d;
			case d_bar:
				return logic.one;
			case one:
				return logic.one;
			case x:
				return logic.x;
			case zero:
				return logic.d;
			default:
				return logic.x;
			}

		case d_bar:
			switch(in2){
			case d:
				return logic.one;
			case d_bar:
				return logic.d_bar;
			case one:
				return logic.one;
			case x:
				return logic.x;
			case zero:
				return logic.d_bar;
			default:
				return logic.x;			
			}

		case one:
			switch(in2){
			case d:
				return logic.one;
			case d_bar:
				return logic.one;
			case one:
				return logic.one;
			case x:
				return logic.x;
			case zero:
				return logic.one;
			default:
				return logic.x;
				
			}
			
		case x:
			switch(in2){
			case d:
				return logic.x;
			case d_bar:
				return logic.x;
			case one:
				return logic.one;
			case x:
				return logic.x;
			case zero:
				return logic.x;
			default:
				return logic.x;	
			}
			
		case zero:
			switch(in2){
			case d:
				return logic.d;
			case d_bar:
				return logic.d_bar;
			case one:
				return logic.one;
			case x:
				return logic.x;
			case zero:
				return logic.zero;
			default:
				return logic.x;					
			}

		default:
			return logic.x;
		}
	}
	
	public static logic xor(logic in1, logic in2)
	{

		return or(and(in1, not(in2)), and(not(in1), in2));
	}
	
	public logic xnor(logic in1, logic in2)
	{
		return or(and(in1, in2), and(not(in1), not(in2)));
	}
	
	public static logic nand(logic in1, logic in2)
	{

		return not(and(in1, in2));
	}
	
	public static logic nor(logic in1, logic in2)
	{

		return not(or(in1, in2));
	}
	
	
	public static logic compute(node compNode){
	
		switch(compNode.gate){
		
		case("PI"):
			return compNode.value;
		
		case("not"):
			return not(compNode.inputNodes.get(0).value);
			
		case("nand"):
			logic val1 = logic.one;
			for(node input : compNode.inputNodes){
				val1 = nand(val1, input.value);
			}
			return val1;
			
		case("nor"):
			logic val2 = logic.zero;
			for(node input : compNode.inputNodes){
				val2 = nor(val2, input.value);
			}
			return val2;
			
		case("and"):
			logic val3 = logic.one;
			for(node input : compNode.inputNodes){
				val3 = and(val3, input.value);
			}
			return val3;
		
		case("or"):
			logic val4 = logic.zero;
			for(node input : compNode.inputNodes){
				val4 = or(val4, input.value);
			}
			return val4;
			
		case("xor"):
			logic val5 = logic.zero;
			for(node input : compNode.inputNodes){
				val5 = xor(val5, input.value);
			}
			return val5;
			
		case("xnor"):
			logic val6 = logic.one;
			for(node input : compNode.inputNodes){
				val6 = xor(val6, input.value);
			}
			return not(val6);
			
		case("noGate"):
			//Only one input by default
			return compNode.inputNodes.get(0).value;
		
		default: 
			System.out.println("Gate not found");
			return logic.x;
		}
	
	}
}