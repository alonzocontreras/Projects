package interpreter.ByteCode;

import interpreter.VirtualMachine;

import java.util.ArrayList;

public class LoadCode extends ByteCode {
    private String code;
    private String variable;

    @Override
    public void init(ArrayList<String> string) {
        code = string.get(0);
        variable = string.get(1);
    }

    @Override
    public void execute(VirtualMachine virtualmachine) {
        virtualmachine.loadNew(Integer.parseInt(code));
    }

    @Override
    public String toString() {
        return "LOAD " + code + " " + variable;
    }
}
