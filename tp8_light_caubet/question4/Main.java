package question4;
import java.io.*;

public class Main
{
    public static void Main ()throws IOException
    {
        InputStream is = new PushbackInputStream(new UpperCaseInputStream(new BufferedInputStream( new FileInputStream(new File("README.TXT")))));   // déclaration à décorer ....
        //PushbackInputStream is = new UpperCaseInputStream(new BufferedInputStream( new FileInputStream(new File("README.TXT"))));   // déclaration à décorer ....
        //new UpperCaseInputStream(new BufferedInputStream(
        ((PushbackInputStream)is).unread(2);
        
        System.out.println((char)is.read());
        //System.out.println(is.unread(1));
        //is.unread(1);
        System.out.println((char)is.read());
    }

}
