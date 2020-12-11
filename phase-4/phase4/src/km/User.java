package km;
public class User {
	private String id;
	private String pw;

	public User(String id, String pw) {
		this.id = id;
		this.pw = pw;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPW() {
		return pw;
	}

	public void setPW(String pw) {
		this.pw = pw;
	}
}
