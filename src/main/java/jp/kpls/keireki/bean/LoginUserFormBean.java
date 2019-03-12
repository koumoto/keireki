package jp.kpls.keireki.bean;

/**
 * ログインフォームBean
 *
 * @author koumoto
 */
public class LoginUserFormBean {

	/** 社員番号 */
	private String employeeNo;

	/** パスワード */
	private String password;

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmployeeNo() {
		return employeeNo;
	}

	public void setEmployeeNo(String employeeNo) {
		this.employeeNo = employeeNo;
	}

}
