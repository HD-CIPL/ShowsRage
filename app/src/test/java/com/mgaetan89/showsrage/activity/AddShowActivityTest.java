package com.mgaetan89.showsrage.activity;

import com.mgaetan89.showsrage.R;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class AddShowActivityTest {
	private AddShowActivity activity;

	@Before
	public void before() {
		this.activity = new AddShowActivity();
	}

	@Test
	public void getSelectedMenuId() {
		assertThat(this.activity.getSelectedMenuId()).isEqualTo(R.id.menu_shows);
	}

	@Test
	public void getTitleResourceId() {
		assertThat(this.activity.getTitleResourceId()).isEqualTo(R.string.add_show);
	}

	@After
	public void after() {
		this.activity = null;
	}
}
