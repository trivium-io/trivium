/*
 * Copyright 2016 Jens Walter
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package io.trivium.anystore;

import io.trivium.dep.io.qdb.buffer.MessageCursor;
import io.trivium.dep.io.qdb.buffer.PersistentMessageBuffer;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.LongBuffer;
import java.nio.channels.FileChannel;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Queue {
    static HashMap<String,Queue> queueList = new HashMap<>();
    Logger logger = Logger.getLogger(getClass().getName());

    PersistentMessageBuffer queue;
    LongBuffer readPointer;

    public Queue(String path){
        try {
            RandomAccessFile aFile     = new RandomAccessFile(path+".readpos", "rw");
            FileChannel inChannel = aFile.getChannel();
            readPointer = inChannel.map(FileChannel.MapMode.READ_WRITE, 0, 16).asLongBuffer();
            //to prevent a buffer underflow on read
            readPointer.get();

            queue = new PersistentMessageBuffer(new File(path));
            //set size to 100mb
            queue.setMaxSize(100*1024*1024);
        }catch(Exception ex){
            logger.log(Level.SEVERE,"error initializing queue on path "+path,ex);
        }
    }

    public void append(byte[] msg){
        try {
            //routing key is empty since it is not used
            queue.append(System.currentTimeMillis(), "", msg);
        } catch (IOException e) {
            logger.log(Level.SEVERE,"error appending message to queue",e);
        }
    }

    public MessageCursor getCursor(){
        try {
            readPointer.flip();
            long pos = readPointer.get();
            return queue.cursor(pos);
        } catch (IllegalArgumentException ex) {
            logger.log(Level.INFO,"cursor position is invalid, resetting to position 0",ex);
            try {
                return queue.cursor(0);
            }catch(IOException io){
                //ignore
            }
        } catch (IOException e) {
            logger.log(Level.SEVERE,"error creating cursor",e);
        }
        return null;
    }

    public synchronized static Queue getQueue(String path){
        if(queueList.containsKey(path)){
            return queueList.get(path);
        }else{
            Queue q = new Queue(path);
            queueList.put(path,q);
            return q;
        }
    }

    public void setReadPointer(long position){
        readPointer.flip();
        readPointer.put(position);
    }
}
