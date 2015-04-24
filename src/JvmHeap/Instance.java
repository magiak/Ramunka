/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JvmHeap;

import JavaInfo.ClassInfo;
import java.util.HashMap;
import java.util.Map;


/**
 *
 * @author lkmoch
 */
public class Instance{
    public Instance(ClassInfo classInfo){
        ClassInfo = classInfo;
    }
    /*public Map<String, Object> Values;
    
    public Instance(){
        Values = new HashMap<>();
    }*/
    
    /*
    public String String;
    public int Int;
    public float Float;
    ...
    */
    public Object[] Array;  
    public JvmClass Class;
    
    public ClassInfo ClassInfo;
}
