package jp.kpls.keireki.domain;

public class LoginUser {

	/** 社員番号 */
	private String employeeNo;

	/** ユーザ名 */
	private String userName;

	/** パスワード */
	private String password;

	public String getEmployeeNo() {
		return employeeNo;
	}

	public void setEmployeeNo(String employeeNo) {
		this.employeeNo = employeeNo;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}



}
