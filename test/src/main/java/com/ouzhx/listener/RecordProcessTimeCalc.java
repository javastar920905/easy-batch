package com.ouzhx.listener;

import org.easybatch.core.job.DefaultJobReportFormatter;
import org.easybatch.core.job.JobParameters;
import org.easybatch.core.job.JobReport;
import org.easybatch.core.listener.JobListener;
import org.easybatch.core.listener.PipelineListener;
import org.easybatch.core.record.Record;

/**
 * Created by ouzhx on 2017/5/17.
 */
public class RecordProcessTimeCalc implements PipelineListener, JobListener {
  private long startTime;
  private long nbRecords;
  private long recordProcessingTimesSum;

  /**
   * Called before starting the job.
   *
   * @param jobParameters the job parameters
   */
  @Override
  public void beforeJobStart(JobParameters jobParameters) {
    //
  }

  /**
   * Called after job end.
   *
   * @param jobReport The job execution report
   */
  @Override
  public void afterJobEnd(JobReport jobReport) {
    jobReport.getMetrics().addMetric("Record processing time average (in ms)",
        (double) recordProcessingTimesSum / (double) nbRecords);
    String htmlReport = new DefaultJobReportFormatter().formatReport(jobReport);
    System.out.println(htmlReport);
  }

  /**
   * Called before the record gets processed. If you create a new record, you <strong>must</strong>
   * keep the original header of the modified record.
   *
   * @param record The record that will be processed.
   */
  @Override
  public Record beforeRecordProcessing(Record record) {
    nbRecords++;
    startTime = System.currentTimeMillis();
    return record;
  }

  /**
   * Called after the record has been processed.
   *
   * @param inputRecord The record to process.
   * @param outputRecord The processed record. <strong>May be null if the record has been
   *        filtered</strong>
   */
  @Override
  public void afterRecordProcessing(Record inputRecord, Record outputRecord) {
    recordProcessingTimesSum += System.currentTimeMillis() - startTime;
  }

  /**
   * Called when an exception occurs during record processing
   *
   * @param record the record attempted to be processed
   * @param throwable the exception occurred during record processing
   */
  @Override
  public void onRecordProcessingException(Record record, Throwable throwable) {
    recordProcessingTimesSum += System.currentTimeMillis() - startTime;
  }
}
