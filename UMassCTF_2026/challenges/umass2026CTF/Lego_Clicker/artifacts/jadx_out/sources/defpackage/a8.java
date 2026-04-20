package defpackage;

import android.view.View;
import android.view.ViewGroup;
import androidx.constraintlayout.widget.ConstraintLayout;

/* JADX INFO: compiled from: r8-map-id-51b54c9e59b63e0fb0abe04daff984b4fb85d07ef3fcb7973595bce215c10515 */
/* JADX INFO: loaded from: classes.dex */
public final class a8 {
    public final ConstraintLayout a;
    public int b;
    public int c;
    public int d;
    public int e;
    public int f;
    public int g;
    public final /* synthetic */ ConstraintLayout h;

    public a8(ConstraintLayout constraintLayout, ConstraintLayout constraintLayout2) {
        this.h = constraintLayout;
        this.a = constraintLayout2;
    }

    public static boolean a(int i, int i2, int i3) {
        if (i == i2) {
            return true;
        }
        int mode = View.MeasureSpec.getMode(i);
        int mode2 = View.MeasureSpec.getMode(i2);
        int size = View.MeasureSpec.getSize(i2);
        if (mode2 == 1073741824) {
            return (mode == Integer.MIN_VALUE || mode == 0) && i3 == size;
        }
        return false;
    }

