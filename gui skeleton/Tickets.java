import java.util.List;

public class Tickets {
	private int tID;
	private String dateCreated;
	private String lastUpdated;
	private String status;
	private String title;
	private String description;
	private String submittedBy;
	private int priority;
	private List<Comments> lComments;
	
	public Tickets(){
		
	}
	public Tickets(int newTID, String newDateCreated, String newLastUpdated, String newStatus, String newTitle, String newDescription, String newSubmittedBy, int newPriority, List<Comments> newLComments){
		tID = newTID;
		dateCreated = newDateCreated;
		lastUpdated = newLastUpdated;
		status = newStatus;
		title = newTitle;
		description = newDescription;
		submittedBy = newSubmittedBy;
		priority = newPriority;
		lComments = newLComments;
	}

	public int gettID() {
		return tID;
	}

	public void settID(int tID) {
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

	public String getSubmittedBy() {
		return submittedBy;
	}

	public void setSubmittedBy(String submittedBy) {
		this.submittedBy = submittedBy;
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
}
