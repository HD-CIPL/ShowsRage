package com.mgaetan89.showsrage.fragment;

import android.content.res.Resources;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.widget.Spinner;

import com.mgaetan89.showsrage.R;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Collection;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;

@RunWith(Parameterized.class)
public class AddShowOptionsFragment_GetStatusTest {
	@Parameterized.Parameter(1)
	public String status;

	@Parameterized.Parameter(0)
	public Spinner spinner;

	private AddShowOptionsFragment fragment;

	@Before
	public void before() {
		Resources resources = mock(Resources.class);
		when(resources.getStringArray(R.array.status_keys)).thenReturn(new String[]{
				"wanted",
				"skipped",
				"archived",
				"ignored",
		});

		FragmentActivity activity = mock(FragmentActivity.class);
		when(activity.getResources()).thenReturn(resources);

		this.fragment = spy(new AddShowOptionsFragment());

		try {
			Field fragmentActivityField = Fragment.class.getDeclaredField("mActivity");
			fragmentActivityField.setAccessible(true);
			fragmentActivityField.set(this.fragment, activity);
		} catch (IllegalAccessException ignored) {
		} catch (NoSuchFieldException ignored) {
		}

		this.fragment.onAttach(activity);

		when(this.fragment.getResources()).thenReturn(resources);
	}

	@Test
	public void getStatus() {
		assertThat(this.fragment.getStatus(this.spinner)).isEqualTo(this.status);
	}

	@After
	public void after() {
		this.fragment = null;
	}

	@Parameterized.Parameters
	public static Collection<Object[]> data() {
		return Arrays.asList(new Object[][]{
				{null, "wanted"},
				{getMockedSpinner(-1), null},
				{getMockedSpinner(0), "wanted"},
				{getMockedSpinner(1), "skipped"},
				{getMockedSpinner(2), "archived"},
				{getMockedSpinner(3), "ignored"},
				{getMockedSpinner(4), null},
		});
	}

	private static Spinner getMockedSpinner(int selectedItemPosition) {
		Spinner spinner = mock(Spinner.class);
		when(spinner.getSelectedItemPosition()).thenReturn(selectedItemPosition);

		return spinner;
	}
}
