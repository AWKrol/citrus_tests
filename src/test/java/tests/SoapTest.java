package tests;

import static com.consol.citrus.ws.actions.SoapActionBuilder.soap;

import com.consol.citrus.annotations.CitrusTest;
import com.consol.citrus.context.TestContext;
import com.consol.citrus.testng.spring.TestNGCitrusSpringSupport;
import com.dataaccess.webservicesserver.NumberToDollars;
import com.dataaccess.webservicesserver.NumberToDollarsResponse;
import features.PojoToXML;
import org.testng.annotations.Test;
import java.math.BigDecimal;

public class SoapTest extends TestNGCitrusSpringSupport {

  private TestContext context;

  @Test(description = "SOAP")
  @CitrusTest
  public void getTestActions() {
    context = citrus.getCitrusContext().createTestContext();

    PojoToXML<Class<NumberToDollars>> convRequest = new PojoToXML<>();
    PojoToXML<Class<NumberToDollarsResponse>> convResponse = new PojoToXML<>();

    run(soap()
        .client("soapHelper")
        .send()
            .message()
            .body(convRequest.convert(NumberToDollars.class, getNumberToDollarsRequest(), "http://www.dataaccess.com/webservicesserver/", "NumberToDollars"))
    );

    run(soap()
            .client("soapHelper")
            .receive()
            .message()
            .body(convResponse.convert(NumberToDollarsResponse.class, getNumberToDollarsResponse(), "http://www.dataaccess.com/webservicesserver/", "NumberToDollarsResponse"))
    );
  }

  private NumberToDollars getNumberToDollarsRequest() {
    NumberToDollars numberToDollars = new NumberToDollars();
    numberToDollars.setDNum(new BigDecimal("5"));
    return numberToDollars;
  }

  private NumberToDollarsResponse getNumberToDollarsResponse() {
    NumberToDollarsResponse numberToDollarsResponse = new NumberToDollarsResponse();
    numberToDollarsResponse.setNumberToDollarsResult("five dollars");
    return numberToDollarsResponse;
  }

}
