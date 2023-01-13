package bookmall.vo;

public class BookVo {
	private Long no;
	private String title;
	private Long price;
	private String category;
	
	public BookVo() {
		
	}
	public BookVo(String title) {
		this.title = title;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Long getPrice() {
		return price;
	}
	public void setPrice(Long price) {
		this.price = price;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public Long getNo() {
		return no;
	}
	public void setNo(Long no) {
		this.no = no;
	}
	
	@Override
	public String toString() {
		return "[책번호:"+no+", 책명:" + title + ", 가격:" + price + ", 분류:" + category + "]";
	}
	
}
