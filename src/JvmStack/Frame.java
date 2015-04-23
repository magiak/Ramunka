/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JvmStack;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author lkmoch
 */
public class Frame {
    public OperandStack OperandStack;
    public Map<Integer, Object> LocalVariables;
    
    public Frame(){
        OperandStack = new OperandStack();
        LocalVariables = new HashMap<>();
    }
}
