/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JavaInfo;

import static javavirtualmachine.JavaClassLoader.CONSTANT_UTF8;

/**
 *
 * @author lkmoch
 */
public class ClassInfo {
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
    public int[] AttributesTable;
}
