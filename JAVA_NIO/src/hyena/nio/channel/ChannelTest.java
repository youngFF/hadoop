package hyena.nio.channel;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/*
 * channel
 *   FileChannel
 *   SocketChannel
 *   ServerSocketChannel
 *   DatagramChannel
 *
 *   A CHANNEL represents an open connection to an entry such
 *   as a hard device , a network socket , or a program component that is
 *   capable of performing I/O operations
 **/
public class ChannelTest {

    public static final int _1MB = 1024 * 1024 ;

    public static void main(String[] args) throws IOException {
        File file = new File("/home/hyena/myfile");
        FileInputStream fileInputStream = new FileInputStream(file);
        FileChannel fileChannel = fileInputStream.getChannel();

        ByteBuffer byteBuffer ;
        try {
            System.out.println(System.currentTimeMillis());
            // allocate _1MB memory
            byteBuffer = ByteBuffer.allocate(_1MB);
            System.out.println(System.currentTimeMillis());
        } catch (OutOfMemoryError error) {
            // throw runtimeException
            throw new RuntimeException("convert OutOfMeoryError to RuntimeException") ;
        }

        int length = fileChannel.read(byteBuffer);

        System.out.println(length);

    }
}
