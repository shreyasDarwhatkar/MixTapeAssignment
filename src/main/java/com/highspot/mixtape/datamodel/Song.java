package com.highspot.mixtape.datamodel;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/*
 *Data Model for song Entity
 */

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Song {
  private int id;
  private String artist;
  private String title;
}
