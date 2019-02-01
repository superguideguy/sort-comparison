package com.github.superguideguy.sort.comparison.sync;

public enum CurrentTask {
	SORTING("Sorting array..."),
	CHECKING("Checking array..."),
	CREATING("Creating array..."),
	SHUFFLING("Shuffling array...")
	;
	private final String task;
	CurrentTask(String task) {
		this.task = task;
	}
	String getTaskString() {
		return task;
	}
}
