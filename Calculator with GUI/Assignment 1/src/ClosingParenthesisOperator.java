
class ClosingParenthesisOperator extends Operator {

    public ClosingParenthesisOperator() {
        priority = 0;
    }

    @Override
    public int priority() {
        return priority;
    }

    @Override
    public Operand execute(Operand op1, Operand op2) {
        return null;
    }
}
