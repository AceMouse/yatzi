package Games.Yatzy.Rules;

public class OnePair implements Rule{
    @Override
    public String getName(){
        return "1 Par";
    }
    @Override
    public int maxPossible(){
        return 6*2;
    }
    @Override
    public int getScore(byte[] dice) {
        return RuleUtil.getMaxGroupScore(RuleUtil.count(dice), 2, 2);
    }
}
