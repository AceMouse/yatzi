package Rules;

public class ThreePair implements Rule{
    @Override
    public String getName(){
        return "3 Par";
    }
    @Override
    public int maxPossible(){
        return 2*6+2*5+2*4;
    }
    @Override
    public int getScore(byte[] dice) {
        return RuleUtil.getGroupsScore(RuleUtil.count(dice), new byte[]{2,2,2});
    }
}
