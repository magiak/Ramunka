/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Instructions;

/**
 *
 * @author lkmoch
 */
public class Iconst_1 extends Instruction {
    @Override
    public void Execute(){
        Frame.OperandStack.Push(1);
    }
}
