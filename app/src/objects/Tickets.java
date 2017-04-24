package objects;

import java.util.List;

public class Tickets {
	private String tID;
	private String dateCreated;
	private String lastUpdated;
	private String status;
	private String title;
	private String description;
	private String submitter;
	private int priority;
	private String category;
	private List<Comments> lComments;
	private String assignee;

	public Tickets() {

	}

	public Tickets(String newTID, String newDateCreated, String newLastUpdated, String newStatus, String newTitle,
			String newDescription, String newSubmitter, int newPriority, String newCategory) {
		tID = newTID;
		dateCreated = newDateCreated;
		lastUpdated = newLastUpdated;
		status = newStatus;
		title = newTitle;
		description = newDescription;
		submitter = newSubmitter;
		priority = newPriority;
		category = newCategory;
	}

	public String gettID() {
		return tID;
	}

	public void settID(String tID) {
		this.tID = tID;
	}

	public String getDateCreated() {
		return dateCreated;
	}

	public void setDateCreated(String dateCreated) {
		this.dateCreated = dateCreated;
	}

	public String getLastUpdated() {
		return lastUpdated;
	}

	public void setLastUpdated(String lastUpdated) {
		this.lastUpdated = lastUpdated;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getSubmitter() {
		return submitter;
	}

	public void setSubmitter(String submitter) {
		this.submitter = submitter;
	}

	public int getPriority() {
		return priority;
	}

	public void setPriority(int priority) {
		this.priority = priority;
	}

	public List<Comments> getlComments() {
		return lComments;
	}

	public void setlComments(List<Comments> lComments) {
		this.lComments = lComments;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getAssignee() {
		return assignee;
	}

	public void setAssignee(String assignee) {
		this.assignee = assignee;
	}

	@Override
	public String toString() {
		return "Tickets [tID=" + tID + ", dateCreated=" + dateCreated + ", lastUpdated=" + lastUpdated + ", status="
				+ status + ", title=" + title + ", description=" + description + ", submitter=" + submitter
				+ ", priority=" + priority + ", lComments=" + lComments + "]";
	}
}
