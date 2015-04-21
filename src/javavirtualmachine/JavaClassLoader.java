/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javavirtualmachine;

import JavaInfo.AttributeInfo;
import JavaInfo.ClassInfo;
import JavaInfo.ConstantInfo;
import JavaInfo.MethodInfo;
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
    public static final byte INVOKE_DYNAMIC = 18;
    
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
            case INVOKE_DYNAMIC:
                size = 4;
                break;
        }
        
        return size;
    }
    
    
    
    public AttributeInfo ReadAtrribute(DataInputStream dis) throws IOException{
        AttributeInfo info = new AttributeInfo();
        info.AttributeNameIndex = dis.readUnsignedShort();
        info.AttributeLength = dis.readInt();
        
        info.Attribute = new byte[info.AttributeLength];
        dis.readFully(info.Attribute);
        
        return info;
    }
    
    public MethodInfo ReadMethod(DataInputStream dis) throws IOException{
        MethodInfo info = new MethodInfo();
        
        info.Accessflags = dis.readUnsignedShort();
        info.NameIndex = dis.readUnsignedShort();
        info.DescriptorIndex = dis.readUnsignedShort();
        
        info.AttributesCount = dis.readUnsignedShort();
        info.Attributes = new AttributeInfo[info.AttributesCount];
        for(int i = 0; i < info.AttributesCount; i++){
            info.Attributes[i] = ReadAtrribute(dis);
        }
        
        return info;
    }
    
    public ConstantInfo ReadConstant(DataInputStream dis) throws IOException{
        ConstantInfo info = new ConstantInfo();
        info.Tag = dis.readByte();
        info.Size = GetSize(info.Tag);
        if(info.Tag == CONSTANT_UTF8){
            info.Size = dis.readUnsignedShort();
            dis.skip(info.Size);
        }else{
            dis.skip(info.Size);
        }

        return info;
    }
    
    public ClassInfo ReadClass(DataInputStream dis) throws IOException{
        ClassInfo info = new ClassInfo();
        
        info.MagicNumber = dis.readInt();
        info.MinorVersion = dis.readUnsignedShort();
        info.MajorVersion = dis.readUnsignedShort();
        info.ConstantPoolCount = dis.readUnsignedShort();
        info.ConstantPoolTable = new ConstantInfo[info.ConstantPoolCount - 1];
        for(int i = 0; i < info.ConstantPoolCount - 1; i++){
            info.ConstantPoolTable[i] = ReadConstant(dis);
        }

        info.AccessFlags = dis.readUnsignedShort();
        info.ThisClass = dis.readUnsignedShort();
        info.SuperClass = dis.readUnsignedShort();
        info.InterfaceCount = dis.readUnsignedShort();
        info.Interfaces = new int[info.InterfaceCount];
        for(int i = 0; i < info.InterfaceCount; i++){
            info.Interfaces[i] = dis.readUnsignedShort();
        }

        info.FieldsCount = dis.readUnsignedShort();

        info.FieldsTable = new int[info.FieldsCount];
        for(int i = 0; i < info.FieldsCount; i++){
            info.FieldsTable[i] = dis.readUnsignedShort();
        }

        info.MethodCount = dis.readUnsignedShort();
        info.Methods = new MethodInfo[info.MethodCount];
        for(int i = 0; i < info.MethodCount; i++){
            info.Methods[i] = ReadMethod(dis);
        }

        info.AttributeCount = dis.readUnsignedShort();
        info.AttributesTable = new int[info.AttributeCount];
        for(int i = 0; i < info.AttributeCount; i++){
            ReadAtrribute(dis);
        }
        
        return info;
    }
    
    public void Load(String fileUrl){
        Path path = Paths.get(fileUrl);
        try {
            File file = new File(fileUrl);
            InputStream is = new FileInputStream(file);
            DataInputStream dis = new DataInputStream(new BufferedInputStream(is));
            
            ClassInfo info = ReadClass(dis);
            
            Boolean isEnd = dis.read() == -1 ;
            
            int a = 5;
        } catch (IOException ex) {
            Logger.getLogger(JavaClassLoader.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
