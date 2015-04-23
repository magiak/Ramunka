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
import JvmHeap.Instance;
import JvmStack.OperandStack;
import java.util.logging.Level;
import java.util.logging.Logger;
import javavirtualmachine.JavaClassLoader;
import javavirtualmachine.Tuple;

/**
 *
 * @author lkmoch
 */
public class New extends Instruction {
    @Override
    public void Execute(){
        Tuple<Integer,Integer> tuple = ByteReader.ReadShort(Code, CurrentPosition);
        CurrentPosition = tuple.Object2;
        int index = tuple.Object1;
        ConstantInfo classInfo = ClassInfo.GetConstantInfo(index);
        ConstantInfo classNameInfo = ClassInfo.GetConstantInfo(classInfo.NameIndex);
        String classFileName = classNameInfo.String;
        ClassInfo newClassInfo = null;
        try {
            newClassInfo = JavaClassLoader.Load(classFileName);
        } catch (LoadFileClassIsNotCompletedException ex) {
            Logger.getLogger(New.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        Instance instance = new Instance();
        instance.Object = newClassInfo;
        int classInfoIndex = Heap.AddInstance(instance);
        Frame.OperandStack.Push(classInfoIndex);
        
        OperandStack stack = Frame.OperandStack;
        int a = 5;
    }
}