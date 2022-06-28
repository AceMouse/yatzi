package Games.Yatzy.Rules;

public class FullHouse implements Rule{
    @Override
    public String getName(){
        return "Fuldt hus";
    }
    @Override
    public int maxPossible(){
        return 6*3 + 5*2;
    }
    @Override
    public int getScore(byte[] dice) {
        return RuleUtil.getGroupsScore(RuleUtil.count(dice), new byte[]{2, 3});
    }
}
