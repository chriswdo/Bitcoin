package org.cleancode.dept14;

import java.util.HashMap;
import java.util.HashSet;
import java.util.ListIterator;
import java.util.Map;
import java.util.Set;

public class Args {
	private Map<Character,ArgumentMarshaler>marshalers;
	
	private Set<Character> argsFound;
	
	private ListIterator<String>currentArgument;
	
	public Args(String schema,String[]args)throws ArgsException{
		marshalers = new HashMap<Character,ArgumentMarshaler>();
		argsFound = new HashSet<Character>();
		
	}
	
}
