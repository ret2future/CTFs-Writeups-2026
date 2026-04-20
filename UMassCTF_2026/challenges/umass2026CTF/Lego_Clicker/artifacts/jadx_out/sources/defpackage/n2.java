package defpackage;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
import java.util.WeakHashMap;

/* JADX INFO: compiled from: r8-map-id-51b54c9e59b63e0fb0abe04daff984b4fb85d07ef3fcb7973595bce215c10515 */
/* JADX INFO: loaded from: classes.dex */
public final class n2 {
    public final View a;
    public sp d;
    public sp e;
    public sp f;
    public int c = -1;
    public final q3 b = q3.a();

    public n2(View view) {
        this.a = view;
    }

    public final void a() {
        View view = this.a;
        Drawable background = view.getBackground();
        if (background != null) {
            if (this.d != null) {
                if (this.f == null) {
                    this.f = new sp();
                }
                sp spVar = this.f;
                spVar.a = null;
                spVar.d = false;
                spVar.b = null;
                spVar.c = false;
                WeakHashMap weakHashMap = os.a;
                ColorStateList colorStateListG = ds.g(view);
                if (colorStateListG != null) {
                    spVar.d = true;
                    spVar.a = colorStateListG;
                }
                PorterDuff.Mode modeH = ds.h(view);
                if (modeH != null) {
                    spVar.c = true;
                    spVar.b = modeH;
                }
                if (spVar.d || spVar.c) {
                    q3.d(background, spVar, view.getDrawableState());
                    return;
                }
            }
            sp spVar2 = this.e;
            if (spVar2 != null) {
                q3.d(background, spVar2, view.getDrawableState());
                return;
            }
            sp spVar3 = this.d;
            if (spVar3 != null) {
                q3.d(background, spVar3, view.getDrawableState());
            }
        }
    }

    public final ColorStateList b() {
        sp spVar = this.e;
        if (spVar != null) {
            return spVar.a;
        }
        return null;
    }

    public final PorterDuff.Mode c() {
        sp spVar = this.e;
        if (spVar != null) {
            return spVar.b;
        }
        return null;
    }

    public final void d(AttributeSet attributeSet, int i) {
        ColorStateList colorStateListG;
        View view = this.a;
        Context context = view.getContext();
        int[] iArr = fl.y;
        j5 j5VarQ = j5.q(context, attributeSet, iArr, i);
        TypedArray typedArray = (TypedArray) j5VarQ.a;
        View view2 = this.a;
        os.g(view2, view2.getContext(), iArr, attributeSet, (TypedArray) j5VarQ.a, i);
        try {
            if (typedArray.hasValue(0)) {
                this.c = typedArray.getResourceId(0, -1);
                q3 q3Var = this.b;
                Context context2 = view.getContext();
                int i2 = this.c;
                synchronized (q3Var) {
                    colorStateListG = q3Var.a.g(context2, i2);
                }
                if (colorStateListG != null) {
                    g(colorStateListG);
                }
            }
            if (typedArray.hasValue(1)) {
                ds.q(view, j5VarQ.j(1));
            }
            if (typedArray.hasValue(2)) {
                ds.r(view, ea.b(typedArray.getInt(2, -1), null));
            }
            j5VarQ.s();
        } catch (Throwable th) {
            j5VarQ.s();
            throw th;
        }
    }

    public final void e() {
        this.c = -1;
        g(null);
        a();
    }

    public final void f(int i) {
        ColorStateList colorStateListG;
        this.c = i;
        q3 q3Var = this.b;
        if (q3Var != null) {
            Context context = this.a.getContext();
            synchronized (q3Var) {
                colorStateListG = q3Var.a.g(context, i);
            }
        } else {
            colorStateListG = null;
        }
        g(colorStateListG);
        a();
    }

    public final void g(ColorStateList colorStateList) {
        if (colorStateList != null) {
            if (this.d == null) {
                this.d = new sp();
            }
            sp spVar = this.d;
            spVar.a = colorStateList;
            spVar.d = true;
        } else {
            this.d = null;
        }
        a();
    }

    public final void h(ColorStateList colorStateList) {
        if (this.e == null) {
            this.e = new sp();
        }
        sp spVar = this.e;
        spVar.a = colorStateList;
        spVar.d = true;
        a();
    }

    public final void i(PorterDuff.Mode mode) {
        if (this.e == null) {
            this.e = new sp();
        }
        sp spVar = this.e;
        spVar.b = mode;
        spVar.c = true;
        a();
    }
}
