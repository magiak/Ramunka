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
public class Iinc extends Instruction {
    @Override
    public void Execute(){
        Tuple<Byte,Integer> tuple = ByteReader.ReadByte(Code, CurrentPosition);
        CurrentPosition = tuple.Object2;
        int localArrayIndex = tuple.Object1;
        
        Tuple<Byte,Integer> tuple2 = ByteReader.ReadByte(Code, CurrentPosition);
        CurrentPosition = tuple2.Object2;
        int increase = tuple2.Object1;
        
        Frame.LocalVariables.put(localArrayIndex, (int)Frame.LocalVariables.get(localArrayIndex) + increase);
    }
}
