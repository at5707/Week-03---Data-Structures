import java.util.Arrays;

public class SelectionSort {
    public static void selectionSort(int[] arr){
        int n=arr.length;
        for (int i = 0; i < n-1; i++) {
            int minIndex=i;
            for (int j = i+1; j < n; j++) {
                if (arr[j]<arr[minIndex]) {
                    minIndex=j;
                }
            }
            int temp=arr[minIndex];
            arr[minIndex]=arr[i];
            arr[i]=temp;
        }
    }
    public static void main(String[] args) {
        int[] arr={88,75,92,60,85,70};
        selectionSort(arr);
        System.out.println(Arrays.toString(arr));
    }
}
