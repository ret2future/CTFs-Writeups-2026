package androidx.recyclerview.widget;

import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Log;
import android.util.SparseIntArray;
import android.view.View;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityNodeInfo;
import defpackage.am;
import defpackage.d0;
import defpackage.le;
import defpackage.os;
import defpackage.pe;
import defpackage.pg;
import defpackage.qg;
import defpackage.ql;
import defpackage.rg;
import defpackage.rl;
import defpackage.wl;
import defpackage.z3;
import java.util.Arrays;
import java.util.WeakHashMap;

/* JADX INFO: compiled from: r8-map-id-51b54c9e59b63e0fb0abe04daff984b4fb85d07ef3fcb7973595bce215c10515 */
/* JADX INFO: loaded from: classes.dex */
public class GridLayoutManager extends LinearLayoutManager {
    public boolean D;
    public final int E;
    public int[] F;
    public View[] G;
    public final SparseIntArray H;
    public final SparseIntArray I;
    public final z3 J;
    public final Rect K;

    public GridLayoutManager(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        this.D = false;
        this.E = -1;
        this.H = new SparseIntArray();
        this.I = new SparseIntArray();
        z3 z3Var = new z3(8);
        this.J = z3Var;
        this.K = new Rect();
        int i3 = ql.D(context, attributeSet, i, i2).b;
        if (i3 == this.E) {
            return;
        }
        this.D = true;
        if (i3 < 1) {
            throw new IllegalArgumentException("Span count should be at least 1. Provided " + i3);
        }
        this.E = i3;
        z3Var.m();
        h0();
    }

    @Override // defpackage.ql
    public final int E(wl wlVar, am amVar) {
        if (this.o == 0) {
            return this.E;
        }
        if (amVar.b() < 1) {
            return 0;
        }
        return Y0(amVar.b() - 1, wlVar, amVar) + 1;
    }

