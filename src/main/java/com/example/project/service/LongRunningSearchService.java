package com.example.project.service;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

import javax.ejb.Asynchronous;
import javax.ejb.Stateless;

import com.example.project.model.Result;

@Stateless
public class LongRunningSearchService {

	@Asynchronous
	public void asyncLoadResults(Consumer<List<Result>> callback) {
		long fiveSecondsLater = System.nanoTime() + (long) 5e9;

		while (System.nanoTime() < fiveSecondsLater) {
			// Crunch.
		}

		List<Result> results = new ArrayList<>();
		results.add(Result.create(1));
		results.add(Result.create(2));
		results.add(Result.create(3));

		callback.accept(results);
	}
}
