
class ExponentOperation extends Operator {

    public ExponentOperation() {
        priority = 4;
    }

    @Override
    public int priority() {
        return priority;
    }

    @Override
    public Operand execute(Operand op1, Operand op2) {
        int exponential = (int) java.lang.Math.pow(op1.value, op2.value);
        return new Operand(exponential);
    }
    
}
