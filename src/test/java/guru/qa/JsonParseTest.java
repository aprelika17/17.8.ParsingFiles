package guru.qa;

import com.fasterxml.jackson.databind.ObjectMapper;
import guru.qa.model.DboModel;
import org.junit.jupiter.api.Test;

import java.io.InputStream;
import java.io.InputStreamReader;

import static org.assertj.core.api.Assertions.assertThat;

public class JsonParseTest {

    ObjectMapper objectMapper = new ObjectMapper();
    ClassLoader cl = JsonParseTest.class.getClassLoader();

    @Test
    void jsonParseTest() throws Exception {


        try (
                InputStream resource = cl.getResourceAsStream("files/jsonExample.json");
                InputStreamReader reader = new InputStreamReader(resource)
        ) {
            DboModel jsonObject = objectMapper.readValue(reader, DboModel.class);
            assertThat(jsonObject.id).isEqualTo(145);
            assertThat(jsonObject.isMarried).isTrue();
            assertThat(jsonObject.data.patronymic).isNull();
            assertThat(jsonObject.data.gender).isEqualTo("MALE");
            assertThat(jsonObject.data.hobby).contains("Shess");
        }
    }

}