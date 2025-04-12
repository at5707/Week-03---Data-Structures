public class FirstLastOccurrence {
    public static int findFirst(int[] arr,int target) {
        int left=0,right=arr.length-1;
        int result=-1;
        while (left<=right) {
            int mid=(left+right)/2;
            if (arr[mid]==target) {
                result=mid;
                right=mid-1;
            } else if (arr[mid]<target) {
                left=mid+1;
            } else {
                right=mid-1;   
            }
        }
        return result;
    }
    public static int findLast(int[] arr,int target) {
        int left=0,right=arr.length-1;
        int result=-1;
        while (left<=right) {
            int mid=(left+right)/2;
            if (arr[mid]==target) {
                result=mid;
                left=mid+1;
            } else if (arr[mid]<target) {
                left=mid+1;
            } else {
             right=mid-1;   
            }
        }
        return result;
    }
    public static void main(String[] args) {
        int[] sortedArray={1,2,4,4,4,5,6};
        int target=4;
        int firstIndex=findFirst(sortedArray, target);
        int lastIndex=findLast(sortedArray, target);
        if (firstIndex!=-1 && lastIndex!=-1) {
            System.out.println("First occurrence of "+target+" is at index: "+firstIndex);
            System.out.println("Last occurrence of "+target+" is at index: "+lastIndex);
        } else {
            System.out.println("Target "+target+" not found in the array");
        }
    }
}