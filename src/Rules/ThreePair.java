package Rules;

public class ThreePair implements Rule{
    @Override
    public String getName(){
        return "3 Par";
    }
    @Override
    public int getScore(byte[] dice) {
        return RuleUtil.getGroupsScore(RuleUtil.count(dice), new byte[]{2,2,2});
    }
}
