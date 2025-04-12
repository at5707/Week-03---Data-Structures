public class PerformanceComparison {
    public static void main(String[] args) {
        int iterations=1000000;
        String word="hello";
        long startTimeBuffer=System.nanoTime();
        StringBuffer stringBuffer=new StringBuffer();
        for (int i = 0; i < iterations; i++) {
            stringBuffer.append(word);
        }
        long endTimeBuffer=System.nanoTime();
        long durationBuffer=endTimeBuffer-startTimeBuffer;
        long startTimeBuilder=System.nanoTime();
        StringBuilder stringBuilder=new StringBuilder();
        for (int i = 0; i < iterations; i++) {
            stringBuilder.append(word);
        }
        long endTimeBuilder=System.nanoTime();
        long durationBuilder=endTimeBuilder-startTimeBuilder;
        System.out.println("Time taken by StringBuffer: "+durationBuffer);
        System.out.println("Time taken by StringBuilder: "+durationBuilder);
    }
}