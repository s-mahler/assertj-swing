/*
 * Created on Jan 24, 2011
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 * 
 * Copyright @2011 the original author or authors.
 */
package org.assertj.swing.assertions.error;

import static junit.framework.Assert.assertEquals;
import static org.assertj.core.data.Offset.offset;
import static org.assertj.swing.assertions.error.ShouldBeEqualImages.shouldBeEqualImages;

import org.assertj.core.description.TextDescription;
import org.assertj.core.error.ErrorMessageFactory;
import org.assertj.core.presentation.StandardRepresentation;
import org.junit.Before;
import org.junit.Test;

/**
 * Tests for <code>{@link ShouldBeEqualImages#create(Description)}</code>.
 * 
 * @author Yvonne Wang
 */
public class ShouldBeEqualImages_create_Test {

  private ErrorMessageFactory factory;

  @Before
  public void setUp() {
    factory = shouldBeEqualImages(offset(6));
  }

  @Test
  public void should_create_error_message() {
    String message = factory.create(new TextDescription("Test"), new StandardRepresentation());
    assertEquals("[Test] expecting images to be equal within offset:<6>", message);
  }
}
