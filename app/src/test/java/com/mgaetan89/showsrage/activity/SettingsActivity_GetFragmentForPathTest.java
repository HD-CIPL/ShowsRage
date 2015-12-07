package com.mgaetan89.showsrage.activity;

import com.mgaetan89.showsrage.fragment.SettingsAboutFragment;
import com.mgaetan89.showsrage.fragment.SettingsBehaviorFragment;
import com.mgaetan89.showsrage.fragment.SettingsDisplayFragment;
import com.mgaetan89.showsrage.fragment.SettingsExperimentalFeaturesFragment;
import com.mgaetan89.showsrage.fragment.SettingsFragment;
import com.mgaetan89.showsrage.fragment.SettingsNetworkFragment;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(Parameterized.class)
public class SettingsActivity_GetFragmentForPathTest {
	@Parameterized.Parameter(1)
	public Class<SettingsFragment> fragmentClass;

	@Parameterized.Parameter(0)
	public String path;

	@Test
	public void getFragmentForPath() {
		assertThat(SettingsActivity.getFragmentForPath(this.path)).isExactlyInstanceOf(this.fragmentClass);
	}

	@Parameterized.Parameters
	public static Collection<Object[]> data() {
		return Arrays.asList(new Object[][]{
				{null, SettingsFragment.class},
				{"", SettingsFragment.class},
				{" ", SettingsFragment.class},
				{"/", SettingsFragment.class},
				{"/wrong_path", SettingsFragment.class},
				{"/about", SettingsAboutFragment.class},
				{"/behavior", SettingsBehaviorFragment.class},
				{"/display", SettingsDisplayFragment.class},
				{"/experimental_features", SettingsExperimentalFeaturesFragment.class},
				{"/network", SettingsNetworkFragment.class},
		});
	}
}
