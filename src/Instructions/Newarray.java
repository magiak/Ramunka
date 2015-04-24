/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Instructions;

import ByteHelper.ByteReader;
import JvmHeap.Heap;
import JvmHeap.Instance;
import javavirtualmachine.Tuple;

/**
 *
 * @author lkmoch
 */
public class Newarray extends Instruction {
    @Override
    public void Execute(){
        Tuple<Byte, Integer> tuple = ByteReader.ReadByte(Code, CurrentPosition);
        CurrentPosition = tuple.Object2;
        int value = tuple.Object1; // o jaky typ pole jde //https://docs.oracle.com/javase/specs/jvms/se7/html/jvms-6.html#jvms-6.5.newarray
        int arraySize = (int)Frame.OperandStack.Pop();
        Instance instance = new Instance(ClassInfo);
        instance.Array = new Object[arraySize];
        int index = Heap.AddInstance(instance);
        Frame.OperandStack.Push(index);
    }
}
