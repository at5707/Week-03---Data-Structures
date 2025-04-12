import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class FileReadComparison {
    public static void readWithFileReader(String filePath) throws IOException {
        long start=System.nanoTime();
        try(FileReader fr=new FileReader(filePath)) {
            while (fr.read()!=-1);
        }
        long time=System.nanoTime()-start;
        System.out.printf("FileReader: %6.3f seconds\n", time/1e9);
    }
    public static void readWithInputStreamReader(String filePath) throws IOException {
        long start=System.nanoTime();
        try (InputStreamReader isr=new InputStreamReader(new FileInputStream(filePath))) {
            while (isr.read()!=-1);
        }
        long time=System.nanoTime()-start;
        System.out.printf("InputStreamReader: %6.3f seconds\n",time/1e9);
    }
    public static void main(String[] args) {
        String filePath="largefile.txt";
        System.out.println("Large File Reading Efficiency Comparison\n");
        try {
            readWithFileReader(filePath);
            readWithInputStreamReader(filePath);
        } catch (IOException e) {
            System.out.println("Error reading file: "+e.getMessage());
        }
    }
}