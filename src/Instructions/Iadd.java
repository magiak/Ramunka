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
public class Iadd extends Instruction {
    @Override
    public void Execute(){
       OperandStack stack = Frame.OperandStack;
        
       int cislo1 = (int)Frame.OperandStack.Pop();
       int cislo2 = (int)Frame.OperandStack.Pop();
       
       Frame.OperandStack.Push(cislo2 + cislo1);
    }
}
