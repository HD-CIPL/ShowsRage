package com.mgaetan89.showsrage.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public class Show implements Serializable {
	@SerializedName("air_by_date")
	private int airByDate;
	private String airs;
	private int anime;
	@SerializedName("archive_firstmatch")
	private int archiveFirstmatch;
	private Map<String, Integer> cache;
	@SerializedName("dvdorder")
	private int dvdOrder;
	@SerializedName("flatten_folders")
	private int flattenFolders;
	private List<String> genre;
	@SerializedName("imdbid")
	private String imdbId;
	@SerializedName("indexerid")
	private int indexerId;
	private String language;
	private String location;
	private String network;
	@SerializedName("next_ep_airdate")
	private String nextEpisodeAirDate;
	private int paused;
	private String quality;
	@SerializedName("quality_details")
	private Quality qualityDetails;
	@SerializedName("rls_ignore_words")
	private List<String> rlsIgnoreWords;
	@SerializedName("rls_require_words")
	private List<String> rlsRequireWords;
	private int scene;
	@SerializedName("season_list")
	private List<Integer> seasonList;
	@SerializedName("show_name")
	private String showName;
	private int sports;
	private String status;
	private int subtitles;
	@SerializedName("tvdbid")
	private int tvDbId;
	@SerializedName("tvrage_id")
	private int tvRageId;
	@SerializedName("tvrage_name")
	private String tvRageName;

	public int getAirByDate() {
		return this.airByDate;
	}

	public String getAirs() {
		return this.airs;
	}

	public int getAnime() {
		return this.anime;
	}

	public int getArchiveFirstmatch() {
		return this.archiveFirstmatch;
	}

	public Map<String, Integer> getCache() {
		return this.cache;
	}

	public int getDvdOrder() {
		return this.dvdOrder;
	}

	public int getFlattenFolders() {
		return this.flattenFolders;
	}

	public List<String> getGenre() {
		return this.genre;
	}

	public String getImdbId() {
		return this.imdbId;
	}

	public int getIndexerId() {
		return this.indexerId;
	}

	public String getLanguage() {
		return this.language;
	}

	public String getLocation() {
		return this.location;
	}

	public String getNetwork() {
		return this.network;
	}

	public String getNextEpisodeAirDate() {
		return this.nextEpisodeAirDate;
	}

	public int getPaused() {
		return this.paused;
	}

	public String getQuality() {
		return this.quality;
	}

	public Quality getQualityDetails() {
		return this.qualityDetails;
	}

	public List<String> getRlsIgnoreWords() {
		return this.rlsIgnoreWords;
	}

	public List<String> getRlsRequireWords() {
		return this.rlsRequireWords;
	}

	public int getScene() {
		return this.scene;
	}

	public List<Integer> getSeasonList() {
		return this.seasonList;
	}

	public String getShowName() {
		return this.showName;
	}

	public int getSports() {
		return this.sports;
	}

	public String getStatus() {
		return this.status;
	}

	public int getSubtitles() {
		return this.subtitles;
	}

	public int getTvDbId() {
		return this.tvDbId;
	}

	public int getTvRageId() {
		return this.tvRageId;
	}

	public String getTvRageName() {
		return this.tvRageName;
	}
}
