package androidx.recyclerview.widget;

import android.content.Context;
import android.graphics.Rect;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityEvent;
import defpackage.am;
import defpackage.b6;
import defpackage.hb;
import defpackage.le;
import defpackage.os;
import defpackage.pl;
import defpackage.ql;
import defpackage.ra;
import defpackage.rl;
import defpackage.uo;
import defpackage.vo;
import defpackage.wf;
import defpackage.wl;
import defpackage.xo;
import defpackage.yo;
import defpackage.z3;
import defpackage.z6;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.BitSet;
import java.util.WeakHashMap;

/* JADX INFO: compiled from: r8-map-id-51b54c9e59b63e0fb0abe04daff984b4fb85d07ef3fcb7973595bce215c10515 */
/* JADX INFO: loaded from: classes.dex */
public class StaggeredGridLayoutManager extends ql {
    public final z3 A;
    public final int B;
    public boolean C;
    public boolean D;
    public xo E;
    public final Rect F;
    public final uo G;
    public final boolean H;
    public int[] I;
    public final b6 J;
    public final int o;
    public final yo[] p;
    public final ra q;
    public final ra r;
    public final int s;
    public int t;
    public final wf u;
    public boolean v;
    public final BitSet x;
    public boolean w = false;
    public int y = -1;
    public int z = Integer.MIN_VALUE;

    public StaggeredGridLayoutManager(Context context, AttributeSet attributeSet, int i, int i2) {
        this.o = -1;
        this.v = false;
        z3 z3Var = new z3(10, false);
        this.A = z3Var;
        this.B = 2;
        this.F = new Rect();
        this.G = new uo(this);
        this.H = true;
        this.J = new b6(8, this);
        pl plVarD = ql.D(context, attributeSet, i, i2);
        int i3 = plVarD.a;
        if (i3 != 0 && i3 != 1) {
            z6.f("invalid orientation.");
            throw null;
        }
        b(null);
        if (i3 != this.s) {
            this.s = i3;
            ra raVar = this.q;
            this.q = this.r;
            this.r = raVar;
            h0();
        }
        int i4 = plVarD.b;
        b(null);
        if (i4 != this.o) {
            z3Var.h();
            h0();
            this.o = i4;
            this.x = new BitSet(this.o);
            this.p = new yo[this.o];
            for (int i5 = 0; i5 < this.o; i5++) {
                this.p[i5] = new yo(this, i5);
            }
            h0();
        }
        boolean z = plVarD.c;
        b(null);
        xo xoVar = this.E;
        if (xoVar != null && xoVar.h != z) {
            xoVar.h = z;
        }
        this.v = z;
        h0();
        wf wfVar = new wf();
        wfVar.a = true;
        wfVar.f = 0;
        wfVar.g = 0;
        this.u = wfVar;
        this.q = ra.a(this, this.s);
        this.r = ra.a(this, 1 - this.s);
    }

    public static int T0(int i, int i2, int i3) {
        int mode;
        return (!(i2 == 0 && i3 == 0) && ((mode = View.MeasureSpec.getMode(i)) == Integer.MIN_VALUE || mode == 1073741824)) ? View.MeasureSpec.makeMeasureSpec(Math.max(0, (View.MeasureSpec.getSize(i) - i2) - i3), mode) : i;
    }

    public final int A0() {
        if (u() == 0) {
            return 0;
        }
        return ql.C(t(0));
    }

    public final int B0() {
        int iU = u();
        if (iU == 0) {
            return 0;
        }
        return ql.C(t(iU - 1));
    }

    public final int C0(int i) {
        int iF = this.p[0].f(i);
        for (int i2 = 1; i2 < this.o; i2++) {
            int iF2 = this.p[i2].f(i);
            if (iF2 > iF) {
                iF = iF2;
            }
        }
        return iF;
    }

    public final int D0(int i) {
        int iH = this.p[0].h(i);
        for (int i2 = 1; i2 < this.o; i2++) {
            int iH2 = this.p[i2].h(i);
            if (iH2 < iH) {
                iH = iH2;
            }
        }
        return iH;
    }

