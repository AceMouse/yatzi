package Rules;

public class TwoPair implements Rule{
    @Override
    public String getName(){
        return "2 Par";
    }
    @Override
    public int getScore(byte[] dice) {
        return RuleUtil.getGroupsScore(RuleUtil.count(dice), new byte[]{2,2});
    }
}
