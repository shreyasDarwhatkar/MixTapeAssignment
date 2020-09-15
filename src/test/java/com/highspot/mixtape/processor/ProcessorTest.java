package com.highspot.mixtape.processor;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.highspot.mixtape.datamodel.MixTape;
import com.highspot.mixtape.datamodel.MixTapeRepository;
import com.highspot.mixtape.datamodel.PlayList;
import com.highspot.mixtape.datamodel.Song;
import com.highspot.mixtape.datamodel.User;
import com.highspot.mixtape.exception.EntityAlreadyPresentException;
import com.highspot.mixtape.exception.EntityNotPresentException;
import com.highspot.mixtape.operations.OperationsList;
import com.highspot.mixtape.processors.MixTapeRepositoryUtil;
import com.highspot.mixtape.processors.Processor;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Objects;

import static junit.framework.TestCase.assertEquals;

public class ProcessorTest {
  @Mock MixTape mixTape;

  @Mock MixTapeRepository mixTapeRepository;

  @Mock OperationsList operationsList;

  @Mock Processor processor;

  private MixTape readMixTapeJson() {
    ObjectMapper objectMapper = new ObjectMapper();
    MixTape mixtape = new MixTape();
    User user1 = new User(1, "First User");
    User user2 = new User(2, "Second User");
    Song song1 = new Song(1, "artist1", "title1");
    Song song2 = new Song(2, "artist2", "title2");
    Song song3 = new Song(3, "artist3", "title3");
    PlayList playList1 = new PlayList(1, 1, Arrays.asList(1, 3));
    PlayList playList2 = new PlayList(2, 2, Arrays.asList(1, 2));
    mixtape.setUsers(Arrays.asList(user1, user2));
    mixtape.setSongs(Arrays.asList(song1, song2, song3));
    mixtape.setPlaylists(Arrays.asList(playList1, playList2));
    return mixtape;
  }

  private OperationsList readChangesFileJson(String fileName)
      throws URISyntaxException, IOException {

    ClassLoader classLoader = getClass().getClassLoader();
    URI resource = Objects.requireNonNull(classLoader.getResource(fileName)).toURI();
    String contents = new String(Files.readAllBytes(Paths.get(resource)));
    ObjectMapper objectMapper = new ObjectMapper();
    return objectMapper.readValue(contents, OperationsList.class);
  }

  @Before
  public void setup() {
    mixTape = readMixTapeJson();
    mixTapeRepository = MixTapeRepositoryUtil.initializeMixTape(mixTape);
  }

  @Test
  public void processCreatePlayListSuccess()
      throws EntityAlreadyPresentException, EntityNotPresentException, IOException,
          URISyntaxException {
    processor = new Processor(mixTapeRepository);
    operationsList = readChangesFileJson("createPlayList.json");
    mixTape = processor.process(operationsList);
    assertEquals(mixTape.getPlaylists().size(), 3);
  }

 @Test
 public void processRemovePlayListSuccess()
         throws EntityAlreadyPresentException, EntityNotPresentException, IOException,
         URISyntaxException {
   processor = new Processor(mixTapeRepository);
   operationsList = readChangesFileJson("removePlayList.json");
   mixTape = processor.process(operationsList);
   assertEquals(mixTape.getPlaylists().size(), 1);
 }

  @Test(expected = EntityAlreadyPresentException.class)
  public void processCreatePlayListFailed()
          throws EntityAlreadyPresentException, EntityNotPresentException, IOException,
          URISyntaxException {
    processor = new Processor(mixTapeRepository);
    operationsList = readChangesFileJson("createPlayListFailed.json");
    mixTape = processor.process(operationsList);
  }

}