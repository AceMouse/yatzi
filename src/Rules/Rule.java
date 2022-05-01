package Rules;

public interface Rule {
    /**
     *
     * @param dice
     * @return the maximal possible score with this rule, 0 if illegal
     */
    public int getScore(byte[] dice);
    public String getName();
}
