
class SubtractionOperator extends Operator{

    public SubtractionOperator() {
        priority = 2;
    }

    @Override
    public int priority() {
        return priority;
    }

    @Override
    public Operand execute(Operand op1, Operand op2) {
        return new Operand(op1.value - op2.value);
    }

}
