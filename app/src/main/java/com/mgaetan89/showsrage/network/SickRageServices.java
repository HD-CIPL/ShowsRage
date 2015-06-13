package com.mgaetan89.showsrage.network;

import com.mgaetan89.showsrage.model.ComingEpisodes;
import com.mgaetan89.showsrage.model.Episodes;
import com.mgaetan89.showsrage.model.GenericResponse;
import com.mgaetan89.showsrage.model.Histories;
import com.mgaetan89.showsrage.model.LogLevel;
import com.mgaetan89.showsrage.model.Logs;
import com.mgaetan89.showsrage.model.SearchResults;
import com.mgaetan89.showsrage.model.Seasons;
import com.mgaetan89.showsrage.model.ShowStats;
import com.mgaetan89.showsrage.model.Shows;
import com.mgaetan89.showsrage.model.ShowsStats;
import com.mgaetan89.showsrage.model.SingleEpisode;
import com.mgaetan89.showsrage.model.SingleShow;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Query;

public interface SickRageServices {
	@GET("/{api_path}/{api_key}/?cmd=show.addnew")
	void addNewShow(@Query("indexerid") int indexerId, Callback<GenericResponse> callback);

	@GET("/{api_path}/{api_key}/?cmd=history.clear")
	void clearHistory(Callback<GenericResponse> callback);

	@GET("/{api_path}/{api_key}/?cmd=future")
	void getComingEpisodes(Callback<ComingEpisodes> callback);

	@GET("/{api_path}/{api_key}/?cmd=episode")
	void getEpisode(@Query("indexerid") int indexerId, @Query("season") int season, @Query("episode") int episode, Callback<SingleEpisode> callback);

	@GET("/{api_path}/{api_key}/?cmd=show.seasons")
	void getEpisodes(@Query("indexerid") int indexerId, @Query("season") int season, Callback<Episodes> callback);

	@GET("/{api_path}/{api_key}/?cmd=history")
	void getHistory(Callback<Histories> callback);

	@GET("/{api_path}/{api_key}/?cmd=logs")
	void getLogs(@Query("min_level") LogLevel minLevel, Callback<Logs> callback);

	@GET("/{api_path}/{api_key}/?cmd=show.seasonlist")
	void getSeasons(@Query("indexerid") int indexerId, Callback<Seasons> callback);

	@GET("/{api_path}/{api_key}/?cmd=show")
	void getShow(@Query("indexerid") int indexerId, Callback<SingleShow> callback);

	@GET("/{api_path}/{api_key}/?cmd=shows&sort=name")
	void getShows(Callback<Shows> callback);

	@GET("/{api_path}/{api_key}/?cmd=shows.stats")
	void getShowsStats(Callback<ShowsStats> callback);

	@GET("/{api_path}/{api_key}/?cmd=show.stats")
	void getShowStats(@Query("indexerid") int indexerId, Callback<ShowStats> callback);

	@GET("/{api_path}/{api_key}/?cmd=sb.ping")
	void ping(Callback<GenericResponse> callback);

	@GET("/{api_path}/{api_key}/?cmd=sb.restart")
	void restart(Callback<GenericResponse> callback);

	@GET("/{api_path}/{api_key}/?cmd=sb.searchindexers")
	void search(@Query("name") String name, Callback<SearchResults> callback);

	@GET("/{api_path}/{api_key}/?cmd=episode.search")
	void searchEpisode(@Query("indexerid") int indexerId, @Query("season") int season, @Query("episode") int episode, Callback<GenericResponse> callback);

	@GET("/{api_path}/{api_key}/?cmd=episode.setstatus")
	void setEpisodeStatus(@Query("indexerid") int indexerId, @Query("season") int season, @Query("episode") int episode, @Query("force") int force, @Query("status") String status, Callback<GenericResponse> callback);
}
