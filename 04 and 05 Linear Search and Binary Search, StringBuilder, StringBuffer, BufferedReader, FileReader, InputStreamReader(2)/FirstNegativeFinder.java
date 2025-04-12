public class FirstNegativeFinder {
    public static int findFirstNegative(int[] array) {
        for (int i = 0; i < array.length; i++) {
            if (array[i]<0) {
                return i;
            }
        }
        return -1;
    }
    public static void main(String[] args) {
        int[] numbers={5,10,0,7,-3,8,-1};
        int index=findFirstNegative(numbers);
        if (index!=-1) {
            System.out.println("First negative number found at index: "+index);
            System.out.println("Value: "+numbers[index]);
        } else {
            System.out.println("No negative numbers found in the array.");
        }
    }
}