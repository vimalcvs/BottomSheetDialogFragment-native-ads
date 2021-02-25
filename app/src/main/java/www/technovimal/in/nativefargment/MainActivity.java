package www.technovimal.in.nativefargment;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.ads.nativetemplates.TemplateView;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdLoader;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.nativead.NativeAd;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.hellokh.sovary.addialog.R;

public class MainActivity extends AppCompatActivity {

    NativeAd nativeAd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MobileAds.initialize(MainActivity.this, initializationStatus -> {
        });

        TemplateView template = findViewById(R.id.ad_template);
        AdLoader.Builder adBuilder = new AdLoader.Builder(this, "ca-app-pub-3940256099942544/2247696110");
        adBuilder.withAdListener(new AdListener(){
            @Override
            public void onAdFailedToLoad(LoadAdError loadAdError) {
                super.onAdFailedToLoad(loadAdError);
                Toast.makeText(MainActivity.this, loadAdError.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        adBuilder.forNativeAd(nativeAd -> {
            template.setVisibility(View.VISIBLE);
            template.setNativeAd(nativeAd);
            Toast.makeText(MainActivity.this, "Ad loaded MainActivity", Toast.LENGTH_SHORT).show();
        });
        AdLoader adLoaderTwo = adBuilder.build();
        adLoaderTwo.loadAd(new AdRequest.Builder().build());





        //Fragment use
        AdLoader.Builder builder = new AdLoader.Builder(MainActivity.this,"ca-app-pub-3940256099942544/2247696110");
        builder.withAdListener(new AdListener(){
            @Override
            public void onAdFailedToLoad(LoadAdError loadAdError) {
                super.onAdFailedToLoad(loadAdError);
                Toast.makeText(MainActivity.this, loadAdError.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        builder.forNativeAd(NativeAd -> {
            nativeAd = NativeAd;
            Toast.makeText(MainActivity.this, "Ad loaded fragment", Toast.LENGTH_SHORT).show();
        });
        AdLoader adLoader =builder.build();
        adLoader.loadAd(new AdRequest.Builder().build());

    }

    @Override
    public void onBackPressed() {
        if(this.nativeAd == null) {
            finish();
        }
        else {
            BottomSheetDialogFragment bottomSheetDialogFragment = new ExitBottomSheetFragment(MainActivity.this, this.nativeAd);
            bottomSheetDialogFragment.show(getSupportFragmentManager(), bottomSheetDialogFragment.getTag());
        }
    }
}

