package com.able.android.ui;

import android.os.Bundle;
import android.widget.ImageView;

import com.able.android.R;
import com.able.rx.activity.BaseLoadingActivity;
import com.able.rx.convert.ConvertCallback;
import com.able.rx.convert.RxConvert;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.MultiTransformation;

import able.com.debug.logger.Logger;
import jp.wasabeef.glide.transformations.BlurTransformation;
import jp.wasabeef.glide.transformations.RoundedCornersTransformation;

import static com.bumptech.glide.request.RequestOptions.bitmapTransform;

public class MainActivity extends BaseLoadingActivity {

    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imageView = (ImageView) findViewById(R.id.iv_image);
        ddd();
    }

    private void ddd() {
        MultiTransformation multi = new MultiTransformation(
                new BlurTransformation(25),
                new RoundedCornersTransformation(128, 0, RoundedCornersTransformation.CornerType.BOTTOM));
        Glide.with(this).load("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1518251723159&di=e8d3dca0cd534f29abe46eba057fbabc&imgtype=0&src=http%3A%2F%2Fimg17.3lian.com%2Fd%2Ffile%2F201703%2F02%2Ff80bdb35783222b0184a5b2bcddf0441.jpg")
                .apply(bitmapTransform(multi))
                .into(imageView);
    }

    private void convert() {
        RxConvert.getInstance().convert(new ConvertCallback<String, Integer>() {
            @Override
            public String getSource() {
                return "1,2,3,4";
            }

            @Override
            public Integer convertAction(String source) {
                return Integer.parseInt(source);
            }

            @Override
            public void convertComplete(boolean success, Integer s, Throwable throwable) {
                String result = "success:" + success + "\ns:" + s;

                if (success) {
                    Logger.d(result);
                } else {
                    Logger.d(throwable.toString());
                }
            }
        });
    }
}
