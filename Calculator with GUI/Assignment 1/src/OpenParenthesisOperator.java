
class OpenParenthesisOperator extends Operator {

    public OpenParenthesisOperator() {
        priority = 1;
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
