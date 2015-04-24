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
    public static final byte CONSTANT_INVOKE_DYNAMIC = 18;
    
    public static final int ACC_PUBLIC = 0x0001;
    public static final int ACC_FINAL = 0x0010;
    public static final int ACC_SUPER = 0x0020;
    public static final int ACC_INTERFACE = 0x0200;
    public static final int ACC_ABSTRACT = 0x0400;
    public static final int ACC_SYNTHETIC = 0x1000;
    public static final int ACC_ANNOTATION = 0x2000;
    public static final int ACC_ENUM = 0x4000;
    
    public Boolean IsInicialized;
    
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
    public FieldInfo[] FieldsTable;
    
    public int MethodCount;
    public MethodInfo[] Methods;

    public int AttributeCount;
    public AttributeInfo[] AttributesTable;
    
    public String GetFieldName(FieldInfo fieldInfo){
        ConstantInfo nameConstant = ConstantPoolTable[fieldInfo.NameIndex - 1];
        return nameConstant.String;
    }
    
    public FieldInfo GetField(String name){
        for(FieldInfo fieldInfo : FieldsTable){
            String fieldName = GetFieldName(fieldInfo);
            if(fieldName == null ? name == null : fieldName.equals(name)){
                return fieldInfo;
            }
        }
        
        return null;
    }
    
    public FieldInfo GetField(int nameIndex, int descriptorIndex){
        for(FieldInfo fieldInfo : FieldsTable){
            if(fieldInfo.NameIndex == nameIndex && fieldInfo.DescriptorIndex == descriptorIndex){
                return fieldInfo;
            }
        }
        
        return null;
    }
    
    public String GetMethodName(MethodInfo methodInfo){
        ConstantInfo info = ConstantPoolTable[methodInfo.NameIndex - 1];
        if(info.Tag == CONSTANT_UTF8){
            return info.String;
        }else{
            return "";
        }
    }
    
    public String GetMethodDescriptor(MethodInfo methodInfo){
        ConstantInfo info = ConstantPoolTable[methodInfo.DescriptorIndex - 1];
        if(info.Tag == CONSTANT_UTF8){
            return info.String;
        }else{
            return "";
        }
    }
    
    public MethodInfo GetMethod(String methodName, String methodDescriptor){
        for(MethodInfo methodInfo : Methods){
            String currentMethodName = GetMethodName(methodInfo);
            String currentMethodDescriptor = GetMethodDescriptor(methodInfo);
            if(currentMethodName.equals(methodName) && currentMethodDescriptor.equals(methodDescriptor)){
                return methodInfo;
            }
        }
        
        return null;
    }
    
    public ConstantInfo GetConstantInfo(int index){
        return ConstantPoolTable[index - 1];
    }
    
    @Deprecated
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
    
    public String GetClassName(){
        ConstantInfo classInfo = ConstantPoolTable[ThisClass - 1];
        ConstantInfo classNameInfo = ConstantPoolTable[classInfo.NameIndex - 1];
        
        if(classNameInfo.Tag == CONSTANT_UTF8){
            return classNameInfo.String;
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
    
    public String GetClassNameFromPoolTable(int index){
        ConstantInfo info = ConstantPoolTable[index - 1];
        return info.String;
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
