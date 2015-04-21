/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javavirtualmachine;

import Memory.JvmMemory;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author lkmoch
 */
public class JavaVirtualMachine {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) 
    {
        //Nacti vsechny class soubory ze soucasne slozky
        List<String> classFiles = new ArrayList<>();
        String currentDirectory = System.getProperty("user.dir");
        File[] files = new File(System.getProperty("user.dir")).listFiles();
        //If this pathname does not denote a directory, then listFiles() returns null. 

        for (File file : files) {
            if (file.isFile()) {
                String fileName = file.getName();
                String[] nameAndExtension = fileName.split("\\.");
                if(nameAndExtension.length == 2){
                    if("class".equals(nameAndExtension[1])){
                        classFiles.add(file.getAbsolutePath());
                    }
                }
            }
        }
        
        // TODO code application logic here
        JVM jvm = new JVM(classFiles, new Interpreter(new JvmMemory()));
        
        jvm.Execute();
    }
}
