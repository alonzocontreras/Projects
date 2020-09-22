package interpreter;
import interpreter.ByteCode.*;

import java.util.ArrayList;
import java.util.HashMap;

public class Program {

    private ArrayList<ByteCode> program;
    private static HashMap<String, Integer> LabelName;

    public Program() {

        program = new ArrayList<>();
    }

    protected ByteCode getCode(int pc) {

        return this.program.get(pc);
    }

    public int getSize() {

        return this.program.size();
    }

    public void addLabel(ByteCode bytecode){
        program.add(bytecode);
    }

    /**
     * This function should go through the program and resolve all addresses.
     * Currently all labels look like LABEL <<num>>>, these need to be converted into
     * correct addresses so the VirtualMachine knows what to set the Program Counter(PC)
     * HINT: make note what type of data-stucture bytecodes are stored in.
     *
     * @param program Program object that holds a list of ByteCodes
     */
    public void resolveAddrs(Program program) {
        LabelName = new HashMap<>();

        int size = program.getSize();
        for (int i = 0; i < size; i++){
            ByteCode currentCode = program.getCode(i);
            if (currentCode instanceof LabelCode){
                LabelName.put(((LabelCode) currentCode).get(), i);
            }
        }
        for (int j = 0; j < size; j++){
            ByteCode currentCode = program.getCode(j);
            if (currentCode instanceof FalseBranchCode){
                if(LabelName.containsKey(((FalseBranchCode)currentCode).get())){
                    ((FalseBranchCode)currentCode).set(LabelName.get(((FalseBranchCode)currentCode).get()));
                    //System.out.println(((FalseBranchCode) currentCode).get());
                }
            }
            if (currentCode instanceof GotoCode){
                if(LabelName.containsKey(((GotoCode)currentCode).get())){
                    ((GotoCode)currentCode).set(LabelName.get(((GotoCode)currentCode).get()));
                    //System.out.println(((GotoCode) currentCode).get());
                }
            }
            if (currentCode instanceof CallCode){
                if (LabelName.containsKey(((CallCode)currentCode).get())){
                    ((CallCode)currentCode).set(LabelName.get(((CallCode)currentCode).get()));
                    //System.out.println(((CallCode) currentCode).get());
                }
            }
        }
        /*for (int i = 0; i < 42; i++){
            System.out.println(program.getCode(i).getClass());
        }*/
    }
}
