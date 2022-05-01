package Rules;

public class TwoXThreeOfAKind implements Rule{
    @Override
    public String getName(){
        return "2x3 Ens";
    }
    @Override
    public int getScore(byte[] dice) {
        return RuleUtil.getGroupsScore(RuleUtil.count(dice),new byte[]{3,3});
    }
}
