public class StringConcatComparison {
    public static void testConcatPerformance(int count) {
        String text="a";
        System.out.printf("\nConcatenating %,d strings:\n",count);
        if (count<=10000) {
            long start=System.nanoTime();
            String s="";
            for (int i = 0; i < count; i++) {
                s+=text;
            }
            long time=System.nanoTime()-start;
            System.out.printf("String: %6.3f ms\n",time/1e6);
        } else {
            System.out.println("String: Skipped (Too Slow)");
        }
        long start=System.nanoTime();
        StringBuilder sb=new StringBuilder();
        for (int i = 0; i < count; i++) {
            sb.append(text);
        }
        long time=System.nanoTime()-start;
        System.out.printf("StringBuilder: %6.3f ms\n", time/1e6);
        start=System.nanoTime();
        StringBuffer sbf=new StringBuffer();
        for (int i = 0; i < count; i++) {
            sbf.append(text);
        }
        time=System.nanoTime()-start;
        System.out.printf("StringBuffer: %6.3f ms\n", time/1e6);
    }
    public static void main(String[] args) {
        System.out.println("String Concatenation Performance Comparison");
        int[] counts={1000,10000,1000000};
        for (int count : counts) {
            testConcatPerformance(count);
        }
    }
}