import java.util.HashSet;
import java.util.TreeSet;

public class SearchComparison2 {
    public static void searchInArray(int[] arr,int target) {
        long start=System.nanoTime();
        boolean found=false;
        for (int num : arr) {
            if (num==target) {
                found=true;
                break;
            }
        }
        long time=System.nanoTime()-start;
        System.out.printf("Array Search: %6.3f ms (Found: %s)\n", time/1e6, found);
    }
    public static void searchInHashSet(HashSet<Integer> set,int target) {
        long start=System.nanoTime();
        boolean found=set.contains(target);
        long time=System.nanoTime()-start;
        System.out.printf("HashSet Search: %6.3f ms (Found: %s)\n", time/1e6, found);
    }
    public static void searchInTreeSet(TreeSet<Integer> set,int target) {
        long start=System.nanoTime();
        boolean found=set.contains(target);
        long time=System.nanoTime()-start;
        System.out.printf("TreeSet Search: %6.3f ms (Found: %s)\n", time/1e6, found);
    }
    public static void testSearch(int n) {
        System.out.printf("\nDataset Size: %,d\n", n);
        int[] arr=new int[n];
        HashSet<Integer> hashSet=new HashSet<>(n);
        TreeSet<Integer> treeSet=new TreeSet<>();
        for (int i = 0; i < n; i++) {
            arr[i]=i;
            hashSet.add(i);
            treeSet.add(i);
        }
        int target=n-1;
        searchInArray(arr, target);
        searchInHashSet(hashSet, target);
        searchInTreeSet(treeSet, target);
    }
    public static void main(String[] args) {
        System.out.println("Search Performance Comparison");
        int[] sizes={1000,10000,1000000};
        for (int size : sizes) {
            testSearch(size);
        }
    }
}