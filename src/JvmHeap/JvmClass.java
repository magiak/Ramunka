/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JvmHeap;

import JavaInfo.ClassInfo;
import JavaInfo.FieldInfo;
import static JavaInfo.FieldInfo.ACC_STATIC;
import java.util.HashMap;

/**
 *
 * @author lkmoch
 */
public class JvmClass {
    public HashMap<String, Object> Fields;
    
    public JvmClass(){
        Fields = new HashMap<>();
    }
    
    public static JvmClass Create(ClassInfo info){
        JvmClass jvmClass = new JvmClass();
        
        for(FieldInfo fieldInfo : info.FieldsTable){
            if(!fieldInfo.HasFlag(ACC_STATIC)){ //neni staticka
                String fieldName = info.GetFieldName(fieldInfo);
                jvmClass.Fields.put(fieldName, fieldInfo.Value);
            }
        }
        
        return jvmClass;
    }
}
