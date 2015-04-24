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
/*
u2             access_flags;
u2             name_index;
u2             descriptor_index;
u2             attributes_count;
attribute_info attributes[attributes_count];
*/
public class FieldInfo {
    public static final int ACC_PUBLIC = 0x0001;
    public static final int ACC_PRIVATE = 0x0002;
    public static final int ACC_PROTECTED = 0x0004;
    public static final int ACC_STATIC = 0x0008;
    public static final int ACC_FINAL = 0x0010;
    public static final int ACC_VOLATILE = 0x0040;
    public static final int ACC_TRANSIENT = 0x0080;
    public static final int ACC_SYNTHETIC = 0x1000;
    public static final int ACC_ENUM = 0x4000;
    
    public int AccessFlags;
    public int NameIndex;
    public int DescriptorIndex;
    public int AttributesCount;
    public AttributeInfo[] Attributes;
    public Object Value;
    
    public Boolean HasFlag(int flag){
        return (AccessFlags >> (int)(Math.log((double)flag)/Math.log(2.0))) % 2 == 1;
    }
}
