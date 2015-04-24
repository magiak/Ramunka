/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Instructions;

import ByteHelper.ByteReader;
import Exceptions.LoadFileClassIsNotCompletedException;
import JavaInfo.ClassInfo;
import JavaInfo.ConstantInfo;
import JavaInfo.FieldInfo;
import java.util.logging.Level;
import java.util.logging.Logger;
import javavirtualmachine.JavaClassLoader;
import javavirtualmachine.Tuple;

/**
 *
 * @author lkmoch
 */
public class Getstatic extends Instruction {
    @Override
    public void Execute(){
        Tuple<Integer, Integer> tuple = ByteReader.ReadShort(Code, CurrentPosition);
        CurrentPosition = tuple.Object2;
        int value = tuple.Object1;
        
        /*int classIndex = ClassInfo.GetClassIndexFromPoolTable(value);
        int nameAndType = ClassInfo.GetNameAndTypeIndexFromPoolTable(value);

        ConstantInfo constantFieldInfo = ClassInfo.GetConstantInfo(constantFieldInfoIndex);
        ConstantInfo fieldInfoNameAndType = ClassInfo.GetConstantInfo(constantFieldInfo.NameAndTypeIndex);
        String fieldName = ClassInfo.GetConstantInfo(fieldInfoNameAndType.NameIndex).String;*/
        
        int constantFieldInfoIndex = tuple.Object1;
        
        ConstantInfo constantFieldInfo = ClassInfo.GetConstantInfo(constantFieldInfoIndex);
        
        ConstantInfo constantClassInfo = ClassInfo.GetConstantInfo(constantFieldInfo.ClassIndex);
        ConstantInfo constantClassName = ClassInfo.GetConstantInfo(constantClassInfo.NameIndex);
        String className = constantClassName.String;
        
        ConstantInfo fieldInfoNameAndType = ClassInfo.GetConstantInfo(constantFieldInfo.NameAndTypeIndex);
        String fieldName = ClassInfo.GetConstantInfo(fieldInfoNameAndType.NameIndex).String;
        String fieldDescriptor = ClassInfo.GetConstantInfo(fieldInfoNameAndType.DescriptorIndex).String;
        
        try {
            ClassInfo classInfo = JavaClassLoader.Load(className, Interpreter);
            FieldInfo fieldInfo = classInfo.GetField(fieldName);
            
            Frame.OperandStack.Push(fieldInfo.Value);
        } catch (LoadFileClassIsNotCompletedException ex) {
            Logger.getLogger(Getstatic.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
