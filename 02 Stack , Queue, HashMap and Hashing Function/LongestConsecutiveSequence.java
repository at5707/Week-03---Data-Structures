import java.util.*;
public class LongestConsecutiveSequence {
    public static int longestConsecutive(int[] nums){
        Set<Integer> numSet=new HashSet<>();
        for (int num : nums) {
            numSet.add(num);
        }
        int maxLength=0;
        for (int num : nums) {
            if (!numSet.contains(num-1)) {
                int current=num;
                int length=1;
                while (numSet.contains(current+1)) {
                    current++;
                    length++;
                }
                maxLength=Math.max(maxLength, length);
            }
        }
        return maxLength;
    }
    public static void main(String[] args) {
        int[] arr={100,4,200,1,3,2};
        System.out.println("Longest consecutive sequence length: "+longestConsecutive(arr));
    }
}