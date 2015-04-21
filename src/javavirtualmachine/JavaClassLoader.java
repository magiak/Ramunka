/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javavirtualmachine;

import java.io.File;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
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
    
    public int BytePairToInt(byte[] byteArray,  int index1, int index2){
        StringBuilder sb = new StringBuilder();

        byte[] myArray = new byte[index2 - index1 + 1];
        int j = 0;
        for(int i = index1; i < index2 + 1; i++){
            myArray[j++] = byteArray[i]; 
        }
        
        ByteBuffer bb = ByteBuffer.wrap(myArray);
        bb.order(ByteOrder.BIG_ENDIAN);
        while(bb.hasRemaining()) {
           short v = bb.getShort();
        }
        
        
        return 0;
    }
    
    public void Load(String fileUrl){
        Path path = Paths.get(fileUrl);
        try {
            byte[] byteArray = Files.readAllBytes(path);
            for(byte b : byteArray){
                System.out.print(" " + String.format("%02X ", b));
            }
            
            int a = BytePairToInt(byteArray, 6, 7);

        } catch (IOException ex) {
            Logger.getLogger(JavaClassLoader.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
}
