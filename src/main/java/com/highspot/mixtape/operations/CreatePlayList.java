package com.highspot.mixtape.operations;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.highspot.mixtape.Exception.EntityAlreadyPresentException;
import com.highspot.mixtape.Exception.EntityNotPresentException;
import com.highspot.mixtape.datamodel.PlayList;
import com.highspot.mixtape.datamodel.User;
import com.highspot.mixtape.datamodel.MixTapeRepository;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/*
 * This Class is responsible for creating a new playlist with existing songs.
 *
 * */
@Data
@EqualsAndHashCode(callSuper=false)
@JsonTypeName("create_playlist")
public class CreatePlayList extends AbstractOperation {
  @JsonProperty("playlist")
  PlayList playlist;

  @Override
  public void execute(MixTapeRepository mixTapeRepository)
          throws EntityNotPresentException, EntityAlreadyPresentException {
    final User user = mixTapeRepository.getUsersSearchMap().get(playlist.getUserID());


    //check if the playList is already present in the mixtape
    if(mixTapeRepository.getPlaylistSearchMap().containsKey(playlist.getId())){
      throw new EntityAlreadyPresentException(String.format("%s: PlayList Already Present", getClass()));
    }

    // check if the user id is provided and it is already present
    if (user == null) {
      throw new EntityNotPresentException(String.format("%s: UserId not provided", getClass()));
    }
    List<Integer> songsList = playlist.getSongIds();

    // check if the  songs list is not empty
    if (songsList == null || songsList.size() <= 0) {
      throw new EntityNotPresentException(String.format("%s: SongList not provided", getClass()));
    }

    // check if all the songsID are present in the repository
    for (int songId : songsList) {
      if (!mixTapeRepository.getSongSearchMap().containsKey(songId)) {
        throw new EntityNotPresentException(
            String.format("%s : Song ID not present in repository", getClass()));
      }
    }



    PlayList newPlayList = new PlayList();
    newPlayList.setId(playlist.getId());
    newPlayList.setSongIds(songsList);
    newPlayList.setUserID(playlist.getUserID());
    mixTapeRepository.getPlaylistSearchMap().put(playlist.getId(), newPlayList);
  }
}
