/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javavirtualmachine;

/**
 *
 * @author lkmoch
 */
public enum InstructionTypes {
    //Zakladni
    gotoInst,
    returnInst,
    
    //Prace s int
    iconst, //ekvivalent k bipush ale umi pouze cisla -1 az 5 
    bipush, //ekvivalent k i const
    istore_, 
    iload_,
    
    //Prace s objekty
    aconst,
    astore,
    aload,
    
    //Matematicke
    iadd, //scitani
    isub, //odcitani
    idiv, //deleni
    imul, //nasobeni
    irem, //modulo
    
    //If
    //Porovnavani int a 0
    ifeq,
    ifne,
    iflt,
    ifge,
    ifgt,
    ifle,
    
    //Porovnávání intu
    if_icmpeq,
    if_icmpne,
    if_icmplt,
    if_icmpge,
    if_icmpgt,
    if_icmple,
    
    //Objekty
    ifnonnull, //POZOR object == null
    ifnull //POZOR object != null
    
    
}
