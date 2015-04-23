/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javavirtualmachine;

import ByteHelper.ByteReader;
import Instructions.Aload_0;
import Instructions.Astore_1;
import Instructions.Bipush;
import Instructions.Dup;
import Instructions.Getstatic;
import Instructions.Goto;
import Instructions.Iadd;
import Instructions.Iconst_0;
import Instructions.Iconst_1;
import Instructions.Iconst_2;
import Instructions.Iconst_3;
import Instructions.Iconst_4;
import Instructions.Iconst_5;
import Instructions.Ifeq;
import Instructions.Ifne;
import Instructions.Iload;
import Instructions.Iload_0;
import Instructions.Iload_1;
import Instructions.Iload_2;
import Instructions.Iload_3;
import Instructions.Instruction;
import Instructions.Invokespecial;
import Instructions.Istore;
import Instructions.Istore_0;
import Instructions.Istore_1;
import Instructions.Istore_2;
import Instructions.Istore_3;
import Instructions.New;
import Instructions.ReturnInst;
import JavaInfo.AttributeInfo;
import static JavaInfo.AttributeInfo.ATTRIBUTE_CODE;
import JavaInfo.ClassInfo;
import static JavaInfo.ClassInfo.CONSTANT_CLASS;
import JavaInfo.ConstantInfo;
import JavaInfo.MethodInfo;
import JvmHeap.Heap;
import JvmStack.Frame;
import JvmStack.Stack;
import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.HashMap;
import java.util.List;
import static javavirtualmachine.InstructionTypes.ifne;

/**
 *
 * @author lkmoch
 */
public class Interpreter {
    private static HashMap<Byte, Instruction> _instructionMap;
    private ClassInfo _mainClass;
    private List<String> _classes;
    
    private Heap _heap;
    private Stack _stack;

    
    public Interpreter(){
        _instructionMap = new HashMap<>();
        _instructionMap.put((byte)3, new Iconst_0());
        _instructionMap.put((byte)4, new Iconst_1());
        _instructionMap.put((byte)5, new Iconst_2());
        _instructionMap.put((byte)6, new Iconst_3());
        _instructionMap.put((byte)7, new Iconst_4());
        _instructionMap.put((byte)8, new Iconst_5());
        _instructionMap.put((byte)16, new Bipush());
        _instructionMap.put((byte)54, new Istore());
        _instructionMap.put((byte)59, new Istore_0());
        _instructionMap.put((byte)60, new Istore_1());
        _instructionMap.put((byte)61, new Istore_2());
        _instructionMap.put((byte)62, new Istore_3());
        _instructionMap.put((byte)21, new Iload());
        _instructionMap.put((byte)26, new Iload_0());
        _instructionMap.put((byte)27, new Iload_1());
        _instructionMap.put((byte)28, new Iload_2());
        _instructionMap.put((byte)29, new Iload_3());
        _instructionMap.put((byte)96, new Iadd());
        _instructionMap.put((byte)177, new ReturnInst());
        _instructionMap.put((byte)153, new Ifeq());
        _instructionMap.put((byte)154, new Ifne());
        _instructionMap.put((byte)167, new Goto());
        _instructionMap.put((byte)178, new Getstatic());
        _instructionMap.put((byte)187, new New());
        _instructionMap.put((byte)89, new Dup());
        _instructionMap.put((byte)183, new Invokespecial());
        _instructionMap.put((byte)42, new Aload_0());
        _instructionMap.put((byte)76, new Astore_1());
        //_instructionMap.put((byte), new );
        
        _heap = new Heap();
        _stack = new Stack();
    }

    public void PrintClassInfo(ClassInfo classInfo) throws IOException{
        for(ConstantInfo constInfo : classInfo.ConstantPoolTable){
            System.out.println(constInfo.Tag);
            System.out.println(constInfo.String);
            if(constInfo.Tag == CONSTANT_CLASS){
                System.out.println("Nazvy metod");
            }
        }
        
        System.out.println("Nazvy metod");
        
        for(MethodInfo methodInfo : classInfo.Methods){
            System.out.println("METODA:" + classInfo.GetMethodName(methodInfo));
            System.out.println("ATTRIBUTY:");
            for(AttributeInfo attributeInfo : methodInfo.Attributes){
                System.out.println(classInfo.GetAttributeName(attributeInfo));

                InputStream is = new ByteArrayInputStream(attributeInfo.Info);
                DataInputStream dis = new DataInputStream(new BufferedInputStream(is));

                int maxStack = dis.readUnsignedShort();
                int maxLocals = dis.readUnsignedShort();
                int codeLength = dis.readInt();
                byte[] code = new byte[codeLength];
                dis.readFully(code);

                System.out.println(maxStack + " " + maxLocals + " " + codeLength);
                StringBuilder sb = new StringBuilder();
                for(byte b: code)
                    sb.append(String.format("%02x", b));
                System.out.println(sb.toString());
            }
        }
    }
    
    //Argumenty potrebuju v pripade kdy volam z jednoho invoke jiny
    public void Invoke(MethodInfo methodInfo, ClassInfo classInfo, List<Object> args) throws IOException{
        //Vytvor novy frame v kterem bude tato metoda pracovat
        Frame frame = null;
        try{
            frame = _stack.AddNewFrame(); 

            //Ziskej kod
            AttributeInfo codeInfo = null;
            for(AttributeInfo info : methodInfo.Attributes){
                if(info.Tag == ATTRIBUTE_CODE){
                    codeInfo = info;
                }
            }

            if(codeInfo != null){
                InputStream is = new ByteArrayInputStream(codeInfo.Info);
                DataInputStream dis = new DataInputStream(new BufferedInputStream(is));

                int maxStack = dis.readUnsignedShort();
                int maxLocals = dis.readUnsignedShort();
                int codeLength = dis.readInt();
                byte[] code = new byte[codeLength];
                dis.readFully(code);

                ExecuteCode(frame, codeLength, code, args);
            }
        } finally {
            if (frame != null) {
                //TODO smaz frame
            }
        }
    }
  
    public void ExecuteCode(Frame frame, int codeLength, byte[] code, List<Object> args) throws IOException{
        int currentPosition = 0;
        while(currentPosition < codeLength){
            Tuple<Byte, Integer> result = ByteReader.ReadByte(code, currentPosition);
            byte instructionCode = result.Object1;
            currentPosition = result.Object2;
            //Zvys index

            Instruction instruction = _instructionMap.get(instructionCode);
            if(instruction == null){
                throw new UnsupportedOperationException();
            }
            instruction.ClassInfo = _mainClass;
            instruction.CurrentPosition = currentPosition;
            instruction.Code = code;
            instruction.CodeLength = codeLength;
            instruction.Heap = _heap;
            instruction.Frame = frame;
            instruction.Interpreter = this;

            instruction.Execute();
            currentPosition = instruction.CurrentPosition;
            
        }
    }
    
    public void Execute(ClassInfo classInfo, List<String> classFileList) throws IOException{
        PrintClassInfo(classInfo);
        
        //TODO Reseni pocita jen s jednou class
        _mainClass = classInfo;
        _classes = classFileList;
        
        MethodInfo mainMethodInfo = null;
        for(MethodInfo methodInfo : classInfo.Methods){
            String methodName = classInfo.GetMethodName(methodInfo);
            if("main".equals(methodName)){
                mainMethodInfo = methodInfo;
                break;
            }
        }
        
        if(mainMethodInfo != null){
            Invoke(mainMethodInfo, classInfo, null);
        }
    }
}
