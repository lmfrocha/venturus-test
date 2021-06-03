package br.com.venturus.api.operand;

public class LessThan implements Operand<Double, Boolean> {
	
	private Operand<Double, Double> operand1;
	private Operand<Double, Double> operand2;
	
	public LessThan(Operand<Double, Double> operand1, Operand<Double, Double> operand2) {
		this.operand1 = operand1;
		this.operand2 = operand2;
	}
	
	@Override
	public Boolean evaluate() {
		Boolean result = Boolean.FALSE;
		if(operand1.evaluate() < operand2.evaluate()) {
			result = Boolean.TRUE;
		}
		return result;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		
		sb.append("(" + operand1.toString() + " < " + operand2.toString() + ")");
		
		return sb.toString();
	}

}
