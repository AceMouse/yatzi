package Players;

import Rules.Rule;

public interface Player {
    public void promptKeep(boolean[] keep, Rule[] rules, int[][] board, boolean[][] used, byte[] dice, int player);
    public void promptRule(Rule[] rules, int[][] board, boolean[][] used, byte[] dice, int player);
    public boolean getShow();
    public String getName();

    public default int getScore(int[][] board, int player){
        int total = 0;
        for (int i = 0; i < board.length; i++) {
            total += board[i][player];
        }
        return total;
    }
}
