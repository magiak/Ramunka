/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javavirtualmachine;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author lkmoch
 */
public class JavaClassLoader {
    public void Load(String fileUrl){
        Path path = Paths.get(fileUrl);
        try {
            byte[] byteArray = Files.readAllBytes(path);
            
            
        } catch (IOException ex) {
            Logger.getLogger(JavaClassLoader.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
}