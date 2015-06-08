package com.mgaetan89.showsrage.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class SearchResultItem implements Serializable {
	private static final long serialVersionUID = -2543517162206194921L;

	@SerializedName("first_aired")
	private String firstAired;
	private int indexer;
	private String name;
	@SerializedName("tvdbid")
	private int tvDbId;
	@SerializedName("tvrageid")
	private int tvRageId;

	public String getFirstAired() {
		return this.firstAired;
	}

	public int getIndexer() {
		return this.indexer;
	}

	public String getName() {
		return this.name;
	}

	public int getTvDbId() {
		return this.tvDbId;
	}

	public int getTvRageId() {
		return this.tvRageId;
	}
}
