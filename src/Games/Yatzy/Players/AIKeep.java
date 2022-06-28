package Games.Yatzy.Players;

import NeuralNetwork.NeuralNetwork;
import Games.Yatzy.Rules.Rule;

public class AIKeep implements Player{
    private final int inputSize, keepOutputSize , ruleOutputSize ;
    private final String name;
    private NeuralNetwork keepNN;
    private NeuralNetwork ruleNN;

    public AIKeep(int inputSize, int keepOutputSize, int ruleOutputSize, String name){
        this.inputSize = inputSize;
        this.keepOutputSize = keepOutputSize;
        this.ruleOutputSize = ruleOutputSize;
        this.name = name;
        keepNN = new NeuralNetwork(inputSize, keepOutputSize, 10, 100, 1f, 1f);
        ruleNN = new NeuralNetwork(inputSize, ruleOutputSize, 10, 100, 1f, 1f);
    }

    public AIKeep(int inputSize, int keepOutputSize, int ruleOutputSize, NeuralNetwork keepNN, NeuralNetwork ruleNN, String name){
        this.inputSize = inputSize;
        this.keepOutputSize = keepOutputSize;
        this.ruleOutputSize = ruleOutputSize;
        this.name = name;
        this.keepNN = keepNN;
        this.ruleNN = ruleNN;
    }

    public NeuralNetwork getKeepNN(){
        return keepNN;
    }
    public NeuralNetwork getRuleNN(){
        return ruleNN;
    }
    @Override
    public void promptKeep(boolean[] keep, Rule[] rules, int[][] board, boolean[][] used, byte[] dice, int player) {
        float[] input = new float[inputSize];
        for (int i = 0; i < dice.length; i++) {
            input[i*6+dice[i]-1] = 1f;
        }
        for (int i = 0; i < board.length; i++) {
            input[i + dice.length*6] = board[i][player];
        }
        for (int i = 0; i < used.length; i++) {
            input[i + dice.length*6 + board.length] = used[i][player]?1f:0f;
        }
        float[] output = keepNN.predict(input);
        for (int i = 0; i < output.length; i++) {
            keep[i] = output[i] > 0.5f;
        }
    }

    @Override
    public void promptRule(Rule[] rules, int[][] board, boolean[][] used, byte[] dice, int player) {
        int max = 0;
        int idx = 0;
        for (int i = 0; i < rules.length; i++) {
            if (used[i][player])
                continue;
            if (rules[i].getScore(dice)>=max) {
                max = rules[i].getScore(dice);
                idx = i;
            }
        }
        board[idx][player] = max;
        used[idx][player] = true;

    }

    @Override
    public boolean getShow(boolean finalDice) {
        return false;
    }

    @Override
    public String getName() {
        return name;
    }
}

