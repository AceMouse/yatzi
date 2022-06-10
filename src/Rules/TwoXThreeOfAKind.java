package Rules;

public class TwoXThreeOfAKind implements Rule{
    @Override
    public String getName(){
        return "2x3 Ens";
    }
    @Override
    public int maxPossible(){
        return 3*6 + 3*5;
    }
    @Override
    public int getScore(byte[] dice) {
        return RuleUtil.getGroupsScore(RuleUtil.count(dice),new byte[]{3,3});
    }
}
