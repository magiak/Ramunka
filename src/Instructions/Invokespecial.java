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
import JavaInfo.MethodInfo;
import JvmStack.OperandStack;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javavirtualmachine.Interpreter;
import javavirtualmachine.JavaClassLoader;
import javavirtualmachine.Tuple;

/**
 *
 * @author lkmoch
 */
public class Invokespecial extends Instruction {
    @Override
    public void Execute(){
        Tuple<Integer, Integer> tuple = ByteReader.ReadShort(Code, CurrentPosition);
        CurrentPosition = tuple.Object2;
        int value = tuple.Object1;
        
        String thisClassName = ClassInfo.GetClassName();
        
        ConstantInfo methodRef = ClassInfo.GetConstantInfo(value);
        int classNameIndex = methodRef.ClassIndex;
        int nameAndType = methodRef.NameAndTypeIndex;
        
        //Ziskej informace o volane metode
        ConstantInfo classConstantInfo = ClassInfo.GetConstantInfo(classNameIndex);
        String className = ClassInfo.GetConstantInfo(classConstantInfo.NameIndex).String;
        ConstantInfo methodConstantInfo = ClassInfo.GetConstantInfo(nameAndType);
        String methodName = ClassInfo.GetConstantInfo(methodConstantInfo.NameIndex).String;
        
        ClassInfo classInfo = null;
        
        try {
            classInfo = JavaClassLoader.Load(className, Interpreter);
            
            if(classInfo!= null){
                //Najdi spravnou metodu
                MethodInfo methodInfo = classInfo.GetMethodByName(methodName);
                //TODO pri volani pod metod se mi prepisuje pozice
                //lepsi by tedy bylo mit soucasnou pozici nekde ve Framu aby se mi neprepsala
                int currentPosition = CurrentPosition;
                
                //Ziskej attributy pro volanou metodu
                LinkedList<Object> arguments = new LinkedList<>();
                for(int i = 0; i < methodInfo.AttributesCount; i ++){
                    arguments.addFirst(Frame.OperandStack.Pop());
                }
                
                Object result = Interpreter.Invoke(methodInfo, classInfo, arguments);
                if(result != null) Frame.OperandStack.Push(result);
                
                if(currentPosition != CurrentPosition){
                    CurrentPosition = currentPosition;
                }
            }else{
                throw new UnsupportedOperationException("The system cannot find the CLASS file.");
            }
        } catch (LoadFileClassIsNotCompletedException ex) {
            Logger.getLogger(Invokespecial.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Invokespecial.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
