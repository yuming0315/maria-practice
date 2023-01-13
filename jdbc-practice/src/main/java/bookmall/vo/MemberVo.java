package bookmall.vo;

public class MemberVo {
	private Long no;
	private String name;
	private String email;
	private String pw;
	private String phonenum;
	
	public MemberVo() {
		
	}
	
	public MemberVo(String id,String pw) {
		this.name=id;
		this.pw=pw;
	}
	
	public Long getNo() {
		return no;
	}
	public void setNo(Long no) {
		this.no = no;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPw() {
		return pw;
	}
	public void setPw(String pw) {
		this.pw = pw;
	}
	public String getPhonenum() {
		return phonenum;
	}
	public void setPhonenum(String phonenum) {
		this.phonenum = phonenum;
	}
	
	@Override
	public String toString() {
		return "[유저이름:" + name + ", 이메일:" + email + " , 전화번호:" + (phonenum == null ? "미등록" : phonenum) + "]";
	}
	
}
