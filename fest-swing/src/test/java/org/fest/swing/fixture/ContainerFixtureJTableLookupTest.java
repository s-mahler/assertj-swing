/*
 * Created on Jun 7, 2009
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
package org.fest.swing.fixture;

import static org.fest.assertions.Assertions.assertThat;
import static org.fest.swing.edt.GuiActionRunner.execute;
import static org.fest.swing.test.core.CommonAssertions.failWhenExpectingException;

import javax.swing.JTable;

import org.fest.swing.core.GenericTypeMatcher;
import org.fest.swing.edt.GuiQuery;
import org.fest.swing.edt.GuiTask;
import org.fest.swing.exception.ComponentLookupException;
import org.fest.swing.test.core.RobotBasedTestCase;
import org.fest.swing.test.swing.TestWindow;
import org.junit.Test;

/**
 * Tests lookup of {@code JTable}s in {@link AbstractContainerFixture}.
 * 
 * @author Alex Ruiz
 */
public class ContainerFixtureJTableLookupTest extends RobotBasedTestCase {
  private ConcreteContainerFixture fixture;
  private MyWindow window;

  @Override
  protected final void onSetUp() {
    window = MyWindow.createNew();
    fixture = new ConcreteContainerFixture(robot, window);
    robot.showWindow(window);
  }

  @Test
  public void shouldFindJTableByType() {
    JTableFixture table = fixture.table();
    assertThatFixtureHasCorrectJTable(table);
  }

  @Test
  public void shouldFailIfJTableCannotBeFoundByType() {
    execute(new GuiTask() {
      @Override
      protected void executeInEDT() {
        window.remove(window.table);
      }
    });
    robot.waitForIdle();
    try {
      fixture.table();
      failWhenExpectingException();
    } catch (ComponentLookupException e) {
      assertThat(e.getMessage()).contains("Unable to find component using matcher").contains(
          "type=javax.swing.JTable, requireShowing=true");
    }
  }

  @Test
  public void shouldFindJTableByName() {
    JTableFixture table = fixture.table("myTable");
    assertThatFixtureHasCorrectJTable(table);
  }

  @Test
  public void shouldFailIfJTableCannotBeFoundByName() {
    try {
      fixture.table("someTable");
      failWhenExpectingException();
    } catch (ComponentLookupException e) {
      assertThat(e.getMessage()).contains("Unable to find component using matcher").contains(
          "name='someTable', type=javax.swing.JTable, requireShowing=true");
    }
  }

  @Test
  public void shouldFindJTableWithCustomMatcher() {
    JTableFixture table = fixture.table(new GenericTypeMatcher<JTable>(JTable.class) {
      @Override
      protected boolean isMatching(JTable t) {
        return t.getRowCount() == 8 && t.getColumnCount() == 6;
      }
    });
    assertThatFixtureHasCorrectJTable(table);
  }

  private void assertThatFixtureHasCorrectJTable(JTableFixture tableFixture) {
    assertThat(tableFixture.target()).isSameAs(window.table);
  }

  @Test
  public void shouldFailIfJTableCannotBeFoundWithCustomMatcher() {
    try {
      fixture.table(new GenericTypeMatcher<JTable>(JTable.class) {
        @Override
        protected boolean isMatching(JTable t) {
          return false;
        }
      });
      failWhenExpectingException();
    } catch (ComponentLookupException e) {
      assertThat(e.getMessage()).contains("Unable to find component using matcher");
    }
  }

  private static class MyWindow extends TestWindow {
    final JTable table = new JTable(8, 6);

    static MyWindow createNew() {
      return execute(new GuiQuery<MyWindow>() {
        @Override
        protected MyWindow executeInEDT() {
          return new MyWindow();
        }
      });
    }

    private MyWindow() {
      super(ContainerFixtureJTableLookupTest.class);
      table.setName("myTable");
      addComponents(table);
    }
  }
}
