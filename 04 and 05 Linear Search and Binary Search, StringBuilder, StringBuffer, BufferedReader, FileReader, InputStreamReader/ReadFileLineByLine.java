import java.io.BufferedReader;
import java.io.FileReader;

public class ReadFileLineByLine {
    public static void main(String[] args) {
        String filePath="example.txt";
        try {
            FileReader fr=new FileReader(filePath);
            BufferedReader br=new BufferedReader(fr);
            String line;
            while ((line=br.readLine())!=null) {
                System.out.println(line);
            }
            br.close();
            fr.close();
        } catch (Exception e) {
            System.out.println("An error occure while reading the file");
            e.printStackTrace();
        }
    }
}
