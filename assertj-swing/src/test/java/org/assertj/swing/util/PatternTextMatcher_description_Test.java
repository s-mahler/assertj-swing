/*
 * Created on Jul 30, 2009
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
 * Copyright @2009-2013 the original author or authors.
 */
package org.assertj.swing.util;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.regex.Pattern;

import org.junit.Test;

/**
 * Tests for {@link PatternTextMatcher#description()}.
 * 
 * @author Alex Ruiz
 */
public class PatternTextMatcher_description_Test {
  @Test
  public void should_erturn_pattern_word_as_description_if_matcher_has_only_one_pattern() {
    PatternTextMatcher matcher = new PatternTextMatcher(Pattern.compile("one"));
    assertThat(matcher.description()).isEqualTo("pattern");
  }

  @Test
  public void should_return_patterns_word_as_description_if_matcher_has_more_than_one_pattern() {
    PatternTextMatcher matcher = new PatternTextMatcher(Pattern.compile("one"), Pattern.compile("two"));
    assertThat(matcher.description()).isEqualTo("patterns");
  }
}
