package hyena.test.checkedException2Runtime;

import io.netty.channel.ChannelException;
import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class Exception2Runtime {



    @Test
    public void test() throws Exception{
        File file = new File("noExistFile");
        try {
            InputStream inputStream = new FileInputStream(file);
        } catch (FileNotFoundException e) {
//            e.printStackTrace();
            throw new Exception();
        }
    }

    public void invoke() throws Exception{
        test();
    }
}
