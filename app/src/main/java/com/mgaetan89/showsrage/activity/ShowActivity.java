package com.mgaetan89.showsrage.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.MenuItem;
import android.widget.Toast;

import com.mgaetan89.showsrage.Constants;
import com.mgaetan89.showsrage.R;
import com.mgaetan89.showsrage.adapter.EpisodesAdapter;
import com.mgaetan89.showsrage.fragment.ShowFragment;
import com.mgaetan89.showsrage.model.Episode;
import com.mgaetan89.showsrage.model.GenericResponse;
import com.mgaetan89.showsrage.model.Show;
import com.mgaetan89.showsrage.network.SickRageApi;

import retrofit.Callback;

/**
 * Requires a {@link com.mgaetan89.showsrage.Constants.Bundle#SHOW_MODEL Constants.Bundle#SHOW_MODEL} associated with a non-{@code null} {@link com.mgaetan89.showsrage.model.Show Show} in its {@link android.content.Intent Intent}.
 */
public class ShowActivity extends BaseActivity implements EpisodesAdapter.OnEpisodeActionSelectedListener, EpisodesAdapter.OnEpisodeSelectedListener {
	@Nullable
	private Show show = null;

	@Override
	public void onEpisodeActionSelected(int seasonNumber, int episodeNumber, MenuItem action) {
		switch (action.getItemId()) {
			case R.id.menu_episode_search:
				this.searchEpisode(seasonNumber, episodeNumber);

				break;

			case R.id.menu_episode_set_status_archived:
			case R.id.menu_episode_set_status_failed:
			case R.id.menu_episode_set_status_ignored:
			case R.id.menu_episode_set_status_skipped:
			case R.id.menu_episode_set_status_wanted:
				this.setEpisodeStatus(seasonNumber, episodeNumber, Episode.getStatusForMenuId(action.getItemId()));

				break;
		}
	}

	@Override
	public void onEpisodeSelected(int seasonNumber, int episodeNumber, @NonNull Episode episode, int episodesCount) {
		Intent intent = new Intent(this, EpisodeActivity.class);
		intent.putExtra(Constants.Bundle.COLOR_PRIMARY, this.getIntent().getIntExtra(Constants.Bundle.COLOR_PRIMARY, 0));
		intent.putExtra(Constants.Bundle.EPISODE_MODEL, episode);
		intent.putExtra(Constants.Bundle.EPISODE_NUMBER, episodeNumber);
		intent.putExtra(Constants.Bundle.EPISODES_COUNT, episodesCount);
		intent.putExtra(Constants.Bundle.SEASON_NUMBER, seasonNumber);
		intent.putExtra(Constants.Bundle.SHOW_MODEL, this.show);

		this.startActivity(intent);
	}

	@Override
	protected boolean displayHomeAsUp() {
		return true;
	}

	@Override
	protected Fragment getFragment() {
		return new ShowFragment();
	}

	@Override
	protected int getSelectedMenuId() {
		return R.id.menu_shows;
	}

	@Override
	protected int getTitleResourceId() {
		return R.string.show;
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		this.show = (Show) this.getIntent().getSerializableExtra(Constants.Bundle.SHOW_MODEL);
	}

	private void searchEpisode(int seasonNumber, int episodeNumber) {
		if (this.show == null) {
			return;
		}

		Toast.makeText(this, this.getString(R.string.episode_search, episodeNumber, seasonNumber), Toast.LENGTH_SHORT).show();

		SickRageApi.getInstance().getServices().searchEpisode(this.show.getIndexerId(), seasonNumber, episodeNumber, this);
	}

	private void setEpisodeStatus(final int seasonNumber, final int episodeNumber, final String status) {
		if (this.show == null) {
			return;
		}

		final Callback<GenericResponse> callback = this;
		final int indexerId = this.show.getIndexerId();

		new AlertDialog.Builder(this)
				.setMessage(R.string.replace_existing_episode)
				.setPositiveButton(R.string.replace, new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						SickRageApi.getInstance().getServices().setEpisodeStatus(indexerId, seasonNumber, episodeNumber, 1, status, callback);
					}
				})
				.setNegativeButton(R.string.keep, new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						SickRageApi.getInstance().getServices().setEpisodeStatus(indexerId, seasonNumber, episodeNumber, 0, status, callback);
					}
				})
				.show();
	}
}
