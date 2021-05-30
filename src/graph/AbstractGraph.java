/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package graph;


import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.PriorityQueue;
/**
 *
 * @author LTSACH
 */
public abstract class AbstractGraph<T> implements IGraph<T>{
    protected List<VertexNode<T>> nodeList;

    public AbstractGraph(){
        nodeList = new LinkedList<>();
    }
    ///Utilities
    protected VertexNode<T> getVertexNode(T vertex){
        ListIterator<VertexNode<T>> it = nodeList.listIterator();
        while(it.hasNext()){
            VertexNode<T> node = it.next();
            if(node.vertex.equals(vertex)) return node;
        }
        return null;
    }
    ///
    @Override
    public void add(T vertex) {
        this.nodeList.add(new VertexNode(vertex));
    }

    @Override
    public abstract void remove(T vertex) throws VertexNotFoundException;
    @Override
    public boolean contains(T vertex){
        VertexNode<T> node = getVertexNode(vertex);
        return node != null;
    }

    @Override
    public void connect(T from, T to) throws VertexNotFoundException{
        connect(from, to, 0);
    }

    @Override
    public abstract void connect(T from, T to, float weight) throws VertexNotFoundException;

    @Override
    public abstract void disconnect(T from, T to) throws VertexNotFoundException, EdgeNotFoundException;

    public void checkNullVertex(VertexNode<T> vertexNode) throws VertexNotFoundException {
        if (vertexNode == null) throw new VertexNotFoundException(vertexNode);
    }
    public void checkNullVertex(VertexNode<T> from, VertexNode<T> to) throws VertexNotFoundException {
        checkNullVertex(from);
        checkNullVertex(to);
    }


    @Override
    public float weight(T from, T to) throws VertexNotFoundException, EdgeNotFoundException{
        VertexNode<T> _from = getVertexNode(from), _to = getVertexNode(to);
        checkNullVertex(_from, _to);
        Edge<T> edge = _from.getEdge(_to);
        if (edge == null) throw new EdgeNotFoundException("Does not exist edge");
        return edge.weight;
    }

    @Override
    public List getOutwardEdges(T from) throws VertexNotFoundException{
        VertexNode<T> _from = getVertexNode(from);
        if (_from == null) throw new VertexNotFoundException(null);
        return _from.getOutwardEdges();
    }

    @Override
    public List getInwardEdges(T to) throws VertexNotFoundException{

        List<T> inWardEdge = new LinkedList<>();
        if (to == null) throw new VertexNotFoundException(null);
        for (VertexNode<T> node : nodeList) {
            List<T> outwardNode = node.getOutwardEdges();
            if (outwardNode.contains(to)) inWardEdge.add(node.vertex);
        }
        return inWardEdge;
    }

    @Override
    public java.util.Iterator<T> iterator(){
        return new GraphIterator<>(this, this.nodeList.iterator());
    }
    @Override
    public int size() {
        return this.nodeList.size();
    }

    @Override
    public int inDegree(T vertex) throws VertexNotFoundException{
        VertexNode<T> vertexNode = getVertexNode(vertex);
        if (vertexNode == null) throw new VertexNotFoundException(null);
        return vertexNode.inDegree();
    }
    @Override
    public int outDegree(T vertex) throws VertexNotFoundException{
        VertexNode<T> vertexNode = getVertexNode(vertex);
        if (vertexNode == null) throw new VertexNotFoundException(null);
        return vertexNode.outDegree();
    }

    @Override
    public void println(){
        System.out.println(toString());
    }
    @Override
    public String toString(){
        String desc = String.format("%s\n", new String(new char[80]).replace('\0', '='));
        desc += "Vertices:\n";
        Iterator<VertexNode<T>> nodeIt = this.nodeList.iterator();
        while(nodeIt.hasNext()){
            VertexNode<T> node = nodeIt.next();
            desc += "  " + node.toString() + "\n";
        }
        desc += String.format("%s\n", new String(new char[40]).replace('\0', '-'));
        desc += "Edges:\n";

        nodeIt = this.nodeList.iterator();
        while(nodeIt.hasNext()){
            VertexNode<T> node = nodeIt.next();
            Iterator<Edge<T>> edgeIt = node.adList.iterator();
            while(edgeIt.hasNext()){
                Edge<T> edge = edgeIt.next();
                String line = String.format("E(%s,%s, %6.2f)", node.vertex, edge.to.vertex, edge.weight);
                desc += "  " + line + "\n";
            }
        }
        desc += String.format("%s\n", new String(new char[80]).replace('\0', '='));
        return desc;
    }

}

