package com.dmcliver.performancecars;

import static com.dmcliver.performancecars.EnumMetadata.getTag;
import static com.dmcliver.performancecars.domain.EngineAspiration.naturallyAspirated;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class EnumMetadataTest {

	@Test
	public void getTag_WithEnumContainingTag_ReturnsTagName() throws Exception {
		
		String tag = getTag(naturallyAspirated);
		assertThat(tag, equalTo("Naturally Aspirated"));
	}
}
