package NeuralNetwork;

import java.util.HashSet;
import java.util.Random;

public class NN {
    Random rand = new Random();
    Node[][] nodes;

    public NN(int inputs, int outputs, int hiddenLayers, int nodesInHiddenLayer){
        nodes = new Node[hiddenLayers+2][];
        nodes[0] = new Node[inputs];
        nodes[nodes.length-1] = new Node[outputs];
        for (int i = 1; i < nodes.length-1; i++) {
            nodes[i] = new Node[nodesInHiddenLayer];
        }
        for (int i = 0; i < nodes.length; i++) {
            for (int j = 0; j < nodes[i].length; j++) {
                nodes[i][j] = new Node(rand.nextFloat());
            }
        }
        for (int i = 0; i < nodes.length-1; i++) {
            for (int j = 0; j < nodes[i].length; j++) {
                int edges = rand.nextInt(nodes[i+1].length);
                HashSet<Integer> edgeSet = new HashSet<>();
                while (edges-- > 0){
                    edgeSet.add(rand.nextInt(nodes[i+1].length));
                }
                for (int n : edgeSet){
                    nodes[i][j].addEdgeTo(nodes[i][n], rand.nextFloat());
                }
            }
        }
    }
    public NN (Node[][] nodes){
        this.nodes = nodes;
    }
    public float[] predict(float[] input){
        for (int i = 0; i < input.length; i++) {
            nodes[0][i].add(input[i]);
            nodes[0][i].activate();
        }
        for (int i = 1; i < nodes.length-1; i++) {
            for (int j = 0; j < nodes[i].length; j++) {
                nodes[i][j].activate();
            }
        }
        float[] output = new float[nodes[nodes.length-1].length];
        int i = 0;
        for (Node node : nodes[nodes.length-1])
            output[i++] = node.activate();
        return output;
    }
    public void mutate(float biasMutate, float edgeMutate){
        for (int i = 0; i < nodes.length; i++) {
            for (int j = 0; j < nodes[i].length; j++) {
                nodes[i][j].mutate(biasMutate, edgeMutate, rand);
            }
        }
    }
}
