/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javavirtualmachine;

import Exceptions.LoadFileClassIsNotCompletedException;
import Instructions.Iconst_0;
import Instructions.Instruction;
import JavaInfo.ClassInfo;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author lkmoch
 */
public class JVM {
    private String _mainClassFileName;
    private List<String> _classFiles;
    private Interpreter _interpreter;
    
    
    public JVM(String mainClassFileName, List<String> classFiles, Interpreter interpreter){
        _mainClassFileName = mainClassFileName;
        _interpreter = interpreter;
        _classFiles = classFiles;
    }
    
    public void Execute() throws IOException{
        ClassInfo classInfo = null;
        try {
            classInfo = JavaClassLoader.Load(_mainClassFileName, _interpreter);
        } catch (LoadFileClassIsNotCompletedException ex) {
            Logger.getLogger(JVM.class.getName()).log(Level.SEVERE, null, ex);
        }
                
        //System.out.println("KONEC NACITANI HLAVNIHO CLASS FILE");
        
        
        //Interpret
        _interpreter.Execute(classInfo, _classFiles);
        
        //System.out.println("KONEC!!!!!!");
    }
}