    /* JADX WARN: Removed duplicated region for block: B:21:0x0035  */
    /* JADX WARN: Removed duplicated region for block: B:22:0x0037  */
    /* JADX WARN: Removed duplicated region for block: B:42:0x0093  */
    /* JADX WARN: Removed duplicated region for block: B:43:0x009d  */
    /* JADX WARN: Removed duplicated region for block: B:45:0x00ac  */
    /* JADX WARN: Removed duplicated region for block: B:51:0x00bd  */
    /* JADX WARN: Removed duplicated region for block: B:54:0x00c3  */
    /* JADX WARN: Removed duplicated region for block: B:66:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void E0(int r11, int r12, int r13) {
        /*
            Method dump skipped, instruction units count: 214
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.recyclerview.widget.StaggeredGridLayoutManager.E0(int, int, int):void");
    }

    /* JADX WARN: Removed duplicated region for block: B:51:0x00e7  */
    /* JADX WARN: Removed duplicated region for block: B:52:0x00e9  */
    /* JADX WARN: Removed duplicated region for block: B:54:0x00ec  */
    /* JADX WARN: Removed duplicated region for block: B:55:0x00ee  */
    /* JADX WARN: Removed duplicated region for block: B:68:0x00f1 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:74:0x002a A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final android.view.View F0() {
        /*
            Method dump skipped, instruction units count: 244
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.recyclerview.widget.StaggeredGridLayoutManager.F0():android.view.View");
    }

    @Override // defpackage.ql
    public final boolean G() {
        return this.B != 0;
    }

    public final boolean G0() {
        RecyclerView recyclerView = this.b;
        WeakHashMap weakHashMap = os.a;
        return recyclerView.getLayoutDirection() == 1;
    }

    public final void H0(View view, int i, int i2) {
        RecyclerView recyclerView = this.b;
        Rect rect = this.F;
        if (recyclerView == null) {
            rect.set(0, 0, 0, 0);
        } else {
            rect.set(recyclerView.I(view));
        }
        vo voVar = (vo) view.getLayoutParams();
        int iT0 = T0(i, ((ViewGroup.MarginLayoutParams) voVar).leftMargin + rect.left, ((ViewGroup.MarginLayoutParams) voVar).rightMargin + rect.right);
        int iT02 = T0(i2, ((ViewGroup.MarginLayoutParams) voVar).topMargin + rect.top, ((ViewGroup.MarginLayoutParams) voVar).bottomMargin + rect.bottom);
        if (p0(view, iT0, iT02, voVar)) {
            view.measure(iT0, iT02);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:107:0x0189  */
    /* JADX WARN: Removed duplicated region for block: B:108:0x018b  */
    /* JADX WARN: Removed duplicated region for block: B:123:0x01c0  */
    /* JADX WARN: Removed duplicated region for block: B:131:0x01e0  */
    /* JADX WARN: Removed duplicated region for block: B:254:0x03e6  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void I0(defpackage.wl r18, defpackage.am r19, boolean r20) {
        /*
            Method dump skipped, instruction units count: 1025
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.recyclerview.widget.StaggeredGridLayoutManager.I0(wl, am, boolean):void");
    }

    @Override // defpackage.ql
    public final void J(int i) {
        super.J(i);
        for (int i2 = 0; i2 < this.o; i2++) {
            yo yoVar = this.p[i2];
            int i3 = yoVar.b;
            if (i3 != Integer.MIN_VALUE) {
                yoVar.b = i3 + i;
            }
            int i4 = yoVar.c;
            if (i4 != Integer.MIN_VALUE) {
                yoVar.c = i4 + i;
            }
        }
    }

    public final boolean J0(int i) {
        if (this.s == 0) {
            return (i == -1) != this.w;
        }
        return ((i == -1) == this.w) == G0();
    }

    @Override // defpackage.ql
    public final void K(int i) {
        super.K(i);
        for (int i2 = 0; i2 < this.o; i2++) {
            yo yoVar = this.p[i2];
            int i3 = yoVar.b;
            if (i3 != Integer.MIN_VALUE) {
                yoVar.b = i3 + i;
            }
            int i4 = yoVar.c;
            if (i4 != Integer.MIN_VALUE) {
                yoVar.c = i4 + i;
            }
        }
    }

    public final void K0(int i) {
        int iA0;
        int i2;
        if (i > 0) {
            iA0 = B0();
            i2 = 1;
        } else {
            iA0 = A0();
            i2 = -1;
        }
        wf wfVar = this.u;
        wfVar.a = true;
        R0(iA0);
        Q0(i2);
        wfVar.c = iA0 + wfVar.d;
        wfVar.b = Math.abs(i);
    }

    @Override // defpackage.ql
    public final void L() {
        this.A.h();
        for (int i = 0; i < this.o; i++) {
            this.p[i].b();
        }
    }

    public final void L0(wl wlVar, wf wfVar) {
        if (!wfVar.a || wfVar.i) {
            return;
        }
        int i = wfVar.b;
        int i2 = wfVar.e;
        if (i == 0) {
            if (i2 == -1) {
                M0(wlVar, wfVar.g);
                return;
            } else {
                N0(wlVar, wfVar.f);
                return;
            }
        }
        int i3 = this.o;
        yo[] yoVarArr = this.p;
        int i4 = 1;
        if (i2 == -1) {
            int i5 = wfVar.f;
            int iH = yoVarArr[0].h(i5);
            while (i4 < i3) {
                int iH2 = yoVarArr[i4].h(i5);
                if (iH2 > iH) {
                    iH = iH2;
                }
                i4++;
            }
            int i6 = i5 - iH;
            int iMin = wfVar.g;
            if (i6 >= 0) {
                iMin -= Math.min(i6, wfVar.b);
            }
            M0(wlVar, iMin);
            return;
        }
        int i7 = wfVar.g;
        int iF = yoVarArr[0].f(i7);
        while (i4 < i3) {
            int iF2 = yoVarArr[i4].f(i7);
            if (iF2 < iF) {
                iF = iF2;
            }
            i4++;
        }
        int i8 = iF - wfVar.g;
        int iMin2 = wfVar.f;
        if (i8 >= 0) {
            iMin2 += Math.min(i8, wfVar.b);
        }
        N0(wlVar, iMin2);
    }

    @Override // defpackage.ql
    public final void M(RecyclerView recyclerView) {
        RecyclerView recyclerView2 = this.b;
        if (recyclerView2 != null) {
            recyclerView2.removeCallbacks(this.J);
        }
        for (int i = 0; i < this.o; i++) {
            this.p[i].b();
        }
        recyclerView.requestLayout();
    }

    public final void M0(wl wlVar, int i) {
        for (int iU = u() - 1; iU >= 0; iU--) {
            View viewT = t(iU);
            ra raVar = this.q;
            if (raVar.e(viewT) < i || raVar.n(viewT) < i) {
                return;
            }
            vo voVar = (vo) viewT.getLayoutParams();
            voVar.getClass();
            if (voVar.e.a.size() == 1) {
                return;
            }
            yo yoVar = voVar.e;
            ArrayList arrayList = yoVar.a;
            int size = arrayList.size();
            View view = (View) arrayList.remove(size - 1);
            vo voVar2 = (vo) view.getLayoutParams();
            voVar2.e = null;
            if (voVar2.a.g() || voVar2.a.j()) {
                yoVar.d -= yoVar.f.q.c(view);
            }
            if (size == 1) {
                yoVar.b = Integer.MIN_VALUE;
            }
            yoVar.c = Integer.MIN_VALUE;
            e0(viewT, wlVar);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:30:0x0046  */
    /* JADX WARN: Removed duplicated region for block: B:34:0x004d  */
    @Override // defpackage.ql
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final android.view.View N(android.view.View r9, int r10, defpackage.wl r11, defpackage.am r12) {
        /*
            Method dump skipped, instruction units count: 325
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.recyclerview.widget.StaggeredGridLayoutManager.N(android.view.View, int, wl, am):android.view.View");
    }

    public final void N0(wl wlVar, int i) {
        while (u() > 0) {
            View viewT = t(0);
            ra raVar = this.q;
            if (raVar.b(viewT) > i || raVar.m(viewT) > i) {
                return;
            }
            vo voVar = (vo) viewT.getLayoutParams();
            voVar.getClass();
            if (voVar.e.a.size() == 1) {
                return;
            }
            yo yoVar = voVar.e;
            ArrayList arrayList = yoVar.a;
            View view = (View) arrayList.remove(0);
            vo voVar2 = (vo) view.getLayoutParams();
            voVar2.e = null;
            if (arrayList.size() == 0) {
                yoVar.c = Integer.MIN_VALUE;
            }
            if (voVar2.a.g() || voVar2.a.j()) {
                yoVar.d -= yoVar.f.q.c(view);
            }
            yoVar.b = Integer.MIN_VALUE;
            e0(viewT, wlVar);
        }
    }

    @Override // defpackage.ql
    public final void O(AccessibilityEvent accessibilityEvent) {
        super.O(accessibilityEvent);
        if (u() > 0) {
            View viewX0 = x0(false);
            View viewW0 = w0(false);
            if (viewX0 == null || viewW0 == null) {
                return;
            }
            int iC = ql.C(viewX0);
            int iC2 = ql.C(viewW0);
            if (iC < iC2) {
                accessibilityEvent.setFromIndex(iC);
                accessibilityEvent.setToIndex(iC2);
            } else {
                accessibilityEvent.setFromIndex(iC2);
                accessibilityEvent.setToIndex(iC);
            }
        }
    }

    public final void O0() {
        if (this.s == 1 || !G0()) {
            this.w = this.v;
        } else {
            this.w = !this.v;
        }
    }

    public final int P0(int i, wl wlVar, am amVar) {
        if (u() == 0 || i == 0) {
            return 0;
        }
        K0(i);
        wf wfVar = this.u;
        int iV0 = v0(wlVar, wfVar, amVar);
        if (wfVar.b >= iV0) {
            i = i < 0 ? -iV0 : iV0;
        }
        this.q.o(-i);
        this.C = this.w;
        wfVar.b = 0;
        L0(wlVar, wfVar);
        return i;
    }

    public final void Q0(int i) {
        wf wfVar = this.u;
        wfVar.e = i;
        wfVar.d = this.w != (i == -1) ? -1 : 1;
    }

    public final void R0(int i) {
        wf wfVar = this.u;
        boolean z = false;
        wfVar.b = 0;
        wfVar.c = i;
        RecyclerView recyclerView = this.b;
        ra raVar = this.q;
        if (recyclerView == null || !recyclerView.h) {
            wfVar.g = raVar.f();
            wfVar.f = 0;
        } else {
            wfVar.f = raVar.k();
            wfVar.g = raVar.g();
        }
        wfVar.h = false;
        wfVar.a = true;
        if (raVar.i() == 0 && raVar.f() == 0) {
            z = true;
        }
        wfVar.i = z;
    }

    @Override // defpackage.ql
    public final void S(int i, int i2) {
        E0(i, i2, 1);
    }

    public final void S0(yo yoVar, int i, int i2) {
        int i3 = yoVar.d;
        int i4 = yoVar.e;
        BitSet bitSet = this.x;
        if (i != -1) {
            int i5 = yoVar.c;
            if (i5 == Integer.MIN_VALUE) {
                yoVar.a();
                i5 = yoVar.c;
            }
            if (i5 - i3 >= i2) {
                bitSet.set(i4, false);
                return;
            }
            return;
        }
        int i6 = yoVar.b;
        if (i6 == Integer.MIN_VALUE) {
            View view = (View) yoVar.a.get(0);
            vo voVar = (vo) view.getLayoutParams();
            yoVar.b = yoVar.f.q.e(view);
            voVar.getClass();
            i6 = yoVar.b;
        }
        if (i6 + i3 <= i2) {
            bitSet.set(i4, false);
        }
    }

    @Override // defpackage.ql
    public final void T() {
        this.A.h();
        h0();
    }

    @Override // defpackage.ql
    public final void U(int i, int i2) {
        E0(i, i2, 8);
    }

    @Override // defpackage.ql
    public final void V(int i, int i2) {
        E0(i, i2, 2);
    }

    @Override // defpackage.ql
    public final void W(int i, int i2) {
        E0(i, i2, 4);
    }

    @Override // defpackage.ql
    public final void X(wl wlVar, am amVar) {
        I0(wlVar, amVar, true);
    }

    @Override // defpackage.ql
    public final void Y(am amVar) {
        this.y = -1;
        this.z = Integer.MIN_VALUE;
        this.E = null;
        this.G.a();
    }

    @Override // defpackage.ql
    public final void Z(Parcelable parcelable) {
        if (parcelable instanceof xo) {
            xo xoVar = (xo) parcelable;
            this.E = xoVar;
            if (this.y != -1) {
                xoVar.a = -1;
                xoVar.b = -1;
                xoVar.d = null;
                xoVar.c = 0;
                xoVar.e = 0;
                xoVar.f = null;
                xoVar.g = null;
            }
            h0();
        }
    }

    @Override // defpackage.ql
    public final Parcelable a0() {
        int iH;
        int iK;
        int[] iArr;
        xo xoVar = this.E;
        if (xoVar != null) {
            xo xoVar2 = new xo();
            xoVar2.c = xoVar.c;
            xoVar2.a = xoVar.a;
            xoVar2.b = xoVar.b;
            xoVar2.d = xoVar.d;
            xoVar2.e = xoVar.e;
            xoVar2.f = xoVar.f;
            xoVar2.h = xoVar.h;
            xoVar2.i = xoVar.i;
            xoVar2.j = xoVar.j;
            xoVar2.g = xoVar.g;
            return xoVar2;
        }
        xo xoVar3 = new xo();
        xoVar3.h = this.v;
        xoVar3.i = this.C;
        xoVar3.j = this.D;
        z3 z3Var = this.A;
        if (z3Var == null || (iArr = (int[]) z3Var.b) == null) {
            xoVar3.e = 0;
        } else {
            xoVar3.f = iArr;
            xoVar3.e = iArr.length;
            xoVar3.g = (ArrayList) z3Var.c;
        }
        if (u() <= 0) {
            xoVar3.a = -1;
            xoVar3.b = -1;
            xoVar3.c = 0;
            return xoVar3;
        }
        xoVar3.a = this.C ? B0() : A0();
        View viewW0 = this.w ? w0(true) : x0(true);
        xoVar3.b = viewW0 != null ? ql.C(viewW0) : -1;
        int i = this.o;
        xoVar3.c = i;
        xoVar3.d = new int[i];
        for (int i2 = 0; i2 < i; i2++) {
            boolean z = this.C;
            ra raVar = this.q;
            yo[] yoVarArr = this.p;
            if (z) {
                iH = yoVarArr[i2].f(Integer.MIN_VALUE);
                if (iH != Integer.MIN_VALUE) {
                    iK = raVar.g();
                    iH -= iK;
                }
            } else {
                iH = yoVarArr[i2].h(Integer.MIN_VALUE);
                if (iH != Integer.MIN_VALUE) {
                    iK = raVar.k();
                    iH -= iK;
                }
            }
            xoVar3.d[i2] = iH;
        }
        return xoVar3;
    }

    @Override // defpackage.ql
    public final void b(String str) {
        RecyclerView recyclerView;
        if (this.E != null || (recyclerView = this.b) == null) {
            return;
        }
        recyclerView.h(str);
    }

    @Override // defpackage.ql
    public final void b0(int i) {
        if (i == 0) {
            t0();
        }
    }

    @Override // defpackage.ql
    public final boolean c() {
        return this.s == 0;
    }

    @Override // defpackage.ql
    public final boolean d() {
        return this.s == 1;
    }

    @Override // defpackage.ql
    public final boolean e(rl rlVar) {
        return rlVar instanceof vo;
    }

    @Override // defpackage.ql
    public final void g(int i, int i2, am amVar, le leVar) {
        wf wfVar;
        int iF;
        if (this.s != 0) {
            i = i2;
        }
        if (u() == 0 || i == 0) {
            return;
        }
        K0(i);
        int[] iArr = this.I;
        int i3 = this.o;
        if (iArr == null || iArr.length < i3) {
            this.I = new int[i3];
        }
        int i4 = 0;
        int i5 = 0;
        while (true) {
            wfVar = this.u;
            if (i4 >= i3) {
                break;
            }
            int i6 = wfVar.d;
            yo[] yoVarArr = this.p;
            if (i6 == -1) {
                int i7 = wfVar.f;
                iF = i7 - yoVarArr[i4].h(i7);
            } else {
                iF = yoVarArr[i4].f(wfVar.g) - wfVar.g;
            }
            if (iF >= 0) {
                this.I[i5] = iF;
                i5++;
            }
            i4++;
        }
        Arrays.sort(this.I, 0, i5);
        for (int i8 = 0; i8 < i5; i8++) {
            int i9 = wfVar.c;
            if (i9 < 0 || i9 >= amVar.b()) {
                return;
            }
            leVar.a(wfVar.c, this.I[i8]);
            wfVar.c += wfVar.d;
        }
    }

    @Override // defpackage.ql
    public final int i(am amVar) {
        if (u() == 0) {
            return 0;
        }
        boolean z = !this.H;
        return hb.l(amVar, this.q, x0(z), w0(z), this, this.H);
    }

    @Override // defpackage.ql
    public final int i0(int i, wl wlVar, am amVar) {
        return P0(i, wlVar, amVar);
    }

    @Override // defpackage.ql
    public final int j(am amVar) {
        return u0(amVar);
    }

    @Override // defpackage.ql
    public final int j0(int i, wl wlVar, am amVar) {
        return P0(i, wlVar, amVar);
    }

    @Override // defpackage.ql
    public final int k(am amVar) {
        if (u() == 0) {
            return 0;
        }
        boolean z = !this.H;
        return hb.n(amVar, this.q, x0(z), w0(z), this, this.H);
    }

    @Override // defpackage.ql
    public final int l(am amVar) {
        if (u() == 0) {
            return 0;
        }
        boolean z = !this.H;
        return hb.l(amVar, this.q, x0(z), w0(z), this, this.H);
    }

    @Override // defpackage.ql
    public final int m(am amVar) {
        return u0(amVar);
    }

    @Override // defpackage.ql
    public final void m0(Rect rect, int i, int i2) {
        int iF;
        int iF2;
        int iA = A() + z();
        int iY = y() + B();
        int i3 = this.s;
        int i4 = this.o;
        if (i3 == 1) {
            int iHeight = rect.height() + iY;
            RecyclerView recyclerView = this.b;
            WeakHashMap weakHashMap = os.a;
            iF2 = ql.f(i2, iHeight, recyclerView.getMinimumHeight());
            iF = ql.f(i, (this.t * i4) + iA, this.b.getMinimumWidth());
        } else {
            int iWidth = rect.width() + iA;
            RecyclerView recyclerView2 = this.b;
            WeakHashMap weakHashMap2 = os.a;
            iF = ql.f(i, iWidth, recyclerView2.getMinimumWidth());
            iF2 = ql.f(i2, (this.t * i4) + iY, this.b.getMinimumHeight());
        }
        this.b.setMeasuredDimension(iF, iF2);
    }

    @Override // defpackage.ql
    public final int n(am amVar) {
        if (u() == 0) {
            return 0;
        }
        boolean z = !this.H;
        return hb.n(amVar, this.q, x0(z), w0(z), this, this.H);
    }

    @Override // defpackage.ql
    public final rl q() {
        return this.s == 0 ? new vo(-2, -1) : new vo(-1, -2);
    }

    @Override // defpackage.ql
    public final rl r(Context context, AttributeSet attributeSet) {
        return new vo(context, attributeSet);
    }

    @Override // defpackage.ql
    public final rl s(ViewGroup.LayoutParams layoutParams) {
        return layoutParams instanceof ViewGroup.MarginLayoutParams ? new vo((ViewGroup.MarginLayoutParams) layoutParams) : new vo(layoutParams);
    }

    @Override // defpackage.ql
    public final boolean s0() {
        return this.E == null;
    }

    public final boolean t0() {
        int iA0;
        if (u() != 0 && this.B != 0 && this.f) {
            if (this.w) {
                iA0 = B0();
                A0();
            } else {
                iA0 = A0();
                B0();
            }
            if (iA0 == 0 && F0() != null) {
                this.A.h();
                this.e = true;
                h0();
                return true;
            }
        }
        return false;
    }

    public final int u0(am amVar) {
        if (u() == 0) {
            return 0;
        }
        boolean z = !this.H;
        return hb.m(amVar, this.q, x0(z), w0(z), this, this.H, this.w);
    }

    /* JADX WARN: Type inference failed for: r5v14 */
    /* JADX WARN: Type inference failed for: r5v2 */
    /* JADX WARN: Type inference failed for: r5v3, types: [boolean, int] */
    public final int v0(wl wlVar, wf wfVar, am amVar) {
        yo[] yoVarArr;
        BitSet bitSet;
        int i;
        yo[] yoVarArr2;
        yo yoVar;
        ?? r5;
        int iH;
        int iC;
        int iC2;
        int iG;
        BitSet bitSet2;
        int i2;
        int i3;
        wl wlVar2 = wlVar;
        BitSet bitSet3 = this.x;
        int i4 = this.o;
        bitSet3.set(0, i4, true);
        wf wfVar2 = this.u;
        int i5 = wfVar2.i ? wfVar.e == 1 ? Integer.MAX_VALUE : Integer.MIN_VALUE : wfVar.e == 1 ? wfVar.g + wfVar.b : wfVar.f - wfVar.b;
        int i6 = wfVar.e;
        int i7 = 0;
        while (true) {
            yoVarArr = this.p;
            if (i7 >= i4) {
                break;
            }
            if (!yoVarArr[i7].a.isEmpty()) {
                S0(yoVarArr[i7], i6, i5);
            }
            i7++;
        }
        boolean z = this.w;
        ra raVar = this.q;
        int iG2 = z ? raVar.g() : raVar.k();
        boolean z2 = false;
        while (true) {
            int i8 = wfVar.c;
            if (i8 < 0 || i8 >= amVar.b() || (!wfVar2.i && bitSet3.isEmpty())) {
                break;
            }
            View view = wlVar2.k(wfVar.c, Long.MAX_VALUE).a;
            wfVar.c += wfVar.d;
            vo voVar = (vo) view.getLayoutParams();
            int iB = voVar.a.b();
            z3 z3Var = this.A;
            int[] iArr = (int[]) z3Var.b;
            int i9 = (iArr == null || iB >= iArr.length) ? -1 : iArr[iB];
            if (i9 == -1) {
                if (J0(wfVar.e)) {
                    i = i4;
                    i3 = i4 - 1;
                    i4 = -1;
                    i2 = -1;
                } else {
                    i = i4;
                    i2 = 1;
                    i3 = 0;
                }
                yo yoVar2 = null;
                int i10 = i2;
                if (wfVar.e == 1) {
                    int iK = raVar.k();
                    yoVarArr2 = yoVarArr;
                    int i11 = i3;
                    int i12 = Integer.MAX_VALUE;
                    while (i11 != i4) {
                        int i13 = i11;
                        yo yoVar3 = yoVarArr2[i13];
                        BitSet bitSet4 = bitSet3;
                        int iF = yoVar3.f(iK);
                        if (iF < i12) {
                            i12 = iF;
                            yoVar2 = yoVar3;
                        }
                        i11 = i13 + i10;
                        bitSet3 = bitSet4;
                    }
                    bitSet = bitSet3;
                } else {
                    bitSet = bitSet3;
                    yoVarArr2 = yoVarArr;
                    int iG3 = raVar.g();
                    int i14 = i3;
                    int i15 = Integer.MIN_VALUE;
                    while (i14 != i4) {
                        yo yoVar4 = yoVarArr2[i14];
                        int i16 = i4;
                        int iH2 = yoVar4.h(iG3);
                        if (iH2 > i15) {
                            i15 = iH2;
                            yoVar2 = yoVar4;
                        }
                        i14 += i10;
                        i4 = i16;
                    }
                }
                yoVar = yoVar2;
                z3Var.i(iB);
                ((int[]) z3Var.b)[iB] = yoVar.e;
            } else {
                bitSet = bitSet3;
                i = i4;
                yoVarArr2 = yoVarArr;
                yoVar = yoVarArr2[i9];
            }
            voVar.e = yoVar;
            if (wfVar.e == 1) {
                r5 = 0;
                a(view, -1, false);
            } else {
                r5 = 0;
                a(view, 0, false);
            }
            int i17 = this.s;
            if (i17 == 1) {
                H0(view, ql.v(r5, this.t, this.k, r5, ((ViewGroup.MarginLayoutParams) voVar).width), ql.v(true, this.n, this.l, y() + B(), ((ViewGroup.MarginLayoutParams) voVar).height));
            } else {
                H0(view, ql.v(true, this.m, this.k, A() + z(), ((ViewGroup.MarginLayoutParams) voVar).width), ql.v(false, this.t, this.l, 0, ((ViewGroup.MarginLayoutParams) voVar).height));
            }
            if (wfVar.e == 1) {
                iC = yoVar.f(iG2);
                iH = raVar.c(view) + iC;
            } else {
                iH = yoVar.h(iG2);
                iC = iH - raVar.c(view);
            }
            int i18 = wfVar.e;
            yo yoVar5 = voVar.e;
            if (i18 == 1) {
                yoVar5.getClass();
                vo voVar2 = (vo) view.getLayoutParams();
                voVar2.e = yoVar5;
                ArrayList arrayList = yoVar5.a;
                arrayList.add(view);
                yoVar5.c = Integer.MIN_VALUE;
                if (arrayList.size() == 1) {
                    yoVar5.b = Integer.MIN_VALUE;
                }
                if (voVar2.a.g() || voVar2.a.j()) {
                    yoVar5.d = yoVar5.f.q.c(view) + yoVar5.d;
                }
            } else {
                yoVar5.getClass();
                vo voVar3 = (vo) view.getLayoutParams();
                voVar3.e = yoVar5;
                ArrayList arrayList2 = yoVar5.a;
                arrayList2.add(0, view);
                yoVar5.b = Integer.MIN_VALUE;
                if (arrayList2.size() == 1) {
                    yoVar5.c = Integer.MIN_VALUE;
                }
                if (voVar3.a.g() || voVar3.a.j()) {
                    yoVar5.d = yoVar5.f.q.c(view) + yoVar5.d;
                }
            }
            boolean zG0 = G0();
            ra raVar2 = this.r;
            if (zG0 && i17 == 1) {
                iG = raVar2.g() - (((i - 1) - yoVar.e) * this.t);
                iC2 = iG - raVar2.c(view);
            } else {
                int iK2 = (yoVar.e * this.t) + raVar2.k();
                int iC3 = raVar2.c(view) + iK2;
                iC2 = iK2;
                iG = iC3;
            }
            z2 = true;
            if (i17 == 1) {
                ql.I(view, iC2, iC, iG, iH);
            } else {
                ql.I(view, iC, iC2, iH, iG);
            }
            S0(yoVar, wfVar2.e, i5);
            wlVar2 = wlVar;
            L0(wlVar2, wfVar2);
            if (wfVar2.h && view.hasFocusable()) {
                bitSet2 = bitSet;
                bitSet2.set(yoVar.e, false);
            } else {
                bitSet2 = bitSet;
            }
            bitSet3 = bitSet2;
            i4 = i;
            yoVarArr = yoVarArr2;
        }
        if (!z2) {
            L0(wlVar2, wfVar2);
        }
        int iK3 = wfVar2.e == -1 ? raVar.k() - D0(raVar.k()) : C0(raVar.g()) - raVar.g();
        if (iK3 > 0) {
            return Math.min(wfVar.b, iK3);
        }
        return 0;
    }

    public final View w0(boolean z) {
        ra raVar = this.q;
        int iK = raVar.k();
        int iG = raVar.g();
        View view = null;
        for (int iU = u() - 1; iU >= 0; iU--) {
            View viewT = t(iU);
            int iE = raVar.e(viewT);
            int iB = raVar.b(viewT);
            if (iB > iK && iE < iG) {
                if (iB <= iG || !z) {
                    return viewT;
                }
                if (view == null) {
                    view = viewT;
                }
            }
        }
        return view;
    }

    public final View x0(boolean z) {
        ra raVar = this.q;
        int iK = raVar.k();
        int iG = raVar.g();
        int iU = u();
        View view = null;
        for (int i = 0; i < iU; i++) {
            View viewT = t(i);
            int iE = raVar.e(viewT);
            if (raVar.b(viewT) > iK && iE < iG) {
                if (iE >= iK || !z) {
                    return viewT;
                }
                if (view == null) {
                    view = viewT;
                }
            }
        }
        return view;
    }

    public final void y0(wl wlVar, am amVar, boolean z) {
        int iG;
        int iC0 = C0(Integer.MIN_VALUE);
        if (iC0 != Integer.MIN_VALUE && (iG = this.q.g() - iC0) > 0) {
            int i = iG - (-P0(-iG, wlVar, amVar));
            if (!z || i <= 0) {
                return;
            }
            this.q.o(i);
        }
    }

    public final void z0(wl wlVar, am amVar, boolean z) {
        int iK;
        int iD0 = D0(Integer.MAX_VALUE);
        if (iD0 != Integer.MAX_VALUE && (iK = iD0 - this.q.k()) > 0) {
            int iP0 = iK - P0(iK, wlVar, amVar);
            if (!z || iP0 <= 0) {
                return;
            }
            this.q.o(-iP0);
        }
    }
}
