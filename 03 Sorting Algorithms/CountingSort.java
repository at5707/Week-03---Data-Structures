import java.util.Arrays;

public class CountingSort {
    public static void countingSort(int[] arr){
        int minAge=10;
        int maxAge=18;
        int range=maxAge-minAge+1;
        int[] count=new int[range];
        int[] output=new int[arr.length];
        for (int age : arr) {
            count[age-minAge]++;
        }
        for (int i = 1; i < count.length; i++) {
            count[i]+=count[i-1];
        }
        for (int i = arr.length-1; i >= 0; i--) {
            int age=arr[i];
            int pos=count[age-minAge]-1;
            output[pos]=age;
            count[age-minAge]--;
        }
        System.arraycopy(output, 0, arr, 0, arr.length);
    }
    public static void main(String[] args) {
        int[] studentAges={12,15,10,18,12,14,17,10,13};
        countingSort(studentAges);
        System.out.println(Arrays.toString(studentAges));
    }
}
