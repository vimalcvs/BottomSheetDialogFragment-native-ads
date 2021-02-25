package www.technovimal.in.nativefargment;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;

import com.google.android.ads.nativetemplates.TemplateView;
import com.google.android.gms.ads.nativead.NativeAd;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.hellokh.sovary.addialog.R;

public class ExitBottomSheetFragment extends BottomSheetDialogFragment {
    Activity activity;
    NativeAd nativeAd;

    public ExitBottomSheetFragment(Activity activity, NativeAd nativeAd) {
        this.activity = activity;
        this.nativeAd = nativeAd;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final BottomSheetDialog dialog = (BottomSheetDialog) super.onCreateDialog(savedInstanceState);
        dialog.setOnShowListener(dialog1 -> {
            FrameLayout bottomSheet = dialog.findViewById(com.google.android.material.R.id.design_bottom_sheet);
            BottomSheetBehavior behavior = BottomSheetBehavior.from(bottomSheet);
            behavior.setSkipCollapsed(true);
            behavior.setState(BottomSheetBehavior.STATE_EXPANDED);
        });

        return dialog;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.exit_sheet_fragment, container, false);


        Button button = v.findViewById(R.id.bt_exit);
        button.setOnClickListener(v1 -> activity.finish());

        TemplateView ad = v.findViewById(R.id.ad_template);
        if(this.nativeAd == null) {
            ad.setVisibility(View.GONE);
        }
        else {
            ad.setVisibility(View.VISIBLE);
            ad.setNativeAd(this.nativeAd);
        }
        getDialog().setCanceledOnTouchOutside(true);
        return v;
    }
}