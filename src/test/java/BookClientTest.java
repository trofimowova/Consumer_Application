

import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.cloud.contract.stubrunner.StubFinder;
import org.testng.annotations.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.stubrunner.spring.AutoConfigureStubRunner;
import org.springframework.cloud.contract.stubrunner.spring.StubRunnerProperties;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@AutoConfigureStubRunner(
        ids = {"com.example:Sbb:+:stubs:8080"},
        stubsMode = StubRunnerProperties.StubsMode.LOCAL
)
class BookClientTest {

    @Autowired
    private BookClient deus;
    @Autowired
    private StubFinder stubFinder;

    @Test
    void testContractToBookStoreServer() {
        stubFinder.trigger("Sbb");

        JsonNode result = deus.getAllAvailableBooks();

        assertTrue(result.isArray());

        JsonNode firstBook = result.get(0);

        assertTrue(firstBook.has("id"));
        assertTrue(firstBook.has("title"));
        assertTrue(firstBook.has("isbn"));
        assertTrue(firstBook.get("isbn").isTextual());
        assertTrue(firstBook.get("genre").isTextual());
        assertTrue(firstBook.get("title").isTextual());
    }
}
