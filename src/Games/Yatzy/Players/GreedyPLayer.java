package Games.Yatzy.Players;

import Games.Yatzy.Rules.Rule;

import java.util.Arrays;

public class GreedyPLayer implements Player{
    private String name;
    public GreedyPLayer(String name){
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public boolean getShow(boolean finalDice) {
        return finalDice;
    }

    @Override
    public void promptKeep(boolean[] keep, Rule[] rules, int[][] board, boolean[][] used, byte[] dice, int player) {
        Arrays.fill(keep, true);
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
}
