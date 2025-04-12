import java.io.*;
import java.util.ArrayList;
import java.util.List;
public class PerformanceAndFileReaderDemo {
    public static void main(String[] args) {
        compareStringConcatenation();
        String filePath="largefile.txt";
        countWordsUsingFileReader(filePath);
        countWordsUsingInputStreamReader(filePath);
    }
    private static void compareStringConcatenation() {
        int iterations=1000000;
        String sample="hello";
        long startBuilder=System.nanoTime();
        StringBuilder sb=new StringBuilder();
        for (int i = 0; i < iterations; i++) {
            sb.append(sample);
        }
        long endBuilder=System.nanoTime();
        System.out.println("StringBuilder time: "+(endBuilder-startBuilder)/1000000.0+" ms");
        long startBuffer=System.nanoTime();
        StringBuffer stringBuffer=new StringBuffer();
        for (int i = 0; i < iterations; i++) {
            stringBuffer.append(sample);
        }
        long endBuffer=System.nanoTime();
        System.out.println("StringBuffer time: "+(endBuffer-startBuffer)/1000000.0+" ms");
    }
    private static void countWordsUsingFileReader(String filePath) {
        long startTime=System.nanoTime();
        int wordCount=0;
        try (BufferedReader reader=new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line=reader.readLine())!=null) {
                String[] words=line.trim().split("\\s+");
                if (!line.isEmpty()) {
                    wordCount+=words.length;
                }
            }
        } catch (Exception e) {
            System.out.println("Error reading file with FileReader");
            e.printStackTrace();
            return;
        }
        long endTime = System.nanoTime();
        System.out.println("Word count using FileReader: " + wordCount);
        System.out.println("Time taken with FileReader: " + (endTime - startTime) / 1_000_000.0 + " ms");
    }
    private static void countWordsUsingInputStreamReader(String filePath) {
        long startTime=System.nanoTime();
        int wordCount=0;
        try (
            FileInputStream fis=new FileInputStream(filePath);
            InputStreamReader isr=new InputStreamReader(fis,"UTF-8");
            BufferedReader reader=new BufferedReader(isr)
        ){
            String line;
            while ((line=reader.readLine())!=null) {
                String[] words=line.trim().split("\\s+");
                if (!line.isEmpty()) {
                    wordCount+=words.length;
                }
            }
        } catch (Exception e) {
            System.out.println("Error reading file with InputStreamReader");
            e.printStackTrace();
            return;
        }
        long endTime=System.nanoTime();
        System.out.println("Word count using InputStreamReader: "+wordCount);
        System.out.println("Time taken with InputStreamReader: "+(endTime-startTime)/1000000.0+" ms");
    }
}