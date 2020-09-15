package com.highspot.mixtape.datamodel;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/*
 * Data model for user entity
 * */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
  private int id;
  private String name;
}
