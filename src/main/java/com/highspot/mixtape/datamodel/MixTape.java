package com.highspot.mixtape.datamodel;

import lombok.Data;

import java.util.List;

/*
 * Data model for the mixtape input file
 * */
@Data
public class MixTape {
  List<User> users;
  List<Song> songs;
  List<PlayList> playlists;
}
