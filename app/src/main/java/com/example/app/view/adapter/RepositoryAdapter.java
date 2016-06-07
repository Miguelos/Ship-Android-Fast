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

package com.example.app.view.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.example.app.R;
import com.example.app.model.Repository;
import java.util.Collections;
import java.util.List;

public class RepositoryAdapter extends RecyclerView.Adapter<RepositoryAdapter.RepoViewHolder> {

  private List<Repository> repositoryList;

  private final LayoutInflater layoutInflater;

  public RepositoryAdapter(Context context) {
    this.layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    this.repositoryList = Collections.emptyList();
  }

  @Override
  public RepoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    final View view = this.layoutInflater.inflate(R.layout.view_repo_row, parent, false);
    return new RepoViewHolder(view);
  }

  @Override
  public void onBindViewHolder(RepoViewHolder holder, int position) {
    final Repository repository = repositoryList.get(position);
    holder.tvId.setText(String.valueOf(repository.getId()));
    holder.tvName.setText(repository.getName());
  }

  @Override
  public int getItemCount() {
    return (this.repositoryList != null) ? this.repositoryList.size() : 0;
  }

  public void setRepositoryList(List<Repository> repositoryList) {
    this.repositoryList = repositoryList;
  }

  static class RepoViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.tv_id)
    TextView tvId;
    @BindView(R.id.tv_name)
    TextView tvName;

    public RepoViewHolder(View view) {
      super(view);
      ButterKnife.bind(this, view);
    }
  }
}
