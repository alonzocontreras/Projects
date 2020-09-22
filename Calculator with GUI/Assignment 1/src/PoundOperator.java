
class PoundOperator extends Operator {

    public PoundOperator() {
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
