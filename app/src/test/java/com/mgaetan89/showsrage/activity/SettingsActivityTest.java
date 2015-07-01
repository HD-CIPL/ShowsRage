package com.mgaetan89.showsrage.activity;

import com.mgaetan89.showsrage.R;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class SettingsActivityTest {
	private SettingsActivity activity;

	@Before
	public void before() {
		this.activity = new SettingsActivity();
	}

	@Test
	public void displayHomeAsUp() {
		assertThat(this.activity.displayHomeAsUp()).isTrue();
	}

	@Test
	public void getFragment() {
		assertThat(this.activity.getFragment()).isNull();
	}

	@Test
	public void getSelectedMenuId() {
		assertThat(this.activity.getSelectedMenuId()).isEqualTo(R.id.menu_settings);
	}

	@Test
	public void getTitleResourceId() {
		assertThat(this.activity.getTitleResourceId()).isEqualTo(R.string.settings);
	}

	@After
	public void after() {
		this.activity = null;
	}
}