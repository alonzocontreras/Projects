package interpreter.ByteCode;

import interpreter.VirtualMachine;

import java.util.ArrayList;

public class GotoCode extends ByteCode {
    private String code;

    public void init(ArrayList<String> string) {
        code = string.get(0);
    }

    public void execute(VirtualMachine virtualmachine) {
        virtualmachine.setPC(Integer.parseInt(code));
    }

    public void set(Integer replace){
        code = replace.toString();
    }

    public String get(){
        return code;
    }

    @Override
    public String toString() {
        return "GOTO " + code;
    }
}

