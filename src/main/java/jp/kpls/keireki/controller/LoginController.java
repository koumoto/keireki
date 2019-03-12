package jp.kpls.keireki.controller;

import java.util.Objects;

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

	@Autowired
	private LoginUserMapper loginUserMapper;

	@Autowired
	private LoginUserSessionBean sessionBean;

	@RequestMapping("login")
	private ModelAndView index(@ModelAttribute LoginUserFormBean form) {

		ModelAndView mv = new ModelAndView();

		// ビュー名を設定
		mv.setViewName("menu");

		// DB挿入
		LoginUser loginUser = loginUserMapper.selectByEmployeeNo(form.getEmployeeNo());

		// ログインユーザ不取得時
		if (loginUser == null) {
			// エラーページに
			mv.setViewName("login_error");
			return mv;
		}

		// パスワード不一致時
		if (! Objects.equals(form.getPassword(), loginUser.getPassword())) {
			// エラーページに
			mv.setViewName("login_error");
			return mv;
		}

		// セッションBeanに追加
		sessionBean.setLoginUserName(loginUser.getUserName());
		sessionBean.setEmployeeNo(loginUser.getEmployeeNo());

		mv.addObject("sessionBean", sessionBean);

		return mv;
	}


}
