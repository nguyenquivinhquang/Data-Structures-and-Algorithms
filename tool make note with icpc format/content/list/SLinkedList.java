
package list;

public class SLinkedList<E> implements java.util.List<E> {
    private static enum MoveType {
        NEXT, PREV
    };
    private Node<E> head, tail;
    private int size;
    public SLinkedList() {
        head = new Node<>(null, null);
        tail = new Node<>(null, null);
        head.next = tail;
        tail.next = head;
        size = 0;
    }

    private void checkValidIndex(int index) {
        if ((index < 0) || (index >= size)) {
            String message = "Invalid index";
            throw new IndexOutOfBoundsException(message);
        }
    }
    private Node<E> getDataNode(int index) {
        checkValidIndex(index);

        Node<E> curNode = head.next;
        int runIndex = 0;
        while (curNode != tail) {
            if (index == runIndex) break;
            runIndex += 1;
            curNode = curNode.next;
        }
        return curNode;
    }
    private Node<E> getNode(int index) {
        if ((index < -1) || (index >= size)) {
            String message = S"Invalid index";
            throw new IndexOutOfBoundsException(message);
        }
        Node<E> curNode = head;
        for (int curIndex = 0; curIndex <= index; curIndex++) {
            curNode = curNode.next;
        }
        return curNode;
    }
    private void addAfter(Node<E> afterThis, Node<E> newNode) {
        newNode.next = afterThis.next;
        afterThis.next = newNode;
        if (newNode.next == tail) tail.next = newNode;
        size += 1;
    }
    private Node<E> removeAfter(Node<E> afterThis) {
        Node<E> removedNode = afterThis.next;
        afterThis.next = removedNode.next;
        if (removedNode.next == tail) tail.next = afterThis;
        removedNode.next = null;
        size -= 1;
        return removedNode;
    public int size() {return siz
    public boolean isEmpty() {return size ==
    public boolean contains(Object o) {
        Node<E> prevNode = head;
        Node<E> curNode = head.next;
        while (curNode != tail) {
            if (curNode.element.equals(o)) {
                return true;
            }
            curNode = curNode.next;
            prevNode = prevNode.next;
        }
        return false; 
 
    public Iterator<E> iterator() {
        return new MyIterator
    public boolean add(E e) {
        Node<E> newNode = new Node(null, e);
        Node<E> lastNode = tail.next;
        addAfter(lastNode, newNode);
        return true;
 
    public boolean remove(Object o) {
        Node<E> prevNode = head;
        Node<E> curNode = head.next;
        boolean found = false;
        while (curNode != tail) {
            if (curNode.element.equals(o)) {
                found = true;
                removeAfter(prevNode);
                break;
            }
            curNode = curNode.next;
            prevNode = prevNode.next;
        }
        return found;
 
    public void clear() {
        while (size != 0) {remove(0);
        head.next = tail;
        tail.next = head; size = 0;
 
    public E get(int index) {
        Node<E> curNode = head.next;
        for (int i = 1; i <= index; i++) {
            curNode = curNode.next;
        }
        return curNode.element; 
 
    public E set(int index, E element) {
        Node<E> curNode = head.next;
        for (int i = 1; i <= index; i++) {
            curNode = curNode.next;
        }
        curNode.element = element;
        return curNode.element;
 
    public void add(int index, E element) {
        if (index < 0 || index > size) {
            String message = "Invalid index";
            throw new IndexOutOfBoundsException(message);
        }
        Node<E> prevNode = getNode(index - 1);
        Node<E> newNode = new Node<>(null, element);
        addAfter(prevNode, newNode);
 
    public E remove(int index) {
        if (size == 0) {
            String message = "Invalid index";
            throw new IndexOutOfBoundsException(message);
        }
        Node<E> prevNode = getNode(index - 1);
        Node<E> curNode = prevNode.next;
        removeAfter(prevNode);
        return curNode.element;
 
    public int indexOf(Object o) {
        Node<E> curNode = head.next;
        for (int index = 0; index < size; index++) {
            if (curNode.element.equals(o))
                return index;
            curNode = curNode.next;
        }
        return -1;
 
    public int lastIndexOf(Object o) {
        Node<E> curNode = head.next;
        int foundIdx = -1;
        int index = 0;
        while (curNode != tail) {
            if (curNode.element.equals(o)) {
                foundIdx = index;}
            index += 1;
            curNode = curNode.next;
        }return foundIdx;
 
    public ListIterator<E> listIterator() {
        return new MyListIterator(
    public ListIterator<E> listIterator(int index) {
        return new MyListIterator(index
    public String toString() {
        String desc = "[";
        Iterator<E> it = this.iterator();
        while (it.hasNext()) {
            E e = it.next();
            desc += String.format("%s,", e);
        }
        if (desc.endsWith(",")) desc = desc.substring(0, desc.length() - 1);
        desc += "]";
        return desc;
    }
    private class Node<E> {
        E element;
        Node<E> next;
        Node(Node<E> next, E element) {
            this.next = next;
            this.element = element;
        }
        void update(Node<E> next, E element) {
            this.next = next;
            this.element = element;
        }
    }
    public class MyIterator implements Iterator<E> {
        Node<E> pprevNode; //use for remove(): prev of prev
        Node<E> prevNode; //pointer to the prev of the current node
        Node<E> curNode; //pointer to the currrent node during the interating
        boolean afterMove; //after next() or previous()
        MoveType moveType; //next or previous
        MyIterator() {
            pprevNode = null;
            prevNode = SLinkedList.this.head;
            curNode = SLinkedList.this.head.next;
            moveType = MoveType.NEXT; //default is move is next()
            afterMove = false;
        }
        public boolean hasNext() {
            return curNode != SLinkedList.this.tail;
        }
        public E next() {
            E element = curNode.element;
            pprevNode = prevNode;
            prevNode = curNode;
            curNode = curNode.next;
            afterMove = true; //allow remove()
            moveType = MoveType.NEXT;
            return element;
        }
        public void remove() {
            if (!afterMove) return;
            removeAfter(pprevNode);
            prevNode = pprevNode;
            afterMove = false; //one remove() call for each call next()
        }
    }
    public class MyListIterator extends MyIterator implements ListIterator<E> {
        int index;
        public MyListIterator() {
            super();index = 0;}
        public MyListIterator(int index) {
            super();this.index = index;}
        public E next() {
            index++;
            return super.next();
        }
        public void remove() {
            if (!afterMove) return;
            if (moveType == MoveType.NEXT) {
                super.remove();
            } else {
                Node<E> prevNode = getNode(index - 1);
                removeAfter(prevNode);
            }
            afterMove = false;
        }
        public boolean hasPrevious() {
            return index != 0;
        }
        //previous(): move curNode to previous, and the return data in new curNode
        public E previous() {
            index -= 1; //move to previous
            moveType = MoveType.PREV;
            afterMove = true;
            curNode = SLinkedList.this.getDataNode(index);
            return curNode.element;
        }
        public int nextIndex() {return this.index;}
        public int previousIndex() {
        	return (index - 1);
        }
        public void set(E e) {
            if (!afterMove) return;
            if (moveType == MoveType.NEXT)
                prevNode.element = e;
            else
                curNode.element = e;
        }
        public void add(E e) {
            if (!afterMove) return;
            if (moveType == MoveType.NEXT) {
                //add at prevNode => result: newnode->prevNode
                Node<E> newNode = new Node<>(prevNode.next, prevNode.element);
                prevNode.update(newNode, e);
                if (curNode.next == tail) tail.next = newNode; //update tail
            } else {
                //add at curNode => result: newnode->curNode
                Node<E> newNode = new Node<>(curNode.next, curNode.element);
                curNode.update(newNode, e);
                if (curNode.next == tail) tail.next = newNode; //update tail
            }
            SLinkedList.this.size += 1;
        }
    }
}