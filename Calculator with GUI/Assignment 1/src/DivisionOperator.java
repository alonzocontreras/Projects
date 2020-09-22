
class DivisionOperator extends Operator{

    public DivisionOperator() {
        priority = 3;
    }

    @Override
    public int priority() {
        return priority;
    }

    @Override
    public Operand execute(Operand op1, Operand op2) {
        return new Operand(op1.value / op2.value);
    }
  
}
