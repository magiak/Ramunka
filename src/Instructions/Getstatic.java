/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Instructions;

import ByteHelper.ByteReader;
import javavirtualmachine.Tuple;

/**
 *
 * @author lkmoch
 */
public class Getstatic extends Instruction {
    @Override
    public void Execute(){
        Tuple<Integer, Integer> tuple = ByteReader.ReadShort(Code, CurrentPosition);
        CurrentPosition = tuple.Object2;
        int value = tuple.Object1;
        int classIndex = ClassInfo.GetClassIndexFromPoolTable(value);
        int nameAndType = ClassInfo.GetNameAndTypeIndexFromPoolTable(value);
        //Frame.OperandStack.Push();
    }
}
