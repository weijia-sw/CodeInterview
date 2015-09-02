public class Solution {
	private boolean isDigit(Character ch){
		return ch>='0' && ch<='9';
	}
	
	private void runCalculation(Stack<Integer> numStack, Stack<Character> opStack){
		// do operation until see ')' on the stack
		while(opStack.size()>0){
			Character op = opStack.pop();
			if(op == '+'){
			    numStack.push(numStack.pop() + numStack.pop());
		    }
		    else if(op == '-'){
			    numStack.push(numStack.pop() - numStack.pop());
		    }				    
			if(op == ')')
				break;
		}
	}
	
	
	public int calculate(String s) {
		if(s == null || s.length() == 0)
		    return 0;
		
		Stack<Integer> numStack = new Stack<Integer>();
		Stack<Character> opStack = new Stack<Character>();
		
		
		int i=s.length()-1;
		while(i>=0){
			if(s.charAt(i) == ')' || s.charAt(i) == '+' || s.charAt(i) == '-'){
				opStack.push(s.charAt(i));
				i--;
			}
			else if(s.charAt(i) == ' '){
				i--;
			}
			else if(isDigit(s.charAt(i))){
				int num = 0;
				int base = 1;
				while(i>=0 && isDigit(s.charAt(i)) ){
					num = num + (s.charAt(i--) - '0')*base;
					base *= 10;
				}
				numStack.push(num);
			}
			else if(s.charAt(i) == '('){
				runCalculation(numStack, opStack);
				i--;
			}
		} //while
    
		if(opStack.size() > 0){
			runCalculation(numStack, opStack);
		}
		
		return numStack.pop();
	
	}
}