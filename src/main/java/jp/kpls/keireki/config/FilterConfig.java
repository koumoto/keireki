package jp.kpls.keireki.config;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import jp.kpls.keireki.filter.LoginFilter;

@Configuration
public class FilterConfig {

	// ログインフィルターを通さないURL
	private List<String> ignoreUrlList = Arrays.asList("/", "/login", "/menu", "/favicon.ico", "/logout");

	@Bean
	public FilterRegistrationBean<LoginFilter> loginFilter() {
		// FilterをnewしてFilterRegistrationBeanのコンストラクタに渡す
		FilterRegistrationBean<LoginFilter> bean = new FilterRegistrationBean<LoginFilter>(new LoginFilter());
		// Filterのurl-patternを指定（可変長引数なので複数指定可能）
		bean.addUrlPatterns("/*");
		// Filterの実行順序。整数値の照準に実行される
		bean.setOrder(Integer.MIN_VALUE);

		Map<String, String> ignoreMap = new HashMap<String, String>();
		// ,(カンマ)区切りで結合
		ignoreMap.put("ignoreUrl", ignoreUrlList.stream().collect(Collectors.joining(",")));
		// フィルター側に渡す
		bean.setInitParameters(ignoreMap);
		return bean;
	}

}
