/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javavirtualmachine;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
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
    public static final byte CONSTANT_CLASS = 7;
    public static final byte CONSTANT_FIELDREF = 9;
    public static final byte CONSTANT_METHODREF = 10;
    public static final byte CONSTANT_INTERFACE_METHODREF = 11;
    public static final byte CONSTANT_STRING = 8;
    public static final byte CONSTANT_INTEGER = 3;
    public static final byte CONSTANT_FLOAT = 4;
    public static final byte CONSTANT_LONG = 5;
    public static final byte CONSTANT_DOUBLE = 6;
    public static final byte CONSTANT_NAME_AND_TYPE = 12;
    public static final byte CONSTANT_METHOD_HANDLE = 15;
    public static final byte CONSTANT_METHOD_TYPE = 16;
    public static final byte CONSTANT_UTF8 = 1;
    
    public int GetSize(byte tag){
        int size = 0;
        switch(tag){
            case CONSTANT_CLASS:
                size = 2;
                break;
            case CONSTANT_FIELDREF:
                size = 4;
                break;
            case CONSTANT_METHODREF:
                size = 4;
                break;
            case CONSTANT_INTERFACE_METHODREF:
                size = 4;
                break;
            case CONSTANT_STRING:
                size = 2;
                break;
            case CONSTANT_INTEGER:
                size = 4;
                break;
            case CONSTANT_FLOAT:
                size = 4;
                break;
            case CONSTANT_LONG:
                size = 8;
                break;
            case CONSTANT_DOUBLE:
                size = 8;
                break;
            case CONSTANT_NAME_AND_TYPE:
                size = 4;
                break;
            case CONSTANT_METHOD_HANDLE:
                size = 3;
                break;
            case CONSTANT_METHOD_TYPE:
                size = 2;
                break;
        }
        
        return size;
    }
    
    public byte[] GetSubByteArray(byte[] byteArray,  int index1, int index2){
        byte[] subArray = new byte[index2 - index1 + 1];
        int j = 0;
        for(int i = index1; i < index2 + 1; i++){
            subArray[j++] = byteArray[i]; 
        }
        
        return subArray;
    }
    
    public int BytePairToInt(byte[] byteArray, int index1, int index2){
        StringBuilder sb = new StringBuilder();

        byte[] subArray = GetSubByteArray(byteArray, index1, index2);
        
        ByteBuffer bb = ByteBuffer.wrap(subArray);
        bb.order(ByteOrder.BIG_ENDIAN);
        return bb.getShort();
    }
    
    public void Load(String fileUrl){
        Path path = Paths.get(fileUrl);
        try {
            File file = new File(fileUrl);
            InputStream is = new FileInputStream(file);
            // create data input stream
            DataInputStream dis = new DataInputStream(new BufferedInputStream(is));
            int magicNumber = dis.readInt();
            int minorVersion = dis.readUnsignedShort();
            int majorVersion = dis.readUnsignedShort();
            int constantPoolCount = dis.readUnsignedShort();
            for(int i = 0; i < constantPoolCount; i++){
                byte tag = dis.readByte();
                int size = GetSize(tag);
                if(tag == CONSTANT_UTF8){
                    int utfSize = dis.readUnsignedShort();
                    dis.skip(utfSize);
                }else{
                    dis.skip(size);
                }
            }
            
            int accessFlags = dis.readUnsignedShort();
            String flags = Integer.toHexString(accessFlags);
            
            int thisClass = dis.readUnsignedShort();
            int superClass = dis.readUnsignedShort();
            int interfaceCount = dis.readUnsignedShort();
            int[] interfaces = new int[interfaceCount];
            for(int i = 0; i < interfaceCount; i++){
                interfaces[i] = dis.readUnsignedShort();
            }
            
            int fieldsCount = dis.readUnsignedShort();
            int[] fieldsTable = new int[fieldsCount];
            for(int i = 0; i < fieldsCount; i++){
                fieldsTable[i] = dis.readUnsignedShort();
            }
            
            byte[] res = new byte[100];
            int a = dis.read(res);
            
            int methodCount = dis.readUnsignedShort();
            
            
           

        } catch (IOException ex) {
            Logger.getLogger(JavaClassLoader.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
}
