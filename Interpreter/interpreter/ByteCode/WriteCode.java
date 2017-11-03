package interpreter.ByteCode;

import interpreter.VirtualMachine;

import java.util.ArrayList;

public class WriteCode extends ByteCode {

    @Override
    public void init(ArrayList<String> string) {}

    @Override
    public void execute(VirtualMachine virtualmachine) {
        int output = virtualmachine.peek();
        System.out.println("---------- Output: " + output + " ----------");
    }

    @Override
    public String toString() {
        return "WRITE";
    }
}

