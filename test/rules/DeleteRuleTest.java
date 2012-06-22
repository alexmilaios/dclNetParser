package rules;

import static org.junit.Assert.*;

import java.io.File;
import java.io.FileInputStream;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import parser.ParseException;
import parser.Parser;

public class DeleteRuleTest {

	DeleteRule rule;
	Parser myParser;
	
	@Before
	public void setUp() throws Exception {
		myParser = new Parser(new FileInputStream(new File("forgetTest.txt")));
	}

	@Test
	public void test() throws ParseException {
		String actual = "forget_my_colour(Self_,OldC,Now_) :- node(Self_),time(Now_),my_colour(Self_,OldC,Now_),new_colour(Self_,C,Now_).";
		rule = (DeleteRule) myParser.rule();
		String result = rule.evaluate();
		assertEquals(actual, result);

		actual = "forget_neighbour_colour(Self_,X,OldC,Now_) :- node(Self_),time(Now_),neighbour_colour(Self_,X,OldC,Now_),receive_vote_colour(Self_,C,X,_,Now_),OldC!=C.";
		rule = (DeleteRule) myParser.rule();
		result = rule.evaluate();
		assertEquals(actual, result);
	}
}