    @Override // androidx.recyclerview.widget.LinearLayoutManager
    public final View E0(wl wlVar, am amVar, boolean z, boolean z2) {
        int i;
        int iU;
        int iU2 = u();
        int i2 = 1;
        if (z2) {
            iU = u() - 1;
            i = -1;
            i2 = -1;
        } else {
            i = iU2;
            iU = 0;
        }
        int iB = amVar.b();
        y0();
        int iK = this.q.k();
        int iG = this.q.g();
        View view = null;
        View view2 = null;
        while (iU != i) {
            View viewT = t(iU);
            int iC = ql.C(viewT);
            if (iC >= 0 && iC < iB && Z0(iC, wlVar, amVar) == 0) {
                if (((rl) viewT.getLayoutParams()).a.g()) {
                    if (view2 == null) {
                        view2 = viewT;
                    }
                } else {
                    if (this.q.e(viewT) < iG && this.q.b(viewT) >= iK) {
                        return viewT;
                    }
                    if (view == null) {
                        view = viewT;
                    }
                }
            }
            iU += i2;
        }
        return view != null ? view : view2;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r14v19 */
    /* JADX WARN: Type inference failed for: r14v20, types: [boolean, int] */
    /* JADX WARN: Type inference failed for: r14v23 */
    /* JADX WARN: Type inference failed for: r14v24 */
    /* JADX WARN: Type inference failed for: r14v31 */
    @Override // androidx.recyclerview.widget.LinearLayoutManager
    public final void K0(wl wlVar, am amVar, rg rgVar, qg qgVar) {
        int i;
        int i2;
        int i3;
        int iD;
        int iB;
        int iZ;
        int iV;
        int iV2;
        ?? r14;
        View viewB;
        int iJ = this.q.j();
        boolean z = iJ != 1073741824;
        int iU = u();
        int i4 = this.E;
        int i5 = iU > 0 ? this.F[i4] : 0;
        if (z) {
            c1();
        }
        boolean z2 = rgVar.e == 1;
        int iA1 = !z2 ? a1(rgVar.d, wlVar, amVar) + Z0(rgVar.d, wlVar, amVar) : i4;
        int i6 = 0;
        while (i6 < i4) {
            int i7 = rgVar.d;
            if (i7 < 0 || i7 >= amVar.b() || iA1 <= 0) {
                break;
            }
            int i8 = rgVar.d;
            int iA12 = a1(i8, wlVar, amVar);
            if (iA12 > i4) {
                throw new IllegalArgumentException("Item at position " + i8 + " requires " + iA12 + " spans but GridLayoutManager has only " + i4 + " spans.");
            }
            iA1 -= iA12;
            if (iA1 < 0 || (viewB = rgVar.b(wlVar)) == null) {
                break;
            }
            this.G[i6] = viewB;
            i6++;
        }
        if (i6 == 0) {
            qgVar.b = true;
            return;
        }
        if (z2) {
            i3 = 1;
            i2 = i6;
            i = 0;
        } else {
            i = i6 - 1;
            i2 = -1;
            i3 = -1;
        }
        int i9 = 0;
        while (i != i2) {
            View view = this.G[i];
            pe peVar = (pe) view.getLayoutParams();
            int iA13 = a1(ql.C(view), wlVar, amVar);
            peVar.f = iA13;
            peVar.e = i9;
            i9 += iA13;
            i += i3;
        }
        float f = 0.0f;
        int i10 = 0;
        for (int i11 = 0; i11 < i6; i11++) {
            View view2 = this.G[i11];
            if (rgVar.k != null) {
                r14 = 0;
                r14 = 0;
                if (z2) {
                    a(view2, -1, true);
                } else {
                    a(view2, 0, true);
                }
            } else if (z2) {
                r14 = 0;
                a(view2, -1, false);
            } else {
                r14 = 0;
                a(view2, 0, false);
            }
            RecyclerView recyclerView = this.b;
            Rect rect = this.K;
            if (recyclerView == null) {
                rect.set(r14, r14, r14, r14);
            } else {
                rect.set(recyclerView.I(view2));
            }
            b1(view2, iJ, r14);
            int iC = this.q.c(view2);
            if (iC > i10) {
                i10 = iC;
            }
            float fD = (this.q.d(view2) * 1.0f) / ((pe) view2.getLayoutParams()).f;
            if (fD > f) {
                f = fD;
            }
        }
        if (z) {
            V0(Math.max(Math.round(f * i4), i5));
            i10 = 0;
            for (int i12 = 0; i12 < i6; i12++) {
                View view3 = this.G[i12];
                b1(view3, 1073741824, true);
                int iC2 = this.q.c(view3);
                if (iC2 > i10) {
                    i10 = iC2;
                }
            }
        }
        for (int i13 = 0; i13 < i6; i13++) {
            View view4 = this.G[i13];
            if (this.q.c(view4) != i10) {
                pe peVar2 = (pe) view4.getLayoutParams();
                Rect rect2 = peVar2.b;
                int i14 = rect2.top + rect2.bottom + ((ViewGroup.MarginLayoutParams) peVar2).topMargin + ((ViewGroup.MarginLayoutParams) peVar2).bottomMargin;
                int i15 = rect2.left + rect2.right + ((ViewGroup.MarginLayoutParams) peVar2).leftMargin + ((ViewGroup.MarginLayoutParams) peVar2).rightMargin;
                int iX0 = X0(peVar2.e, peVar2.f);
                if (this.o == 1) {
                    iV2 = ql.v(false, iX0, 1073741824, i15, ((ViewGroup.MarginLayoutParams) peVar2).width);
                    iV = View.MeasureSpec.makeMeasureSpec(i10 - i14, 1073741824);
                } else {
                    int iMakeMeasureSpec = View.MeasureSpec.makeMeasureSpec(i10 - i15, 1073741824);
                    iV = ql.v(false, iX0, 1073741824, i14, ((ViewGroup.MarginLayoutParams) peVar2).height);
                    iV2 = iMakeMeasureSpec;
                }
                if (r0(view4, iV2, iV, (rl) view4.getLayoutParams())) {
                    view4.measure(iV2, iV);
                }
            }
        }
        qgVar.a = i10;
        int i16 = this.o;
        int i17 = rgVar.f;
        int iD2 = rgVar.b;
        if (i16 != 1) {
            if (i17 == -1) {
                iZ = iD2 - i10;
                iB = 0;
                iD = iD2;
            } else {
                iD = iD2 + i10;
                iB = 0;
                iZ = iD2;
            }
            iD2 = iB;
        } else if (i17 == -1) {
            iB = iD2 - i10;
            iZ = 0;
            iD = 0;
        } else {
            iD = 0;
            iB = iD2;
            iD2 += i10;
            iZ = 0;
        }
        int i18 = 0;
        while (true) {
            View[] viewArr = this.G;
            if (i18 >= i6) {
                Arrays.fill(viewArr, (Object) null);
                return;
            }
            View view5 = viewArr[i18];
            pe peVar3 = (pe) view5.getLayoutParams();
            if (this.o != 1) {
                iB = B() + this.F[peVar3.e];
                iD2 = this.q.d(view5) + iB;
            } else if (J0()) {
                int iZ2 = z() + this.F[i4 - peVar3.e];
                iD = iZ2;
                iZ = iZ2 - this.q.d(view5);
            } else {
                iZ = z() + this.F[peVar3.e];
                iD = this.q.d(view5) + iZ;
            }
            ql.I(view5, iZ, iB, iD, iD2);
            if (peVar3.a.g() || peVar3.a.j()) {
                qgVar.c = true;
            }
            qgVar.d = view5.hasFocusable() | qgVar.d;
            i18++;
        }
    }

    @Override // androidx.recyclerview.widget.LinearLayoutManager
    public final void L0(wl wlVar, am amVar, pg pgVar, int i) {
        c1();
        if (amVar.b() > 0 && !amVar.f) {
            boolean z = i == 1;
            int iZ0 = Z0(pgVar.b, wlVar, amVar);
            if (z) {
                while (iZ0 > 0) {
                    int i2 = pgVar.b;
                    if (i2 <= 0) {
                        break;
                    }
                    int i3 = i2 - 1;
                    pgVar.b = i3;
                    iZ0 = Z0(i3, wlVar, amVar);
                }
            } else {
                int iB = amVar.b() - 1;
                int i4 = pgVar.b;
                while (i4 < iB) {
                    int i5 = i4 + 1;
                    int iZ02 = Z0(i5, wlVar, amVar);
                    if (iZ02 <= iZ0) {
                        break;
                    }
                    i4 = i5;
                    iZ0 = iZ02;
                }
                pgVar.b = i4;
            }
        }
        W0();
    }

    /* JADX WARN: Code restructure failed: missing block: B:62:0x00e0, code lost:
    
        if (r13 == (r2 > r15)) goto L57;
     */
    /* JADX WARN: Code restructure failed: missing block: B:84:0x013d, code lost:
    
        if (r16 == null) goto L86;
     */
    /* JADX WARN: Code restructure failed: missing block: B:85:0x013f, code lost:
    
        return r16;
     */
    /* JADX WARN: Code restructure failed: missing block: B:86:0x0140, code lost:
    
        return r17;
     */
    @Override // androidx.recyclerview.widget.LinearLayoutManager, defpackage.ql
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final android.view.View N(android.view.View r23, int r24, defpackage.wl r25, defpackage.am r26) {
        /*
            Method dump skipped, instruction units count: 321
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.recyclerview.widget.GridLayoutManager.N(android.view.View, int, wl, am):android.view.View");
    }

    @Override // defpackage.ql
    public final void P(wl wlVar, am amVar, d0 d0Var) {
        super.P(wlVar, amVar, d0Var);
        d0Var.a.setClassName("android.widget.GridView");
    }

    @Override // defpackage.ql
    public final void Q(wl wlVar, am amVar, View view, d0 d0Var) {
        AccessibilityNodeInfo accessibilityNodeInfo = d0Var.a;
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        if (!(layoutParams instanceof pe)) {
            R(view, d0Var);
            return;
        }
        pe peVar = (pe) layoutParams;
        int iY0 = Y0(peVar.a.b(), wlVar, amVar);
        int i = this.o;
        int i2 = peVar.e;
        int i3 = peVar.f;
        if (i == 0) {
            accessibilityNodeInfo.setCollectionItemInfo(AccessibilityNodeInfo.CollectionItemInfo.obtain(i2, i3, iY0, 1, false, false));
        } else {
            accessibilityNodeInfo.setCollectionItemInfo(AccessibilityNodeInfo.CollectionItemInfo.obtain(iY0, 1, i2, i3, false, false));
        }
    }

    @Override // androidx.recyclerview.widget.LinearLayoutManager
    public final void R0(boolean z) {
        if (z) {
            throw new UnsupportedOperationException("GridLayoutManager does not support stack from end. Consider using reverse layout");
        }
        super.R0(false);
    }

    @Override // defpackage.ql
    public final void S(int i, int i2) {
        z3 z3Var = this.J;
        z3Var.m();
        ((SparseIntArray) z3Var.c).clear();
    }

    @Override // defpackage.ql
    public final void T() {
        z3 z3Var = this.J;
        z3Var.m();
        ((SparseIntArray) z3Var.c).clear();
    }

    @Override // defpackage.ql
    public final void U(int i, int i2) {
        z3 z3Var = this.J;
        z3Var.m();
        ((SparseIntArray) z3Var.c).clear();
    }

    @Override // defpackage.ql
    public final void V(int i, int i2) {
        z3 z3Var = this.J;
        z3Var.m();
        ((SparseIntArray) z3Var.c).clear();
    }

    public final void V0(int i) {
        int i2;
        int[] iArr = this.F;
        int i3 = this.E;
        if (iArr == null || iArr.length != i3 + 1 || iArr[iArr.length - 1] != i) {
            iArr = new int[i3 + 1];
        }
        int i4 = 0;
        iArr[0] = 0;
        int i5 = i / i3;
        int i6 = i % i3;
        int i7 = 0;
        for (int i8 = 1; i8 <= i3; i8++) {
            i4 += i6;
            if (i4 <= 0 || i3 - i4 >= i6) {
                i2 = i5;
            } else {
                i2 = i5 + 1;
                i4 -= i3;
            }
            i7 += i2;
            iArr[i8] = i7;
        }
        this.F = iArr;
    }

    @Override // defpackage.ql
    public final void W(int i, int i2) {
        z3 z3Var = this.J;
        z3Var.m();
        ((SparseIntArray) z3Var.c).clear();
    }

    public final void W0() {
        View[] viewArr = this.G;
        if (viewArr == null || viewArr.length != this.E) {
            this.G = new View[this.E];
        }
    }

    @Override // androidx.recyclerview.widget.LinearLayoutManager, defpackage.ql
    public final void X(wl wlVar, am amVar) {
        boolean z = amVar.f;
        SparseIntArray sparseIntArray = this.I;
        SparseIntArray sparseIntArray2 = this.H;
        if (z) {
            int iU = u();
            for (int i = 0; i < iU; i++) {
                pe peVar = (pe) t(i).getLayoutParams();
                int iB = peVar.a.b();
                sparseIntArray2.put(iB, peVar.f);
                sparseIntArray.put(iB, peVar.e);
            }
        }
        super.X(wlVar, amVar);
        sparseIntArray2.clear();
        sparseIntArray.clear();
    }

    public final int X0(int i, int i2) {
        if (this.o != 1 || !J0()) {
            int[] iArr = this.F;
            return iArr[i2 + i] - iArr[i];
        }
        int[] iArr2 = this.F;
        int i3 = this.E;
        return iArr2[i3 - i] - iArr2[(i3 - i) - i2];
    }

    @Override // androidx.recyclerview.widget.LinearLayoutManager, defpackage.ql
    public final void Y(am amVar) {
        super.Y(amVar);
        this.D = false;
    }

    public final int Y0(int i, wl wlVar, am amVar) {
        boolean z = amVar.f;
        int i2 = this.E;
        z3 z3Var = this.J;
        if (!z) {
            z3Var.getClass();
            return z3.l(i, i2);
        }
        int iB = wlVar.b(i);
        if (iB != -1) {
            z3Var.getClass();
            return z3.l(iB, i2);
        }
        Log.w("GridLayoutManager", "Cannot find span size for pre layout position. " + i);
        return 0;
    }

    public final int Z0(int i, wl wlVar, am amVar) {
        boolean z = amVar.f;
        int i2 = this.E;
        z3 z3Var = this.J;
        if (!z) {
            z3Var.getClass();
            return i % i2;
        }
        int i3 = this.I.get(i, -1);
        if (i3 != -1) {
            return i3;
        }
        int iB = wlVar.b(i);
        if (iB != -1) {
            z3Var.getClass();
            return iB % i2;
        }
        Log.w("GridLayoutManager", "Cannot find span size for pre layout position. It is not cached, not in the adapter. Pos:" + i);
        return 0;
    }

    public final int a1(int i, wl wlVar, am amVar) {
        boolean z = amVar.f;
        z3 z3Var = this.J;
        if (!z) {
            z3Var.getClass();
            return 1;
        }
        int i2 = this.H.get(i, -1);
        if (i2 != -1) {
            return i2;
        }
        if (wlVar.b(i) != -1) {
            z3Var.getClass();
            return 1;
        }
        Log.w("GridLayoutManager", "Cannot find span size for pre layout position. It is not cached, not in the adapter. Pos:" + i);
        return 1;
    }

    public final void b1(View view, int i, boolean z) {
        int iV;
        int iV2;
        pe peVar = (pe) view.getLayoutParams();
        Rect rect = peVar.b;
        int i2 = rect.top + rect.bottom + ((ViewGroup.MarginLayoutParams) peVar).topMargin + ((ViewGroup.MarginLayoutParams) peVar).bottomMargin;
        int i3 = rect.left + rect.right + ((ViewGroup.MarginLayoutParams) peVar).leftMargin + ((ViewGroup.MarginLayoutParams) peVar).rightMargin;
        int iX0 = X0(peVar.e, peVar.f);
        if (this.o == 1) {
            iV2 = ql.v(false, iX0, i, i3, ((ViewGroup.MarginLayoutParams) peVar).width);
            iV = ql.v(true, this.q.l(), this.l, i2, ((ViewGroup.MarginLayoutParams) peVar).height);
        } else {
            int iV3 = ql.v(false, iX0, i, i2, ((ViewGroup.MarginLayoutParams) peVar).height);
            int iV4 = ql.v(true, this.q.l(), this.k, i3, ((ViewGroup.MarginLayoutParams) peVar).width);
            iV = iV3;
            iV2 = iV4;
        }
        rl rlVar = (rl) view.getLayoutParams();
        if (z ? r0(view, iV2, iV, rlVar) : p0(view, iV2, iV, rlVar)) {
            view.measure(iV2, iV);
        }
    }

    public final void c1() {
        int iY;
        int iB;
        if (this.o == 1) {
            iY = this.m - A();
            iB = z();
        } else {
            iY = this.n - y();
            iB = B();
        }
        V0(iY - iB);
    }

    @Override // defpackage.ql
    public final boolean e(rl rlVar) {
        return rlVar instanceof pe;
    }

    @Override // androidx.recyclerview.widget.LinearLayoutManager, defpackage.ql
    public final int i0(int i, wl wlVar, am amVar) {
        c1();
        W0();
        return super.i0(i, wlVar, amVar);
    }

    @Override // androidx.recyclerview.widget.LinearLayoutManager, defpackage.ql
    public final int j(am amVar) {
        return v0(amVar);
    }

    @Override // androidx.recyclerview.widget.LinearLayoutManager, defpackage.ql
    public final int j0(int i, wl wlVar, am amVar) {
        c1();
        W0();
        return super.j0(i, wlVar, amVar);
    }

    @Override // androidx.recyclerview.widget.LinearLayoutManager, defpackage.ql
    public final int k(am amVar) {
        return w0(amVar);
    }

    @Override // androidx.recyclerview.widget.LinearLayoutManager, defpackage.ql
    public final int m(am amVar) {
        return v0(amVar);
    }

    @Override // defpackage.ql
    public final void m0(Rect rect, int i, int i2) {
        int iF;
        int iF2;
        if (this.F == null) {
            super.m0(rect, i, i2);
        }
        int iA = A() + z();
        int iY = y() + B();
        if (this.o == 1) {
            int iHeight = rect.height() + iY;
            RecyclerView recyclerView = this.b;
            WeakHashMap weakHashMap = os.a;
            iF2 = ql.f(i2, iHeight, recyclerView.getMinimumHeight());
            int[] iArr = this.F;
            iF = ql.f(i, iArr[iArr.length - 1] + iA, this.b.getMinimumWidth());
        } else {
            int iWidth = rect.width() + iA;
            RecyclerView recyclerView2 = this.b;
            WeakHashMap weakHashMap2 = os.a;
            iF = ql.f(i, iWidth, recyclerView2.getMinimumWidth());
            int[] iArr2 = this.F;
            iF2 = ql.f(i2, iArr2[iArr2.length - 1] + iY, this.b.getMinimumHeight());
        }
        this.b.setMeasuredDimension(iF, iF2);
    }

    @Override // androidx.recyclerview.widget.LinearLayoutManager, defpackage.ql
    public final int n(am amVar) {
        return w0(amVar);
    }

    @Override // androidx.recyclerview.widget.LinearLayoutManager, defpackage.ql
    public final rl q() {
        return this.o == 0 ? new pe(-2, -1) : new pe(-1, -2);
    }

    @Override // defpackage.ql
    public final rl r(Context context, AttributeSet attributeSet) {
        pe peVar = new pe(context, attributeSet);
        peVar.e = -1;
        peVar.f = 0;
        return peVar;
    }

    @Override // defpackage.ql
    public final rl s(ViewGroup.LayoutParams layoutParams) {
        if (layoutParams instanceof ViewGroup.MarginLayoutParams) {
            pe peVar = new pe((ViewGroup.MarginLayoutParams) layoutParams);
            peVar.e = -1;
            peVar.f = 0;
            return peVar;
        }
        pe peVar2 = new pe(layoutParams);
        peVar2.e = -1;
        peVar2.f = 0;
        return peVar2;
    }

    @Override // androidx.recyclerview.widget.LinearLayoutManager, defpackage.ql
    public final boolean s0() {
        return this.y == null && !this.D;
    }

    @Override // androidx.recyclerview.widget.LinearLayoutManager
    public final void t0(am amVar, rg rgVar, le leVar) {
        int i = this.E;
        int i2 = i;
        for (int i3 = 0; i3 < i; i3++) {
            int i4 = rgVar.d;
            if (i4 < 0 || i4 >= amVar.b() || i2 <= 0) {
                return;
            }
            leVar.a(rgVar.d, Math.max(0, rgVar.g));
            this.J.getClass();
            i2--;
            rgVar.d += rgVar.e;
        }
    }

    @Override // defpackage.ql
    public final int w(wl wlVar, am amVar) {
        if (this.o == 1) {
            return this.E;
        }
        if (amVar.b() < 1) {
            return 0;
        }
        return Y0(amVar.b() - 1, wlVar, amVar) + 1;
    }
}
