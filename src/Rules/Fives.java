package Rules;

public class Fives implements Rule{
    @Override
    public String getName(){
        return "Femere";
    }
    @Override
    public int getScore(byte[] dice) {
        var counts = RuleUtil.count(dice);
        return counts[5]*5;
    }
}
