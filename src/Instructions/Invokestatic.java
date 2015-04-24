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
import JvmHeap.Instance;
import JvmStack.OperandStack;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javavirtualmachine.Interpreter;
import javavirtualmachine.JavaClassLoader;
import javavirtualmachine.Tuple;

/**
 *
 * @author lkmoch
 */
public class Invokestatic extends Instruction {
    private static Pattern allParamsPattern = Pattern.compile("(\\(.*?\\))");
    private static Pattern paramsPattern = Pattern.compile("(\\[?)(C|Z|S|I|J|F|D|(:?L[^;]+;))");

    public int GetArgumentsCountFromDescription(String description){
        //Nejdrive zahodime navratovy typ
        /*if(description.contains(")")){
            description = description.split(")")[0];
        }*/
        
        Matcher m = allParamsPattern.matcher(description);
        if (!m.find()) {
            throw new IllegalArgumentException("Method signature does not contain parameters");
        }
        String paramsDescriptor = m.group(1);
        Matcher mParam = paramsPattern.matcher(paramsDescriptor);

        int count = 0;
        while (mParam.find()) {
            count++;
        }
        return count;
    }
    
    @Override
    public void Execute() throws IOException{
        Tuple<Integer, Integer> tuple = ByteReader.ReadShort(Code, CurrentPosition);
        CurrentPosition = tuple.Object2;
        int value = tuple.Object1; // => 4
        
        //Informace o metode kterou zavolam
        ConstantInfo methodRef = ClassInfo.GetConstantInfo(value);
        int classNameIndex = methodRef.ClassIndex;
        
        //Z ni dostanu informace o tride
        //ClassInfo
        ConstantInfo classConstantInfo = ClassInfo.GetConstantInfo(classNameIndex);
        //UTF8 info o tride
        ConstantInfo utf8 = ClassInfo.GetConstantInfo(classConstantInfo.NameIndex);
        //Nazev tridy v ktere je volana metoda
        String methodClassName = utf8.String;
        
        //A o samotne metode
        ConstantInfo methodConstantNameAndTypeInfo = ClassInfo.GetConstantInfo(methodRef.NameAndTypeIndex);
        String methodName = ClassInfo.GetConstantInfo(methodConstantNameAndTypeInfo.NameIndex).String;
        String methodDescriptor = ClassInfo.GetConstantInfo(methodConstantNameAndTypeInfo.DescriptorIndex).String;
        
        OperandStack stack = Frame.OperandStack;
        
        int attributesCount = GetArgumentsCountFromDescription(methodDescriptor);
        
        LinkedList<Object> arguments = new LinkedList<>();
        for(int i = 0; i < attributesCount; i ++){
            arguments.addFirst(Frame.OperandStack.Pop());
        }
        
        ClassInfo classInfo = null;
        try {
            //Zjisti zda uz je potrebna trida inicializovana a pripadne ji inicializuje
            classInfo = JavaClassLoader.Load(methodClassName, Interpreter);
        } catch (LoadFileClassIsNotCompletedException ex) {
            Logger.getLogger(Invokestatic.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        //Ziskej z class tu spravnou metodu
        MethodInfo methodInfo = classInfo.GetMethod(methodName, methodDescriptor);
        
        int currentPosition = CurrentPosition;
        
        Object result = Interpreter.Invoke(methodInfo, classInfo, arguments);
        if(result != null) Frame.OperandStack.Push(result);

        if(currentPosition != CurrentPosition){
            CurrentPosition = currentPosition;
        }
        
    }
}
