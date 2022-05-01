package Rules;

public class OnePair implements Rule{
    @Override
    public String getName(){
        return "1 Par";
    }
    @Override
    public int getScore(byte[] dice) {
        return RuleUtil.getMaxGroupScore(RuleUtil.count(dice), 2, 2);
    }
}
