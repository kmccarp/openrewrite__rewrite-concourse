/*
 * Copyright 2021 the original author or authors.
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * https://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.openrewrite.concourse

import org.junit.jupiter.api.Test
import org.openrewrite.Recipe
import org.openrewrite.yaml.YamlRecipeTest

class FindResourceTest : YamlRecipeTest {
    override val recipe: Recipe
        get() = FindResource("git")

    @Test
    fun concourseResource() = assertChanged(
        before = """
            resources:
            - name: git-repo
              type: git
              icon: github
              source:
                uri: ((github-repo))
            - name: file
              type: file
        """,
        after = """
            resources:
            - name: git-repo
              ~~>type: git
              icon: github
              source:
                uri: ((github-repo))
            - name: file
              type: file
        """
    )
}
