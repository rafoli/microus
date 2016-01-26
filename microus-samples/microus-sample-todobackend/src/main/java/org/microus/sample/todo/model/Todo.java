package org.microus.sample.todo.model;

public class Todo {
	private Long id;
	private String title;
	private Boolean completed;
	private Integer order;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public boolean isCompleted() {
		return nonNull(completed, false);
	}

	public void setCompleted(boolean completed) {
		this.completed = completed;
	}

	public int getOrder() {
		return nonNull(order, 0);
	}

	public void setOrder(Integer order) {
		this.order = order;
	}

	public long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	private <T> T nonNull(T value, T defaultValue) {
		return value == null ? defaultValue : value;
	}
}
