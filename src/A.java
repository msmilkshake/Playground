
import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

public class A {
    public static void main(String[] args) {
        try{
            FileOutputStream outputStream = new FileOutputStream("test.txt",true);
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(outputStream, "UTF-8");
            BufferedWriter bufferedWriter = new BufferedWriter(outputStreamWriter);
            
            bufferedWriter.write("एक समय में बड़ा नाम था। पूरे देश में तालाब बनते थे");
            bufferedWriter.newLine();
            bufferedWriter.write("Hẹn gặp lại!");
            
            bufferedWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        
    }
}
