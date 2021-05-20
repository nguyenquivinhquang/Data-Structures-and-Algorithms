/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tree;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import list.MyArrayList;
import stacknqueue.*;

public class BST<T> implements IBinarySearchTree<T>, ITreeWalker<T> {
    private int size;
    Node<T> root;
    
    public BST(){
        this.size = 0;
        this.root = null;
    }

    ///
    private int key(T item){
        return item.hashCode();
    }
    private Node<T> add(Node<T> root, T item){
        if(root == null) return new Node<>(item, null, null);
        if(key(item) < key(root.item))
            root.left = this.add(root.left, item);
        else
            root.right = this.add(root.right, item);
        return root;
    }
    ///
    @Override
    public void add(T item) {
        root = this.add(root, item);
        this.size++;

    }

    private Node<T> remove(Node<T> root, Object key) {
        if(root == null) return null;
        if(key.hashCode() < key(root.item)) {
            root.left = remove(root.left, key);
            return root;
        }
        else if(key.hashCode() > key(root.item)){
            root.right = remove(root.right, key);
            return root;
        }
        else{
            if (root.left == null) {size -= 1; return root.right;}
            else if (root.right == null) {size -= 1;  return root.left;}
            Node<T> largestChild = root.left;
            while (largestChild.right != null) largestChild = largestChild.right;
            root.item = largestChild.item;

            root.left =  remove(root.left, largestChild.item);

            return root;
        }
    }
    @Override
    public void remove(Object key) {
        remove(root, key);
    }

    private T search(Node<T> root, Object key) {
        if(root == null) return null;
        if (key.hashCode() == root.item.hashCode()) return root.item;
        if (key.hashCode() < key(root.item)) return search(root.left, key);
        else return search(root.right, key);
    }
    @Override
    public T search(Object key) {
        return search(this.root, key);
    }

    @Override
    public int size() {
        return this.size;
    }
    
    public String toString(){
        return this.root.toString();
    }
    public void println(){
        System.out.println(this.toString());
    }

    private void ascendingList(Node<T> root, List<T> list) {
        if(root == null) return;
        ascendingList(root.left, list);
        list.add(root.item);
        ascendingList(root.right, list);
    }
    @Override
    public List<T> ascendingList() {
       List<T> list = new LinkedList<T>();
       ascendingList(root, list);
       return list; //remove this line
    }

    private void descendingList(Node<T> root, List<T> list) {
        if(root == null) return;
        descendingList(root.right, list);
        list.add(root.item);
        descendingList(root.left, list);
    }
    @Override
    public List<T> descendingList() {
        List<T> list = new LinkedList<>();
        descendingList(root, list);
        return list;
    }

    @Override
    public List<T> dfs() {

        List<T> list = new LinkedList<>();
        if (root == null) return list;
        Stack<Node<T>> stack = new Stack<Node<T>>();
        stack.push(root);
        while (stack.size() != 0) {
            Node<T> u = stack.pop();
            list.add(u.item);
            if (u.right != null) stack.push(u.right);
            if (u.left != null) stack.push(u.left);
        }
        return list;
    }

    @Override
    public List<T> bfs() {
        List<T> list = new LinkedList<>();
        if (root == null) return list;

        Queue<Node<T>> queue = new Queue<>();
        queue.push(root);
        while (queue.size() != 0) {
            Node<T> u = queue.pop();
            list.add(u.item);
            if (u.left != null) queue.push(u.left);
            if (u.right != null) queue.push(u.right);
        }
        return list;
    }

    private void nlr(Node<T> root, List<T> list) {
       if (root == null) return;
       list.add(root.item);
       nlr(root.left, list);
       nlr(root.right, list);

    }
    private void lrn(Node<T> root, List<T> list) {
        if (root == null) return;
        lrn(root.left, list);
        lrn(root.right, list);
        list.add(root.item);
    }
    private void lnr(Node<T> root, List<T> list) {
        if (root == null) return;
        lnr(root.left, list);
        list.add(root.item);
        lnr(root.right, list);
    }
    @Override
    public List<T> nlr() {
        List<T> list = new LinkedList<>();
        nlr(root, list);
        return list;
    }

    @Override
    public List<T> lrn() {
        List<T> list = new LinkedList<>();
        lrn(root, list);
        return list;
    }

    @Override
    public List<T> lnr() {
        List<T> list = new LinkedList<>();
        lnr(root, list);
        return list;
    }
    //
    public class Node<T>{
        T item;
        Node<T> left, right;
        public Node(T data, Node<T> left, Node<T> right){
            this.item = data;
            this.left = left;
            this.right = right;
        }
       
        public String toString(){
            String desc = "";
            if(this.left == null && this.right == null) 
                desc = String.format("(%s)", item);
            if(this.left == null && this.right != null)
                desc = String.format("(%s () %s)", item, this.right.toString());
            if(this.left != null && this.right == null)
                desc = String.format("(%s %s ())", item, this.left.toString());
            if(this.left != null && this.right != null)
                desc = String.format("(%s %s %s)", item, 
                        this.left.toString(), this.right.toString());
            return desc;
        }
    }
}
