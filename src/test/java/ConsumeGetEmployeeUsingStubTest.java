import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.cloud.contract.stubrunner.spring.AutoConfigureStubRunner;
import org.springframework.cloud.contract.stubrunner.spring.StubRunnerProperties;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)

@SpringBootTest(

        classes = CreateEmployeeApplication.class,

        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT

)

@AutoConfigureStubRunner(

        ids = {"com.jd.spring:get-employee-test-provider-contract:8180"}

        , stubsMode = StubRunnerProperties.StubsMode.LOCAL)

public class ConsumeGetEmployeeUsingStubTest {


    @LocalServerPort

    private int port;

    @Value("${app.createEmployeeBaseURI:http://localhost}")

    String createEmployeeBaseURI;


    @Value("${app.createEmployeeBasePath:/employee-management/employee}")

    String createEmployeeBasePath;


    @Before
    public void setup() {

        RestAssured.useRelaxedHTTPSValidation();

        RestAssured.baseURI = createEmployeeBaseURI;

        if (RestAssured.baseURI.contains("localhost")) {

            RestAssured.port = port;

        }

    }


    @Test

    public void testShouldCreateNewEmployee() throws Exception {

        // given:

        RequestSpecification request = given()

                .header("Content-Type", "application/json")

                .header("Accept", "application/json")

                .body(

                        "{\"firstName\":\"Jagdish\",\"lastName\":\"Raika\",\"identityCardNo\":\"0123456789\"}");


        // when:

        Response response = given().spec(request)

                .post(createEmployeeBasePath);


        // then:

        assertThat(response.statusCode()).isEqualTo(201);

        assertThat(response.header("Content-Type")).matches("application/json.*");

        System.out.println("testShouldCreateNewEmployee: "+response.getBody().asString());

        // and:

        DocumentContext parsedJson = JsonPath.parse(response.getBody().asString());

        assertThatJson(parsedJson).field("['id']").matches("[1-9][0-9]{0,}");

        assertThatJson(parsedJson).field("['firstName']").matches("Jagdish");

        assertThatJson(parsedJson).field("['lastName']").matches("Raika");

        assertThatJson(parsedJson).field("['identityCardNo']").isEqualTo("0123456789");

        assertThatJson(parsedJson).field("['status']").matches("NEW_EMPLOYEE_CREATED");

    }