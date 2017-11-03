package interpreter.ByteCode;

import interpreter.VirtualMachine;

import java.util.ArrayList;

public class ReadCode extends ByteCode {

    @Override
    public void init(ArrayList<String> string) {}

    @Override
    public void execute(VirtualMachine virtualmachine) {
        int result = virtualmachine.userInput();
        virtualmachine.pushOntoStack(result);
    }

    @Override
    public String toString() {
        return "READ";
    }
}
