package defpackage;

import android.graphics.Rect;
import android.view.View;

/* JADX INFO: compiled from: r8-map-id-51b54c9e59b63e0fb0abe04daff984b4fb85d07ef3fcb7973595bce215c10515 */
/* JADX INFO: loaded from: classes.dex */
public abstract class ra {
    public int a;
    public final Object b;
    public final Object c;

    public ra(ql qlVar) {
        this.a = Integer.MIN_VALUE;
        this.c = new Rect();
        this.b = qlVar;
    }

    public static ra a(ql qlVar, int i) {
        if (i == 0) {
            return new gk(qlVar, 0);
        }
        int i2 = 1;
        if (i == 1) {
            return new gk(qlVar, i2);
        }
        z6.f("invalid orientation");
        return null;
    }

    public abstract int b(View view);

    public abstract int c(View view);

    public abstract int d(View view);

    public abstract int e(View view);

    public abstract int f();

    public abstract int g();

    public abstract int h();

    public abstract int i();

    public abstract int j();

    public abstract int k();

    public abstract int l();

    public abstract int m(View view);

    public abstract int n(View view);

    public abstract void o(int i);

    public ra(ua uaVar) {
        this.a = 0;
        this.c = new i9();
        this.b = uaVar;
    }
}
