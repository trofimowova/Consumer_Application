

import com.fasterxml.jackson.databind.JsonNode;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.stubrunner.spring.AutoConfigureStubRunner;
import org.springframework.cloud.contract.stubrunner.spring.StubRunnerProperties;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import java.util.concurrent.ThreadLocalRandom;

import static com.github.jknack.handlebars.helper.ConditionalHelpers.or;
import static org.junit.jupiter.api.Assertions.assertTrue;

//@SpringJUnitConfig    //   or @ExtendWith(SpringExtension.class)

@ContextConfiguration(classes= DGClientTestConfig.class)
@SpringBootTest   // Scanning goes up forward  in packages.
@AutoConfigureStubRunner(
        ids = {"com.example:Sbb:+:stubs:8090"},
        stubsMode = StubRunnerProperties.StubsMode.LOCAL
)

class DGClientTest {

    @Autowired private DGClient DGClient;  //Injection of bookclient to our test

    @Test
    void testShouldMatchDAtaGroup() {
       // stubFinder.trigger("Sbb");

        JsonNode result = DGClient.getDataGroups();

        assertTrue(result.isArray());

        JsonNode firstGroup = result.get(ThreadLocalRandom.current().nextInt(0,6));

        assertTrue(firstGroup.has("id"));
        assertTrue(firstGroup.has("beschreibung"));
        assertTrue(firstGroup.has("adcDump"));
        assertTrue(firstGroup.has("readyForSmoke"));
        assertTrue(firstGroup.has("url"));
        assertTrue(firstGroup.has("erstellungsZeitpunkt"));
        assertTrue(firstGroup.has("status"));
        assertTrue(firstGroup.has("postProcessorVersion"));
        assertTrue(firstGroup.has("exporterVersion"));
        assertTrue(firstGroup.has("adcTemporal"));
        assertTrue(firstGroup.has("adcBuildNumber"));
        assertTrue(firstGroup.has("latestPlandatenId"));

        //assertTrue((firstGroup.get("id").isInt()));
        System.out.println(firstGroup);


    }
}
