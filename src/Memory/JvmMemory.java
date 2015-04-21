/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Memory;

/**
 *
 * @author lkmoch
 */
public class JvmMemory {
    private ClassHeap _classHeap;
    private ObjectHeap _objectHeap;
    
    public JvmMemory(){
        _classHeap = new ClassHeap();
        _objectHeap = new ObjectHeap();
    }
}
