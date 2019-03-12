package jp.kpls.keireki.mapper;

import org.apache.ibatis.annotations.Mapper;

import jp.kpls.keireki.domain.LoginUser;

@Mapper
public interface LoginUserMapper {

	/**
	 * ログインユーザを登録する.
	 *
	 * @param loginUser ログインユーザ
	 */
	void insert(LoginUser loginUser);

	/**
	 * ログインユーザを取得する
	 *
	 * @param employeeNo 社員番号
	 * @return
	 */
	LoginUser selectByEmployeeNo(String employeeNo);
}