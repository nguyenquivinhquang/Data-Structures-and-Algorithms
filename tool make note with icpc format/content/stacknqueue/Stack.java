
package stacknqueue;

import java.util.List;
import java.util.ListIterator;

import list.DLinkedList;
public class Stack<T>{
    private List<T> list;
    public Stack(){
        this.list = new DLinkedList<>();  }
    public void push(T item){
        list.add(0, item);    }
    public T pop(){
        T item = list.get(0);
        list.remove(0);
        return item;
    }
    public T peek(){return list.get(0);}
    public boolean remove(T item){
        ListIterator<T> it = list.listIterator();
        while (it.hasNext()) {
            T cur = it.next();
            if (cur.equals(item)){
                it.remove();
                return true;
            }
        }
        return false;
    }
    public boolean contains(T item){
        ListIterator<T> it = list.listIterator();
        while (it.hasNext()) {
            if (it.next().equals(item)) return true;
        }
        return false;
    }
    public boolean empty(){
        return this.list.isEmpty();}
    public int size(){
        return this.list.size();}
}