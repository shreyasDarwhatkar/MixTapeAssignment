package com.highspot.mixtape.processors;

import com.highspot.mixtape.datamodel.MixTape;
import com.highspot.mixtape.datamodel.MixTapeRepository;
import com.highspot.mixtape.datamodel.PlayList;
import com.highspot.mixtape.datamodel.Song;
import com.highspot.mixtape.datamodel.User;

import java.util.function.Function;
import java.util.stream.Collectors;

/*
* This class contains all the helper methods for MixTape repository.
* */

public class MixTapeRepositoryUtil {
  private MixTapeRepositoryUtil() {}

  /**
   * This method is used for initializing the map of users, songs and playlists.
   *
   * @param mixTape Initializes the map of users, songs and playlist
   * @return MixTapeRepositorySearch which is used for faster lookup of users, songs and playlist by
   *     ID.
   */
  public static MixTapeRepository initializeMixTape(MixTape mixTape) {
    MixTapeRepository mixTapeRepository = new MixTapeRepository();

    mixTapeRepository.setUsersSearchMap(
        mixTape.getUsers().stream().collect(Collectors.toMap(User::getId, Function.identity())));

    mixTapeRepository.setSongSearchMap(
        mixTape.getSongs().stream().collect(Collectors.toMap(Song::getId, Function.identity())));

    mixTapeRepository.setPlaylistSearchMap(
        mixTape.getPlaylists().stream()
            .collect(Collectors.toMap(PlayList::getId, Function.identity())));

    return mixTapeRepository;
  }
}
