package Games.Yatzy.Game;

import NeuralNetwork.NeuralNetwork;
import Games.Yatzy.Players.*;
import Games.Yatzy.Rules.*;

import java.util.Random;
import java.util.Scanner;

public class Game {
    private static final Random rand = new Random();
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("train or play? [T/p]");
        String trainOrPlay = sc.next();
        System.out.println("how many players?");
        int playersTotal = sc.nextInt();
        System.out.println("how many games to average?");
        int games = sc.nextInt();
        Player[] players = new Player[playersTotal];
        boolean training = !trainOrPlay.equalsIgnoreCase("p");
        int gens = 1;
        Rule[] rules = new Rule[]{new Ones(), new Twos(), new Threes(), new Fours(), new Fives(), new Sixes(),
                new OnePair(), new TwoPair(), new ThreePair(), new ThreeOfAKind(), new FourOfAKind(), new FiveOfAKind(), new TwoXThreeOfAKind(),
                new SmallStraight(), new LargeStraight(), new Royal(), new FullHouse(), new Chance(), new Yatzy() };
        if (!training) {
            System.out.println("how many human players?");
            int humans = sc.nextInt();

            for (int i = 0; i < humans; i++) {
                players[i] = new Human("H " + (i + 1));
            }
            for (int i = humans; i < playersTotal; i++) {
                players[i] = new GreedyPLayer("G " + (i + 1));
            }
        } else {
            System.out.println("how many generations?");
            gens = sc.nextInt();

            for (int i = 0; i < playersTotal; i++) {
                players[i] = new AIKeep(rules.length*2+6*6,6, rules.length, "A " + (i + 1));
            }
        }
        sc.nextLine();



        int maxPossibleScore = 0;
        for (int i = 0; i < rules.length; i++) {
            maxPossibleScore += rules[i].maxPossible();
        }
        System.out.println("Max possible score: " + maxPossibleScore);

        while (gens-- > 0) {
            System.out.println();
            System.out.println("gens left: " + (gens+1));
            int[] aggregateScore = new int[playersTotal];
            for (int game = 0; game < games; game++) {
                int[] score = playGame(rules, players, false);
                for (int i = 0; i < playersTotal; i++) {
                    aggregateScore[i] += score[i];
                }
            }
            int idx = 0, max = Integer.MIN_VALUE;
            for (int i = 0; i < playersTotal; i++) {
                if (aggregateScore[i] > max) {
                    idx = i;
                    max = aggregateScore[i];
                }
            }
            if (training) {
                AIKeep ancestor = (AIKeep) players[idx];
                players[0] = ancestor;
                for (int i = 1; i < playersTotal; i++) {
                    NeuralNetwork keep = ancestor.getKeepNN(), rule = ancestor.getRuleNN();
                    players[i] = new AIKeep(rules.length * 2 + 6*6, 6, rules.length, keep.mutate(), rule.mutate(), "A " + (i + 1));
                }
            }
            int avg = 0;
            for (int i = 0; i < playersTotal; i++) {
                avg += aggregateScore[i];
                System.out.print(aggregateScore[i] + " ");
            }
            System.out.println();
            System.out.println("best: " + max + " " + max/games);
            System.out.println("avg: " + avg+ " " + avg/playersTotal/games);
        }




    }

    private static int[] playGame(Rule[] rules, Player[] players, boolean showEndState) {
        int playersTotal = players.length;
        int[][] board = new int[rules.length][playersTotal];
        boolean[][] used = new boolean[board.length][board[0].length];
        byte[] dice = new byte[6];
        int player = 0;
        int turns = playersTotal * rules.length;
        while (turns-->0) {
            boolean[] keep = new boolean[6];
            roll(dice, keep);
            for (int i = 0; i < 2; i++) {
                keep = new boolean[6];
                if (players[player].getShow(false)) {
                    showBoard(rules, board, used, dice, player, players);
                    showDice(dice);
                }
                players[player].promptKeep(keep, rules, board, used, dice, player);
                roll(dice, keep);
            }
            if (players[player].getShow(true)) {
                showBoard(rules, board, used, dice, player, players);
                showDice(dice);
            }
            players[player].promptRule(rules,board,used,dice,player);
            player = (player +1)% playersTotal;
        }
        if (showEndState)
            showBoard(rules, board,used, dice, player, players);
        int[] scores = new int[playersTotal];
        for (int i = 0; i < playersTotal; i++) {
            scores[i] = players[i].getScore(board,i);
        }
        return scores;
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
        for (int i = 0; i < players.length; i++) {
            System.out.printf("%s|", Util.rightPad(String.valueOf(players[i].getScore(board,i)), 4));
        }
        System.out.println();
    }
    private static void showDice(byte[] dice){
        System.out.print("These are your dice:");
        for (var die: dice){
            System.out.printf(" %d", die);
        }
        System.out.println();
    }
}
