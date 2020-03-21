package de.wirvsvirus.trackyourbed.dto.response;

import java.util.UUID;
import javax.validation.Constraint;

public class BedStateDto {

  private UUID id;

  //@Constraint(name = "OCCUPIED")
  private String name;

  //|| name = "FREE" || name = "MAYBE"
   //   || name = "OUT_OF_ORDER" || name = "UNKNOWN")

}
