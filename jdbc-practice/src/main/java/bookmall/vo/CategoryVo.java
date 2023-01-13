package bookmall.vo;

public class CategoryVo {
	private Long no;
	private String category;

	public CategoryVo() {
		
	}
	public CategoryVo(String category) {
		this.category = category;
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
		return "["+no + " 분류명:" + category + "]";
	}
	
}
