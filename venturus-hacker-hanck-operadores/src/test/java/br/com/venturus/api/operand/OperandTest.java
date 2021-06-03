package br.com.venturus.api.operand;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class OperandTest {

	private ArrayList<Operand<Double, Double>> operands = new ArrayList<>();
	private ArrayList<Operand<Double, Double>> operands1 = new ArrayList<>();
	private Sum sum;
	private Division div;
	private GreaterThan gt;
	private LessThan lt;
	private Multiplication mult;
	
	@BeforeEach
	void setUp() {
		div = new Division(new Value(20.0), new Value(10.0));
		sum = new Sum();
		operands.add(new Value(10.0));
		operands.add(new Value(5.0));
		operands.add(new Value(2.0));
		sum.setOperands(operands);
        operands1.add(sum);
        operands1.add(new Value(3.0));
        mult = new Multiplication();
        mult.setOperands(operands1);
        lt = new LessThan(sum, div);
        gt = new GreaterThan(sum, div);
	}
	
	@Test
	void operations_true() {
		assertEquals(mult.evaluate(), 51.0D);
		assertEquals(sum.evaluate(), 17.0D);
		assertEquals(div.evaluate(), 2.0D);
		assertEquals(gt.evaluate(), Boolean.TRUE);
		assertEquals(lt.evaluate(), Boolean.FALSE);
		assertEquals(gt.toString(), "((10.0 + 5.0 + 2.0) > (20.0 / 10.0))");
		assertEquals(lt.toString(), "((10.0 + 5.0 + 2.0) < (20.0 / 10.0))");
	}
	
}
