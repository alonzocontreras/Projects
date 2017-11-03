package interpreter;

import java.util.ArrayList;
import java.util.Stack;

public class RunTimeStack {

    private ArrayList<Integer> runTimeStack;
    private Stack<Integer> framePointer;

    public RunTimeStack() {
        runTimeStack = new ArrayList<>();
        framePointer = new Stack<>();
        //Add initial Frame Pointer, main is the entry
        // point of our language, so its frame pointer is 0.
        framePointer.add(0);
    }

    public void dump() {
        int size = runTimeStack.size();
        int iterator = 0;
        int frmPtr = framePointer.peek();
        boolean once = true;
        //checking if the RTS is empty
        if (runTimeStack.isEmpty() && framePointer.empty()) {
            System.out.println("[]");
        } /*else if (runTimeStack.isEmpty() && !framePointer.empty()) {
            System.out.println("[]");
        }*/
        //checking if the frame pointer is empty
        else if (framePointer.empty()) {
            System.out.println(runTimeStack);
        }
        //checking if there is a frame pointer
        else {
            boolean coma = true;
            int sizeOfFramePointer = framePointer.size();
            if (!runTimeStack.isEmpty()) {
                Stack<Integer> tempFP1 = new Stack<Integer>();
                Stack<Integer> tempFP2 = new Stack<Integer>();
                for (int i = 0; i < sizeOfFramePointer; i++) {
                    tempFP1.push(framePointer.peek());
                    tempFP2.push(framePointer.pop());
                }
                for (int j = 0; j < sizeOfFramePointer; j++) {
                    framePointer.push(tempFP2.pop());
                }
                System.out.print("[");
                for (int k = 0; k < runTimeStack.size(); k++) {
                    if (!tempFP1.empty()) {
                        if (!(tempFP1.peek() == k)) {
                            if (k != 0) {
                                System.out.print("][");
                            }
                            tempFP1.pop();
                        }
                    }
                    if (!tempFP1.empty()) {
                        if (tempFP1.peek() == (k + 1)) {
                            coma = false;
                        }
                    }
                    System.out.print(runTimeStack.get(k));
                    if ((runTimeStack.size() != 1) && ((runTimeStack.size() - 1) != k) && coma) {
                        System.out.print(",");
                    }
                    if (!coma) {
                        coma = true;
                    }
                }
                System.out.println("]");
            }
        }

    }

    public int peek() {
        if (runTimeStack.isEmpty()) {
            //System.out.println("RuntimeStack in RTS.peek is empty!!");
            return 0;
        }
        Integer arg = runTimeStack.get(runTimeStack.size() - 1);
        int code = arg.intValue();
        return code;
    }

    public int pop() {
        if (runTimeStack.isEmpty()) {
            //System.out.println("Runtimestack in RTS.pop is empty!");
            return 0;
        }
        int code = peek();
        //System.out.println(code + " <---value being popped");
        runTimeStack.remove(runTimeStack.size() - 1);
        return code;
    }

    public int push(int i) {
        //System.out.println(i + " <------value i pushed into RTS   " + runTimeStack + " <--- RTS before i");
        runTimeStack.add(i);
        //System.out.println(runTimeStack + " <-----RTS after i");
        return i;
    }

    public void newFrameAt(int offset) {
        framePointer.push(runTimeStack.size() - offset);
    }

    public int store(int offset) {
        runTimeStack.set(peekFrame() + offset, pop());
        return runTimeStack.get(peekFrame() + offset);
    }

    public int load(int offset) {
        if (runTimeStack.isEmpty()) {
            //System.out.println("RunTimeStack in RTS.load is empty!!!");
            return 0;
        }
        runTimeStack.add(runTimeStack.get(offset + peekFrame()));
        return 0;
    }

    public Integer push(Integer i) {
        return push((int) i);
    }

    public int peekFrame() {
        return framePointer.peek();
    }

    public void reset() {
        int i = runTimeStack.remove(runTimeStack.size() - 1);
        while (runTimeStack.size() > framePointer.peek()) {
            runTimeStack.remove(runTimeStack.size() - 1);
        }
        framePointer.pop();
        runTimeStack.add(i);
    }

    public int size() {
        return runTimeStack.size();
    }
}
