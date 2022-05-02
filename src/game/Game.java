package game;

import Rules.*;

import java.util.Random;
import java.util.Scanner;

public class Game {
    private static final Random rand = new Random();
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("how many players?");
        int players = sc.nextInt();
        System.out.println("how many human players?");
        int humans = sc.nextInt();
        sc.nextLine();
        Rule[] rules = new Rule[]{new Ones(), new Twos(), new Threes(), new Fours(), new Fives(), new Sixes(),
                new OnePair(), new TwoPair(), new ThreePair(), new ThreeOfAKind(), new FourOfAKind(), new FiveOfAKind(), new TwoXThreeOfAKind(),
                new SmallStraight(), new LargeStraight(), new Royal(), new FullHouse(), new Chance(), new Yatzy() };
        int[][] board = new int[rules.length][players];
        boolean[][] used = new boolean[board.length][board[0].length];
        byte[] dice = new byte[6];
        int player = 0;

        while (true) {
            boolean[] keep = new boolean[6];
            roll(dice, keep);
            for (int i = 0; i < 2; i++) {
                showBoard(rules, board,used, dice, player);
                System.out.println("Which do you want to keep?");
                var nextLine = sc.nextLine();
                if ("d".equals(nextLine))
                    break;
                for (var die : nextLine.split("")){
                    if (die.length() > 0 && '1' <= die.charAt(0) && die.charAt(0) <= '6')
                        keep[Integer.parseUnsignedInt(die)-1] = true;
                }
                roll(dice, keep);
            }
            showBoard(rules, board,used, dice, player);
            outer:
            while (true) {
                System.out.println("Which rule do you want to use?");
                var nextLine = sc.nextLine();
                if ("q".equals(nextLine))
                    break;
                for (int i = 0; i < rules.length; i++) {
                    if (rules[i].getName().equalsIgnoreCase(nextLine) && !used[i][player]) {
                        board[i][player] = rules[i].getScore(dice);
                        used[i][player] = true;
                        break outer;
                    }
                }
            }
            player = (player +1)%players;
        }
    }
    private static void roll(byte[] dice, boolean[] keep){
        for (int i = 0; i < keep.length; i++) {
            if (!keep[i])
                dice[i] = (byte) rand.nextInt(1, 7);
        }
    }
    private static void showBoard(Rule[] rules, int[][] board, boolean[][] used, byte[] dice, int player){
        System.out.printf("|%s|%s|",Util.rightPad("RULE", 20), Util.rightPad("POSSIBLE POINTS", 20));
        for (int i = 0; i < board[0].length; i++) {
            System.out.printf("%s|", Util.rightPad(String.valueOf(i+1), 4));
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
