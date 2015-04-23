/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Exceptions;

/**
 *
 * @author lkmoch
 */
public class LoadFileClassIsNotCompletedException extends Exception {

    /**
     * Creates a new instance of
     * <code>LoadFileClassIsNotCompletedException</code> without detail message.
     */
    public LoadFileClassIsNotCompletedException() {
    }

    /**
     * Constructs an instance of
     * <code>LoadFileClassIsNotCompletedException</code> with the specified
     * detail message.
     *
     * @param msg the detail message.
     */
    public LoadFileClassIsNotCompletedException(String msg) {
        super(msg);
    }
}
