
public class Comments {
	private int cID;
	private int tID;
	private String comment;
	private String dateCreated;
	
	public Comments(){}
	public Comments(int newCID, int newTID, String newComment, String newDateCreated){
		cID = newCID;
		tID = newTID;
		comment = newComment;
		dateCreated = newDateCreated;
	}
	public int getcID() {
		return cID;
	}
	public void setcID(int cID) {
		this.cID = cID;
	}
	public int gettID() {
		return tID;
	}
	public void settID(int tID) {
		this.tID = tID;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public String getDateCreated() {
		return dateCreated;
	}
	public void setDateCreated(String dateCreated) {
		this.dateCreated = dateCreated;
	}
}
