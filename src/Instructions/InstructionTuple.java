/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Instructions;

import javavirtualmachine.InstructionTypes;

/**
 *
 * @author lkmoch
 */
public class InstructionTuple {
    public Instruction Instruction;
    public Integer Par;
    
    public InstructionTuple(Instruction instruction, Integer par){
        Instruction = instruction;
        Par = par;
    }
}
