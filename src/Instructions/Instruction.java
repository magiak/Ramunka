/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Instructions;

import JavaInfo.ClassInfo;
import JvmHeap.Heap;
import JvmStack.Frame;
import java.io.DataInputStream;
import java.io.IOException;
import javavirtualmachine.Interpreter;

/**
 *
 * @author lkmoch
 */
public abstract class Instruction {
    public ClassInfo ClassInfo;
    public int CurrentPosition;
    public byte[] Code;
    public int CodeLength;
    //public Heap Heap;
    public Frame Frame;
    public Interpreter Interpreter;
    public Object Result;
    
    public abstract void Execute() throws IOException;
}
