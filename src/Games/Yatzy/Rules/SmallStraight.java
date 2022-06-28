package Games.Yatzy.Rules;

public class SmallStraight implements Rule{
    @Override
    public String getName(){
        return "Lille Straight";
    }
    @Override
    public int maxPossible(){
        return 15;
    }
    @Override
    public int getScore(byte[] dice) {
        return RuleUtil.matches(dice, new byte[]{0,1,1,1,1,1,0})?15:0;
    }
}
