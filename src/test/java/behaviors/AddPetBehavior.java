package behaviors;

import static com.consol.citrus.http.actions.HttpActionBuilder.http;

import com.consol.citrus.TestActionRunner;
import com.consol.citrus.TestBehavior;
import com.consol.citrus.context.TestContext;
import com.consol.citrus.message.builder.ObjectMappingPayloadBuilder;
import org.springframework.http.HttpStatus;
import pojo.pet.AddPet;

public class AddPetBehavior implements TestBehavior {

  private TestContext context;
  private AddPet addPet;

  public AddPetBehavior(TestContext context, AddPet addPet) {
    this.context = context;
    this.addPet = addPet;
  }

  @Override
  public void apply(TestActionRunner testActionRunner) {
    testActionRunner.run(http()
            .client("restPetStore")
            .send()
            .post("pet")
            .message()
            .type("application/json")
            .body(new ObjectMappingPayloadBuilder(addPet, "objectMapper")));

    testActionRunner.run(http()
            .client("restPetStore")
            .receive()
            .response(HttpStatus.OK)
            .message()
            .type("application/json")
            .body(new ObjectMappingPayloadBuilder(addPet, "objectMapper"))
    );
  }
}