    public final void b(k8 k8Var, j6 j6Var) {
        int iMakeMeasureSpec;
        int iMakeMeasureSpec2;
        int iMax;
        int iMax2;
        boolean z;
        int baseline;
        int i;
        v7 v7Var = k8Var.J;
        v7 v7Var2 = k8Var.H;
        if (k8Var.f0 == 8) {
            j6Var.e = 0;
            j6Var.f = 0;
            j6Var.g = 0;
            return;
        }
        if (k8Var.S == null) {
            return;
        }
        io ioVar = ConstraintLayout.p;
        int i2 = j6Var.a;
        int i3 = j6Var.b;
        int i4 = j6Var.c;
        int i5 = j6Var.d;
        int i6 = this.b + this.c;
        int i7 = this.d;
        View view = k8Var.e0;
        int iC = lo.c(i2);
        if (iC == 0) {
            iMakeMeasureSpec = View.MeasureSpec.makeMeasureSpec(i4, 1073741824);
        } else if (iC == 1) {
            iMakeMeasureSpec = ViewGroup.getChildMeasureSpec(this.f, i7, -2);
        } else if (iC == 2) {
            iMakeMeasureSpec = ViewGroup.getChildMeasureSpec(this.f, i7, -2);
            boolean z2 = k8Var.r == 1;
            int i8 = j6Var.j;
            if (i8 == 1 || i8 == 2) {
                boolean z3 = view.getMeasuredHeight() == k8Var.i();
                if (j6Var.j == 2 || !z2 || ((z2 && z3) || k8Var.y())) {
                    iMakeMeasureSpec = View.MeasureSpec.makeMeasureSpec(k8Var.o(), 1073741824);
                }
            }
        } else if (iC != 3) {
            iMakeMeasureSpec = 0;
        } else {
            int i9 = this.f;
            int i10 = v7Var2 != null ? v7Var2.g : 0;
            if (v7Var != null) {
                i10 += v7Var.g;
            }
            iMakeMeasureSpec = ViewGroup.getChildMeasureSpec(i9, i7 + i10, -1);
        }
        int iC2 = lo.c(i3);
        if (iC2 == 0) {
            iMakeMeasureSpec2 = View.MeasureSpec.makeMeasureSpec(i5, 1073741824);
        } else if (iC2 == 1) {
            iMakeMeasureSpec2 = ViewGroup.getChildMeasureSpec(this.g, i6, -2);
        } else if (iC2 == 2) {
            iMakeMeasureSpec2 = ViewGroup.getChildMeasureSpec(this.g, i6, -2);
            boolean z4 = k8Var.s == 1;
            int i11 = j6Var.j;
            if (i11 == 1 || i11 == 2) {
                boolean z5 = view.getMeasuredWidth() == k8Var.o();
                if (j6Var.j == 2 || !z4 || ((z4 && z5) || k8Var.z())) {
                    iMakeMeasureSpec2 = View.MeasureSpec.makeMeasureSpec(k8Var.i(), 1073741824);
                }
            }
        } else if (iC2 != 3) {
            iMakeMeasureSpec2 = 0;
        } else {
            int i12 = this.g;
            int i13 = v7Var2 != null ? k8Var.I.g : 0;
            if (v7Var != null) {
                i13 += k8Var.K.g;
            }
            iMakeMeasureSpec2 = ViewGroup.getChildMeasureSpec(i12, i6 + i13, -1);
        }
        l8 l8Var = (l8) k8Var.S;
        ConstraintLayout constraintLayout = this.h;
        if (l8Var != null && d.q(constraintLayout.i, 256) && view.getMeasuredWidth() == k8Var.o() && view.getMeasuredWidth() < l8Var.o() && view.getMeasuredHeight() == k8Var.i() && view.getMeasuredHeight() < l8Var.i() && view.getBaseline() == k8Var.Z && !k8Var.x() && a(k8Var.F, iMakeMeasureSpec, k8Var.o()) && a(k8Var.G, iMakeMeasureSpec2, k8Var.i())) {
            j6Var.e = k8Var.o();
            j6Var.f = k8Var.i();
            j6Var.g = k8Var.Z;
            return;
        }
        boolean z6 = i2 == 3;
        boolean z7 = i3 == 3;
        boolean z8 = i3 == 4 || i3 == 1;
        boolean z9 = i2 == 4 || i2 == 1;
        boolean z10 = z6 && k8Var.V > 0.0f;
        boolean z11 = z7 && k8Var.V > 0.0f;
        if (view == null) {
            return;
        }
        z7 z7Var = (z7) view.getLayoutParams();
        int i14 = j6Var.j;
        if (i14 != 1 && i14 != 2 && z6 && k8Var.r == 0 && z7 && k8Var.s == 0) {
            i = -1;
            z = false;
            baseline = 0;
            iMax2 = 0;
            iMax = 0;
        } else {
            view.measure(iMakeMeasureSpec, iMakeMeasureSpec2);
            k8Var.F = iMakeMeasureSpec;
            k8Var.G = iMakeMeasureSpec2;
            k8Var.g = false;
            int measuredWidth = view.getMeasuredWidth();
            int measuredHeight = view.getMeasuredHeight();
            int baseline2 = view.getBaseline();
            int i15 = k8Var.u;
            iMax = i15 > 0 ? Math.max(i15, measuredWidth) : measuredWidth;
            int i16 = k8Var.v;
            if (i16 > 0) {
                iMax = Math.min(i16, iMax);
            }
            int i17 = k8Var.x;
            iMax2 = i17 > 0 ? Math.max(i17, measuredHeight) : measuredHeight;
            int i18 = iMakeMeasureSpec2;
            int i19 = k8Var.y;
            if (i19 > 0) {
                iMax2 = Math.min(i19, iMax2);
            }
            if (!d.q(constraintLayout.i, 1)) {
                if (z10 && z8) {
                    iMax = (int) ((iMax2 * k8Var.V) + 0.5f);
                } else if (z11 && z9) {
                    iMax2 = (int) ((iMax / k8Var.V) + 0.5f);
                }
            }
            if (measuredWidth == iMax && measuredHeight == iMax2) {
                baseline = baseline2;
                i = -1;
                z = false;
            } else {
                if (measuredWidth != iMax) {
                    iMakeMeasureSpec = View.MeasureSpec.makeMeasureSpec(iMax, 1073741824);
                }
                int iMakeMeasureSpec3 = measuredHeight != iMax2 ? View.MeasureSpec.makeMeasureSpec(iMax2, 1073741824) : i18;
                view.measure(iMakeMeasureSpec, iMakeMeasureSpec3);
                k8Var.F = iMakeMeasureSpec;
                k8Var.G = iMakeMeasureSpec3;
                z = false;
                k8Var.g = false;
                int measuredWidth2 = view.getMeasuredWidth();
                int measuredHeight2 = view.getMeasuredHeight();
                baseline = view.getBaseline();
                iMax = measuredWidth2;
                iMax2 = measuredHeight2;
                i = -1;
            }
        }
        boolean z12 = baseline != i ? true : z;
        j6Var.i = (iMax == j6Var.c && iMax2 == j6Var.d) ? z : true;
        boolean z13 = z7Var.c0 ? true : z12;
        if (z13 && baseline != -1 && k8Var.Z != baseline) {
            j6Var.i = true;
        }
        j6Var.e = iMax;
        j6Var.f = iMax2;
        j6Var.h = z13;
        j6Var.g = baseline;
    }
}
