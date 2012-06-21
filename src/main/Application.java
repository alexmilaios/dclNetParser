package main;

import java.io.*;

import parser.Parser;
import rules.Rule;
public class Application {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		Parser parser = new Parser(new FileInputStream(new File(args[0])));
		OutputStreamWriter out = new OutputStreamWriter(new FileOutputStream(new File(args[1])));
		try{
			Rule rule;
			while((rule =parser.rule())!=null){
				out.write(rule.evaluate()+"\n\n");
				out.flush();
			}
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
	}

}
