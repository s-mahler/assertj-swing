/*
 * Created on Mar 9, 2010
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
 * Copyright @2010 the original author or authors.
 */
package org.assertj.swing.testng.listener;

import static java.io.File.separator;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.util.Files.newFolder;
import static org.assertj.core.util.Files.newTemporaryFolder;
import static org.assertj.core.util.Strings.concat;
import static org.easymock.EasyMock.expect;
import static org.easymock.classextension.EasyMock.createMock;

import java.io.File;

import org.assertj.core.util.FilesException;
import org.fest.mocks.EasyMockTemplate;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.testng.ITestContext;

/**
 * Tests for <code>{@link OutputDirectory#createIfNecessary()}</code>.
 * 
 * @author Alex Ruiz
 */
public class OutputDirectory_createIfNecessary_Test {

  private ITestContext context;
  private String parentPath;
  private String unwritablePath;
  private String path;

  @Before
  public void setUp() {
    context = createMock(ITestContext.class);
    parentPath = newTemporaryFolder().getAbsolutePath();
    unwritablePath = concat(parentPath, separator, "notwritable");
    path = concat(parentPath, separator, "abc");
  }

  @After
  public void tearDown() {
    deleteFiles(path, unwritablePath, parentPath);
  }

  private void deleteFiles(String... paths) {
    for (String p : paths)
      new File(p).delete();
  }

  @Test
  public void should_not_create_output_folder_if_it_already_exists() {
    assertThat(new File(parentPath)).exists();
    new EasyMockTemplate(context) {
      @Override
      protected void expectations() {
        expect(context.getOutputDirectory()).andReturn(parentPath);
      }

      @Override
      protected void codeToTest() {
        OutputDirectory output = new OutputDirectory(context);
        output.createIfNecessary();
        assertThat(new File(parentPath)).exists();
      }
    }.run();
  }

  @Test
  public void should_create_output_folder_if_it_does_not_exist() {
    assertThat(new File(path)).doesNotExist();
    new EasyMockTemplate(context) {
      @Override
      protected void expectations() {
        expect(context.getOutputDirectory()).andReturn(path);
      }

      @Override
      protected void codeToTest() {
        OutputDirectory output = new OutputDirectory(context);
        output.createIfNecessary();
        assertThat(new File(path)).exists();
      }
    }.run();
  }

  @Test(expected = FilesException.class)
  public void should_throw_error_if_output_folder_cannot_be_created() {
    File folder = newFolder(unwritablePath);
    assertThat(folder.setReadOnly()).isTrue();
    try {
      new EasyMockTemplate(context) {
        @Override
        protected void expectations() {
          expect(context.getOutputDirectory()).andReturn(concat(unwritablePath, separator, "zz:-//"));
        }

        @Override
        protected void codeToTest() {
          OutputDirectory output = new OutputDirectory(context);
          output.createIfNecessary();
        }
      }.run();
    } finally {
      assertThat(folder.setWritable(true)).isTrue();
    }
  }
}
