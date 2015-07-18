package com.mgaetan89.showsrage.helper;

import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;

import com.bumptech.glide.load.Transformation;
import com.bumptech.glide.load.engine.Resource;
import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import com.bumptech.glide.load.resource.bitmap.BitmapResource;

// From: glide-transformations
// Version: 1.0.5
// Link: https://github.com/wasabeef/glide-transformations/blob/1.0.5/transformations/src/main/java/jp/wasabeef/glide/transformations/CropCircleTransformation.java
public class GlideCircleTransformation implements Transformation<Bitmap> {
	private final BitmapPool mBitmapPool;

	public GlideCircleTransformation(BitmapPool pool) {
		this.mBitmapPool = pool;
	}

	@Override
	public Resource<Bitmap> transform(Resource<Bitmap> resource, int outWidth, int outHeight) {
		Bitmap source = resource.get();
		int size = Math.min(source.getWidth(), source.getHeight());

		int width = (source.getWidth() - size) / 2;
		int height = (source.getHeight() - size) / 2;

		Bitmap bitmap = this.mBitmapPool.get(size, size, Bitmap.Config.ARGB_8888);
		if (bitmap == null) {
			bitmap = Bitmap.createBitmap(size, size, Bitmap.Config.ARGB_8888);
		}

		Canvas canvas = new Canvas(bitmap);
		Paint paint = new Paint();
		BitmapShader shader = new BitmapShader(source, BitmapShader.TileMode.CLAMP,
				BitmapShader.TileMode.CLAMP);
		if (width != 0 || height != 0) {
			Matrix matrix = new Matrix();
			matrix.setTranslate(-width, -height);
			shader.setLocalMatrix(matrix);
		}
		paint.setShader(shader);
		paint.setAntiAlias(true);

		@SuppressWarnings("MagicNumber")
		float r = size / 2f;
		canvas.drawCircle(r, r, r, paint);

		return BitmapResource.obtain(bitmap, this.mBitmapPool);
	}

	@Override
	public String getId() {
		return "CropCircleTransformation()";
	}
}
