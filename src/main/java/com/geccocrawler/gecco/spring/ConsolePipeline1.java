package com.geccocrawler.gecco.spring;

import com.alibaba.fastjson.JSON;
import com.geccocrawler.gecco.pipeline.Pipeline;
import com.geccocrawler.gecco.spider.SpiderBean;

public class ConsolePipeline1 implements Pipeline<SpiderBean> {

	@Override
	public void process(SpiderBean bean) {
		System.out.println("Procssed by ConsolePipeline:"+JSON.toJSONString(bean));
		if(bean instanceof MyGithub) {
			MyGithub bean1 = (MyGithub)bean;
			System.out.println("watch:"+JSON.toJSONString( ((MyGithub)bean).getWatch() ));
			for(  Object obj : bean1.getAll()  ){
				System.out.println(obj.getClass().getName()+" : " + obj);
			}
			
		}
	}

}
