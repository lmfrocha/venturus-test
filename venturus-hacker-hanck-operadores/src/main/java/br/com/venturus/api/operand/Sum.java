package br.com.venturus.api.operand;

import java.util.List;

public class Sum implements Operand<Double, Double> {

	private List<Operand<Double, Double>> operands;

	@Override
	public Double evaluate() {
		Double sum = 0.0;
		for (Operand<Double, Double> operand : operands) {
			sum = sum + operand.evaluate();
		}
		return sum;
	}

	@Override
	public String toString() {
		StringBuilder toReturn = new StringBuilder("(");
		for (int i = 0; i < operands.size(); i++) {
			toReturn.append(operands.get(i).toString());
			if (i < operands.size() - 1) {
				toReturn.append(" + ");
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