package main;

import java.io.*;
import java.util.StringTokenizer;

import parser.Parser;
import rules.Rule;
public class Application {

	/**
	 * @param args
	 */
	@SuppressWarnings("static-access")
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		Parser parser = new Parser(new FileInputStream(new File(args[0])));
		OutputStreamWriter out = new OutputStreamWriter(new FileOutputStream(new File(args[1])));
		try{
			Rule rule;
			while((rule =parser.rule())!=null){
				StringTokenizer tokens = new StringTokenizer(rule.evaluate(),".");
				while(tokens.hasMoreTokens()){
					String tmp = tokens.nextToken();
					out.write(tmp+".\n\n");
					out.flush();
				}
			}
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
	}

}
