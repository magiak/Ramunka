/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Instructions;

import ByteHelper.ByteReader;
import Exceptions.LoadFileClassIsNotCompletedException;
import JavaInfo.ClassInfo;
import JavaInfo.ConstantInfo;
import JavaInfo.FieldInfo;
import JvmHeap.Heap;
import JvmHeap.Instance;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javavirtualmachine.JavaClassLoader;
import javavirtualmachine.Tuple;

/**
 *
 * @author lkmoch
 */
public class Putfield extends Instruction {
    @Override
    public void Execute(){
        Tuple<Integer, Integer> tuple = ByteReader.ReadShort(Code, CurrentPosition);
        CurrentPosition = tuple.Object2;
        int constantFieldInfoIndex = tuple.Object1;
        
        ConstantInfo constantFieldInfo = ClassInfo.GetConstantInfo(constantFieldInfoIndex);
        ConstantInfo fieldInfoNameAndType = ClassInfo.GetConstantInfo(constantFieldInfo.NameAndTypeIndex);
        String fieldName = ClassInfo.GetConstantInfo(fieldInfoNameAndType.NameIndex).String;
        
        Object value = Frame.OperandStack.Pop();
        int objectRef = (int)Frame.OperandStack.Pop();

        Instance instance = Heap.GetInstance(objectRef);
        instance.Class.Fields.put(fieldName, value);
        Heap.UpdateInstance(objectRef, instance);
    }
}
