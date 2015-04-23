/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JvmHeap;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author lkmoch
 */
public class Heap {
    private Integer _nextInstanceIndex;
    private Map<Integer, Instance> _instances;
    
    public Heap(){
        _nextInstanceIndex = 0;
        _instances = new HashMap<>();
    }
    
    public int AddInstance(Instance instance){
        _instances.put(_nextInstanceIndex, instance);
        return _nextInstanceIndex ++;
    }
    
    public Instance GetInstance(Integer index){
        return _instances.get(index);
    }
}
