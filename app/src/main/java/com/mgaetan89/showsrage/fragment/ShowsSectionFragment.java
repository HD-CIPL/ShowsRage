package com.mgaetan89.showsrage.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mgaetan89.showsrage.Constants;
import com.mgaetan89.showsrage.R;
import com.mgaetan89.showsrage.activity.AddShowActivity;
import com.mgaetan89.showsrage.adapter.ShowsAdapter;
import com.mgaetan89.showsrage.model.Show;
import com.mgaetan89.showsrage.model.ShowStat;
import com.mgaetan89.showsrage.model.ShowStatWrapper;
import com.mgaetan89.showsrage.model.ShowStats;
import com.mgaetan89.showsrage.model.ShowStatsWrapper;
import com.mgaetan89.showsrage.network.SickRageApi;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class ShowsSectionFragment extends Fragment implements View.OnClickListener {
	@Nullable
	private ShowsAdapter adapter = null;

	@Nullable
	private TextView emptyView = null;

	@Nullable
	private RecyclerView recyclerView = null;

	@NonNull
	private final List<Show> shows = new ArrayList<>();

	public ShowsSectionFragment() {
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);

		Bundle arguments = this.getArguments();

		if (arguments != null) {
			Collection<Show> shows = (Collection<Show>) arguments.getSerializable(Constants.Bundle.SHOWS);

			if (shows != null) {
				this.shows.addAll(shows);
			}
		}

		if (!this.shows.isEmpty()) {
			String command = getCommand(this.shows);
			Map<String, Integer> parameters = getCommandParameters(this.shows);

			SickRageApi.getInstance().getServices().getShowStats(command, parameters, new ShowStatsCallback());
		}

		if (this.shows.isEmpty()) {
			if (this.emptyView != null) {
				this.emptyView.setVisibility(View.VISIBLE);
			}

			if (this.recyclerView != null) {
				this.recyclerView.setVisibility(View.GONE);
			}
		} else {
			if (this.emptyView != null) {
				this.emptyView.setVisibility(View.GONE);
			}

			if (this.recyclerView != null) {
				this.recyclerView.setVisibility(View.VISIBLE);
			}
		}

		if (this.adapter != null) {
			this.adapter.notifyDataSetChanged();
		}
	}

	@Override
	public void onClick(View view) {
		if (view == null) {
			return;
		}

		if (view.getId() == R.id.add_show) {
			Intent intent = new Intent(this.getActivity(), AddShowActivity.class);

			this.startActivity(intent);
		}
	}

	@Override
	public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_shows_section, container, false);

		if (view != null) {
			this.emptyView = (TextView) view.findViewById(android.R.id.empty);
			this.recyclerView = (RecyclerView) view.findViewById(android.R.id.list);

			FloatingActionButton addShow = (FloatingActionButton) view.findViewById(R.id.add_show);

			if (addShow != null) {
				addShow.setOnClickListener(this);
			}

			if (this.recyclerView != null) {
				int columnCount = this.getResources().getInteger(R.integer.shows_column_count);
				this.adapter = new ShowsAdapter(this.shows);

				this.recyclerView.setAdapter(this.adapter);
				this.recyclerView.setLayoutManager(new GridLayoutManager(this.getActivity(), columnCount));
			}
		}

		return view;
	}

	@Override
	public void onDestroy() {
		this.shows.clear();

		super.onDestroy();
	}

	@Override
	public void onDestroyView() {
		this.emptyView = null;
		this.recyclerView = null;

		super.onDestroyView();
	}

	@NonNull
	/* package */ static String getCommand(@Nullable Iterable<Show> shows) {
		StringBuilder command = new StringBuilder();

		if (shows != null) {
			for (Show show : shows) {
				if (!isShowValid(show)) {
					continue;
				}

				if (command.length() > 0) {
					command.append("|");
				}

				command.append("show.stats_").append(show.getIndexerId());
			}
		}

		return command.toString();
	}

	@NonNull
	/* package */ static Map<String, Integer> getCommandParameters(@Nullable Iterable<Show> shows) {
		Map<String, Integer> parameters = new HashMap<>();

		if (shows != null) {
			for (Show show : shows) {
				if (!isShowValid(show)) {
					continue;
				}

				int indexerId = show.getIndexerId();

				parameters.put("show.stats_" + indexerId + ".indexerid", indexerId);
			}
		}

		return parameters;
	}

	/* package */
	static boolean isShowValid(@Nullable Show show) {
		return show != null && show.getIndexerId() > 0;
	}

	private final class ShowStatsCallback implements Callback<ShowStatsWrapper> {
		@Override
		public void failure(RetrofitError error) {
			error.printStackTrace();
		}

		@Override
		public void success(ShowStatsWrapper showStatsWrapper, Response response) {
			ShowStatWrapper data = showStatsWrapper.getData();
			Map<Integer, ShowStats> showStats = data.getShowStats();

			if (showStats != null) {
				for (Map.Entry<Integer, ShowStats> entry : showStats.entrySet()) {
					ShowStat showStatsData = entry.getValue().getData();
					int indexerId = entry.getKey();

					for (Show show : ShowsSectionFragment.this.shows) {
						if (show.getIndexerId() == indexerId) {
							show.setEpisodesCount(showStatsData.getTotal());
							show.setDownloaded(showStatsData.getTotalDone());
							show.setSnatched(showStatsData.getTotalPending());

							break;
						}
					}
				}
			}

			if (ShowsSectionFragment.this.adapter != null) {
				ShowsSectionFragment.this.adapter.notifyDataSetChanged();
			}
		}
	}
}
