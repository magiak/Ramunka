/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Instructions;

import ByteHelper.ByteReader;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javavirtualmachine.Tuple;

/**
 *
 * @author lkmoch
 */
public class Bipush extends Instruction {
    @Override
    public void Execute(){
        Tuple<Byte, Integer> tuple = ByteReader.ReadByte(Code, CurrentPosition);
        CurrentPosition = tuple.Object2;
        
        Frame.OperandStack.Push((int)tuple.Object1);
    }
}
