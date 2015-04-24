/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Instructions;

import ByteHelper.ByteReader;
import JavaInfo.ConstantInfo;
import JavaInfo.FieldInfo;
import JvmHeap.Heap;
import JvmHeap.Instance;
import java.util.Map;
import javavirtualmachine.Tuple;

/**
 *
 * @author lkmoch
 */
public class Getfield extends Instruction {
    @Override
    public void Execute(){
        Tuple<Integer, Integer> tuple = ByteReader.ReadShort(Code, CurrentPosition);
        CurrentPosition = tuple.Object2;
        int constantFieldInfoIndex = tuple.Object1;
        
        ConstantInfo constantFieldInfo = ClassInfo.GetConstantInfo(constantFieldInfoIndex);
        ConstantInfo fieldInfoNameAndType = ClassInfo.GetConstantInfo(constantFieldInfo.NameAndTypeIndex);
        String fieldName = ClassInfo.GetConstantInfo(fieldInfoNameAndType.NameIndex).String;
        
        int objectRef = (int)Frame.OperandStack.Pop();
                
        Instance instance = Heap.GetInstance(objectRef);
        Frame.OperandStack.Push(instance.Class.Fields.get(fieldName));
    }
}
