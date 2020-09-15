package com.highspot.mixtape.operations;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.highspot.mixtape.Exception.EntityNotPresentException;
import com.highspot.mixtape.datamodel.PlayList;
import com.highspot.mixtape.datamodel.MixTapeRepository;
import lombok.Data;
import lombok.EqualsAndHashCode;

/*
 * This class is responsible for performing removing the existing playlist.
 * */
@Data
@EqualsAndHashCode(callSuper=false)
@JsonTypeName("remove_playlist")
public class RemovePlayList extends AbstractOperation {
  @JsonProperty("playlist_id")
  private int playListId;

  /**
   * @param mixTapeRepository: Object of mixTapeRepositorySearch which contains the
   *     directories of users, songs and playlist
   * @throws EntityNotPresentException thrown when the playlist is not present
   */
  @Override
  public void execute(MixTapeRepository mixTapeRepository)
      throws EntityNotPresentException {
    final PlayList playList = mixTapeRepository.getPlaylistSearchMap().get(playListId);

    if (playList == null)
      throw new EntityNotPresentException(
          String.format("%s: PlayList not present in repository", getClass()));

    mixTapeRepository.getPlaylistSearchMap().remove(playList.getId());
  }
}