/////////////////////////////////////////////////////////////////////////
///// Utiliy classes
/////////////////////////////////////////////////////////////////////////
class VertexNode<T>{
    T vertex;
    int inDegree, outDegree;
    List<Edge<T>> adList;

    public VertexNode(T data){
        this.vertex = data;
        this.inDegree = this.outDegree = 0;
        this.adList = new LinkedList<>();
    }
    public void connect(VertexNode<T> to){
        connect(to, 0);
    }
    public void connect(VertexNode<T> to, float weight){
        Edge<T> edge = getEdge(to);
        if(edge == null){
            edge = new Edge(this, to, weight);
            this.adList.add(edge);
            edge.from.outDegree += 1;
            edge.to.inDegree += 1;
        }
        else edge.weight = weight;
    }
    public List<T> getOutwardEdges(){
        List<T> list = new LinkedList<>();
        Iterator<Edge<T>> it = this.adList.iterator();
        while(it.hasNext()){
            T to = it.next().to.vertex;
            list.add(to);
        }
        return list;
    }
    Edge<T> getEdge(VertexNode<T> to){
        Iterator<Edge<T>> edgeIt = this.adList.iterator();
        while(edgeIt.hasNext()){
            Edge<T> edge = edgeIt.next();
            if(edge.equals(new Edge<>(this, to))) return edge;
        }
        return null;
    }

    public void removeTo(VertexNode<T> to){
        Iterator<Edge<T>> edgeIt = this.adList.iterator();
        while(edgeIt.hasNext()){
            Edge<T> edge = edgeIt.next();
            if(edge.to.vertex.equals(to.vertex)){
                edgeIt.remove();
                edge.from.outDegree -= 1;
                edge.to.inDegree -= 1;
                break;
            }
        }
    }

    public int inDegree(){
        return this.inDegree;
    }
    public int outDegree(){
        return this.outDegree;
    }
    public String toString(){
        String desc = String.format("V(%s, in:%4d, out:%4d)",
                this.vertex, this.inDegree, this.outDegree);
        return desc;
    }
}
class Edge<T>{
    VertexNode<T> from;
    VertexNode<T> to;
    float weight;
    public Edge(VertexNode<T> from, VertexNode<T> to, float weight){
        this.from = from;
        this.to = to;
        this.weight = weight;
    }
    public Edge(VertexNode<T> from, VertexNode<T> to){
        this.from = from;
        this.to = to;
        this.weight = 0;
    }
    public boolean equals(Object newEdge){
        Edge<T> edge = (Edge<T>)newEdge;
        boolean fromEquality = this.from.vertex.equals(edge.from.vertex);
        boolean toEquality = this.to.vertex.equals(edge.to.vertex);
        return fromEquality && toEquality;
    }
    public String toString(){
        String desc = String.format("E(from:%s, to:%s)", this.from, this.to);
        return desc;
    }
}

///
class GraphIterator<T> implements Iterator<T>{
    IGraph<T> graph;
    Iterator<VertexNode<T>> nodeIt;
    VertexNode<T> node;
    boolean afterMove = false;

    GraphIterator(IGraph<T> graph, Iterator<VertexNode<T>> nodeIt){
        this.graph = graph;
        this.nodeIt = nodeIt;
        node = null;
    }
    @Override
    public boolean hasNext() {
        return this.nodeIt.hasNext();
    }

    @Override
    public T next() {
        this.node = this.nodeIt.next();
        afterMove = true;
        return node.vertex;
    }

    @Override
    public void remove() {
        if(afterMove){
            this.graph.remove(node.vertex);
            afterMove = false;
        }
    }
}//End of GraphIterator
