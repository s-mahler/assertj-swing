/*
 * Created on Nov 3, 2008
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
 * Copyright @2008-2013 the original author or authors.
 */
package org.assertj.swing.driver;

import static org.assertj.swing.edt.GuiActionRunner.execute;

import javax.swing.JList;

import org.assertj.swing.annotation.RunsInEDT;
import org.assertj.swing.edt.GuiTask;
import org.junit.Test;

/**
 * Tests for {@link JListItemPreconditions#checkIndexInBounds(JList, int)}.
 * 
 * @author Alex Ruiz
 */
public class JListItemIndexValidator_validateIndex_Test extends JListItemIndexValidator_TestCase {
  @Test
  public void should_pass_if_index_is_valid() {
    validateIndex(0);
  }

  @Test
  public void should_throw_error_if_index_is_negative() {
    thrown.expectIndexOutOfBoundsException("Item index (-1) should be between [0] and [2] (inclusive)");
    validateIndex(-1);
  }

  @Test
  public void should_throw_error_if_index_is_greater_than_index_of_last_item() {
    thrown.expectIndexOutOfBoundsException("Item index (3) should be between [0] and [2] (inclusive)");
    validateIndex(3);
  }

  @RunsInEDT
  private void validateIndex(int index) {
    validateIndex(list, index);
  }

  @RunsInEDT
  private static void validateIndex(final JList list, final int index) {
    execute(new GuiTask() {
      @Override
      protected void executeInEDT() {
        JListItemPreconditions.checkIndexInBounds(list, index);
      }
    });
  }
}
