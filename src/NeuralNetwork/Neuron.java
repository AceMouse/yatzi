package NeuralNetwork;

import NeuralNetwork.ActivationFunction.ActivationFunction;
import NeuralNetwork.ActivationFunction.ReLU;

import java.util.Arrays;
import java.util.Random;

public class Neuron {
    float[] w;
    float b;
    ActivationFunction f = new ReLU();
    Random rand = new Random();

    public Neuron(int w){
        this.w = new float[w];
        for (int i = 0; i < w; i++) {
            this.w[i] = rand.nextFloat(-1f,1f);
        }
        b = 0;
    }

    public Neuron(float[] w, float b){
        this.w = w;
        this.b = b;
    }
    public Neuron mutate(float maxWeightMutation, float maxBiasMutation){
        float[] nw = Arrays.copyOf(w, w.length);
        for (int i = 0; i < nw.length; i++) {
            nw[i] += rand.nextFloat(-maxWeightMutation, maxWeightMutation);
        }
        float nb = b + rand.nextFloat(-maxBiasMutation, maxBiasMutation);
        return new Neuron(nw, nb);
    }
    public float activate(float[] inputs){
        float output = b;
        for (int i = 0; i < inputs.length; i++) {
            output += w[i]*inputs[i];
        }
        return f.activate(output);
    }
}
