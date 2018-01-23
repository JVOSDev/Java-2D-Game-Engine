package tests.com.base.opengl;

import static org.junit.Assert.*;

import org.junit.Test;

import com.base.opengl.Utils;

public class TestUtilsClass {

	@Test
	public void test() 
	{
		int[] expVal = {0,5,84,6577,321456};
		Integer[] inVals = {new Integer("0"),new Integer("5"),new Integer("84"),new Integer("6577"),new Integer("321456")};
		
		assertArrayEquals(expVal, Utils.toIntArray(inVals));
	}

}
