package hyena.nio.buffer;

import org.junit.Test;

import java.nio.Buffer;
import java.nio.IntBuffer;

/**
 * CharBuffer ByteBuffer
 * ShortBuffer IntBuffer
 * FloatBuffer DoubleBuffer
 * LongBuffer
 * 这些类都是public级别的，也就是说提供给用户的使用的接口。
 * <p>
 * DirectIntBufferRU
 * DirectIntBufferL
 * DirectBufferRS
 * 都是proteced级别的，用户使用不了
 */
public class BufferTest {


    public static final int _1MB = 1024 * 1024;

    // protect instance
    private BufferTest() {

    }


    @Test
    public void test() {

        // allocate 1MB memeory
        Buffer buffer = IntBuffer.allocate(_1MB);


    }


}
