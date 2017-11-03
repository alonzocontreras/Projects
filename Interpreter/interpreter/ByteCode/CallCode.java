package interpreter.ByteCode;

import interpreter.VirtualMachine;

import java.util.ArrayList;

public class CallCode extends ByteCode {
    private String code;

    @Override
    public void init(ArrayList<String> string) {
        code = string.get(0);
    }

    @Override
    public void execute(VirtualMachine virtualmachine) {
        int currPC = virtualmachine.getPc();
        virtualmachine.pushOntoResolveAddress(currPC);
        virtualmachine.setPC(Integer.parseInt(code));
    }
    public void set(int replace){
        code = Integer.toString(replace);
    }

    public String get(){
        return code;
    }

    @Override
    public String toString() {
        return "CALL " + code;
    }
}
