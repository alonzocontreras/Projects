
package interpreter;

import interpreter.ByteCode.ByteCode;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class ByteCodeLoader extends Object {

    private BufferedReader byteSource;
    private Program program = new Program ();
    private ArrayList<String> tokenHolder;

    public ByteCodeLoader(String file) throws IOException {
        this.byteSource = new BufferedReader(new FileReader(file));
    }
    /**
     * This function should read one line of source code at a time.
     * For each line it should:
     *      Tokenize string to break it into parts.
     *      Grab correct class name for the given bytecode from CodeTable
     *      create an instance of the bytecode class name returned from code table.
     *      Parse any additional arguments for the given bytecode and send them to
     *      the newly created bytecode instance via the init function.
     */
    public Program loadCodes() {
        try {
            String stringLine = "";
            String token = "";
            String arg = "";
            while((stringLine = byteSource.readLine()) != null) {   //reads in one line of source code at a time
                tokenHolder = new ArrayList<String>();
                StringTokenizer string = new StringTokenizer(stringLine);   //tokenize's the string
                token = string.nextToken();     //breaks the string into parts
                //grab correct class name for the given bytecode from codetable
                String codeClass = CodeTable.getClassName(token);

                while (string.hasMoreTokens()) {
                    arg = string.nextToken();   //parse any additional arguments for the bytecode
                    tokenHolder.add(arg);   //send the arguments to the newly created bytecode instance
                }
                //create an instance of the bytecode class name returned from the code table
                ByteCode bytecode = (ByteCode) (Class.forName("interpreter.ByteCode." + codeClass).newInstance());
                //System.out.println(token);
                bytecode.init(tokenHolder);
                program.addLabel(bytecode);
            }
            program.resolveAddrs(program);
        } catch (IOException ex){
            System.out.println("IO Error");
        } catch (ClassNotFoundException ex1){
            System.out.println("Class Not Found");
        } catch (IllegalAccessException ex2){
            System.out.println("Illegal Access");
        } catch (InstantiationException ex3){
            System.out.println("Instantiation Error");
        }
        return program;
    }
}
