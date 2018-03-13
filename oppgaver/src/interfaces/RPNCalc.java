package interfaces;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;
import java.util.function.BinaryOperator;
import java.util.function.Supplier;
import java.util.function.UnaryOperator;

public class RPNCalc {
	
	private Stack<Double> operandStack;
	private Map<Character, BinaryOperator<Double>> operators;
	private Map<Character, BinaryOperator<Double>> binaryOperators;
	private Map<Character, UnaryOperator<Double>> unaryOperators;
	private Map<Character, Supplier<Double>> suppliers;
	
	public RPNCalc() {
		operandStack = new Stack<Double>();
		operators = new HashMap<>();
		binaryOperators = new HashMap<>();
		unaryOperators = new HashMap<>();
		suppliers = new HashMap<>();
	}
	
	@Override
	public String toString() {
		return operandStack.toString();
	}

	public void push(double value) {
		operandStack.push(value);
	}

	public int getSize() {
		return operandStack.size();
	}

	public double peek(int n) {
		return peek(n, Double.NaN);
	}

	public double peek(int n, double def) {
		if (n < 0 || operandStack.size() <= n) {
			return def;
		}
		return operandStack.get(operandStack.size() - n - 1);
	}

	// Method used in earlier versions. Currently not a part of the assignment.
	public double pop(double def) {
		if (operandStack.isEmpty()) {
			return def;
		}
		return operandStack.pop();
	}

	public double pop() {
		return pop(Double.NaN);
	}

	// perform the operation denoted by op
	// each operation pops and pushes values off and onto the operand stack,
	public void performOperation(char op) { 
		if (operators.containsKey(op)) {
			double d2 = pop();
			double d1 = pop();
			push(operators.get(op).apply(d1, d2));
		}
		else {
			throw new UnsupportedOperationException("The given operation has not been added.");
		}
	}
	
	public boolean addOperator(char op, BinaryOperator<Double> operator) {
		if(operators.containsKey(op)) return false;
		operators.put(op, operator);
		return true;
	}
	
	public void removeOperator(char op){
		operators.remove(op);
	}
	
	///////// Extra Task ////////////
	// Below, two implementations of the extra tasks are shown. To separate the methods with equal
	// names, Version 1 has a suffix of 1 to method names, and version 2 has a 2.
	
	///////// Version 1 /////////////
	// This version only allows an operator to be added as one of binary, unary or supplier.
	// This shifts the responsibility of handling missing numbers (Double.NaN) to the operators
	// The result is that the operators become somewhat more complex, but the logic of this class
	// is simpler and more general than Version 2 (below)
	
	// perform the operation denoted by op
	// each operation pops and pushes values off and onto the operand stack,
	public void performOperation1(char op) { 
		if (binaryOperators.containsKey(op)) {
			double d2 = pop();
			double d1 = pop();
			push(binaryOperators.get(op).apply(d1, d2));
		}
		else if (unaryOperators.containsKey(op)) {
			double d = pop();
			push(unaryOperators.get(op).apply(d));
		}
		else if (suppliers.containsKey(op)) {
			push(suppliers.get(op).get());
		}
		else {
			throw new UnsupportedOperationException("The given operation has not been added.");
		}
	}
	
	private boolean containsOperator(char op) {
		return binaryOperators.containsKey(op) || unaryOperators.containsKey(op) || suppliers.containsKey(op);
	}
	
	public boolean addOperator1(char op, BinaryOperator<Double> operator) {
		if(containsOperator(op)) return false;
		binaryOperators.put(op, operator);
		return true;
	}
	
	public boolean addOperator1(char op, UnaryOperator<Double> operator) {
		if (containsOperator(op)) return false;
		unaryOperators.put(op, operator);
		return true;
	}
	
	public boolean addOperator1(char op, Supplier<Double> operator) {
		if (containsOperator(op)) return false;
		suppliers.put(op, operator);
		return true;
	}
	
	public void removeOperator1(char op){
		binaryOperators.remove(op);
		unaryOperators.remove(op);
		suppliers.remove(op);
	}
	
	///////////// Version 2 ////////////
	// This version allows for multiple definitions for each operator, one for binary,
	// one for unary, and one for suppliers. The system will always choose the operator
	// that takes in the most amount of operands, as long as there is that many
	// operands left in the stack. This lets the operator-definition simpler, as we don't
	// need to handle NaNs anywhere. However, it also enforces the assumption that we always
	// want to use the operator with most operands, which might not (but probably is) always
	// be true
	
	
	public void performOperation2(char op) {
		if (binaryOperators.containsKey(op) && getSize() >= 2) {
			double d2 = pop();
			double d1 = pop();
			push(binaryOperators.get(op).apply(d1, d2));
		}
		else if (unaryOperators.containsKey(op) && getSize() >= 1) {
			double d = pop();
			push(unaryOperators.get(op).apply(d));
		}
		else if (suppliers.containsKey(op))
			push(suppliers.get(op).get());
		else
			throw new UnsupportedOperationException("Operation " + op + " is not supported for at most " + getSize() + " operands.");
	}
	
	public boolean addOperator2(char op, BinaryOperator<Double> operator) {
		if(binaryOperators.containsKey(op)) return false;
		binaryOperators.put(op, operator);
		return true;
	}
	
	public boolean addOperator2(char op, UnaryOperator<Double> operator) {
		if (unaryOperators.containsKey(op)) return false;
		unaryOperators.put(op, operator);
		return true;
	}
	
	public boolean addOperator2(char op, Supplier<Double> operator) {
		if (suppliers.containsKey(op)) return false;
		suppliers.put(op, operator);
		return true;
	}
	
	public void removeOperator2(char op, int numOperands) {
		switch (numOperands) {
		case 0:
			suppliers.remove(op);
			break;
		case 1:
			unaryOperators.remove(op);
			break;
		case 2:
			binaryOperators.remove(op);
			break;
		default:
			throw new IllegalArgumentException("Number of operands must be 0, 1 or 2.");
		}
	}
	
	public void removeOperators2(char op) {
		binaryOperators.remove(op);
		unaryOperators.remove(op);
		suppliers.remove(op);
	}
}
