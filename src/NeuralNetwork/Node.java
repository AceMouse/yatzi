package NeuralNetwork;

import java.util.ArrayList;
import java.util.Random;

public class Node {
    private float bias;
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
    public void mutate(float biasMutate, float edgeMutate, Random rand){
        bias += (rand.nextFloat()-0.5f)*biasMutate;
        for(Edge edge : edgeTo)
            edge.mutate(edgeMutate, rand);
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
    }
}
