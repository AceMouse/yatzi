package game;

import Players.GreedyPLayer;
import Players.Human;
import Players.Player;
import Rules.*;

import java.util.Random;
import java.util.Scanner;

public class Game {
    private static final Random rand = new Random();
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("how many players?");
        int playersTotal = sc.nextInt();
        System.out.println("how many human players?");
        int humans = sc.nextInt();
        sc.nextLine();
        Rule[] rules = new Rule[]{new Ones(), new Twos(), new Threes(), new Fours(), new Fives(), new Sixes(),
                new OnePair(), new TwoPair(), new ThreePair(), new ThreeOfAKind(), new FourOfAKind(), new FiveOfAKind(), new TwoXThreeOfAKind(),
                new SmallStraight(), new LargeStraight(), new Royal(), new FullHouse(), new Chance(), new Yatzy() };
        int[][] board = new int[rules.length][playersTotal];
        boolean[][] used = new boolean[board.length][board[0].length];
        byte[] dice = new byte[6];
        int player = 0;
        Player[] players = new Player[playersTotal];
        for (int i = 0; i < humans; i++) {
            players[i] = new Human("H " + (i+1));
        }
        for (int i = humans; i < playersTotal; i++) {
            players[i] = new GreedyPLayer("G " + (i+1));
        }

        while (true) {
            boolean[] keep = new boolean[6];
            roll(dice, keep);
            for (int i = 0; i < 2; i++) {
                keep = new boolean[6];
                if (players[player].show())
                    showBoard(rules, board,used, dice, player, players);
                if (players[player].keep(keep, rules, board, used, dice, player))
                    break;
                roll(dice, keep);
            }
            if (players[player].show())
                showBoard(rules, board,used, dice, player, players);
            players[player].rule(rules,board,used,dice,player);
            player = (player +1)%playersTotal;
        }
    }
    private static void roll(byte[] dice, boolean[] keep){
        for (int i = 0; i < keep.length; i++) {
            if (!keep[i])
                dice[i] = (byte) rand.nextInt(1, 7);
        }
    }
    private static void showBoard(Rule[] rules, int[][] board, boolean[][] used, byte[] dice, int player, Player[] players){
        System.out.printf("|%s|%s|",Util.rightPad("RULE", 20), Util.rightPad("POSSIBLE POINTS", 20));
        for (int i = 0; i < board[0].length; i++) {
            System.out.printf("%s|", Util.rightPad(players[i].getName(), 4));
        }
        System.out.println();
        for (int i = 0; i < rules.length; i++) {
            System.out.printf("|%s|%s|",Util.rightPad(rules[i].getName(), 20), Util.rightPad(used[i][player]?"":String.valueOf(rules[i].getScore(dice)), 20));
            for (int j = 0; j < board[0].length; j++) {
                System.out.printf("%s|", Util.rightPad(board[i][j] == 0?(used[i][j]?"/":""):String.valueOf(board[i][j]), 4));
            }
            System.out.println();
        }
        System.out.printf("|%s|%s|",Util.rightPad("Total", 20), Util.rightPad("", 20));
        for (int i = 0; i < board[0].length; i++) {
            int total = 0;
            for (int j = 0; j < board.length; j++) {
                total += board[j][i];
            }
            System.out.printf("%s|", Util.rightPad(String.valueOf(total), 4));
        }
        System.out.println();
        System.out.print("These are your dice:");
        for (var die: dice){
            System.out.printf(" %d", die);
        }
        System.out.println();

    }
}
