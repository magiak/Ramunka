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
    
    //CONSTANT_METHODREF:
    public int NameAndTypeIndex;
    public int ClassIndex;
    
    //CONSTANT_NAME_AND_TYPE:
    //public int NameIndex;
    public int DescriptorIndex;
    
    //CONSTANT_UTF8
    public int Length;
    public String String;
}
