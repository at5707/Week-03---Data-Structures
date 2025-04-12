import java.io.*;

public class ReadBinaryAsText {
    public static void main(String[] args) {
        String filePath="example.txt";
        try {
            FileInputStream fis=new FileInputStream(filePath);
            InputStreamReader isr=new InputStreamReader(fis,"UTF-8");
            BufferedReader br=new BufferedReader(isr);
            String line;
            while ((line=br.readLine())!=null) {
                System.out.println(line);
            }
            br.close();
            isr.close();
            fis.close();
        } catch (UnsupportedEncodingException e) {
            System.out.println("The character encoding is not supported");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("An error occurred while reading the file");
            e.printStackTrace();
        }
    }
}