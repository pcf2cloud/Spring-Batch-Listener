package com.example.demo.config;

import java.util.Arrays;
import java.util.List;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.support.ListItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;

import com.example.demo.Listeners.*;

@Configuration
public class ListenerJobConfiguration {

	@Autowired
	private JobBuilderFactory jobBuilderFactory;
	@Autowired
	private StepBuilderFactory stepBuilderFactory;

	@Bean
	public ItemReader<String> reader() {

		return new ListItemReader<String>(Arrays.asList("1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12",
				"13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29",
				"30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46",
				"47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59", "60", "61", "62", "63",
				"64", "65", "66", "67", "68", "69", "70", "71", "72", "73", "74", "75", "76", "77", "78", "79", "80",
				"81", "82", "83"));

	}

	@Bean
	public ItemWriter<String> writer() {
		return new ItemWriter<String>() {

			@Override
			public void write(List<? extends String> items) throws Exception {
				// TODO Auto-generated method stub
				for (String item : items) {
					System.out.println("Writing item : " + item);

				}

			}
		};
	}

	@Bean
	public Step step1() {
		return stepBuilderFactory.get("Step-1").<String, String>chunk(5).faultTolerant().listener(new ChunkListeners())
				.reader(reader()).writer(writer()).build();
	}

	@Bean
	public Job job1(JavaMailSender mailSender) {
		return jobBuilderFactory.get("Job-1").start(step1()).listener(new JobListener(mailSender)).build();
	}

}