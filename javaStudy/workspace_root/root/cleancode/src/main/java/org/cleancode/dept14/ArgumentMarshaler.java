package org.cleancode.dept14;

import java.util.Iterator;

public interface ArgumentMarshaler {
	
	void set(Iterator<String>currentArgument) throws ArgsException;

}
