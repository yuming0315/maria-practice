package bookmall.vo;

public class OrderVo {
	private Long members_no;
	private Long no;
	private String address;
	private String payas;
	private Long totalamount;
	
	public OrderVo() {
		
	}
	public OrderVo(Long members_no) {
		this.members_no = members_no;
	}
	
	public Long getMembers_no() {
		return members_no;
	}
	public void setMembers_no(Long members_no) {
		this.members_no = members_no;
	}
	public Long getNo() {
		return no;
	}
	public void setNo(Long no) {
		this.no = no;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPayas() {
		return payas;
	}
	public void setPayas(String payas) {
		this.payas = payas;
	}
	public Long getTotalamount() {
		return totalamount;
	}
	public void setTotalamount(Long totalamount) {
		this.totalamount = totalamount;
	}
	
	@Override
	public String toString() {
		return "배송지 : "+address + "\n결제수단 : "+payas+"\n총 금액 : "+totalamount;
	}
	
}
