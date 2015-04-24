/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JavaInfo;

/**
 *
 * @author lkmoch
 */
public class ConstantInfo {
    public byte Tag;
    public int Size;
    
    //CONSTANT_CLASS:
    public int NameIndex;
    
    //CONSTANT_FIELDREF, CONSTANT_METHODREF a CONSTANT_INTERFACE_METHODREF:
    public int NameAndTypeIndex;
    public int ClassIndex;
    
    //CONSTANT_NAME_AND_TYPE:
    //public int NameIndex;
    public int DescriptorIndex;
    
    //CONSTANT_UTF8
    public int Length;
    public String String;
    
    //CONSTANT_STRING
    public int StringIndex;
    
    //CONSTANT_INTEGER a CONSTANT_FLOAT:
    public int Bytes;
    
    //CONSTANT_METHOD_HANDLE:
    public byte ReferenceKind;
    public int ReferenceIndex;
    
    //CONSTANT_LONG a CONSTANT_DOUBLE:
    public int HighBytes;
    public int LowBytes;
    
    //CONSTANT_METHOD_TYPE:
    //public int DescriptorIndex;
    
    //CONSTANT_INVOKE_DYNAMIC:
    public int BootstrapMethodAttrIndex;
    //public int NameAndTypeIndex;
}
