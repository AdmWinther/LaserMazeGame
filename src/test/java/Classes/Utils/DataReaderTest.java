package Classes.Utils;

import junit.framework.TestCase;

import javax.xml.crypto.Data;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class DataReaderTest extends TestCase {

    public void testRead(){
        String data = DataReader.Read("./src/test/java/Classes/Utils/DataReaderTest.txt");
        assertEquals("hello my friend.", data);
    }
    
}
