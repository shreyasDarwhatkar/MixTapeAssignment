package com.highspot.mixtape.operations;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.highspot.mixtape.datamodel.MixTapeRepository;
import com.highspot.mixtape.datamodel.PlayList;
import com.highspot.mixtape.datamodel.Song;
import com.highspot.mixtape.exception.EntityAlreadyPresentException;
import com.highspot.mixtape.exception.EntityNotPresentException;
import lombok.Data;
import lombok.EqualsAndHashCode;

/** This class is mainly responsible for adding the existing song to a existing playlist */
@Data
@EqualsAndHashCode(callSuper=false)
@JsonTypeName("add_song")
public class AddSong extends AbstractOperation {
  @JsonProperty("song_id")
  private int songId;

  @JsonProperty("playlist_id")
  private int playlistId;

  /**
   * @param mixTapeRepository :Object of mixTapeRepositorySearch which contains the
   *     directories of users songs and playlist
   * @throws EntityNotPresentException
   * @throws EntityAlreadyPresentException
   */
  @Override
  public void execute(MixTapeRepository mixTapeRepository)
      throws EntityNotPresentException, EntityAlreadyPresentException {
    Song song = mixTapeRepository.getSongSearchMap().get(songId);
    // Checking if does exist in the map
    if (song == null) {
      throw new EntityNotPresentException(
          String.format("%s : Song ID not present in repository", getClass()));
    }

    // Check if playlist exist in the mixtape
    PlayList playList = mixTapeRepository.getPlaylistSearchMap().get(playlistId);
    if (playList == null)
      throw new EntityNotPresentException(
          String.format("%s : PlayList not present in repository", getClass()));

    // check if playlist does already contain the song
    if (playList.getSongIds().contains(songId))
      throw new EntityAlreadyPresentException(
          String.format("%s : Song already present in playlist", getClass()));

    playList.getSongIds().add(songId);
  }
}
