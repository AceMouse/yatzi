package Games.Yatzy.Rules;

public class Chance implements Rule{
    @Override
    public String getName(){
        return "Chancen";
    }

    @Override
    public int maxPossible(){
        return 6*6;
    }
    @Override
    public int getScore(byte[] dice) {
        var counts = RuleUtil.count(dice);
        int score = 0;
        for (int i = 0; i < counts.length; i++) {
            score += i*counts[i];
        }
        return score;
    }
}
