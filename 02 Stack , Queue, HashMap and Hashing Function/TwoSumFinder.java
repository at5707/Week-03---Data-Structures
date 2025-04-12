import java.util.*;
public class TwoSumFinder {
    public static boolean hasPairWithSum(int[] arr,int target){
        Set<Integer> seen=new HashSet<>();
        for (int num : arr) {
            int complement=target-num;
            if (seen.contains(complement)) {
                System.out.println("Pair found: "+num+" + "+complement+" = "+target);
                return true;
            }
            seen.add(num);
        }
        System.out.println("No pair found with sum "+target);
        return false;
    }
    public static void main(String[] args) {
        int[] arr={8,4,1,6,2,9};
        int target=10;
        hasPairWithSum(arr, target);
    }
}
