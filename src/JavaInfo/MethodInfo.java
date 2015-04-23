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
public class MethodInfo {
    public int Accessflags;
    public int NameIndex;
    public int DescriptorIndex;
    
    public int AttributesCount;
       
    public AttributeInfo[] Attributes;
    
    public AttributeInfo GetCodeAttribute(){
        for(AttributeInfo info : Attributes){
            if(info.Tag == ATTRIBUTE_CODE)
                return info;
        }
        
        return null;
    }
}
