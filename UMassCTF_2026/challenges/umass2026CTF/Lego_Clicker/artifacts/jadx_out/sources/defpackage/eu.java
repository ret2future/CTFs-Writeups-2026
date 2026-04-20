package defpackage;

import android.os.Build;
import android.view.View;
import android.view.WindowInsets;
import java.util.Objects;
import java.util.WeakHashMap;

/* JADX INFO: compiled from: r8-map-id-51b54c9e59b63e0fb0abe04daff984b4fb85d07ef3fcb7973595bce215c10515 */
/* JADX INFO: loaded from: classes.dex */
public final class eu {
    public static final eu b;
    public final du a;

    static {
        if (Build.VERSION.SDK_INT >= 30) {
            b = cu.l;
        } else {
            b = du.b;
        }
    }

    public eu(WindowInsets windowInsets) {
        int i = Build.VERSION.SDK_INT;
        if (i >= 30) {
            this.a = new cu(this, windowInsets);
            return;
        }
        if (i >= 29) {
            this.a = new bu(this, windowInsets);
        } else if (i >= 28) {
            this.a = new au(this, windowInsets);
        } else {
            this.a = new zt(this, windowInsets);
        }
    }

    public static jf a(jf jfVar, int i, int i2, int i3, int i4) {
        int iMax = Math.max(0, jfVar.a - i);
        int iMax2 = Math.max(0, jfVar.b - i2);
        int iMax3 = Math.max(0, jfVar.c - i3);
        int iMax4 = Math.max(0, jfVar.d - i4);
        return (iMax == i && iMax2 == i2 && iMax3 == i3 && iMax4 == i4) ? jfVar : jf.a(iMax, iMax2, iMax3, iMax4);
    }

    public static eu c(WindowInsets windowInsets, View view) {
        windowInsets.getClass();
        eu euVar = new eu(windowInsets);
        if (view != null && view.isAttachedToWindow()) {
            WeakHashMap weakHashMap = os.a;
            eu euVarA = es.a(view);
            du duVar = euVar.a;
            duVar.l(euVarA);
            duVar.d(view.getRootView());
        }
        return euVar;
    }

    public final WindowInsets b() {
        du duVar = this.a;
        if (duVar instanceof yt) {
            return ((yt) duVar).c;
        }
        return null;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof eu) {
            return Objects.equals(this.a, ((eu) obj).a);
        }
        return false;
    }

    public final int hashCode() {
        du duVar = this.a;
        if (duVar == null) {
            return 0;
        }
        return duVar.hashCode();
    }

    public eu() {
        this.a = new du(this);
    }
}
