package day24.model;

import java.sql.Date;

public class BoardVO {
	private long seq;
	private String title;
	private String contents;
	private int writer;
	private Date date;
	private int viewcount;
	private String password;
	private String image;

	public BoardVO() {
		super();
	}

	public BoardVO(long seq, String title, String contents, int writer, Date date, int viewcount, String password,
				   String image) {
		super();
		this.seq = seq;
		this.title = title;
		this.contents = contents;
		this.writer = writer;
		this.date = date;
		this.viewcount = viewcount;
		this.password = password;
		this.image = image;
	}



	public long getSeq() {
		return seq;
	}

	public void setSeq(long seq) {
		this.seq = seq;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContents() {
		return contents;
	}

	public void setContents(String contents) {
		this.contents = contents;
	}

	public int getWriter() {
		return writer;
	}

	public void setWriter(int writer) {
		this.writer = writer;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public int getViewcount() {
		return viewcount;
	}

	public void setViewcount(int viewcount) {
		this.viewcount = viewcount;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Board 정보 [seq=").append(seq).append(", title=").append(title).append(", contents=")
				.append(contents).append(", writer=").append(writer).append(", date=").append(date)
				.append(", viewcount=").append(viewcount).append(", password=").append(password).append(", image=")
				.append(image).append("]");
		return builder.toString();
	}




}