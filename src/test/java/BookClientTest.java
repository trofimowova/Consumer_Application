

import com.fasterxml.jackson.databind.JsonNode;
import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.stubrunner.spring.AutoConfigureStubRunner;
import org.springframework.cloud.contract.stubrunner.spring.StubRunnerProperties;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertTrue;


@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes= BookClientTestConfig.class)
@SpringBootTest
@AutoConfigureStubRunner(
        ids = {"com.example:Sbb:+:stubs:8090"},
        stubsMode = StubRunnerProperties.StubsMode.LOCAL
)

class BookClientTest {

    @Autowired private BookClient bookClient;

    @Test
    void testShouldMatchDAtaGroup() {
       // stubFinder.trigger("Sbb");

        JsonNode result = bookClient.getDataGroups();

        assertTrue(result.isArray());

        JsonNode firstGroup = result.get(0);

        assertTrue(firstGroup.has("id"));
        //assertTrue(firstBook.get("id").isNumber());

    }
}
