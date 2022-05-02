package Rules;

import java.util.Arrays;

public class RuleUtil {
    public static byte[] count(byte[] dice){
        var counts = new byte[7];
        for (byte die : dice)
            counts[die]++;
        return counts;
    }
    public static boolean matches(byte[] dice, byte[] required){
        var counts = count(dice);
        for (int i = 0; i < counts.length; i++) {
            if (counts[i] < required[i])
                return false;
        }
        return true;
    }
    public static int getMaxGroupScore(byte[] counts, int minSize, int maxSize){
        int max = 0;
        for (int i = 0; i < counts.length; i++) {
            if (counts[i] >= minSize && max < counts[i]*i)
                max = Math.min(counts[i], maxSize) * i;
        }
        return max;
    }
    public static int getMaxGroupIdx(byte[] counts, int minSize, int maxSize){
        int max = 0;
        int idx = 0;
        for (int i = 0; i < counts.length; i++) {
            if (counts[i] >= minSize && max < counts[i]*i) {
                max = Math.min(counts[i], maxSize) * i;
                idx = i;
            }
        }
        return idx;
    }
    public static int getGroupsScore(byte[] counts, byte[] sizes){
        Arrays.sort(sizes);
        int score = 0;
        for (int i = sizes.length-1; i >= 0; i--) {
            var idx = getMaxGroupIdx(counts, sizes[i], sizes[i]);
            if (idx == 0)
                return 0;
            score += getMaxGroupScore(counts, sizes[i], sizes[i]);
            counts[idx] = 0;
        }
        return score;
    }
}
