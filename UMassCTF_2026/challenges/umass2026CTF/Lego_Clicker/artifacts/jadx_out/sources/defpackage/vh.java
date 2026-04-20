package defpackage;

import android.content.ClipData;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.KeyEvent;

/* JADX INFO: compiled from: r8-map-id-51b54c9e59b63e0fb0abe04daff984b4fb85d07ef3fcb7973595bce215c10515 */
/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class vh {
    public final /* synthetic */ int a;
    public final /* synthetic */ KeyEvent.Callback b;

    public /* synthetic */ vh(KeyEvent.Callback callback, int i) {
        this.a = i;
        this.b = callback;
    }

    public boolean a(e0 e0Var, int i, Bundle bundle) {
        t8 e0Var2;
        s3 s3Var = (s3) this.b;
        int i2 = Build.VERSION.SDK_INT;
        if (i2 >= 25 && (i & 1) != 0) {
            try {
                ((gf) e0Var.b).a();
                Parcelable parcelable = (Parcelable) ((gf) e0Var.b).d();
                bundle = bundle == null ? new Bundle() : new Bundle(bundle);
                bundle.putParcelable("androidx.core.view.extra.INPUT_CONTENT_INFO", parcelable);
            } catch (Exception e) {
                Log.w("InputConnectionCompat", "Can't insert content from IME; requestPermission() failed", e);
                return false;
            }
        }
        gf gfVar = (gf) e0Var.b;
        ClipData clipData = new ClipData(gfVar.c(), new ClipData.Item(gfVar.e()));
        if (i2 >= 31) {
            e0Var2 = new e0(clipData, 2);
        } else {
            u8 u8Var = new u8();
            u8Var.b = clipData;
            u8Var.c = 2;
            e0Var2 = u8Var;
        }
        e0Var2.m(gfVar.b());
        e0Var2.setExtras(bundle);
        return os.f(s3Var, e0Var2.build()) == null;
    }
}
