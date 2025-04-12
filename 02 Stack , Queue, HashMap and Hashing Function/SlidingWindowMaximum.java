import java.util.*;
public class SlidingWindowMaximum {
    public static List<Integer> maxInWindows(int[] arr,int k){
        List<Integer> result=new ArrayList<>();
        Deque<Integer> deque=new LinkedList<>();
        for (int i = 0; i < arr.length; i++) {
            while (!deque.isEmpty()&&deque.peekFirst()<=i-k) {
                deque.pollFirst();
            }
            while (!deque.isEmpty()&&arr[deque.peekLast()]<arr[i]) {
                deque.pollLast();
            }
            deque.offerLast(i);
            if (i>=k-1) {
                result.add(arr[deque.peekFirst()]);
            }
        }
        return result;
    }
    public static void main(String[] args) {
        int[] arr={1,3,-1,-3,5,3,6,7};
        int k=3;
        List<Integer> maxValues=maxInWindows(arr, k);
        System.out.println("Sliding Window Maximums: "+maxValues);
    }
}