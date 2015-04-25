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
public class Aaload extends Instruction {
    @Override
    public void Execute(){
        int index = (int)Frame.OperandStack.Pop();
        int arrayRef = (int)Frame.OperandStack.Pop();
        
        Instance instance = Heap.GetInstance(arrayRef);
        Frame.OperandStack.Push(instance.Array[index]);
        
    }
}
