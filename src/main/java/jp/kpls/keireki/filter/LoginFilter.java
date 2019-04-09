package jp.kpls.keireki.filter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jp.kpls.keireki.bean.LoginUserSessionBean;

public class LoginFilter implements Filter {

	/** ログイン画面へのURL */
	private static final String LOGIN_URL = "/";

	/** フィルターを無視するURL */
	private List<String> ignoreUrlList = new ArrayList<String>();

	/**
	 * ログインフィルター
	 *
	 */
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		String path = ((HttpServletRequest) request).getServletPath();

		System.out.println(path);

		// 除外URL以外
		if (!ignoreUrlList.contains(path)) {

			// セッションを取得
			HttpSession session = ((HttpServletRequest) request).getSession();

			// セッションよりセッションBeabを取得
			LoginUserSessionBean sessionBean = (LoginUserSessionBean) session.getAttribute("sessionBean");

			// 空ならログインページにリダイレクト
			if (sessionBean == null) {
				((HttpServletResponse) response).sendRedirect(LOGIN_URL);
				return;
			}

			// ユーザ名
			String loginUserName = sessionBean.getLoginUserName();
			// 社員番号
			String employeeNo = sessionBean.getEmployeeNo();

			// 空ならログインページにリダイレクト
			if (loginUserName == null || employeeNo == null) {
				((HttpServletResponse) response).sendRedirect(LOGIN_URL);
				return;
			}
		}

		chain.doFilter(request, response);
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {

		ignoreUrlList = Arrays.asList(filterConfig.getInitParameter("ignoreUrl").split(","));

	}

	@Override
	public void destroy() {
	}

}
