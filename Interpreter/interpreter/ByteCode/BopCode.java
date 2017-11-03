package interpreter.ByteCode;

import interpreter.VirtualMachine;

import java.util.ArrayList;

public class BopCode extends ByteCode {
    private String code;

    @Override
    public void init(ArrayList<String> string) {
        code = string.get(0);
    }

    @Override
    public void execute(VirtualMachine virtualmachine) {
        int firstPop = virtualmachine.popOnce();
        int secondPop = virtualmachine.popOnce();
        int result = 0;
        switch (code) {
            case "+":   result = secondPop + firstPop;
                        break;
            case "-":   result = secondPop - firstPop;
                        break;
            case "*":   result = secondPop * firstPop;
                        break;
            case "/":   result = secondPop / firstPop;
                        break;
            case "==":  if (secondPop == firstPop){
                            result = 1;
                        } else {
                            result = 0;
                        }
                        break;
            case "!=":  if (secondPop != firstPop){
                            result = 1;
                        } else {
                            result = 0;
                        }
                        break;
            case "<=":  if(secondPop <= firstPop){
                            result = 1;
                        } else {
                            result = 0;
                        }
                        break;
            case "<":   if(secondPop < firstPop){
                            result = 1;
                        } else {
                            result = 0;
                        }
                        break;
            case ">=":  if(secondPop >= firstPop){
                            result = 1;
                        } else {
                            result = 0;
                        }
                        break;
            case ">":   if(secondPop > firstPop){
                            result = 1;
                        } else {
                            result = 0;
                        }
                        break;
            case "|":   if(secondPop == 1 || firstPop == 1){
                            result = 1;
                        } else {
                            result = 0;
                        }
                        break;
            case "&":   if(secondPop == 1 && firstPop == 1){
                            result =1;
                        } else {
                            result = 0;
                        }
                        break;
        }
        virtualmachine.pushOntoStack(result);
    }

    @Override
    public String toString() {
        return "BOP " + code;
    }
}