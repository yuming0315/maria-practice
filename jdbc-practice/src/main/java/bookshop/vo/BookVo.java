package bookshop.vo;

public class BookVo {
	private Long no;
	private String title;
	private String rent;
	private Long authorNo;
	private String author;
	
	public Long getNo() {
		return no;
	}
	public void setNo(Long no) {
		this.no = no;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getRent() {
		return rent;
	}
	public void setRent(String rent) {
		this.rent = rent;
	}
	public Long getAuthorNo() {
		return authorNo;
	}
	public void setAuthorNo(Long author_no) {
		this.authorNo = author_no;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	
	@Override
	public String toString() {
		return "[no=" + no + ", title=" + title + ", rent=" + rent + ", author=" + author + "]";
	}
	
}
