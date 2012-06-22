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

public class AddRuleTest {

	AddRule rule;
	Parser myParser;
	@Before
	public void setUp() throws Exception {
		myParser = new Parser(new FileInputStream(new File("addTest.txt")));
		rule = (AddRule) myParser.rule();
	}

	@Test
	public void testEvaluate() throws ParseException {
		String actual = "neighbour(Self_,X,Now_+1) :- node(Self_),time(Now_),init_neighbour(Self_,X,Now_).";
		String result = rule.evaluate();
		assertEquals(actual, result);
		actual = "my_colour(Self_,C,Now_+1) :- node(Self_),time(Now_),new_colour(Self_,C,Now_).";
		rule = (AddRule) myParser.rule();
		result = rule.evaluate();
		assertEquals(actual, result);
		actual = "neighbour_colour(Self_,X,C,Now_+1) :- node(Self_),time(Now_),receive_vote_colour(Self_,C,X,_,Now_),not neighbour_colour(Self_,X,C,Now_).";
		rule = (AddRule) myParser.rule();
		result = rule.evaluate();
		assertEquals(actual, result);
	}

}
