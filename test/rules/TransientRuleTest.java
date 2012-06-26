package rules;

import static org.junit.Assert.*;

import java.io.File;
import java.io.FileInputStream;
import java.text.ParseException;

import org.junit.Before;

import org.junit.Test;

import parser.Parser;

public class TransientRuleTest {

	TransientRule rule;
	Parser myParser;
	
	@Before
	public void setUp() throws Exception {
		myParser = new Parser(new FileInputStream(new File("transientTest.txt")));
	}

	@Test
	public void testEvaluate() throws parser.ParseException {
		String actual = "new_colour(Self_,C,Now_) :- node(Self_),time(Now_),init_colour(Self_,C,Now_),not my_colour(Self_,red,Now_),not my_colour(Self_,blue,Now_).";
		rule = (TransientRule) myParser.rule();
		String result = rule.evaluate();
		assertEquals(actual, result);
		
		actual = "new_colour(Self_,red,Now_) :- node(Self_),time(Now_),num_reds(Self_,N,Now_),num_blues(Self_,M,Now_),N>=M,my_colour(Self_,blue,Now_)."; 
		rule = (TransientRule) myParser.rule();
		result = rule.evaluate();
		assertEquals(actual, result);
		
		actual = "new_colour(Self_,blue,Now_) :- node(Self_),time(Now_),num_reds(Self_,N,Now_),num_blues(Self_,M,Now_),N<M,my_colour(Self_,red,Now_).";
		rule = (TransientRule) myParser.rule();
		result = rule.evaluate();
		assertEquals(actual, result);
		
		
		actual = "latest_colour(Self_,Self_,C,Now_) :- node(Self_),time(Now_),my_colour(Self_,C,Now_).";
		rule = (TransientRule) myParser.rule();
		result = rule.evaluate();
		assertEquals(actual, result);
		
		actual = "latest_colour(Self_,X,C,Now_) :- node(Self_),time(Now_),receive_vote_colour(Self_,C,X,_,Now_).";
		rule = (TransientRule) myParser.rule();
		result = rule.evaluate();
		assertEquals(actual, result);
		
		actual = "latest_colour(Self_,X,C,Now_) :- node(Self_),time(Now_),neighbour_colour(Self_,X,C,Now_),not receive_vote_colour(Self_,red,X,_,Now_),not receive_vote_colour(Self_,blue,X,_,Now_).";
		rule = (TransientRule) myParser.rule();
		result = rule.evaluate();
		assertEquals(actual, result);
		
		actual = "num_reds_count(Self_,X,Now_) :- node(Self_),time(Now_),latest_colour(Self_,X,red,Now_),node(X).num_reds(Self_,CountX,Now_) :- node(Self_),time(Now_),CountX := #count { num_reds_count(Self_,X,Now_) }.";
		rule = (TransientRule) myParser.rule();
		result = rule.evaluate();
		assertEquals(actual, result);
		
		actual = "num_blues_count(Self_,X,Now_) :- node(Self_),time(Now_),latest_colour(Self_,X,blue,Now_),node(X).num_blues(Self_,CountX,Now_) :- node(Self_),time(Now_),CountX := #count { num_blues_count(Self_,X,Now_) }.";
		rule = (TransientRule) myParser.rule();
		result = rule.evaluate();
		assertEquals(actual, result);
	}

}
