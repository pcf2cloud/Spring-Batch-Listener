package com.example.demo.Listeners;

import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

public class JobListener implements JobExecutionListener {

	private JavaMailSender mailSender;

	public JobListener(JavaMailSender mailSender) {
		this.mailSender = mailSender;
	}

	@Override
	public void beforeJob(JobExecution jobExecution) {
		// TODO Auto-generated method stub
		
		String jobName = jobExecution.getJobInstance().getJobName();
		
		SimpleMailMessage mailMessage = getSimpleMailMessage(String.format("%s is started ", jobName),String.format("per your request, we are informing you that %s is starting ", jobName));
		mailSender.send(mailMessage);

	}

	private SimpleMailMessage getSimpleMailMessage(String subject, String text) {
		// TODO Auto-generated method stub
		
		SimpleMailMessage mail = new SimpleMailMessage();
		mail.setTo("manvendra80386@gmail.com");
		mail.setSubject(subject);
		mail.setText(text);
		return mail;
	}

	@Override
	public void afterJob(JobExecution jobExecution) {
		// TODO Auto-generated method stub
		
		String jobName = jobExecution.getJobInstance().getJobName();
		
		SimpleMailMessage mailMessage = getSimpleMailMessage(String.format("%s has completed ", jobName),String.format("per your request, we are informing you that %s is starting ", jobName));
		mailSender.send(mailMessage);

	}

}