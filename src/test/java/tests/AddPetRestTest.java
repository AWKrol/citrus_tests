package tests;

import behaviors.AddPetBehavior;
import behaviors.DeletePetBehavior;
import behaviors.GetPetBehavior;
import com.consol.citrus.annotations.CitrusTest;
import com.consol.citrus.context.TestContext;
import com.consol.citrus.testng.spring.TestNGCitrusSpringSupport;
import com.github.javafaker.Faker;
import org.testng.annotations.Test;
import pojo.pet.AddPet;
import pojo.pet.Category;
import pojo.pet.Tags;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class AddPetRestTest extends TestNGCitrusSpringSupport {

  private TestContext context;

  @Test(description = "Успешное добавление питомца")
  @CitrusTest
  public void addPetOk() {
    Faker faker = new Faker();
    context = citrus.getCitrusContext().createTestContext();
    int id = faker.number().numberBetween(0, 1000000);
    String name = faker.dog().name();
    int idCategory = faker.number().numberBetween(1, 10);
    String nameCategory = "dogs";
    List<String> photoUrls = Collections.singletonList("pathToPhoto");
    String status = "available";
    int idTags = faker.number().numberBetween(1, 10000);
    String nameTags = "tagName";
    List<Tags> tags = Arrays.asList(new Tags[] {Tags
            .builder().id(idTags)
            .name(nameTags).build()});

    AddPet addPet = AddPet
            .builder()
            .id(id)
            .category(Category.builder()
                    .id(idCategory)
                    .name(nameCategory)
                    .build())
            .name(name)
            .photoUrls(photoUrls)
            .tags(tags)
            .status(status)
            .build();

    run(applyBehavior(new AddPetBehavior(context, addPet)));
    run(applyBehavior(new GetPetBehavior(context, addPet)));
    run(applyBehavior(new DeletePetBehavior(context, id)));
  }
}
