package interpreter.ByteCode;

import interpreter.VirtualMachine;

import java.util.ArrayList;

public class HaltCode extends ByteCode {
    private String arg = null;

    @Override
    public void init(ArrayList<String> string) {
    }

    @Override
    public void execute(VirtualMachine virtualmachine) {
        virtualmachine.setIsRunning(false);
    }

    @Override
    public String toString() {
        return "HALT";
    }
}