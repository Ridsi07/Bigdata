package com.java.scoring;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.*;

public class BaseballScoreMapper extends MapReduceBase implements Mapper <LongWritable, Text, Text, IntWritable> {
	private static IntWritable finalValue = null;

	public void map(LongWritable key, Text value, OutputCollector <Text, IntWritable> output, Reporter reporter) throws IOException {

		String valueString = value.toString();
		String[] SingleCountryData = valueString.split(",");
		String data1 = SingleCountryData[12];
		String data2 = SingleCountryData[13];
		String data3 = SingleCountryData[14];
		String data4 = SingleCountryData[25];
		Integer d1 = 0, d2 = 0, d3=0, d4=0;
		try {
			d1 = Integer.parseInt(data1);
		}catch(Exception e){
			d1 = 0;
		}
		try {
			d2 = Integer.parseInt(data2);
		}catch(Exception e){
			d2 = 0;
		}
		try {
			d3 = Integer.parseInt(data3);
		}catch(Exception e){
			d3 = 0;
		}
		try {
			d4 = Integer.parseInt(data4);
		}catch(Exception e){
			d4 = 0;
		}
		Integer intFinal = d1+d2+d3+d4;
		finalValue = new IntWritable(intFinal);
		output.collect(new Text(SingleCountryData[0]+"-"+SingleCountryData[2]), finalValue);
	}
}
