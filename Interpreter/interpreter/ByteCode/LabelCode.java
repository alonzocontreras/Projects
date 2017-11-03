package interpreter.ByteCode;

import interpreter.VirtualMachine;

import java.util.ArrayList;

public class LabelCode extends ByteCode {
    private String code;
    @Override
    public void init(ArrayList<String> string) {
        code = string.get(0);
    }

    @Override
    public void execute(VirtualMachine virtualmachine) {

    }

    public String get(){
        return code;
    }

    @Override
    public String toString() {
        return "LABEL " + code;
    }
}
