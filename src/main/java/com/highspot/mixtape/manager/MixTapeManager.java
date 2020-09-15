package com.highspot.mixtape.manager;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.highspot.mixtape.Exception.EntityAlreadyPresentException;
import com.highspot.mixtape.Exception.EntityNotPresentException;
import com.highspot.mixtape.datamodel.MixTape;
import com.highspot.mixtape.operations.OperationsList;
import com.highspot.mixtape.datamodel.MixTapeRepository;
import com.highspot.mixtape.processors.MixTapeRepositoryUtil;
import com.highspot.mixtape.processors.Processor;
import picocli.CommandLine;

import java.io.File;
import java.io.IOException;

@CommandLine.Command(
    name = "mixtape-run",
    mixinStandardHelpOptions = true,
    version = "0.1",
    description = "Use for processing the input file and the change file")
public class MixTapeManager implements Runnable {
  @CommandLine.Option(
      names = "--input-file",
      description = "filepath of a json file contains users, songs and playlist",
      defaultValue = "/Volumes/unix/GitWorkplace/MixTapeAssignment/src/main/resources/mixtape.json")
  private File inputFile;

  @CommandLine.Option(
      names = "--changes-file",
      description = "filepath of a json file contains changes for the input json file.",
      defaultValue = "/Volumes/unix/GitWorkplace/MixTapeAssignment/src/main/resources/changes.json")
  private File changesFile;

  @CommandLine.Option(
      names = "--output-file",
      description = "filepath for the output file after changes applied.",
      defaultValue = "/Volumes/unix/GitWorkplace/MixTapeAssignment/src/main/resources/output.json")
  private File outputFile;

  public static void main(String[] args) {
    MixTapeManager mixTapeManager = new MixTapeManager();
    int exitCode = new CommandLine(mixTapeManager).execute(args);
    System.exit(exitCode);
  }

  @Override
  public void run() {

    ObjectMapper objectMapper = new ObjectMapper();
    try {
      MixTape mixTape = objectMapper.readValue(inputFile, MixTape.class);
      System.out.println("*************Intial Mixtape elements**********************");
      System.out.println(objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(mixTape));

      MixTapeRepository mixTapeRepository =
          MixTapeRepositoryUtil.initializeMixTape(mixTape);
      Processor processor = new Processor(mixTapeRepository);

      OperationsList operationsList = objectMapper.readValue(changesFile, OperationsList.class);
      System.out.println("*************Change  file operations**********************");
      System.out.println(
          objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(operationsList));

      MixTape outputFileObj = processor.process(operationsList);

      System.out.println("*************output file operations**********************");
      objectMapper.writerWithDefaultPrettyPrinter().writeValue(outputFile, outputFileObj);
      System.out.println(
          objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(outputFileObj));

    } catch (IOException e) {
      e.printStackTrace();
    } catch (EntityNotPresentException entityNotPresentException) {
      entityNotPresentException.printStackTrace();
    } catch (EntityAlreadyPresentException entityAlreadyPresentException) {
      entityAlreadyPresentException.printStackTrace();
    }
  }
}
