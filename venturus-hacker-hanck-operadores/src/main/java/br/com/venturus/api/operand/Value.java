package br.com.venturus.api.operand;

public class Value implements Operand<Double, Double> {
	Double value;

	public Value(Double value) {
		this.value = value;
	}

	@Override
	public Double evaluate() {
		return value;
	}

	@Override
	public String toString() {
		return String.valueOf(value);
	}

	public Double getValue() {
		return value;
	}

	public void setValue(Double value) {
		this.value = value;
	}
}