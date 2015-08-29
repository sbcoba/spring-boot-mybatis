package sample.springboot.mybatis.groovymapper.service;

/**
 * Sample DTO
 * 
 * @author sbcoba
 *
 */
public class Member {
	private Long id;
	private String name;
	private String phone;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	@Override
	public String toString() {
		return "Member [id=" + id + ", name=" + name + ", phone=" + phone + "]";
	}
}
