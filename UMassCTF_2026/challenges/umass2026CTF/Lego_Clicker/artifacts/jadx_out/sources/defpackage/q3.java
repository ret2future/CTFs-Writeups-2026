package defpackage;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.util.Log;

/* JADX INFO: compiled from: r8-map-id-51b54c9e59b63e0fb0abe04daff984b4fb85d07ef3fcb7973595bce215c10515 */
/* JADX INFO: loaded from: classes.dex */
public final class q3 {
    public static final PorterDuff.Mode b = PorterDuff.Mode.SRC_IN;
    public static q3 c;
    public ym a;

    public static synchronized q3 a() {
        try {
            if (c == null) {
                c();
            }
        } catch (Throwable th) {
            throw th;
        }
        return c;
    }

    public static synchronized void c() {
        if (c == null) {
            q3 q3Var = new q3();
            c = q3Var;
            q3Var.a = ym.c();
            ym ymVar = c.a;
            p3 p3Var = new p3();
            synchronized (ymVar) {
                ymVar.e = p3Var;
            }
        }
    }

    public static void d(Drawable drawable, sp spVar, int[] iArr) {
        PorterDuff.Mode mode = ym.f;
        int[] state = drawable.getState();
        if (drawable.mutate() != drawable) {
            Log.d("ResourceManagerInternal", "Mutated drawable is not the same instance as the input.");
            return;
        }
        if ((drawable instanceof LayerDrawable) && drawable.isStateful()) {
            drawable.setState(new int[0]);
            drawable.setState(state);
        }
        boolean z = spVar.d;
        if (!z && !spVar.c) {
            drawable.clearColorFilter();
            return;
        }
        PorterDuffColorFilter porterDuffColorFilterF = null;
        ColorStateList colorStateList = z ? spVar.a : null;
        PorterDuff.Mode mode2 = spVar.c ? spVar.b : ym.f;
        if (colorStateList != null && mode2 != null) {
            porterDuffColorFilterF = ym.f(colorStateList.getColorForState(iArr, 0), mode2);
        }
        drawable.setColorFilter(porterDuffColorFilterF);
    }

    public final synchronized Drawable b(Context context, int i) {
        return this.a.d(context, i);
    }
}
