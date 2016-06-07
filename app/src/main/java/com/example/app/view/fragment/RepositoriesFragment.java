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

package com.example.app.view.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import butterknife.BindView;
import com.example.app.AndroidApp;
import com.example.app.R;
import com.example.app.exception.ErrorMessageFactory;
import com.example.app.model.Repository;
import com.example.app.presenter.RepositoriesPresenter;
import com.example.app.view.adapter.RepositoryAdapter;
import com.example.app.view.viewPresenter.RepositoriesView;
import com.example.domain.exception.ErrorBundle;
import java.util.List;
import javax.inject.Inject;

public class RepositoriesFragment extends BaseFragment implements RepositoriesView {

  @Inject
  RepositoriesPresenter repositoriesPresenter;

  @BindView(R.id.rl_repository_list)
  RelativeLayout rlRepositoryList;
  @BindView(R.id.rv_repo)
  RecyclerView rvRepo;
  @BindView(R.id.rl_progress)
  RelativeLayout rlProgress;
  @BindView(R.id.rl_retry)
  RelativeLayout rlRetry;

  public static RepositoriesFragment newInstance() {
    return new RepositoriesFragment();
  }

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    AndroidApp.get(getActivity()).getComponent().inject(this);
  }

  @Override
  public View onCreateView(
      LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState
  ) {
    return inflater.inflate(R.layout.fragment_repositories, container, false);
  }

  @Override
  public void onViewCreated(
      View view,
      @Nullable
      Bundle savedInstanceState
  ) {
    super.onViewCreated(view, savedInstanceState);
    setupRecyclerView(rvRepo);
    if (repositoriesPresenter != null) {
      repositoriesPresenter.attachView(this);
      repositoriesPresenter.initialize();
    }
  }

  @Override
  public void showRepositories(List<Repository> repositories) {
    RepositoryAdapter adapter = (RepositoryAdapter) rvRepo.getAdapter();
    adapter.setRepositoryList(repositories);
    adapter.notifyDataSetChanged();
  }

  @Override
  public void showLoading() {
    if (rlProgress != null) {
      rlProgress.setVisibility(View.VISIBLE);
    }
  }

  @Override
  public void hideLoading() {
    if (rlProgress != null) {
      rlProgress.setVisibility(View.GONE);
    }
  }

  @Override
  public void showRetry() {
    if (rlRetry != null) {
      rlRetry.setVisibility(View.VISIBLE);
    }
  }

  @Override
  public void hideRetry() {
    if (rlRetry != null) {
      rlRetry.setVisibility(View.GONE);
    }
  }

  @Override
  public void showError(ErrorBundle errorBundle) {
    if (isAdded() && rlRepositoryList != null) {
      this.showSnackbarMessage(this.rlRepositoryList,
                               ErrorMessageFactory.create(getContext(), errorBundle.getException())
      );
    }
  }

  private void setupRecyclerView(@NonNull RecyclerView recyclerView) {
    RepositoryAdapter adapter = new RepositoryAdapter(getContext());
    recyclerView.setAdapter(adapter);
    recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
  }
}
