package Rules;

public class FiveOfAKind implements Rule{
    @Override
    public String getName(){
        return "5 Ens";
    }
    @Override
    public int maxPossible(){
        return 5*6;
    }
    @Override
    public int getScore(byte[] dice) {
        return RuleUtil.getMaxGroupScore(RuleUtil.count(dice),5, 5);
    }
}
