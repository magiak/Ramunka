/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Instructions;

import JvmStack.OperandStack;

/**
 *
 * @author lkmoch
 */
public class Dup extends Instruction {
    @Override
    public void Execute(){
       OperandStack stack = Frame.OperandStack;
        
       Object object = Frame.OperandStack.Pop();
       
       Frame.OperandStack.Push(object);
       Frame.OperandStack.Push(object);
    }
}
