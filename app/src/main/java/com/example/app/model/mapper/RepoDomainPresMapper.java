/*
 *     Copyright (c) 2015-present Victor Hidalgo Lorenzo
 *
 *     Licensed under the Apache License, Version 2.0 (the "License");
 *     you may not use this file except in compliance with the License.
 *     You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *     Unless required by applicable law or agreed to in writing, software
 *     distributed under the License is distributed on an "AS IS" BASIS,
 *     WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *     See the License for the specific language governing permissions and
 *     limitations under the License.
 */

package com.example.app.model.mapper;

import com.example.app.model.Repository;
import com.example.domain.model.RepoDomain;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class RepoDomainPresMapper {

  @Inject
  public RepoDomainPresMapper() {
    // Empty
  }

  public Repository transform(RepoDomain repoDomain) {
    return Repository.create(repoDomain.getId(), repoDomain.getName());
  }

  public List<Repository> transform(Collection<RepoDomain> repoEntityCollection) {
    List<Repository> repositoryList = new ArrayList<>(repoEntityCollection.size());
    Repository repository;
    for (RepoDomain repoDomain : repoEntityCollection) {
      repository = transform(repoDomain);
      if (repository != null) {
        repositoryList.add(repository);
      }
    }

    return repositoryList;
  }
}
