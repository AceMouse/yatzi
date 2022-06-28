package Games.Yatzy.Rules;

public class FourOfAKind implements Rule{
    @Override
    public String getName(){
        return "4 Ens";
    }
    @Override
    public int maxPossible(){
        return 6*4;
    }
    @Override
    public int getScore(byte[] dice) {
        return RuleUtil.getMaxGroupScore(RuleUtil.count(dice),4, 4);
    }
}
