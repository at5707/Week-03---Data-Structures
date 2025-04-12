import java.io.*;
public class ConsoleToFileWriter {
    public static void main(String[] args) {
        String filePath="output.txt";
        try {
            InputStreamReader isr=new InputStreamReader(System.in);
            BufferedReader reader=new BufferedReader(isr);
            FileWriter writer=new FileWriter(filePath);
            System.out.println("Enter text to write to the file (type 'exit' to finish):");
            String line;
            while (!(line=reader.readLine()).equalsIgnoreCase("exit")) {
                writer.write(line);
                writer.write(System.lineSeparator());
            }
            writer.close();
            reader.close();
            isr.close();
            System.out.println("Data successfully written to "+filePath);
        } catch (IOException e) {
            System.out.println("An error occurred while reading input or writing to the file.");
            e.printStackTrace();
        }
    }
}