package NeuralNetwork;

import java.util.ArrayList;
import java.util.Random;

public class Node {
    private float bias;
    private Node mostResentCopy;
    private final ArrayList<Edge> edgeTo = new ArrayList<>();
    private float acc = 0;
    public Node(float bias){
        this.bias = bias;
    }
    public float activate(){
        float tmp = acc;
        acc = 0;
        for(Edge edge : edgeTo)
            edge.use(bias + tmp);
        return bias + tmp;
    }
    public void add(float f){
        acc += f;
    }
    public void addEdgeTo(Node node, float weight){
        edgeTo.add(new Edge(node, weight));
    }
    public void removeEdge(Random rand){
        if (edgeTo.size() > 0)
            edgeTo.remove(rand.nextInt(edgeTo.size()));
    }
    public void addEdgeTo(Edge e){
        edgeTo.add(e);
    }

    public void mutate(float biasMutate, float edgeMutate, Random rand){
        bias += (rand.nextFloat()-0.5f)*biasMutate;
        for(Edge edge : edgeTo)
            edge.mutate(edgeMutate, rand);
    }
    public void copyNode(){
        mostResentCopy = new Node(bias);
    }
    public void copyEdges(){
        for(Edge e : edgeTo){
            mostResentCopy.addEdgeTo(e.copy());
        }
    }
    public Node getCopy(){
        return mostResentCopy;
    }
    private class Edge{
        private Node dest;
        private float weight;
        public Edge(Node dest, float weight){
            this.dest = dest;
            this.weight = weight;
        }
        public void mutate(float edgeMutate, Random rand){
            weight += (rand.nextFloat()-0.5f)*edgeMutate;
        }
        public void use(float f){
            dest.add(f*weight);
        }
        private Edge copy(){
            return new Edge(dest.mostResentCopy, weight);
        }
    }
}
