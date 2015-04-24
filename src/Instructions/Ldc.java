/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Instructions;

import ByteHelper.ByteReader;
import static JavaInfo.ClassInfo.CONSTANT_CLASS;
import static JavaInfo.ClassInfo.CONSTANT_FLOAT;
import static JavaInfo.ClassInfo.CONSTANT_INTEGER;
import static JavaInfo.ClassInfo.CONSTANT_STRING;
import JavaInfo.ConstantInfo;
import javavirtualmachine.Tuple;

/**
 *
 * @author lkmoch
 */
public class Ldc extends Instruction {
    @Override
    public void Execute(){
        Tuple<Byte,Integer> tuple = ByteReader.ReadByte(Code, CurrentPosition);
        CurrentPosition = tuple.Object2;
        int index = tuple.Object1;
        ConstantInfo info = ClassInfo.GetConstantInfo(index);
        
        switch(info.Tag){
            case CONSTANT_INTEGER:
            case CONSTANT_FLOAT:
                //TODO
                int a = 5;
                break;
            case CONSTANT_STRING:
                Frame.OperandStack.Push(index);
                break;
            case CONSTANT_CLASS:
                //TODO
                int b = 5;
                break;
            
        }
        
    }
}
