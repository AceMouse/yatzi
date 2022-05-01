package Rules;

public class Ones implements Rule{
    @Override
    public String getName(){
        return "Enere";
    }
    @Override
    public int getScore(byte[] dice) {
        var count = RuleUtil.count(dice);
        return count[1];
    }
}
