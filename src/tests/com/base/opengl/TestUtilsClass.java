package tests.com.base.opengl;

import static org.junit.Assert.assertArrayEquals;

import org.junit.Test;

import com.base.opengl.Utils;

// TODO: Auto-generated Javadoc
/**
 * The Class TestUtilsClass.
 */
public class TestUtilsClass
{

	/**
	 * Test.
	 */
	@Test
	public void test()
	{
		int[] expVal = {0, 5, 84, 6577, 321456};
		Integer[] inVals = {0, 5, 84, 6577, 321456};

		assertArrayEquals(expVal, Utils.toIntArray(inVals));
	}

}
