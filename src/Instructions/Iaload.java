/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Instructions;

import JvmHeap.Heap;
import JvmHeap.Instance;

/**
 *
 * @author lkmoch
 */
public class Iaload extends Instruction {
    @Override
    public void Execute(){
        int index = (int)Frame.OperandStack.Pop();
        int arrayref = (int)Frame.OperandStack.Pop();
        Instance instance = Heap.GetInstance(arrayref);
        Object[] array = instance.Array;
        Frame.OperandStack.Push(array[index]);
    }
}
