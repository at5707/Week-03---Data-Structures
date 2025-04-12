import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class MissingPositiveAndBinarySearch {
    public static int findFirstMissingPositive(int[] nums) {
        Set<Integer> set=new HashSet<>();
        for (int num : nums) {
            if (num>0) {
                set.add(num);
            }
        }
        int i=1;
        while (true) {
            if (!set.contains(i)) {
                return i;
            }
            i++;
        }
    }
    public static int binarySearch(int[] arr,int target) {
        int left=0,right=arr.length-1;
        while (left<=right) {
            int mid=(left+right)/2;
            if (arr[mid]==target) {
                return mid;
            } else if (arr[mid]<target) {
                left=mid+1;
            } else {
                right=mid-1;
            }
        }
        return -1;
    }
    public static void main(String[] args) {
        int[] nums={3,4,-1,1,2,5,7};
        int firstMissing=findFirstMissingPositive(nums);
        System.out.println("First missing positive integer "+firstMissing);
        int target=4;
        Arrays.sort(nums);
        int index=binarySearch(nums, target);
        if (index!=-1) {
            System.out.println("Target "+target+" found at index: "+index);
        } else {
            System.out.println("Target "+target+" not found");
        }
    }
}