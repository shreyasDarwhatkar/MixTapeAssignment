package com.highspot.mixtape.processors;

import com.highspot.mixtape.Exception.EntityAlreadyPresentException;
import com.highspot.mixtape.Exception.EntityNotPresentException;
import com.highspot.mixtape.datamodel.MixTape;
import com.highspot.mixtape.datamodel.MixTapeRepository;
import com.highspot.mixtape.operations.AbstractOperation;
import com.highspot.mixtape.operations.OperationsList;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;

@RequiredArgsConstructor
public class Processor {
  private final MixTapeRepository mixTapeRepository;

  /**
   * @param operationList Contains the list of operations to be performed.
   * @return Returns the updated object of MixTape
   * @throws EntityNotPresentException Thrown when the Entity that needs to be removed or updated is
   *     not present
   * @throws EntityAlreadyPresentException Thrown when Entity is already present in the list.
   */
  public MixTape process(OperationsList operationList)
      throws EntityNotPresentException, EntityAlreadyPresentException {
    for (AbstractOperation operationType : operationList.getOperations()) {
      operationType.execute(mixTapeRepository);
    }
    return updateMixTape();
  }

  /**
   * This method is used for writing the changes to a new MixTape object
   *
   * @return Returns the updates object of MixTape
   */
  public MixTape updateMixTape() {
    MixTape mixTape = new MixTape();
    mixTape.setUsers(new ArrayList<>(this.mixTapeRepository.getUsersSearchMap().values()));
    mixTape.setSongs(new ArrayList<>(this.mixTapeRepository.getSongSearchMap().values()));
    mixTape.setPlaylists(
        new ArrayList<>(this.mixTapeRepository.getPlaylistSearchMap().values()));
    return mixTape;
  }
}
