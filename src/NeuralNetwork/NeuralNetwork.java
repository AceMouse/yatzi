package NeuralNetwork;

public class NeuralNetwork {
    Neuron[][] nn;
    float maxWeightMutation, maxBiasMutation;
    int inputs, outputs, neuronsInHiddenLayer, hiddenLayers;
    public NeuralNetwork(int inputs, int outputs, int hiddenLayers, int neuronsInHiddenLayer, float maxWeightMutation, float maxBiasMutation){
        this.maxBiasMutation = maxBiasMutation;
        this.maxWeightMutation = maxWeightMutation;
        this.inputs = inputs;
        this.outputs = outputs;
        this.neuronsInHiddenLayer = neuronsInHiddenLayer;
        this.hiddenLayers = hiddenLayers;
        nn = new Neuron[hiddenLayers+1][];
        int nodesInPreviousLayer = inputs;
        for (int i = 0; i < hiddenLayers; i++) {
            nn[i] = new Neuron[neuronsInHiddenLayer];
            for (int j = 0; j < neuronsInHiddenLayer; j++) {
                nn[i][j] = new Neuron(nodesInPreviousLayer);
            }
            nodesInPreviousLayer = neuronsInHiddenLayer;
        }
        nn[hiddenLayers] = new Neuron[outputs];
        for (int i = 0; i < outputs; i++) {
            nn[hiddenLayers][i] = new Neuron(nodesInPreviousLayer);
        }
    }
    public NeuralNetwork(Neuron[][] nn, int inputs, int outputs, int hiddenLayers, int neuronsInHiddenLayer, float maxWeightMutation, float maxBiasMutation){
        this.maxBiasMutation = maxBiasMutation;
        this.maxWeightMutation = maxWeightMutation;
        this.inputs = inputs;
        this.outputs = outputs;
        this.neuronsInHiddenLayer = neuronsInHiddenLayer;
        this.hiddenLayers = hiddenLayers;
        this.nn = new Neuron[hiddenLayers+1][];
        for (int i = 0; i < hiddenLayers; i++) {
            this.nn[i] = new Neuron[neuronsInHiddenLayer];
            for (int j = 0; j < neuronsInHiddenLayer; j++) {
                this.nn[i][j] = nn[i][j].mutate(maxWeightMutation, maxBiasMutation);
            }
        }
        this.nn[hiddenLayers] = new Neuron[outputs];
        for (int i = 0; i < outputs; i++) {
            this.nn[hiddenLayers][i] = nn[hiddenLayers][i].mutate(maxWeightMutation, maxBiasMutation);
        }
    }
    public NeuralNetwork mutate(){
        return new NeuralNetwork(nn, inputs, outputs, hiddenLayers, neuronsInHiddenLayer, maxWeightMutation, maxBiasMutation);
    }
    public int predictMax(float[] inputs){
        inputs = predict(inputs);
        int idx = 0;
        float max = Float.MIN_VALUE;
        for (int i = 0; i < inputs.length; i++) {
            if (inputs[i] > max){
                max = inputs[i];
                idx = i;
            }
        }
        return idx;
    }
    public float[] predict(float[] inputs){
        for (int i = 0; i < nn.length; i++) {
            float[] newInputs = new float[nn[i].length];
            for (int j = 0; j < nn[i].length; j++) {
                newInputs[j] = nn[i][j].activate(inputs);
            }
            inputs = newInputs;
        }
        return inputs;
    }
}
