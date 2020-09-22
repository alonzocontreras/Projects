package interpreter;

import java.util.Stack;
import interpreter.ByteCode.ByteCode;
import interpreter.ByteCode.CallCode;
import interpreter.ByteCode.ReturnCode;
import interpreter.ByteCode.DumpCode;
import java.util.Scanner;

public class VirtualMachine {

    private RunTimeStack runStack;
    private int pc;
    private Stack<Integer> returnAddrs;
    private boolean isRunning;
    private Program program;
    private boolean isDump = false;

    protected VirtualMachine(Program program) {
        this.program = program;
    }

    public void executeProgram(){
        pc = 0;     // program counter set to 0
        runStack = new RunTimeStack();
        returnAddrs = new Stack();  // push/pop when call/return functions
        isRunning = true;   // set true while VM is running
        while (isRunning){
            ByteCode code = program.getCode(pc);
            code.execute(this);
            //System.out.println(code.toString());
            //runStack.dump(); //check that the operation is correct
            //System.out.println(pc);
            if (isDump){
                if(code instanceof DumpCode){

                } else {
                    System.out.println(code);
                    runStack.dump();
                }
            }
            pc++;
        }
    }

    public int loadNew (int offset){
        return runStack.load(offset);
    }

    public int pop(){
        //System.out.println("value being popped: " + runStack.peek() );
        return runStack.pop();
    }

    public int peek(){
        return runStack.peek();
    }

    public void setPC(int newPC){
        pc = newPC;
    }

    public void pushOntoResolveAddress(int currPC){
        returnAddrs.push(currPC);
        //System.out.println(returnAddrs + " <-- push return Address");
    }

    public int popFromResolveAddress(){
        return returnAddrs.pop();
    }

    public void newFrame(int frameLocation){
        runStack.newFrameAt(frameLocation);
    }

    public int popOnce (){
        return runStack.pop();
    }

    public boolean setIsRunning(boolean bool){
        return isRunning = bool;
    }

    public void popStack(int n){
        if(n > runStack.size()){
            for(int i = 0; runStack.size() >= i; i++){
                runStack.pop();
            }
        }
        if (n < runStack.size()){
            for(int i = 0; n >= i; i++){
                runStack.pop();
            }
        }
    }

    public void pushOntoStack(int i){
        runStack.push(i);
    }

    public int userInput (){
        System.out.print("Please input a number: ");
        Scanner scan = new Scanner(System.in);
        int i = scan.nextInt();
        return i;
    }

    public int storeCall (int offset){
        return runStack.store(offset);
    }

    public void pushLiteral(Integer i){
        runStack.push(i);
    }

    public int getPc(){
        return pc;
    }

    public void reset(){
        runStack.reset();
    }

    public void dump(boolean onORoff){
        isDump = onORoff;
    }
}
