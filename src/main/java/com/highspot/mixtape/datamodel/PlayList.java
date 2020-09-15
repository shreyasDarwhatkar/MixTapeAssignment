package com.highspot.mixtape.datamodel;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
/*
 *Data model for playlist entity
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PlayList {
  private int id;

  @JsonProperty("user_id")
  private int userID;

  @JsonProperty("song_ids")
  private List<Integer> songIds;
}
