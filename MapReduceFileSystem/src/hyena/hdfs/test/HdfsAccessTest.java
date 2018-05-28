package hyena.hdfs.test;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.AbstractFileSystem;
import org.apache.hadoop.fs.Hdfs;
import org.apache.hadoop.fs.Path;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class HdfsAccessTest {


    public static void main(String[] args) throws URISyntaxException, IOException {
        String path = "hdfs://localhost:9000";
        URI uri = new URI(path);
        Configuration configuration = new Configuration();
        AbstractFileSystem hdfs = Hdfs.get(uri, configuration);
        System.out.println(hdfs);
        // 这样是可以访问Hdfs的
        System.out.println(hdfs.delete(new Path("/user/hyena/output"), true));
    }


}
