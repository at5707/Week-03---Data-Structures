import java.util.Arrays;
import java.util.Random;

public class SortingComparison {
    public static void bubbleSort(int[] arr) {
        int n=arr.length;
        boolean swapped;
        for (int i = 0; i < n-1; i++) {
            swapped=false;
            for (int j = 0; j < n-i-1; j++) {
                if (arr[j]>arr[j+1]) {
                    int temp=arr[j];
                    arr[j]=arr[j+1];
                    arr[j+1]=temp;
                    swapped=true;
                }
            }
            if (!swapped) {
                break;
            }
        }
    }
    public static void mergeSort(int[] arr) {
        if (arr.length<2) return;
        int mid=arr.length/2;
        int[] left=Arrays.copyOfRange(arr, 0, mid);
        int[] right=Arrays.copyOfRange(arr, mid, arr.length);
        mergeSort(left);
        mergeSort(right);
        merge(arr,left,right);
    }
    private static void merge(int[] arr,int[] left,int[] right) {
        int i=0,j=0,k=0;
        while (i<left.length && j<right.length) 
            arr[k++]=(left[i]<=right[j])?left[i++]:right[j++];
        while (i<left.length) arr[k++]=left[i++];
        while (j<right.length) arr[k++]=right[j++]; 
    }
    public static void quickSort(int[] arr,int low,int high) {
        if (low<high) {
            int pi=partition(arr,low,high);
            quickSort(arr, low, pi-1);
            quickSort(arr, pi+1, high);
        }
    }
    private static int partition(int[] arr,int low,int high) {
        int pivot=arr[high];
        int i=(low-1);
        for (int j = low; j < high; j++) {
            if (arr[j]<pivot) {
                i++;
                int temp=arr[i];
                arr[i]=arr[j];
                arr[j]=temp;
            }
        }
        int temp=arr[i+1];
        arr[i+1]=arr[high];
        arr[high]=temp;
        return i+1;
    }
    public static int[] generateArray(int size,Random rand) {
        int[] arr=new int[size];
        for (int i = 0; i < size; i++) {
            arr[i]=rand.nextInt(size*10);
        }
        return arr;
    }
    public static void testSortingPerformane(int size) {
        Random rand=new Random();
        int[] original=generateArray(size, rand);
        int[] bubble=Arrays.copyOf(original, original.length);
        int[] merge=Arrays.copyOf(original, original.length);
        int[] quick=Arrays.copyOf(original, original.length);
        System.out.printf("\nDataset Size: %,d\n",size);
        if (size<=10000) {
            long start=System.nanoTime();
            bubbleSort(bubble);
            long time=System.nanoTime()-start;
            System.out.printf("Bubble Sort: %6.3f ms\n",time/1e6);
        } else {
            System.out.println("Bubble Sort: Skipped (Too slow)");
        }
        long start=System.nanoTime();
        mergeSort(merge);
        long time=System.nanoTime()-start;
        System.out.printf("Merge sort: %6.3f ms\n",time/1e6);
        start=System.nanoTime();
        quickSort(quick, 0, quick.length-1);
        time=System.nanoTime()-start;
        System.out.printf("Quick sort: %6.3f ms\n",time/1e6);
    }
    public static void main(String[] args) {
        System.out.println("Sorting Algorithm Performance Comparison");
        int[] sizes={1000,10000,1000000};
        for (int size : sizes) {
            testSortingPerformane(size);
        }
    }
}