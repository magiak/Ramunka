/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Instructions;

import JvmStack.OperandStack;
import java.io.EOFException;
import java.io.IOException;

/**
 *
 * @author lkmoch
 */
public class ReturnInst extends Instruction {
    @Override
    public void Execute() throws IOException{
        CurrentPosition = CodeLength;
    }
}
