/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hash;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;

/**
 *
 * @author LTSACH
 */
public class XHashMap<K, V> implements IMap<K,V>{
    private static final int MAX_CAPACITY = Integer.MAX_VALUE - 8;
    private Entry<K,V>[] table;
    private int size;
    private float loadFactor;
    
    public XHashMap(){
        this(10, 0.75f);
    }
    public XHashMap(int initialCapacity, float loadFactor){
        this.table = new Entry[initialCapacity];
        this.size = 0;
        this.loadFactor = loadFactor;
    }
    //////////////////////////////////////////////////////////////////////////
    /////////////////// Utility methods (private)         ////////////////////
    ////////////////////////////////////////////////////////////////////////// 
    private void ensureLoadFactor(int minCapacity){
        int maxSize = (int)(this.loadFactor*this.table.length);
        if((minCapacity < 0) || (minCapacity > MAX_CAPACITY))
            throw new OutOfMemoryError("Not enough memory to store the array");
        if(minCapacity >= maxSize){
            //grow: oldCapacity >> 1 (= oldCapacity/2)
            int oldCapacity = this.table.length;
            int newCapacity = 2*oldCapacity;
            if(newCapacity < 0) newCapacity = MAX_CAPACITY;
            rehash(newCapacity);
        }        
    }
    private void rehash(int newCapacity){
        Entry<K,V>[] oldTable = this.table;
        this.table = new Entry[newCapacity];
        this.size = 0;
        
        for(Entry<K, V> entry : oldTable) {
            while(entry != null){
                this.put(entry.key, entry.value);
                entry = entry.next;
            }
        }
    }

    protected int key2index(K key){
        return key.hashCode()%this.table.length;
    }
    
    //////////////////////////////////////////////////////////////////////////
    /////////////////// API of XHashMap                    ///////////////////
    ////////////////////////////////////////////////////////////////////////// 
    
    @Override
    public V put(K key, V value) {
        int index = this.key2index(key);
        ensureLoadFactor(index);

        Entry<K, V> curNode = table[index], newNode;
        if (curNode == null) {
            curNode = new Entry<>(key, value, null, null);
            table[index] = curNode;
            ensureLoadFactor(++this.size);
            return curNode.value;
        }
        while (curNode.next != null) {
            if (curNode.key.equals(key)) {
                curNode.value = value;
                return value;
            }
            curNode = curNode.next;
        }

        newNode = new Entry<>(key, value, curNode, null);
        curNode.next = newNode;
        ensureLoadFactor(++this.size);
        return value;
    }

    @Override
    public V get(K key) {
        int index = this.key2index(key);
        Entry<K, V> curNode = table[index];
        while (curNode != null) {
            if (curNode.key.equals(key)) return curNode.value;
            curNode = curNode.next;
        }
        return null;
    }

    @Override
    public V remove(K key) {
        int index = this.key2index(key);
        Entry<K,V> entry = this.table[index];
        while(entry != null){
            if (entry.key.equals(key)) {
                size -= 1;
                if (entry.prev == null ) {
                    this.table[index] = entry.next;
                    if (this.table[index] == null) return entry.value;
                    if (this.table[index].prev != null ) this.table[index].prev = null;
                    return entry.value;
                }
                if (entry.next != null ) entry.next.prev = entry.prev;
                if (entry.prev == null && entry.next == null) this.table[index] = null;
                return entry.value;
            }
            entry = entry.next;
        }
        return null;
    }

    @Override
    public boolean remove(K key, V value) {
        int index = this.key2index(key);
        Entry<K,V> entry = this.table[index];
        while(entry != null){
            if(entry.key.equals(key) && entry.value.equals(value)){
                size -= 1;
                if (entry.prev == null ) {
                    this.table[index] = entry.next;
                    if (this.table[index] == null) return true;
                    if (this.table[index].prev != null )this.table[index].prev = null;
                    return true;
                }
                if (entry.next != null ) entry.next.prev = entry.prev;
                if (entry.prev == null && entry.next == null) this.table[index] = null;
                return true;
            }
            entry = entry.next;
        }
        return false;
    }

    @Override
    public boolean containsKey(K key) {
        int index = this.key2index(key);
        Entry<K,V> entry = this.table[index];
        while(entry != null){
            if (entry.key.equals(key)) return true;
            entry = entry.next;
        }
        return false;
    }

    @Override
    public boolean containsValue(V value) {
        for(Entry<K, V> entry : this.table) {
            while(entry != null){
                if (entry.value.equals(value)) return true;
                entry = entry.next;
            }
        }
        return false;
    }

    @Override
    public boolean empty() {
        return this.size == 0;
    }

    @Override
    public int size(){
        return this.size;
    }
    
    public void println(){
        System.out.println(this.toString());
    }
    public String toString(){
        String desc = String.format("%s\n", new String(new char[50]).replace('\0', '-'));
        desc += String.format("Capacity: %d\n", this.capacity());
        desc += String.format("Size: %d\n", this.size());
        for(int idx=0; idx < this.table.length; idx++){
            Entry<K,V> entry = this.table[idx];
            String line = String.format("[%4d]:", idx);
            while(entry != null){
                line += String.format("(%s, %s),", entry.key, entry.value);
                entry = entry.next;
            }
            if(line.endsWith(",")) line = line.substring(0, line.length() - 1);
            desc += line + "\n";
        }
        desc += String.format("%s\n", new String(new char[50]).replace('\0', '-'));
        return desc;
    }
    
    /////////////////////////////////////////////////////////
    ///// The following methods are used for testing only ///
    /////////////////////////////////////////////////////////
    public int capacity(){
        return this.table.length;
    }
    public int numEntryAt(int index){
        Entry<K,V> entry = this.table[index];
        int count = 0;
        while(entry != null){
            count += 1;
            entry = entry.next;
        }
        return count;
    }
}

class Entry<K,V>{
    K key;
    V value;
    Entry<K,V> prev;
    Entry<K,V> next;
    Entry(){
        this(null, null, null, null);
    }
    Entry(K key, V value, Entry<K,V> prev, Entry<K,V> next){
        this.key = key;
        this.value = value;
        this.prev = prev;
        this.next = next;
    }
}