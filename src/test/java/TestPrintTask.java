import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

   public class TestPrintTask {

      @Test
      public void test() {

         String newLine="";
         if (System.getProperty("os.name").startsWith("Windows")) {
            newLine="\r\n";
         } else {
            newLine="\n";
         }

         String sideA="";
         String sideB="";
         String angle="";

         if (System.getProperty("os.name").startsWith("Windows")) {
            sideA="5,467892";
            sideB="6,567345";
            angle="4,4543454";
         } else {
            sideA="5.467892";
            sideB="6.567345";
            angle="4.4543454";
         }

         InputStream stdin = System.in;
         String input=sideA+newLine+sideB+newLine+angle+newLine;

         System.setIn(new ByteArrayInputStream(input.getBytes()));
         ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
         PrintStream ps = new PrintStream(byteArrayOutputStream);
         PrintStream stdout = System.out;
         System.setOut(ps);

         MyTriangle.main(new String[0]);

         System.setIn(stdin);
         System.setOut(stdout);

         String expected="Haromszog teruletenek meghatarozasa!"+newLine;
         String actual=byteArrayOutputStream.toString();

         System.out.println("Elvart:"+newLine+expected);
         System.out.println("Aktualis:"+newLine+actual);

         boolean found=actual.contains(expected);

         Assertions.assertTrue(found,"Nem irta ki a feladatot!");
      }
   }
