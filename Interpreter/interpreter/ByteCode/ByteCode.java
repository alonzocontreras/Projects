package interpreter.ByteCode;
import interpreter.VirtualMachine;

import java.util.ArrayList;

public abstract class ByteCode {
    public abstract void init (ArrayList<String> string);
    public abstract void execute(VirtualMachine virtualmachine);
    public abstract String toString();

}
