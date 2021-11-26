package com.geccocrawler.gecco.spring;

import static org.junit.Assert.assertNotNull;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.Test;

/**
 * quick test for jsoup
 * refer:  jsoup cookbook
 * https://jsoup.org/cookbook/
 * @author haifeng.li
 *
 */
public class TestJsoup{
	
	@Test
	public  void testBody() {
		String html = "<html>\r\n"
				+ " <head>\r\n"
				+ "  <title>First parse</title>\r\n"
				+ " </head>\r\n"
				+ " <body>\r\n"
				+ "  <p>Parsed HTML into a doc.</p>\r\n"
				+ " </body>\r\n"
				+ "</html>\r\n"
				+ "";
		Document doc = Jsoup.parse(html);
		System.out.println(doc);
		
		//html片段
		String html2 = "<div><p>Lorem ipsum.</p>";
		Document doc2 = Jsoup.parseBodyFragment(html2);
		Element body = doc2.body();
		System.out.println(body);
		assertNotNull(body);
	}

	@Test
	public  void testUrl() throws IOException {
		Document doc = Jsoup.connect("http://example.com/").get();
		String title = doc.title();
		System.out.println(title);
		assertNotNull(title);
		
		Document doc2 = Jsoup.connect("http://example.com")
				  .data("query", "Java")
				  .userAgent("Mozilla")
				  .cookie("auth", "token")
				  .timeout(3000)
				  .post();		
		
		assertNotNull(doc2);
		
	}
	
	@Test
	public  void testParseElement() throws IOException {
		Document doc = Jsoup.connect("http://example.com/").get();
		String title = doc.title();
		System.out.println(title);
		assertNotNull(title);
		
		//Element content = doc.getElementById("content");
		Element content = doc.body();
		
		Elements links = content.getElementsByTag("a");
		for (Element link : links) {
		  String linkHref = link.attr("href");
		  String linkText = link.text();
		  System.out.println(linkHref+":"+linkText);
		}
		
	}
	
	@Test
	public  void testSelector() throws IOException {
		Document doc = Jsoup.connect("http://example.com/").get();
		String title = doc.title();
		System.out.println(title);
		
		Element content = doc.body();
		
		Elements links = doc.select("a[href]"); // a with href
		Elements pngs = doc.select("img[src$=.png]");
		  // img with src ending .png

		Element masthead = doc.select("div.masthead").first();
		  // div with class=masthead

		Elements resultLinks = doc.select("h3.r > a"); // direct a after h3
		
		assertNotNull(title);
	}
	
	@Test
	public  void testUrl2() throws IOException {
		Document doc = Jsoup.connect("http://example.com/").get();
		String title = doc.title();
		System.out.println(title);
		Element link = doc.select("a").first();
		String relHref = link.attr("href"); // == "/"
		String absHref = link.attr("abs:href"); // "http://jsoup.org/"
		assertNotNull(title);
		
	}
	
	@Test 
	public void testProxy() throws IOException {
       String url = "http://news.ycombinator.com/";
        print("Fetching %s...", url);

        Document doc = Jsoup.connect(url)
        		.proxy("127.0.0.1", 9001)
        		.get();
        
        Elements links = doc.select("a[href]");
        print("\nLinks: (%d)", links.size());
        for (Element link : links) {
            print(" * a: <%s>  (%s)", link.attr("abs:href"), trim(link.text(), 35));
        }
		
	}
	
	
	
	@Test
	public void testListLinks() throws IOException {
	       String url = "http://news.ycombinator.com/";
	       url= "https://hackernews.cc/";
	        print("Fetching %s...", url);

	        Document doc = Jsoup.connect(url).get();
	        Elements links = doc.select("a[href]");
	        Elements media = doc.select("[src]");
	        Elements imports = doc.select("link[href]");

	        print("\nMedia: (%d)", media.size());
	        for (Element src : media) {
	            if (src.nodeName().equals("img"))
	                print(" * %s: <%s> %sx%s (%s)",
	                        src.tagName(), src.attr("abs:src"), src.attr("width"), src.attr("height"),
	                        trim(src.attr("alt"), 20));
	            else
	                print(" * %s: <%s>", src.tagName(), src.attr("abs:src"));
	        }

	        print("\nImports: (%d)", imports.size());
	        for (Element link : imports) {
	            print(" * %s <%s> (%s)", link.tagName(),link.attr("abs:href"), link.attr("rel"));
	        }

	        print("\nLinks: (%d)", links.size());
	        for (Element link : links) {
	            print(" * a: <%s>  (%s)", link.attr("abs:href"), trim(link.text(), 35));
	        }
	    }

	    private static void print(String msg, Object... args) {
	        System.out.println(String.format(msg, args));
	    }

	    private static String trim(String s, int width) {
	        if (s.length() > width)
	            return s.substring(0, width-1) + ".";
	        else
	            return s;
	    }	
}