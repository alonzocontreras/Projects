
import java.util.*;

public class Evaluator {

    private Stack<Operand> operandStack;
    private Stack<Operator> operatorStack;

    private StringTokenizer tokenizer;
    private static final String DELIMITERS = "+-*^/() ";

    public Evaluator() {
        operandStack = new Stack<>();
        operatorStack = new Stack<>();
    }

    private void evaluateParenthis() {
        while (!(operatorStack.peek().priority == 1)) {
            Operator oldOpr = operatorStack.pop();
            Operand op2 = operandStack.pop();
            Operand op1 = operandStack.pop();
            operandStack.push(oldOpr.execute(op1, op2));
        }
        operatorStack.pop();
    }

    public int eval(String expression) {
        String token;
        int ResultValue;
        operatorStack.push(new PoundOperator());
        // The 3rd argument is true to indicate that the delimiters should be used
        // as tokens, too. But, we'll need to remember to filter out spaces.
        this.tokenizer = new StringTokenizer(expression, DELIMITERS, true);

        while (this.tokenizer.hasMoreTokens()) {

            if (!(token = this.tokenizer.nextToken()).equals(" ")) {
                // check if token is an operand
                if (!Operand.check(token)) {
                    operandStack.push(new Operand(token));
                } else {
                    if (!Operator.check(token)) {
                        System.out.println("*****invalid token******");
                        System.exit(1);
                    }

                    Operator newOperator = Operator.whichOperator(token);

                    if (token.equals("(")) {
                        operatorStack.push(newOperator);
                        continue;
                    }
                    if (token.equals(")")) {
                        this.evaluateParenthis();
                        continue;
                    }
                    if (!(operatorStack.peek().priority == 0)) {
                        while (operatorStack.peek().priority() > newOperator.priority()) {
                            Operator oldOpr = operatorStack.pop();
                            Operand op2 = operandStack.pop();
                            Operand op1 = operandStack.pop();
                            operandStack.push(oldOpr.execute(op1, op2));
                        }
                    }
                    operatorStack.push(newOperator);
                }
            }
        }

        while (!(operatorStack.peek().priority == 0)) {
            Operator oldOpr = operatorStack.pop();
            Operand op2 = operandStack.pop();
            Operand op1 = operandStack.pop();
            operandStack.push(oldOpr.execute(op1, op2));
        }
        operatorStack.pop();
        return operandStack.pop().getValue();
    }
}
