package pojo.pet;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Tags {
  private String name;
  private int id;

}
