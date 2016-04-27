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

package com.example.data.entity.mapper;

import com.example.data.entity.RepoEntity;
import com.example.domain.model.RepoDomain;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class RepoDataDomainMapper {

  @Inject
  public RepoDataDomainMapper() {
    // Empty
  }

  public RepoDomain transform(RepoEntity repoEntity) {
    return RepoDomain.create(repoEntity.getId(), repoEntity.getName());
  }

  public List<RepoDomain> transform(Collection<RepoEntity> repoEntityCollection) {
    List<RepoDomain> repoDomainList = new ArrayList<>(repoEntityCollection.size());
    RepoDomain repoDomain;
    for (RepoEntity repoEntity : repoEntityCollection) {
      repoDomain = transform(repoEntity);
      if (repoDomain != null) {
        repoDomainList.add(repoDomain);
      }
    }

    return repoDomainList;
  }
}
