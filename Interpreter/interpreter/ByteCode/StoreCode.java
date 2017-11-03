package interpreter.ByteCode;

import interpreter.VirtualMachine;

import java.util.ArrayList;

public class StoreCode extends ByteCode {
    private String code;
    private String variable;

    @Override
    public void init(ArrayList<String> string) {
        code = string.get(0);
        if (!string.isEmpty()){
            variable = string.get(1);
        }
    }

    @Override
    public void execute(VirtualMachine virtualmachine) {
        int offset = virtualmachine.storeCall(Integer.parseInt(code));
        virtualmachine.pushOntoStack(offset);
    }

    @Override
    public String toString() {
        return "STORE " + code + " " + variable;
    }
}
