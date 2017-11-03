package interpreter.ByteCode;

import interpreter.VirtualMachine;

import java.util.ArrayList;

public class DumpCode extends ByteCode {
    private String code;
    private boolean print;

    @Override
    public void init(ArrayList<String> string) {
        code = string.get(0);
        if (code.equals("ON")){
            print = true;
        }
        else {
            print = false;
        }
    }

    @Override
    public void execute(VirtualMachine virtualmachine) {
        virtualmachine.dump(print);
    }

    @Override
    public String toString() {
        return "";
    }
}
