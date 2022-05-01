package Rules;

public class LargeStraight implements Rule{
    @Override
    public String getName(){
        return "Stor straight";
    }
    @Override
    public int getScore(byte[] dice) {
        return RuleUtil.matches(dice, new byte[]{0,0,1,1,1,1,1})?20:0;
    }
}
