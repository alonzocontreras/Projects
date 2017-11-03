package interpreter.ByteCode;

import interpreter.VirtualMachine;

import java.util.ArrayList;

public class LitCode extends ByteCode {
    private String code;
    private String variable;
    @Override
    public void init(ArrayList<String> string) {
        code = string.get(0);
        if (string.isEmpty()){
            variable = string.get(1);
        }
    }

    @Override
    public void execute(VirtualMachine virtualmachine) {
        virtualmachine.pushLiteral(Integer.parseInt(code));
    }

    @Override
    public String toString() {
        return "LIT " + code + " " + variable;
    }
}
