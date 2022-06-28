package NeuralNetwork.ActivationFunction;

public class ReLU implements ActivationFunction{
    public float activate(float x){
        return x > 0f ? x : 0f;
    }
}
