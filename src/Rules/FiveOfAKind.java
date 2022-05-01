package Rules;

public class FiveOfAKind implements Rule{
    @Override
    public String getName(){
        return "5 Ens";
    }
    @Override
    public int getScore(byte[] dice) {
        return RuleUtil.getMaxGroupScore(RuleUtil.count(dice),5, 5);
    }
}
