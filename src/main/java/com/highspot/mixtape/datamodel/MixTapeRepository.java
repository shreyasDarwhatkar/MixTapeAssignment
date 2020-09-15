package com.highspot.mixtape.datamodel;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

/** This Class maintains the mapping of users, songs and playlist to their respective id's. */
@Data
@NoArgsConstructor
public class MixTapeRepository {
  private static MixTapeRepository mixTapeRepository;
  private Map<Integer, User> usersSearchMap;
  private Map<Integer, Song> songSearchMap;
  private Map<Integer, PlayList> playlistSearchMap;
}
