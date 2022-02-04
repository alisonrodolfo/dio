package checkout;

import checkout.setup.ProstgreSQL;
import com.hatanaka.ecommerce.checkout.CheckoutApplication;
import io.cucumber.spring.CucumberContextConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.wiremock.AutoConfigureWireMock;
import org.springframework.test.context.ActiveProfiles;

/**
 * @author https://github.com/alisonrodolfo
 */
@ActiveProfiles("test")
@CucumberContextConfiguration
@SpringBootTest(classes = {
        CheckoutApplication.class,
        ProstgreSQL.class
}, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWireMock(port = 8888)
public class SpringIntegrationTest {
}