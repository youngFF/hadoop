package avrotest;

import com.yang.test.bean.User;
import org.apache.avro.file.DataFileWriter;
import org.apache.avro.io.DatumWriter;
import org.apache.avro.specific.SpecificDatumWriter;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

public class AvroTest {


    @Test
    public void avroSerializable() throws IOException {
        User user = new User();
        user.newBuilder().setAge(4)
                .setHobby("skate")
                .setName("hyena");

        DatumWriter<User> dataUserDatumWriter = new SpecificDatumWriter<>(User.class);
        DataFileWriter<User> dataFileWriter = new DataFileWriter<>(dataUserDatumWriter);
        dataFileWriter.create(user.getSchema(), new File("/Users/hyena/User.schema"));

        dataFileWriter.close();
    }


    @Test
    public void avroDeSerializable() {

    }

}
