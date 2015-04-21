/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Instructions;

import Memory.JvmMemory;

/**
 *
 * @author lkmoch
 */
public abstract class Instruction {
    public JvmMemory Memory;
    
    public abstract void Execute(Integer par);
}
