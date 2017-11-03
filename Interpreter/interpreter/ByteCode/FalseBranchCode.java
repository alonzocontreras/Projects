package interpreter.ByteCode;

import interpreter.VirtualMachine;

import java.util.ArrayList;

public class FalseBranchCode extends ByteCode {
    private String code;

    @Override
    public void init(ArrayList<String> string) {
        code = string.get(0);
    }

    @Override
    public void execute(VirtualMachine virtualmachine) {
        int i = virtualmachine.pop();
        if (i == 0){
            virtualmachine.setPC(Integer.parseInt(code));
        }
    }

    public void set(Integer replace){
        code = replace.toString();
    }

    public String get(){
        return code;
    }

    @Override
    public String toString() {
        return "FALSEBRANCH " + code;
    }
}
