package com.mgaetan89.showsrage;

import com.mgaetan89.showsrage.model.LogLevel;

import retrofit.RestAdapter;

public abstract class Constants {
	public static final RestAdapter.LogLevel NETWORK_LOG_LEVEL = BuildConfig.DEBUG ? RestAdapter.LogLevel.FULL : RestAdapter.LogLevel.NONE;

	public abstract static class Bundle {
		public static final String COLOR_ACCENT = "color_accent";
		public static final String COLOR_PRIMARY = "color_primary";
		public static final String EPISODE_MODEL = "episode_model";
		public static final String EPISODE_NUMBER = "episode_number";
		public static final String EPISODES_COUNT = "episodes_count";
		public static final String FILTER_MODE = "filter_mode";
		public static final String FILTER_STATUS = "filter_status";
		public static final String INDEXER_ID = "indexer_id";
		public static final String MENU_ID = "menu_id";
		public static final String SCHEDULES = "schedules";
		public static final String SEARCH_QUERY = "search_query";
		public static final String SEASON_NUMBER = "season_number";
		public static final String SHOW_MODEL = "show_model";
		public static final String SHOWS = "shows";
		public static final String UPDATE_MODEL = "update_model";
	}

	public abstract static class Intents {
		public static final String ACTION_EPISODE_ACTION_SELECTED = BuildConfig.APPLICATION_ID + ".action.episode_action_selected";
		public static final String ACTION_EPISODE_SELECTED = BuildConfig.APPLICATION_ID + ".action.episode_selected";
		public static final String ACTION_FILTER_SHOWS = BuildConfig.APPLICATION_ID + ".action.filter_shows";
		public static final String ACTION_SEARCH_RESULT_SELECTED = BuildConfig.APPLICATION_ID + ".action.search_result_selected";
		public static final String ACTION_SHOW_SELECTED = BuildConfig.APPLICATION_ID + ".action.show_selected";
	}

	public abstract static class Preferences {
		public abstract static class Fields {
			public static final String LAST_VERSION_CHECK_TIME = "last_version_check_time";
			public static final String LOGS_LEVEL = "logs_level";
			public static final String ROOT_DIRS = "root_dirs";
		}

		public abstract static class Defaults {
			public static final LogLevel LOGS_LEVEL = LogLevel.ERROR;
		}
	}
}
