package com.geccocrawler.gecco.spring;

import java.util.List;

import com.geccocrawler.gecco.annotation.Gecco;
import com.geccocrawler.gecco.annotation.Href;
import com.geccocrawler.gecco.annotation.HtmlField;
import com.geccocrawler.gecco.annotation.Request;
import com.geccocrawler.gecco.annotation.RequestParameter;
import com.geccocrawler.gecco.annotation.Text;
import com.geccocrawler.gecco.request.HttpRequest;
import com.geccocrawler.gecco.spider.HtmlBean;

/**
 * 注意用户登录和未登陆的界面是不一样的，所以某些数据可能无法取得
 * @author haifeng.li
 * @date 2021/11/26
 *
 */
@Gecco(matchUrl="https://github.com/{user}/{project}", pipelines="consolePipeline1")
public class MyGithub implements HtmlBean {

	private static final long serialVersionUID = -7127412585200687225L;
	
	@Request
	private HttpRequest request;
	
	@RequestParameter("user")
	private String user;
	
	@RequestParameter("project")
	private String project;
	
	@Text
	@HtmlField(cssPath=".repository-meta-content")
	private String title;
	
	//可以取得所有的对象，用于debug
	@HtmlField(cssPath="*")
	private List<Object> all;

	//未登陆的话，是没有这个数据的，所以无法取得
	@HtmlField(cssPath=".pagehead-actions li:nth-child(1) .social-count")
	private String watch;

	@Text
	@HtmlField(cssPath=".pagehead-actions li:nth-child(2) .social-count")
	private String star;
	
	@Text
	@HtmlField(cssPath=".pagehead-actions li:nth-child(3) .social-count")
	private String fork;

	@Href(click=false)
	@HtmlField(cssPath="ul.numbers-summary > li:nth-child(4) > a")
	private String contributors;
	
	@HtmlField(cssPath=".entry-content")
	private String readme;

	public HttpRequest getRequest() {
		return request;
	}

	public void setRequest(HttpRequest request) {
		this.request = request;
	}

	public String getReadme() {
		return readme;
	}

	public void setReadme(String readme) {
		this.readme = readme;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getProject() {
		return project;
	}

	public void setProject(String project) {
		this.project = project;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getWatch() {
		return watch;
	}

	public void setWatch(String watch) {
		this.watch = watch;
	}

	public String getStar() {
		return star;
	}

	public void setStar(String star) {
		this.star = star;
	}

	public String getFork() {
		return fork;
	}

	public void setFork(String fork) {
		this.fork = fork;
	}
	
	public String getContributors() {
		return contributors;
	}

	public void setContributors(String contributors) {
		this.contributors = contributors;
	}

	public List<Object> getAll() {
		return all;
	}

	public void setAll(List<Object> all) {
		this.all = all;
	}

}
