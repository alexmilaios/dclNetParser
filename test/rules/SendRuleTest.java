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

public class SendRuleTest {

	SendRule rule;
	Parser myParser;

	@Before
	public void setUp() throws Exception {
		myParser = new Parser(new FileInputStream(new File("sendTest.txt")));
	}

	@Test
	public void testEvaluate() throws ParseException {
		String actual = "send_vote_colour(Self_,C,X,Now_+1) :- node(Self_),time(Now_),new_colour(Self_,C,Now_),neighbour(Self_,X,Now_).";
		rule = (SendRule) myParser.rule();
		String result = rule.evaluate();
		assertEquals(actual, result);
	}

}
