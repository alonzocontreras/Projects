package interpreter.ByteCode;

import interpreter.VirtualMachine;

import java.util.ArrayList;

public class PopCode extends ByteCode {
    private String code;
    @Override
    public void init(ArrayList<String> string) {
        code = string.get(0);
    }

    @Override
    public void execute(VirtualMachine virtualmachine) {
        virtualmachine.popStack(Integer.parseInt(code));
    }

    @Override
    public String toString() {
        return "POP " + code;
    }
}
