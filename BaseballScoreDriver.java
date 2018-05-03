package com.java.scoring;

//import java.util.Scanner;

import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.FileInputFormat;
import org.apache.hadoop.mapred.FileOutputFormat;
import org.apache.hadoop.mapred.JobClient;
import org.apache.hadoop.mapred.JobConf;
import org.apache.hadoop.mapred.TextInputFormat;
import org.apache.hadoop.mapred.TextOutputFormat;

public class BaseballScoreDriver {

	public static void main(String[] args) {
		/*Scanner in=new Scanner(System.in);
		System.out.println("Enter a input path: ");
	     String args0 = in.nextLine();
	     
	     System.out.println("Enter a output path: ");
	     String args1 = in.nextLine();*/
		
		
		
		if (args.length != 2) {
		      System.out.printf(
		          "Usage: tvc <input dir> <output dir>\n");
		      System.exit(-1);
		}
		
		JobClient my_client = new JobClient();
		JobConf job_conf = new JobConf(BaseballScoreDriver.class);

		job_conf.setJobName("BestHits");

		job_conf.setOutputKeyClass(Text.class);
		job_conf.setOutputValueClass(IntWritable.class);

		job_conf.setMapperClass(BaseballScoreMapper.class);
		job_conf.setReducerClass(BaseballScoreReducer.class);

		job_conf.setInputFormat(TextInputFormat.class);
		job_conf.setOutputFormat(TextOutputFormat.class);


		FileInputFormat.setInputPaths(job_conf, new Path(args[0]));
		FileOutputFormat.setOutputPath(job_conf, new Path(args[1]));

		my_client.setConf(job_conf);
		try {
			JobClient.runJob(job_conf);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}