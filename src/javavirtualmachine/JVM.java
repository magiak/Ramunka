/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javavirtualmachine;

import Instructions.Iconst;
import Instructions.Instruction;
import Memory.ClassHeap;
import Memory.JvmMemory;
import Memory.ObjectHeap;
import java.io.File;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

/**
 *
 * @author lkmoch
 */
public class JVM {
    private List<String> _classFiles;
    private Interpreter _interpreter;
    private JvmMemory _memory;
    private JavaClassLoader _javaClassLoader;
    
    public JVM(List<String> classFiles, Interpreter interpreter){
        _interpreter = interpreter;
        _classFiles = classFiles;
        _javaClassLoader = new JavaClassLoader();
    }
    
    public void Execute(){
        for(String file : _classFiles){
            _javaClassLoader.Load(file);
        }
    }
}
