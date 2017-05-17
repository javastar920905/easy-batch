package com.ouzhx.reader;

import org.easybatch.core.reader.RecordReader;
import org.easybatch.core.record.Record;

/**
 * Created by ouzhx on 2017/5/17.
 */
public class MyRecordReader implements RecordReader {
  /**
   * Open the reader.
   *
   * @throws Exception if an error occurs during reader opening
   */
  @Override
  public void open() throws Exception {

  }

  /**
   * Read next record from the data source.
   *
   * @return the next record from the data source or null if the end of the data source is reached
   * @throws Exception if an error occurs during reading next record
   */
  @Override
  public Record readRecord() throws Exception {
    return null;
  }

  /**
   * Close the reader.
   *
   * @throws Exception if an error occurs during reader closing
   */
  @Override
  public void close() throws Exception {

  }
}
