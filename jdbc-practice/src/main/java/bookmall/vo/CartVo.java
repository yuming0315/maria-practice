package bookmall.vo;

public class CartVo {
	private Long no;
	private Long members_no;
	private Long book_no;
	private Long amount;
	
	private String title;
	private Long price;
	
	public CartVo() {
		
	}
	
	public CartVo(Long userNo) {
		members_no = userNo;
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
	public Long getNo() {
		return no;
	}
	public void setNo(Long no) {
		this.no = no;
	}
	public Long getMembers_no() {
		return members_no;
	}
	public void setMembers_no(Long members_no) {
		this.members_no = members_no;
	}
	public Long getBook_no() {
		return book_no;
	}
	public void setBook_no(Long book_no) {
		this.book_no = book_no;
	}
	public Long getAmount() {
		return amount;
	}
	public void setAmount(Long amount) {
		this.amount = amount;
	}
	
	@Override
	public String toString() {
		return "[책제목: "+title+", price=" + price +", amount=" + amount +  "]";
	}
	
}
