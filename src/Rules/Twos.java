package Rules;

public class Twos implements Rule{
    @Override
    public String getName(){
        return "Toere";
    }
    @Override
    public int maxPossible(){
        return 6*2;
    }
    @Override
    public int getScore(byte[] dice) {
        var counts = RuleUtil.count(dice);
        return counts[2]*2;
    }
}
