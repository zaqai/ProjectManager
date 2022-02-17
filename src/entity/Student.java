package entity;

public class Student {
	/**
	 * 鲜花标识符
	 */
	private String id;

	/**
	 * 鲜花名称
	 */
	private String name;

	/**
	 * 鲜花类别
	 */
	private String passwd;
	private String p_id;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPasswd() {
		return passwd;
	}

	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}

	public String getP_id() {
		return p_id;
	}

	public void setP_id(String p_id) {
		this.p_id = p_id;
	}

}