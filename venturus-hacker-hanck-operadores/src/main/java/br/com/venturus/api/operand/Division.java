package br.com.venturus.api.operand;

public class Division implements Operand<Double, Double> {

	private Operand<Double, Double> dividend;
	private Operand<Double, Double> divisor;

	public Division(Operand<Double, Double> dividend, Operand<Double, Double> divisor) {
		this.dividend = dividend;
		this.divisor = divisor;
	}

	@Override
	public Double evaluate() {
		return dividend.evaluate() / divisor.evaluate();
	}

	@Override
	public String toString() {
		return "(" + dividend + " / " + divisor + ")";
	}

	public Operand<Double, Double> getDividend() {
		return dividend;
	}

	public void setDividend(Operand<Double, Double> dividend) {
		this.dividend = dividend;
	}

	public Operand<Double, Double> getDivisor() {
		return divisor;
	}

	public void setDivisor(Operand<Double, Double> divisor) {
		this.divisor = divisor;
	}

}
