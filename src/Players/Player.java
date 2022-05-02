package Players;

import Rules.Rule;

public interface Player {
    public boolean keep(boolean[] keep, Rule[] rules, int[][] board, boolean[][] used, byte[] dice, int player);
    public void rule(Rule[] rules, int[][] board, boolean[][] used, byte[] dice, int player);
    public boolean show();
    public String getName();
}
