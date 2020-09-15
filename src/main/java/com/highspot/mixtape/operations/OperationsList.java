package com.highspot.mixtape.operations;

import lombok.Data;

import java.util.List;

/*
 * This class contains the list of all the operations from the changes input file.
 * */
@Data
public class OperationsList {
  private List<AbstractOperation> operations;
}
