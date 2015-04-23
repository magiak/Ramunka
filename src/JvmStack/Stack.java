/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JvmStack;

import java.util.LinkedList;

/**
 *
 * @author lkmoch
 */
public class Stack {
    private Frame _currentFrame;
    private LinkedList<Frame> _frames;
    
    public Stack(){
        _frames = new LinkedList<>();
        _currentFrame = null;
    }
    
    public void SetCurrentFrame(Frame frame){
        _currentFrame = frame;
    }
    
    public Frame AddNewFrame(){
        Frame frame = new Frame();
        _frames.add(frame);
        SetCurrentFrame(frame);
        
        return frame;
    }
    
    public Frame Top(){
        return _frames.getLast();
    }
    
    public Frame Pop(){
        Frame frame = Top();
        _frames.removeLast();
        return frame;
    }
    
    public void Push(Frame frame){
        _frames.add(frame);
    }
}
