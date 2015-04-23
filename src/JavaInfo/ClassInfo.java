/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JavaInfo;

import static JavaInfo.AttributeInfo.ATTRIBUTE_CODE;

/**
 *
 * @author lkmoch
 */
public class ClassInfo {
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
    
    public int MagicNumber;
    public int MinorVersion;
    public int MajorVersion;
    
    public int ConstantPoolCount;
    public ConstantInfo[] ConstantPoolTable;

    public int AccessFlags;
    public int ThisClass;
    public int SuperClass;
    
    public int InterfaceCount;
    public int[] Interfaces;

    public int FieldsCount;
    public int[] FieldsTable;
    
    public int MethodCount;
    public MethodInfo[] Methods;

    public int AttributeCount;
    public AttributeInfo[] AttributesTable;
    
    public String GetMethodName(MethodInfo methodInfo){
        ConstantInfo info = ConstantPoolTable[methodInfo.NameIndex - 1];
        if(info.Tag == CONSTANT_UTF8){
            return info.String;
        }else{
            return "";
        }
    }
    
    public ConstantInfo GetConstantInfo(int index){
        return ConstantPoolTable[index - 1];
    }
    
    public MethodInfo GetMethodByName(String methodName){
        for(MethodInfo methodInfo : Methods){
            String currentMethodName = GetMethodName(methodInfo);
            if(currentMethodName == null ? methodName == null : currentMethodName.equals(methodName)){
                return methodInfo;
            }
        }
        
        return null;
    }
    
    public String GetAttributeName(AttributeInfo attributeInfo){
        ConstantInfo info = ConstantPoolTable[attributeInfo.AttributeNameIndex - 1];
        if(info.Tag == CONSTANT_UTF8){
            return info.String;
        }else{
            return "";
        }
    }
    
    public byte GetAttributeTag(AttributeInfo attributeInfo){
        ConstantInfo info = ConstantPoolTable[attributeInfo.AttributeNameIndex - 1];
        
        //TODO 
        String stringTag = info.String;
        switch(stringTag){
            case "Code":
                return ATTRIBUTE_CODE;
            default:
                return 0;
        }
    }
    
    public int GetClassIndexFromPoolTable(int index){
        ConstantInfo info = ConstantPoolTable[index - 1];
        return info.ClassIndex;
    }
    
    public int GetNameAndTypeIndexFromPoolTable(int index){
        ConstantInfo info = ConstantPoolTable[index - 1];
        return info.NameAndTypeIndex;
    }
}
