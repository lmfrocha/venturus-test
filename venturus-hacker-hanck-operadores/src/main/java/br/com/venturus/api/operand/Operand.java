package br.com.venturus.api.operand;

public interface Operand<OperandType, ReturnType> {

	public ReturnType evaluate();

	public String toString();

}
