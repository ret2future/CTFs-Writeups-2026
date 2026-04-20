package defpackage;

import android.annotation.SuppressLint;
import android.graphics.Rect;
import android.os.Build;
import android.util.Log;
import android.view.View;
import android.view.WindowInsets;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Objects;

/* JADX INFO: compiled from: r8-map-id-51b54c9e59b63e0fb0abe04daff984b4fb85d07ef3fcb7973595bce215c10515 */
/* JADX INFO: loaded from: classes.dex */
public abstract class yt extends du {
    public static boolean f = false;
    public static Method g;
    public static Class h;
    public static Field i;
    public static Field j;
    public final WindowInsets c;
    public jf d;
    public jf e;

    public yt(eu euVar, WindowInsets windowInsets) {
        super(euVar);
        this.d = null;
        this.c = windowInsets;
    }

    private jf n(View view) {
        if (Build.VERSION.SDK_INT >= 30) {
            throw new UnsupportedOperationException("getVisibleInsets() should not be called on API >= 30. Use WindowInsets.isVisible() instead.");
        }
        if (!f) {
            o();
        }
        Method method = g;
        if (method != null && h != null && i != null) {
            try {
                Object objInvoke = method.invoke(view, null);
                if (objInvoke == null) {
                    Log.w("WindowInsetsCompat", "Failed to get visible insets. getViewRootImpl() returned null from the provided view. This means that the view is either not attached or the method has been overridden", new NullPointerException());
                    return null;
                }
                Rect rect = (Rect) i.get(j.get(objInvoke));
                if (rect != null) {
                    return jf.a(rect.left, rect.top, rect.right, rect.bottom);
                }
            } catch (ReflectiveOperationException e) {
                Log.e("WindowInsetsCompat", "Failed to get visible insets. (Reflection error). " + e.getMessage(), e);
            }
        }
        return null;
    }

    @SuppressLint({"PrivateApi"})
    private static void o() {
        try {
            g = View.class.getDeclaredMethod("getViewRootImpl", null);
            Class<?> cls = Class.forName("android.view.View$AttachInfo");
            h = cls;
            i = cls.getDeclaredField("mVisibleInsets");
            j = Class.forName("android.view.ViewRootImpl").getDeclaredField("mAttachInfo");
            i.setAccessible(true);
            j.setAccessible(true);
        } catch (ReflectiveOperationException e) {
            Log.e("WindowInsetsCompat", "Failed to get visible insets. (Reflection error). " + e.getMessage(), e);
        }
        f = true;
    }

    @Override // defpackage.du
    public void d(View view) {
        jf jfVarN = n(view);
        if (jfVarN == null) {
            jfVarN = jf.e;
        }
        p(jfVarN);
    }

    @Override // defpackage.du
    public boolean equals(Object obj) {
        if (super.equals(obj)) {
            return Objects.equals(this.e, ((yt) obj).e);
        }
        return false;
    }

    @Override // defpackage.du
    public final jf g() {
        if (this.d == null) {
            WindowInsets windowInsets = this.c;
            this.d = jf.a(windowInsets.getSystemWindowInsetLeft(), windowInsets.getSystemWindowInsetTop(), windowInsets.getSystemWindowInsetRight(), windowInsets.getSystemWindowInsetBottom());
        }
        return this.d;
    }

    @Override // defpackage.du
    public eu h(int i2, int i3, int i4, int i5) {
        eu euVarC = eu.c(this.c, null);
        int i6 = Build.VERSION.SDK_INT;
        xt wtVar = i6 >= 30 ? new wt(euVarC) : i6 >= 29 ? new vt(euVarC) : new ut(euVarC);
        wtVar.d(eu.a(g(), i2, i3, i4, i5));
        wtVar.c(eu.a(f(), i2, i3, i4, i5));
        return wtVar.b();
    }

    @Override // defpackage.du
    public boolean j() {
        return this.c.isRound();
    }

    public void p(jf jfVar) {
        this.e = jfVar;
    }

    @Override // defpackage.du
    public void k(jf[] jfVarArr) {
    }

    @Override // defpackage.du
    public void l(eu euVar) {
    }
}
