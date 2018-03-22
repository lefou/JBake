package org.jbake.util;

import static de.tobiasroeser.lambdatest.Expect.expectEquals;
import static org.hamcrest.core.Is.is;

import org.junit.Assert;
import org.junit.Test;

import de.tobiasroeser.lambdatest.RunnableWithException;
import de.tobiasroeser.lambdatest.junit.FreeSpec;

public class PagingHelperTest extends FreeSpec {

	public PagingHelperTest() {

		test("getNumberOfPages should work", new RunnableWithException() {
			@Override
			public void run() throws Exception {
				expectEquals(new PagingHelper(5, 2).getNumberOfPages(), 3);
			}
		});

		test("getPreviousFileName should return root index page", new RunnableWithException() {
			@Override
			public void run() throws Exception {
				final String previousFileName = new PagingHelper(5, 2).getPreviousFileName(2, "index.html");
				expectEquals(previousFileName, "");
			}
		});

		test("getPreviousFileName should return previous file name", new RunnableWithException() {
			@Override
			public void run() throws Exception {
				final String previousFileName = new PagingHelper(5, 2).getPreviousFileName(3, "index.html");
				expectEquals(previousFileName, "2/");
			}
		});

	}

	@Test
	public void shouldReturnNullIfNoPreviousPageAvailable() throws Exception {
		final PagingHelper helper = new PagingHelper(5, 2);

		final String previousFileName = helper.getPreviousFileName(1, "index.html");

		Assert.assertNull(previousFileName);
	}

	@Test
	public void shouldReturnNullIfNextPageNotAvailable() throws Exception {
		final PagingHelper helper = new PagingHelper(5, 2);

		final String nextFileName = helper.getNextFileName(3, "index.html");

		Assert.assertNull(nextFileName);
	}

	@Test
	public void shouldReturnNextFileName() throws Exception {
		final PagingHelper helper = new PagingHelper(5, 2);

		final String nextFileName = helper.getNextFileName(2, "index.html");

		Assert.assertThat("3/", is(nextFileName));
	}
}