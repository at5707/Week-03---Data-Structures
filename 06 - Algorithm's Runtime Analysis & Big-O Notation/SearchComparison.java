import java.util.Arrays;
import java.util.Random;

public class SearchComparison {
    public static int linearSearch(int[] arr,int target) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i]==target)
                return i;
        }
        return -1;
    }
    public static int binarySearch(int[] arr,int target) {
        int left=0,right=arr.length-1;
        while (left<=right) {
            int mid=left+(right-left)/2;
            if (arr[mid]==target) return mid;
            if (arr[mid]<target) left=mid+1;
            else right=mid-1;
        }
        return -1;
    }
    public static int[] generateArray(int size,Random rand) {
        int[] arr=new int[size];
        for (int i = 0; i < size; i++) 
            arr[i]=rand.nextInt(size*10);
        return arr;
    }
    public static void testSearchPerformance(int size) {
        Random rand=new Random();
        int[] data=generateArray(size, rand);
        int target=data[rand.nextInt(size)];
        long start=System.nanoTime();
        linearSearch(data, target);
        long linearTime=System.nanoTime()-start;
        Arrays.sort(data);
        start=System.nanoTime();
        binarySearch(data, target);
        long binaryTime=System.nanoTime()-start;
        System.out.printf("Dataset Size: %,d | Linear: %6.3f ms | Binary: %6.3f ms\n", size,linearTime/1e6,binaryTime/1e6);
    }
    public static void main(String[] args) {
        System.out.println("Search Performance Comparison\n");
        int[] sizes={1000,10000,1000000};
        for (int size : sizes) {
            testSearchPerformance(size);
        }
    }
}