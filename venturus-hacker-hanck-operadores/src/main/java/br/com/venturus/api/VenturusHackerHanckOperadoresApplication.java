package br.com.venturus.api;

import java.util.ArrayList;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.com.venturus.api.operand.Division;
import br.com.venturus.api.operand.GreaterThan;
import br.com.venturus.api.operand.LessThan;
import br.com.venturus.api.operand.Multiplication;
import br.com.venturus.api.operand.Operand;
import br.com.venturus.api.operand.Sum;
import br.com.venturus.api.operand.Value;

@SpringBootApplication
public class VenturusHackerHanckOperadoresApplication {

	public static void main(String[] args) {
		SpringApplication.run(VenturusHackerHanckOperadoresApplication.class, args);

		Sum sum = new Sum();
		ArrayList<Operand<Double, Double>> operands = new ArrayList<>();
		ArrayList<Operand<Double, Double>> operands1 = new ArrayList<>();
		operands.add(new Value(10.0));
		operands.add(new Value(5.0));
		operands.add(new Value(2.0));
		sum.setOperands(operands);
		operands1.add(sum);
		operands1.add(new Value(3.0));
		
		System.out.println("Multiplication");
		Multiplication mult = new Multiplication();
		mult.setOperands(operands1);
		System.out.println(mult.toString());
		System.out.println(mult.evaluate());
		
		System.out.println("\nSum");
		System.out.println(sum.toString());
		System.out.println(sum.evaluate());
		
		System.out.println("\nDivision");
		Division div = new Division(new Value(20.0), new Value(10.0));
		System.out.println(div.toString());
		System.out.println(div.evaluate());

		System.out.println("\nGreater Than");
		GreaterThan gt = new GreaterThan(sum, div);
		
		System.err.println(gt.evaluate());
		System.out.println(gt.toString());
		
		System.out.println("\nLess Than");
		LessThan lt = new LessThan(sum, div);
		
		System.out.println(lt.evaluate());
		System.out.println(lt.toString());

		

	}

}
