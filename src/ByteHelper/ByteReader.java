/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ByteHelper;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import javavirtualmachine.Tuple;

/**
 *
 * @author lkmoch
 */
public class ByteReader {
    public static Tuple<Integer, Integer> ReadInt(byte[] byteArray, int index){
        ByteBuffer bb = ByteBuffer.wrap(byteArray);
        bb.order(ByteOrder.BIG_ENDIAN);
        return new Tuple<Integer, Integer>(bb.getInt(index), index + 4);
    }
    
    public static Tuple<Integer, Integer> ReadShort(byte[] byteArray, int index){
        ByteBuffer bb = ByteBuffer.wrap(byteArray);
        bb.order(ByteOrder.BIG_ENDIAN);
        return new Tuple<Integer, Integer>((int)bb.getShort(index), index + 2);
    }
    
    public static Tuple<Byte, Integer> ReadByte(byte[] byteArray, int index){
        ByteBuffer bb = ByteBuffer.wrap(byteArray);
        bb.order(ByteOrder.BIG_ENDIAN);
        return new Tuple<Byte, Integer>(bb.get(index), index + 1);
    }
}
