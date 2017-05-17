package com.ouzhx;

import java.io.IOException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

import org.easybatch.core.job.Job;
import org.easybatch.core.job.JobBuilder;
import org.easybatch.core.reader.StringRecordReader;
import org.easybatch.core.writer.FileRecordWriter;

/**
 * Created by ouzhx on 2017/5/17.
 * 
 * 当前示例是将 字符批量串写入到 文本文件/easy-batch-witer.txt中 (在windos中 /
 * 是相对于项目所在磁盘的根目录)
 *
 * 可以将其他文件 做批量操作
 */
public class MainTest {
  public static void main(String[] args) {
    // enableJmx(true)允许通过JMX 监控应用程序 (使用類似VisualVM 等工具)
    // 当激活监控时可以监听通知
    Job job = null;
    try {
      job = JobBuilder.aNewJob().named("ouzhxJob").batchSize(100).errorThreshold(10)
          .enableJmx(false).reader(new StringRecordReader("I love coding"))
          .writer(new FileRecordWriter("/easy-batch-witer.txt")).build();
    } catch (IOException e) {
      e.printStackTrace();
    }

    // 异步执行批处理
    /*
     * JobExecutor jobExecutor = new JobExecutor(); Future<JobReport> jobReportFuture =
     * jobExecutor.submit(job);
     */
    // 使用任务调度执行 5秒后执行
    ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(5);
    ScheduledFuture scheduledFuture = scheduledExecutorService.schedule(job, 5, TimeUnit.SECONDS);
  }
}
