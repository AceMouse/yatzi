package Games.Yatzy.Rules;

public class Sixes implements Rule{
    @Override
    public String getName(){
        return "Seksere";
    }
    @Override
    public int maxPossible(){
        return 6*6;
    }
    @Override
    public int getScore(byte[] dice) {
        var counts = RuleUtil.count(dice);
        return counts[6]*6;
    }
}
