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
public class AttributeInfo {
    public static final byte ATTRIBUTE_CONSTANT_VALUE = 1;
    public static final byte ATTRIBUTE_CODE = 2;
    public static final byte ATTRIBUTE_STACK_MAP_TABLE = 3;
    public static final byte ATTRIBUTE_EXCEPTIONS = 4;
    public static final byte ATTRIBUTE_INNER_CLASSES = 5;
    public static final byte ATTRIBUTE_ENCLOSING_METHOD = 6;
    public static final byte ATTRIBUTE_SYNTHETIC = 7;
    public static final byte ATTRIBUTE_SIGNATURE = 8;
    public static final byte ATTRIBUTE_SOURCE_FILE = 9;
    public static final byte ATTRIBUTE_SOURCE_DEBUG_EXTENSION = 10;
    public static final byte ATTRIBUTE_LINE_NUMBER_TABLE = 11;
    public static final byte ATTRIBUTE_LOCAL_VARIABLE_TABLE = 12;
    public static final byte ATTRIBUTE_LOCAL_VARIABLE_TYPE_TABLE = 13;
    public static final byte ATTRIBUTE_DEPRECATED = 14;
    public static final byte ATTRIBUTE_RUNTIME_VISIBLE_ANNOTATIONS = 15;
    public static final byte ATTRIBUTE_RUNTIME_INVISIBLE_ANNOTATIONS = 16;
    public static final byte ATTRIBUTE_RUNTIME_VISIBLE_PARAMETR_ANNOTATIONS = 17;
    public static final byte ATTRIBUTE_RUNTIME_INVISIBLE_PARAMETR_ANNOTATIONS = 18;
    public static final byte ATTRIBUTE_ANNOTATION_DEFAULT = 19;
    public static final byte ATTRIBUTE_BOOTSTRAP_METHODS = 20;
    
    public byte Tag;
    
    public int AttributeNameIndex; //Index do constant poolu tridy
    public int AttributeLength;
    public byte[] Info;
}
