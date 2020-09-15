package com.highspot.mixtape.operations;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.highspot.mixtape.Exception.EntityAlreadyPresentException;
import com.highspot.mixtape.Exception.EntityNotPresentException;
import com.highspot.mixtape.datamodel.MixTapeRepository;
import lombok.Data;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonTypeInfo(
    use = JsonTypeInfo.Id.NAME,
    include = JsonTypeInfo.As.PROPERTY,
    property = "operation_type")
@JsonSubTypes({
  @JsonSubTypes.Type(value = CreatePlayList.class, name = "create_playlist"),
  @JsonSubTypes.Type(value = AddSong.class, name = "add_song"),
  @JsonSubTypes.Type(value = RemovePlayList.class, name = "remove_playlist")
})
@Data
public abstract class AbstractOperation {
  public abstract void execute(MixTapeRepository mixTapeRepository)
      throws EntityNotPresentException, EntityAlreadyPresentException;
}
