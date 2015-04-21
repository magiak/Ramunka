/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javavirtualmachine;

import Instructions.Bipush;
import Instructions.Iadd;
import Instructions.Iconst;
import Instructions.Iload;
import Instructions.Instruction;
import Instructions.InstructionTuple;
import Instructions.Istore;
import Instructions.ReturnInst;
import Memory.JvmMemory;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author lkmoch
 */
public class Interpreter {
    private static HashMap<String, Instruction> instructionMap;
    private JvmMemory _memory;
    
    public Interpreter(JvmMemory memory){
        instructionMap = new HashMap<>();
        instructionMap.put("iconst", new Iconst());
        instructionMap.put("bipush", new Bipush());
        instructionMap.put("istore", new Istore());
        instructionMap.put("iload", new Iload());
        instructionMap.put("iadd", new Iadd());
        instructionMap.put("return", new ReturnInst());
    }
    
    private InstructionTuple Parse(String codeLine){
        String[] inst = codeLine.split(" ");
        if(inst.length == 2){
            int par = Integer.parseInt(inst[1]);
            return new InstructionTuple(instructionMap.get(inst[0]), par);
        }
        
        inst = codeLine.split("_");
        if(inst.length == 2){
            int par = Integer.parseInt(inst[1]);
            return new InstructionTuple(instructionMap.get(inst[0]), par);
        }
        
        return new InstructionTuple(instructionMap.get(inst[0]), null);
    }
    
    public void Execute(List<String> instructions){
        for(int i = 0; i < instructions.size(); i++){
            InstructionTuple tuple = Parse(instructions.get(i));
            Integer parametr = tuple.Par;
            Instruction instruction = tuple.Instruction;
            instruction.Memory = _memory;
            
            instruction.Execute(parametr);
        }
    }
}
