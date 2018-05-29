package hyena.avro;

import org.apache.avro.file.DataFileWriter;
import org.apache.avro.io.DatumWriter;
import org.apache.avro.specific.SpecificDatumWriter;

public class AvroTest {



    public static void main(String[] args) {

        Object obj = new Object() ;
        DatumWriter<Object> objectDatumWriter = new SpecificDatumWriter<>(Object.class);
        DataFileWriter<Object> dataFileWriter = new DataFileWriter<>(objectDatumWriter);
    }
}
