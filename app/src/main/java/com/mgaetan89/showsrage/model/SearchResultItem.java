package com.mgaetan89.showsrage.model;

import android.support.annotation.Nullable;
import android.support.annotation.StringRes;

import com.google.gson.annotations.SerializedName;
import com.mgaetan89.showsrage.R;

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

	public int getIndexerId() {
		switch (this.indexer) {
			case 1:
				return this.getTvDbId();

			case 2:
				return this.getTvRageId();
		}

		return 0;
	}

	@StringRes
	public int getIndexerNameResource() {
		switch (this.indexer) {
			case 1:
				return R.string.the_tvdb;

			case 2:
				return R.string.tvrage;
		}

		return 0;
	}

	@Nullable
	public Indexer getIndexerType() {
		switch (this.indexer) {
			case 1:
				return Indexer.TVDB;

			case 2:
				return Indexer.TVRAGE;
		}

		return null;
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
