package Rules;

public class Sixes implements Rule{
    @Override
    public String getName(){
        return "Seksere";
    }
    @Override
    public int getScore(byte[] dice) {
        var counts = RuleUtil.count(dice);
        return counts[6]*6;
    }
}
