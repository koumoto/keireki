package jp.kpls.keireki.controller;

import java.util.Objects;

import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import jp.kpls.keireki.bean.LoginUserFormBean;
import jp.kpls.keireki.bean.LoginUserSessionBean;
import jp.kpls.keireki.domain.LoginUser;
import jp.kpls.keireki.mapper.LoginUserMapper;

@Controller
@RequestMapping("/")
public class LoginController {

	/** log */
	private Log log = LogFactory.getLog(LoginController.class);

	@Autowired
	private LoginUserMapper loginUserMapper;

	@Autowired
	HttpSession session;

	@RequestMapping("login")
	private ModelAndView index(@ModelAttribute LoginUserFormBean form) {
		log.info("ログイン処理開始");

		ModelAndView mv = new ModelAndView();

		// ビュー名を設定
		mv.setViewName("menu");

		// セッションが設定されていないとき
		if (session.getAttribute("sessionBean") == null) {

			// DB検索
			LoginUser loginUser = loginUserMapper.selectByEmployeeNo(form.getEmployeeNo());

			// ログインユーザ不取得時
			if (loginUser == null) {
				// エラーページに
				mv.setViewName("login_error");
				return mv;
			}

			// パスワード不一致時
			if (!Objects.equals(form.getPassword(), loginUser.getPassword())) {
				// エラーページに
				mv.setViewName("login_error");
				return mv;
			}

			// セッションBeanに追加
			LoginUserSessionBean sessionBean = new LoginUserSessionBean();
			sessionBean.setLoginUserName(loginUser.getUserName());
			sessionBean.setEmployeeNo(loginUser.getEmployeeNo());

			session.setAttribute("sessionBean", sessionBean);
			//mv.addObject("sessionBean", sessionBean);
		}

		log.info("ログイン処理終了");

		return mv;
	}

	@RequestMapping("menu")
	private ModelAndView menu(@ModelAttribute LoginUserFormBean form) {
		return index(form);
	}

	@RequestMapping("logout")
	private ModelAndView logout() {
		log.info("ログアウト処理開始");

		ModelAndView mv = new ModelAndView();

		// ビュー名を設定
		mv.setViewName("logout");

		// セッション情報をクリア
		session.invalidate();

		log.info("ログアウト処理終了");

		return mv;
	}

}
