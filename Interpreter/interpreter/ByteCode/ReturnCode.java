package interpreter.ByteCode;

import interpreter.VirtualMachine;

import java.util.ArrayList;

public class ReturnCode extends ByteCode {
    private String arg = "null";

    @Override
    public void init(ArrayList<String> string) {
        if (!string.isEmpty()){
            arg = string.get(0);
        }
    }

    @Override
    public void execute(VirtualMachine virtualmachine) {
        int newPC = virtualmachine.popFromResolveAddress();
        virtualmachine.setPC(newPC);
        virtualmachine.reset();
    }

    @Override
    public String toString() {
        if (arg.equals("null")){
            return "RETURN";
        } else {
            return "RETURN " + arg;
        }

    }
}
