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
public class Arraylength extends Instruction {
    @Override
    public void Execute(){
        int arrayIndex = (int) Frame.OperandStack.Pop();
        Instance instance = Heap.GetInstance(arrayIndex);
        Frame.OperandStack.Push(instance.Array.length);
    }
}
