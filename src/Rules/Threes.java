package Rules;

public class Threes implements Rule{
    @Override
    public String getName(){
        return "Treere";
    }
    @Override
    public int getScore(byte[] dice) {
        var counts = RuleUtil.count(dice);
        return counts[3]*3;
    }
}
