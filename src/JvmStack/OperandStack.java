/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JvmStack;

import java.util.LinkedList;

/**
 *
 * @author lkmoch
 */
public class OperandStack {
    private LinkedList<Object> _operandStack;
    
    public OperandStack(){
        _operandStack = new LinkedList<>();
    }

    public Object Top(){
        return _operandStack.getLast();
    }
    
    public Object Pop(){
        Object object = _operandStack.getLast();
        _operandStack.removeLast();
        return object;
    }
    
    public void Push(Object object){
        _operandStack.add(object);
    }
}
