package Games.Yatzy.Rules;

public class Fours implements Rule{
    @Override
    public String getName(){
        return "Fireere";
    }
    @Override
    public int maxPossible(){
        return 6*4;
    }
    @Override
    public int getScore(byte[] dice) {
        var counts = RuleUtil.count(dice);
        return counts[4]*4;
    }
}
