package com.example.demo.Listeners;

import java.time.LocalDate;

import org.springframework.batch.core.annotation.AfterChunk;
import org.springframework.batch.core.annotation.BeforeChunk;
import org.springframework.batch.core.scope.context.ChunkContext;

public class ChunkListeners {
	
	@BeforeChunk
	public void beforeChunk(ChunkContext context) {
		System.out.println(">> Before the chunk========"+LocalDate.now());
	}
	
	@AfterChunk
	public void afterChunk(ChunkContext context) {
		System.out.println("<< After the chunk=========="+LocalDate.now());
		
	}

}