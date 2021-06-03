package br.com.venturus.api.operand;

import java.util.List;

public class Substraction implements Operand<Double, Double> {

	private List<Operand<Double, Double>> operands;

	@Override
	public Double evaluate() {
		Double sub = operands.get(0).evaluate();
		for (int i = 1; i < operands.size(); i++) {
			sub = sub - operands.get(i).evaluate();
		}
		return sub;
	}

	@Override
	public String toString() {
		StringBuilder toReturn = new StringBuilder("(");
		for (int i = 0; i < operands.size(); i++) {
			toReturn.append(operands.get(i).toString());
			if (i < operands.size() - 1) {
				toReturn.append(" - ");
			}
		}
		return toReturn + ")";
	}

	public List<Operand<Double, Double>> getOperands() {
		return operands;
	}

	public void setOperands(List<Operand<Double, Double>> operands) {
		this.operands = operands;
	}
}