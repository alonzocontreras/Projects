
public class EvaluatorTester {

    public static void main(String[] args) {
        Evaluator evaluator = new Evaluator();

        String[] test = { "2*3-6", "2+1*3*4*84^3", "2^2*2+2"};
                
        
        for (String arg : test) {
            System.out.format("%s = %d\n", arg, evaluator.eval(arg));
            // System.out.println( arg + " = " + evaluator.eval( arg ) );
        }
    }
}
