import java.util.HashMap;

public abstract class Operator {

    public int priority;
    
    private static HashMap <String, Operator> operators = new HashMap();
    
    static {
        operators.put("+", new AdditionOperator());
        operators.put("-", new SubtractionOperator());
        operators.put("/", new DivisionOperator());
        operators.put("*", new MultiplicationOperator());
        operators.put("^", new ExponentOperation());
        operators.put("(", new OpenParenthesisOperator());
        operators.put(")", new ClosingParenthesisOperator());
        operators.put("#", new PoundOperator());
    }

    public abstract int priority();

    public abstract Operand execute(Operand op1, Operand op2);

    public static boolean check(String token) {
        return operators.containsKey(token);
    }
    
    public static Operator whichOperator(String token){
        return operators.get(token);
    }

}