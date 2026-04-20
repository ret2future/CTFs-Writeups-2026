package defpackage;

import android.graphics.Typeface;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.widget.TextView;
import java.lang.ref.WeakReference;

/* JADX INFO: compiled from: r8-map-id-51b54c9e59b63e0fb0abe04daff984b4fb85d07ef3fcb7973595bce215c10515 */
/* JADX INFO: loaded from: classes.dex */
public final class r4 {
    public final /* synthetic */ int a;
    public final /* synthetic */ int b;
    public final /* synthetic */ WeakReference c;
    public final /* synthetic */ w4 d;

    public r4(w4 w4Var, int i, int i2, WeakReference weakReference) {
        this.d = w4Var;
        this.a = i;
        this.b = i2;
        this.c = weakReference;
    }

    public final void a() {
        new Handler(Looper.getMainLooper()).post(new cn());
    }

    public final void b(Typeface typeface) {
        int i;
        if (Build.VERSION.SDK_INT >= 28 && (i = this.a) != -1) {
            typeface = v4.a(typeface, i, (this.b & 2) != 0);
        }
        w4 w4Var = this.d;
        if (w4Var.m) {
            w4Var.l = typeface;
            TextView textView = (TextView) this.c.get();
            if (textView != null) {
                boolean zIsAttachedToWindow = textView.isAttachedToWindow();
                int i2 = w4Var.j;
                if (zIsAttachedToWindow) {
                    textView.post(new s4(textView, typeface, i2));
                } else {
                    textView.setTypeface(typeface, i2);
                }
            }
        }
    }
}
