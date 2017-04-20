package objects;

public class Comments {
	private int cID;
	private String tID;
	private String comment;
	private String commentDate;

	public Comments() {
	}

	public Comments(String newTID,String newComment, String newCommentDate) {
		tID = newTID;
		comment = newComment;
		commentDate = newCommentDate;
	}

	public int getcID() {
		return cID;
	}

	public void setcID(int cID) {
		this.cID = cID;
	}

	public String gettID() {
		return tID;
	}

	public void settID(String tID) {
		this.tID = tID;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getCommentDate() {
		return commentDate;
	}

	public void setCommentDate(String commentDate) {
		this.commentDate = commentDate;
	}

	@Override
	public String toString() {
		return "Comments [cID=" + cID + ", tID=" + tID + ", comment=" + comment + ", commentDate=" + commentDate + "]";
	}
}
