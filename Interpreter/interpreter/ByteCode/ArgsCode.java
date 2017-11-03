   package interpreter.ByteCode;

import interpreter.VirtualMachine;

import java.util.ArrayList;

public class ArgsCode extends ByteCode {
    private String code;

    @Override
    public void init(ArrayList<String> string) {
        code = string.get(0);
    }

    @Override
    public void execute(VirtualMachine virtualmachine) {
        virtualmachine.newFrame(Integer.parseInt(code));
    }

    @Override
    public String toString() {
        return "ARGS " + code;
    }
}