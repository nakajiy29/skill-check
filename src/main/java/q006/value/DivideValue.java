package q006.value;

import java.math.BigDecimal;
import java.util.Stack;

/**
 * 割り算を行うクラス
 */
public class DivideValue implements IValue {
    @Override
    public void execute(Stack<BigDecimal> values) {
        BigDecimal right = values.pop();
        BigDecimal left = values.pop();
        values.push(right.divide(left));
    }
}
