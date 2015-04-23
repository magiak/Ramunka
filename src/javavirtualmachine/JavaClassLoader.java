/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javavirtualmachine;

import Exceptions.LoadFileClassIsNotCompletedException;
import JavaInfo.AttributeInfo;
import JavaInfo.ClassInfo;
import static JavaInfo.ClassInfo.CONSTANT_CLASS;
import static JavaInfo.ClassInfo.CONSTANT_DOUBLE;
import static JavaInfo.ClassInfo.CONSTANT_FIELDREF;
import static JavaInfo.ClassInfo.CONSTANT_FLOAT;
import static JavaInfo.ClassInfo.CONSTANT_INTEGER;
import static JavaInfo.ClassInfo.CONSTANT_INTERFACE_METHODREF;
import static JavaInfo.ClassInfo.CONSTANT_LONG;
import static JavaInfo.ClassInfo.CONSTANT_METHODREF;
import static JavaInfo.ClassInfo.CONSTANT_METHOD_HANDLE;
import static JavaInfo.ClassInfo.CONSTANT_METHOD_TYPE;
import static JavaInfo.ClassInfo.CONSTANT_NAME_AND_TYPE;
import static JavaInfo.ClassInfo.CONSTANT_STRING;
import static JavaInfo.ClassInfo.CONSTANT_UTF8;
import static JavaInfo.ClassInfo.INVOKE_DYNAMIC;
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
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author lkmoch
 */
public class JavaClassLoader {
    public static Map<String, ClassInfo> LoadedClass = new HashMap<>();
    
    private static int GetSize(byte tag){
        int size = 0;
        switch(tag){
            case CONSTANT_CLASS:
                size = 2; //DONE
                break;
            case CONSTANT_FIELDREF:
                size = 4; //DONE
                break;
            case CONSTANT_METHODREF:
                size = 4; //DONE
                break;
            case CONSTANT_INTERFACE_METHODREF:
                size = 4;
                break;
            case CONSTANT_STRING:
                size = 2; //DONE
                break;
            case CONSTANT_INTEGER:
                size = 4; //TODO
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
                size = 4; //DONE
                break;
            case CONSTANT_METHOD_HANDLE:
                size = 3; //TODO
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
    
    private static AttributeInfo ReadAtrribute(DataInputStream dis, ClassInfo classInfo) throws IOException{
        AttributeInfo info = new AttributeInfo();
        info.AttributeNameIndex = dis.readUnsignedShort();
        info.Tag = classInfo.GetAttributeTag(info);
        info.AttributeLength = dis.readInt();
        
        info.Info = new byte[info.AttributeLength];
        //String name = //TADY by jsi mel ziskat z constant poolu jmeno
        dis.readFully(info.Info); //TODO
        
        return info;
    }
    
    private static MethodInfo ReadMethod(DataInputStream dis, ClassInfo classInfo) throws IOException{
        MethodInfo info = new MethodInfo();
        
        info.Accessflags = dis.readUnsignedShort();
        info.NameIndex = dis.readUnsignedShort();
        String index = Integer.toHexString(info.NameIndex);
        info.DescriptorIndex = dis.readUnsignedShort();
        
        
        info.AttributesCount = dis.readUnsignedShort();
        info.Attributes = new AttributeInfo[info.AttributesCount];
        for(int i = 0; i < info.AttributesCount; i++){
            info.Attributes[i] = ReadAtrribute(dis, classInfo);
        }
        
        return info;
    }
    
    private static ConstantInfo ReadConstant(DataInputStream dis) throws IOException{
        ConstantInfo info = new ConstantInfo();
        info.Tag = dis.readByte();
        info.Size = GetSize(info.Tag); //toto asi ani nepotrebuju
        
        switch(info.Tag){
            case CONSTANT_CLASS:
                info.NameIndex = dis.readUnsignedShort();
                break;
            case CONSTANT_METHODREF:
                info.ClassIndex = dis.readUnsignedShort();
                info.NameAndTypeIndex = dis.readUnsignedShort();
                break;
            case CONSTANT_NAME_AND_TYPE:
                info.NameIndex = dis.readUnsignedShort();
                info.DescriptorIndex = dis.readUnsignedShort();
                break;
            case CONSTANT_UTF8:
                //info.Length = dis.readUnsignedShort(); //POZOR toto neni delka Stringu ale pole Bytes z ktereho jsme string ziskali
                info.String = dis.readUTF(); //neboli bytes
                break;
            case CONSTANT_FIELDREF:
                info.ClassIndex = dis.readUnsignedShort();
                info.NameAndTypeIndex = dis.readUnsignedShort();
                break;
            case CONSTANT_STRING:
                info.StringIndex = dis.readUnsignedShort();
                break;
            case CONSTANT_INTEGER:
                info.StringIndex = dis.readInt();
                break;
            case CONSTANT_METHOD_HANDLE:
                info.ReferenceKind = dis.readByte();
                info.ReferenceIndex = dis.readUnsignedShort();
                break;
        }

        return info;
    }
    
    private static ClassInfo ReadClass(DataInputStream dis) throws IOException{
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
            info.Methods[i] = ReadMethod(dis, info);
        }

        info.AttributeCount = dis.readUnsignedShort();
        info.AttributesTable = new AttributeInfo[info.AttributeCount];
        for(int i = 0; i < info.AttributeCount; i++){
            info.AttributesTable[i] = ReadAtrribute(dis, info);
        }
        
        return info;
    }
    
    public static ClassInfo Load(String fileUrl) throws LoadFileClassIsNotCompletedException{
        if(!fileUrl.endsWith(".class")){
            fileUrl += ".class";
        }
        if(fileUrl.contains("/")){
            fileUrl = fileUrl.split("/")[fileUrl.split("/").length - 1];
        }
        
        Path path = Paths.get(fileUrl);
        
        ClassInfo info = null;
        
        if(LoadedClass.containsKey(fileUrl)){
            return LoadedClass.get(fileUrl);
        }
        
        try {
            File file = new File(fileUrl);
            InputStream is = new FileInputStream(file);
            DataInputStream dis = new DataInputStream(new BufferedInputStream(is));
            
            info = ReadClass(dis);
            
            Boolean isEnd = dis.read() == -1 ;
            
            if(!isEnd){
                throw new LoadFileClassIsNotCompletedException();
            }
        } catch (IOException ex) {
            Logger.getLogger(JavaClassLoader.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        LoadedClass.put(fileUrl, info);
        
        return info;
    }
}
