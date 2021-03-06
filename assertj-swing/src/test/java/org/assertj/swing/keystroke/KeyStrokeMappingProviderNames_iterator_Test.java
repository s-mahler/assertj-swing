/*
 * Created on Mar 28, 2010
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
 * Copyright @2010-2013 the original author or authors.
 */
package org.assertj.swing.keystroke;

import static java.util.Locale.US;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.swing.test.ExpectedException.none;
import static org.assertj.swing.util.OSFamily.WINDOWS;

import java.util.Iterator;
import java.util.NoSuchElementException;

import org.assertj.swing.test.ExpectedException;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;

/**
 * Tests for {@link KeyStrokeMappingProviderNames#iterator()}.
 * 
 * @author Alex Ruiz
 */
public class KeyStrokeMappingProviderNames_iterator_Test {
  private static KeyStrokeMappingProviderNames names;
  private Iterator<String> iterator;

  @Rule
  public ExpectedException thrown = none();

  @BeforeClass
  public static void setUpOnce() {
    names = KeyStrokeMappingProviderNames.generateNamesFrom(WINDOWS, US);
  }

  @Before
  public void setUp() {
    iterator = names.iterator();
  }

  @Test
  public void should_return_iterate_through_all_names() {
    assertThat(iterator).containsOnly("org.assertj.swing.keystroke.KeyStrokeMappingProvider_win_en_US",
        "org.assertj.swing.keystroke.KeyStrokeMappingProvider_win_en",
        "org.assertj.swing.keystroke.KeyStrokeMappingProvider_en");
  }

  @Test
  public void should_throw_error_if_iterator_does_not_have_more_elements() {
    iterator.next(); // full name
    iterator.next(); // without country
    iterator.next(); // language only
    thrown.expect(NoSuchElementException.class, "There are no more names to generate");
    iterator.next();
  }

  @Test
  public void should_throw_error_if_remove_is_called_on_iterator() {
    thrown.expectUnsupportedOperationException("This iterator does not support 'remove'");
    iterator.remove();
  }
}
