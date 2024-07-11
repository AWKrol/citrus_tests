package behaviors;

import static com.consol.citrus.http.actions.HttpActionBuilder.http;

import com.consol.citrus.TestActionRunner;
import com.consol.citrus.TestBehavior;
import com.consol.citrus.context.TestContext;

public class DeletePetBehavior implements TestBehavior {

  private TestContext context;
  private int id;

  public DeletePetBehavior(TestContext context, int id) {
    this.context = context;
    this.id = id;
  }

  @Override
  public void apply(TestActionRunner testActionRunner) {
    testActionRunner.run(http()
        .client("restPetStore")
        .send()
        .delete("pet/" + id)
    );
  }
}
