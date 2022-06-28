package Games.Yatzy.Rules;

public class Ones implements Rule{
    @Override
    public String getName(){
        return "Enere";
    }
    @Override
    public int maxPossible(){
        return 6*1;
    }
    @Override
    public int getScore(byte[] dice) {
        var count = RuleUtil.count(dice);
        return count[1];
    }
}
