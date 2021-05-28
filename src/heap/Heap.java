/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package heap;

import java.util.Arrays;
import java.util.Comparator;

/**
 *
 * @author LTSACH
 */
public class Heap<T> implements IHeap<T>{
    private static final int MAX_CAPACITY = Integer.MAX_VALUE - 8;
    private T[] elements;
    private int size;
    private int total_size = 0;
    private Comparator<? super T> comparator;
    
    public Heap(){
        this(null);
    }
    public Heap(Comparator<? super T> comparator){
        this.elements = (T[])new Object[10];
        this.size = 0;
        this.comparator = comparator;
    }
    //////////////////////////////////////////////////////////////////////////
    /////////////////// Utility methods (private)         ////////////////////
    ////////////////////////////////////////////////////////////////////////// 

    private boolean aLTb(T a, T b){ 
    /* a less than  b @@ because default heap in testcase of Dr.Sach is MinHeap, so this aLTb will be
        a greater than b

     */
        if(this.comparator == null) return a.hashCode() > b.hashCode();
        else return this.comparator.compare(a,b) > 0;
    }
    private boolean aLTb(int indexA, int  indexB){
        T a = elements[indexA];
        T b = elements[indexB];
        return  aLTb(a, b);
    }
    private void checkCapacity(int minCapacity){
        if((minCapacity < 0) || (minCapacity > MAX_CAPACITY))
            throw new OutOfMemoryError("Not enough memory to store the array");
        if(minCapacity < this.elements.length)
            return;
        else{
            //grow: oldCapacity >> 1 (= oldCapacity/2)
            int oldCapacity = this.elements.length;
            int newCapacity = oldCapacity + oldCapacity >> 1;
            if(newCapacity < 0)
                newCapacity = MAX_CAPACITY;
            this.elements = Arrays.copyOf(this.elements, newCapacity);
        }        
    }
    private void swap(int a, int b){
        T temp = this.elements[a];
        this.elements[a] = this.elements[b];
        this.elements[b] = temp;
    }
    private void reheapUp(int position){
        if (position == 1) return;
        int father = position / 2;
        if (aLTb(elements[father], elements[position]))
            swap(father, position);
        else return;
        reheapUp(father);
    }
    private void reheapDown(int position){
        checkCapacity(position);
        int child_l = position * 2, child_r = position * 2 + 1;
        if (child_l > size) return;
        int child = child_l;
        if (child_r < size ) if (aLTb(child_l, child_r) == true) child = child_r;
        if (aLTb(position, child)) {
            swap(position, child);
            reheapDown(child);
        }
    }
    private int getItem(T item, int curNode){
            if (curNode > size) return  -1;
            if (elements[curNode].equals(item)) return  curNode;
            if (aLTb(item, elements[curNode]) == false) return -1;

            return Math.max(getItem(item, curNode * 2), getItem(item,  curNode * 2 + 1));

    }
    
    //////////////////////////////////////////////////////////////////////////
    /////////////////// API of Doubble-Linked List         ///////////////////
    ////////////////////////////////////////////////////////////////////////// 

    @Override
    public void push(T item) {
        elements[++size] = item;
        reheapUp(size);
    }

    @Override
    public T pop() {
        T element = elements[1];
        remove(elements[1]);
        return element;
    }
    @Override
    public T peek() {
        if(this.size == 0) throw new RuntimeException("Peeking from an empty heap!");
        return this.elements[0];
    }
    
    @Override
    public void remove(T item) {
        int foundIdx = this.getItem(item, 1);
        if(foundIdx == -1) return;
        
        swap(foundIdx, size--);
        if (foundIdx == 1) {
            reheapDown(1);
            return;
        }
        int parent = foundIdx / 2;
        if (aLTb(elements[parent], elements[foundIdx]) == true) {
            reheapUp(foundIdx);
        } else reheapDown(foundIdx);
    }

    @Override
    public boolean contains(T item) {
        return getItem(item, 1) != -1;
    }

    @Override
    public int size() {
        return this.size;
    }
    public boolean empty(){
        return this.size == 0;
    }

    @Override
    public void heapify(T[] array) {
        for(T item: array)
            this.push(item);
    }

    @Override
    public void clear() {
        this.size = 0;
        this.elements = (T[])new Object[10];
    }
    @Override
    public String toString(){
        String desc = "[";
        for(int idx = 1; idx < this.size; idx++)
            desc += this.elements[idx] + ",";
        desc += this.elements[this.size] + "]";
        return desc;
    }
    public void println(){
        System.out.println(this.toString());
    }
}
