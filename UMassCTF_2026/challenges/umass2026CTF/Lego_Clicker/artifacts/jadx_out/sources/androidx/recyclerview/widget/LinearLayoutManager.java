package androidx.recyclerview.widget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Rect;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityEvent;
import defpackage.am;
import defpackage.hb;
import defpackage.le;
import defpackage.os;
import defpackage.pg;
import defpackage.pl;
import defpackage.qg;
import defpackage.ql;
import defpackage.ra;
import defpackage.rg;
import defpackage.rl;
import defpackage.sg;
import defpackage.wl;
import java.util.List;
import java.util.WeakHashMap;

/* JADX INFO: compiled from: r8-map-id-51b54c9e59b63e0fb0abe04daff984b4fb85d07ef3fcb7973595bce215c10515 */
/* JADX INFO: loaded from: classes.dex */
public class LinearLayoutManager extends ql {
    public final qg A;
    public final int B;
    public final int[] C;
    public int o;
    public rg p;
    public ra q;
    public boolean r;
    public final boolean s;
    public boolean t;
    public boolean u;
    public final boolean v;
    public int w;
    public int x;
    public sg y;
    public final pg z;

    @SuppressLint({"UnknownNullness"})
    public LinearLayoutManager(Context context, AttributeSet attributeSet, int i, int i2) {
        this.o = 1;
        this.s = false;
        this.t = false;
        this.u = false;
        this.v = true;
        this.w = -1;
        this.x = Integer.MIN_VALUE;
        this.y = null;
        this.z = new pg();
        this.A = new qg();
        this.B = 2;
        this.C = new int[2];
        pl plVarD = ql.D(context, attributeSet, i, i2);
        Q0(plVarD.a);
        boolean z = plVarD.c;
        b(null);
        if (z != this.s) {
            this.s = z;
            h0();
        }
        R0(plVarD.d);
    }

    public final View A0(boolean z) {
        return this.t ? D0(0, u(), z) : D0(u() - 1, -1, z);
    }

    public final View B0(boolean z) {
        return this.t ? D0(u() - 1, -1, z) : D0(0, u(), z);
    }

    public final View C0(int i, int i2) {
        int i3;
        int i4;
        y0();
        if (i2 <= i && i2 >= i) {
            return t(i);
        }
        if (this.q.e(t(i)) < this.q.k()) {
            i3 = 16644;
            i4 = 16388;
        } else {
            i3 = 4161;
            i4 = 4097;
        }
        return this.o == 0 ? this.c.j(i, i2, i3, i4) : this.d.j(i, i2, i3, i4);
    }

    public final View D0(int i, int i2, boolean z) {
        y0();
        int i3 = z ? 24579 : 320;
        return this.o == 0 ? this.c.j(i, i2, i3, 320) : this.d.j(i, i2, i3, 320);
    }

    /* JADX WARN: Removed duplicated region for block: B:33:0x0075  */
    /* JADX WARN: Removed duplicated region for block: B:35:0x0079  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public android.view.View E0(defpackage.wl r17, defpackage.am r18, boolean r19, boolean r20) {
        /*
            r16 = this;
            r0 = r16
            r0.y0()
            int r1 = r0.u()
            r2 = 0
            r3 = 1
            if (r20 == 0) goto L15
            int r1 = r0.u()
            int r1 = r1 - r3
            r4 = -1
            r5 = r4
            goto L18
        L15:
            r4 = r1
            r1 = r2
            r5 = r3
        L18:
            int r6 = r18.b()
            ra r7 = r0.q
            int r7 = r7.k()
            ra r8 = r0.q
            int r8 = r8.g()
            r9 = 0
            r10 = r9
            r11 = r10
        L2b:
            if (r1 == r4) goto L7c
            android.view.View r12 = r0.t(r1)
            int r13 = defpackage.ql.C(r12)
            ra r14 = r0.q
            int r14 = r14.e(r12)
            ra r15 = r0.q
            int r15 = r15.b(r12)
            if (r13 < 0) goto L7a
            if (r13 >= r6) goto L7a
            android.view.ViewGroup$LayoutParams r13 = r12.getLayoutParams()
            rl r13 = (defpackage.rl) r13
            em r13 = r13.a
            boolean r13 = r13.g()
            if (r13 == 0) goto L57
            if (r11 != 0) goto L7a
            r11 = r12
            goto L7a
        L57:
            if (r15 > r7) goto L5d
            if (r14 >= r7) goto L5d
            r13 = r3
            goto L5e
        L5d:
            r13 = r2
        L5e:
            if (r14 < r8) goto L64
            if (r15 <= r8) goto L64
            r14 = r3
            goto L65
        L64:
            r14 = r2
        L65:
            if (r13 != 0) goto L6b
            if (r14 == 0) goto L6a
            goto L6b
        L6a:
            return r12
        L6b:
            if (r19 == 0) goto L73
            if (r14 == 0) goto L70
            goto L75
        L70:
            if (r9 != 0) goto L7a
            goto L79
        L73:
            if (r13 == 0) goto L77
        L75:
            r10 = r12
            goto L7a
        L77:
            if (r9 != 0) goto L7a
        L79:
            r9 = r12
        L7a:
            int r1 = r1 + r5
            goto L2b
        L7c:
            if (r9 == 0) goto L7f
            return r9
        L7f:
            if (r10 == 0) goto L82
            return r10
        L82:
            return r11
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.recyclerview.widget.LinearLayoutManager.E0(wl, am, boolean, boolean):android.view.View");
    }

    public final int F0(int i, wl wlVar, am amVar, boolean z) {
        int iG;
        int iG2 = this.q.g() - i;
        if (iG2 <= 0) {
            return 0;
        }
        int i2 = -P0(-iG2, wlVar, amVar);
        int i3 = i + i2;
        if (!z || (iG = this.q.g() - i3) <= 0) {
            return i2;
        }
        this.q.o(iG);
        return iG + i2;
    }

    @Override // defpackage.ql
    public final boolean G() {
        return true;
    }

    public final int G0(int i, wl wlVar, am amVar, boolean z) {
        int iK;
        int iK2 = i - this.q.k();
        if (iK2 <= 0) {
            return 0;
        }
        int i2 = -P0(iK2, wlVar, amVar);
        int i3 = i + i2;
        if (!z || (iK = i3 - this.q.k()) <= 0) {
            return i2;
        }
        this.q.o(-iK);
        return i2 - iK;
    }

    public final View H0() {
        return t(this.t ? 0 : u() - 1);
    }

    public final View I0() {
        return t(this.t ? u() - 1 : 0);
    }

    public final boolean J0() {
        RecyclerView recyclerView = this.b;
        WeakHashMap weakHashMap = os.a;
        return recyclerView.getLayoutDirection() == 1;
    }

    public void K0(wl wlVar, am amVar, rg rgVar, qg qgVar) {
        int i;
        int iD;
        int i2;
        int iD2;
        View viewB = rgVar.b(wlVar);
        if (viewB == null) {
            qgVar.b = true;
            return;
        }
        rl rlVar = (rl) viewB.getLayoutParams();
        List list = rgVar.k;
        boolean z = this.t;
        int i3 = rgVar.f;
        if (list == null) {
            if (z == (i3 == -1)) {
                a(viewB, -1, false);
            } else {
                a(viewB, 0, false);
            }
        } else {
            if (z == (i3 == -1)) {
                a(viewB, -1, true);
            } else {
                a(viewB, 0, true);
            }
        }
        rl rlVar2 = (rl) viewB.getLayoutParams();
        Rect rectI = this.b.I(viewB);
        int i4 = rectI.left + rectI.right;
        int i5 = rectI.top + rectI.bottom;
        int iV = ql.v(c(), this.m, this.k, A() + z() + ((ViewGroup.MarginLayoutParams) rlVar2).leftMargin + ((ViewGroup.MarginLayoutParams) rlVar2).rightMargin + i4, ((ViewGroup.MarginLayoutParams) rlVar2).width);
        int iV2 = ql.v(d(), this.n, this.l, y() + B() + ((ViewGroup.MarginLayoutParams) rlVar2).topMargin + ((ViewGroup.MarginLayoutParams) rlVar2).bottomMargin + i5, ((ViewGroup.MarginLayoutParams) rlVar2).height);
        if (p0(viewB, iV, iV2, rlVar2)) {
            viewB.measure(iV, iV2);
        }
        qgVar.a = this.q.c(viewB);
        if (this.o == 1) {
            if (J0()) {
                iD2 = this.m - A();
                iD = iD2 - this.q.d(viewB);
            } else {
                int iZ = z();
                iD2 = this.q.d(viewB) + iZ;
                iD = iZ;
            }
            int i6 = rgVar.f;
            i2 = rgVar.b;
            int i7 = qgVar.a;
            if (i6 == -1) {
                int i8 = i2 - i7;
                i = i2;
                i2 = i8;
            } else {
                i = i7 + i2;
            }
        } else {
            int iB = B();
            int iD3 = this.q.d(viewB) + iB;
            int i9 = rgVar.f;
            int i10 = rgVar.b;
            int i11 = qgVar.a;
            if (i9 == -1) {
                int i12 = i10 - i11;
                iD2 = i10;
                i2 = iB;
                i = iD3;
                iD = i12;
            } else {
                int i13 = i10 + i11;
                i = iD3;
                iD = i10;
                i2 = iB;
                iD2 = i13;
            }
        }
        ql.I(viewB, iD, i2, iD2, i);
        if (rlVar.a.g() || rlVar.a.j()) {
            qgVar.c = true;
        }
        qgVar.d = viewB.hasFocusable();
    }

    public final void M0(wl wlVar, rg rgVar) {
        if (!rgVar.a || rgVar.l) {
            return;
        }
        int i = rgVar.g;
        int i2 = rgVar.i;
        if (rgVar.f == -1) {
            int iU = u();
            if (i < 0) {
                return;
            }
            int iF = (this.q.f() - i) + i2;
            if (this.t) {
                for (int i3 = 0; i3 < iU; i3++) {
                    View viewT = t(i3);
                    if (this.q.e(viewT) < iF || this.q.n(viewT) < iF) {
                        N0(wlVar, 0, i3);
                        return;
                    }
                }
                return;
            }
            int i4 = iU - 1;
            for (int i5 = i4; i5 >= 0; i5--) {
                View viewT2 = t(i5);
                if (this.q.e(viewT2) < iF || this.q.n(viewT2) < iF) {
                    N0(wlVar, i4, i5);
                    return;
                }
            }
            return;
        }
        if (i < 0) {
            return;
        }
        int i6 = i - i2;
        int iU2 = u();
        if (!this.t) {
            for (int i7 = 0; i7 < iU2; i7++) {
                View viewT3 = t(i7);
                if (this.q.b(viewT3) > i6 || this.q.m(viewT3) > i6) {
                    N0(wlVar, 0, i7);
                    return;
                }
            }
            return;
        }
        int i8 = iU2 - 1;
        for (int i9 = i8; i9 >= 0; i9--) {
            View viewT4 = t(i9);
            if (this.q.b(viewT4) > i6 || this.q.m(viewT4) > i6) {
                N0(wlVar, i8, i9);
                return;
            }
        }
    }

    @Override // defpackage.ql
    public View N(View view, int i, wl wlVar, am amVar) {
        int iX0;
        O0();
        if (u() != 0 && (iX0 = x0(i)) != Integer.MIN_VALUE) {
            y0();
            S0(iX0, (int) (this.q.l() * 0.33333334f), false, amVar);
            rg rgVar = this.p;
            rgVar.g = Integer.MIN_VALUE;
            rgVar.a = false;
            z0(wlVar, rgVar, amVar, true);
            boolean z = this.t;
            View viewC0 = iX0 == -1 ? z ? C0(u() - 1, -1) : C0(0, u()) : z ? C0(0, u()) : C0(u() - 1, -1);
            View viewI0 = iX0 == -1 ? I0() : H0();
            if (!viewI0.hasFocusable()) {
                return viewC0;
            }
            if (viewC0 != null) {
                return viewI0;
            }
        }
        return null;
    }

    public final void N0(wl wlVar, int i, int i2) {
        if (i == i2) {
            return;
        }
        if (i2 <= i) {
            while (i > i2) {
                View viewT = t(i);
                f0(i);
                wlVar.h(viewT);
                i--;
            }
            return;
        }
        for (int i3 = i2 - 1; i3 >= i; i3--) {
            View viewT2 = t(i3);
            f0(i3);
            wlVar.h(viewT2);
        }
    }

    @Override // defpackage.ql
    public final void O(AccessibilityEvent accessibilityEvent) {
        super.O(accessibilityEvent);
        if (u() > 0) {
            View viewD0 = D0(0, u(), false);
            accessibilityEvent.setFromIndex(viewD0 == null ? -1 : ql.C(viewD0));
            View viewD02 = D0(u() - 1, -1, false);
            accessibilityEvent.setToIndex(viewD02 != null ? ql.C(viewD02) : -1);
        }
    }

    public final void O0() {
        if (this.o == 1 || !J0()) {
            this.t = this.s;
        } else {
            this.t = !this.s;
        }
    }

    public final int P0(int i, wl wlVar, am amVar) {
        if (u() != 0 && i != 0) {
            y0();
            this.p.a = true;
            int i2 = i > 0 ? 1 : -1;
            int iAbs = Math.abs(i);
            S0(i2, iAbs, true, amVar);
            rg rgVar = this.p;
            int iZ0 = z0(wlVar, rgVar, amVar, false) + rgVar.g;
            if (iZ0 >= 0) {
                if (iAbs > iZ0) {
                    i = i2 * iZ0;
                }
                this.q.o(-i);
                this.p.j = i;
                return i;
            }
        }
        return 0;
    }

    public final void Q0(int i) {
        if (i != 0 && i != 1) {
            throw new IllegalArgumentException("invalid orientation:" + i);
        }
        b(null);
        if (i != this.o || this.q == null) {
            ra raVarA = ra.a(this, i);
            this.q = raVarA;
            this.z.a = raVarA;
            this.o = i;
            h0();
        }
    }

    public void R0(boolean z) {
        b(null);
        if (this.u == z) {
            return;
        }
        this.u = z;
        h0();
    }

    public final void S0(int i, int i2, boolean z, am amVar) {
        int iK;
        this.p.l = this.q.i() == 0 && this.q.f() == 0;
        this.p.f = i;
        int[] iArr = this.C;
        iArr[0] = 0;
        iArr[1] = 0;
        amVar.getClass();
        int i3 = this.p.f;
        iArr[0] = 0;
        iArr[1] = 0;
        int iMax = Math.max(0, 0);
        int iMax2 = Math.max(0, iArr[1]);
        boolean z2 = i == 1;
        rg rgVar = this.p;
        int i4 = z2 ? iMax2 : iMax;
        rgVar.h = i4;
        if (!z2) {
            iMax = iMax2;
        }
        rgVar.i = iMax;
        if (z2) {
            rgVar.h = this.q.h() + i4;
            View viewH0 = H0();
            rg rgVar2 = this.p;
            rgVar2.e = this.t ? -1 : 1;
            int iC = ql.C(viewH0);
            rg rgVar3 = this.p;
            rgVar2.d = iC + rgVar3.e;
            rgVar3.b = this.q.b(viewH0);
            iK = this.q.b(viewH0) - this.q.g();
        } else {
            View viewI0 = I0();
            rg rgVar4 = this.p;
            rgVar4.h = this.q.k() + rgVar4.h;
            rg rgVar5 = this.p;
            rgVar5.e = this.t ? 1 : -1;
            int iC2 = ql.C(viewI0);
            rg rgVar6 = this.p;
            rgVar5.d = iC2 + rgVar6.e;
            rgVar6.b = this.q.e(viewI0);
            iK = (-this.q.e(viewI0)) + this.q.k();
        }
        rg rgVar7 = this.p;
        rgVar7.c = i2;
        if (z) {
            rgVar7.c = i2 - iK;
        }
        rgVar7.g = iK;
    }

    public final void T0(int i, int i2) {
        this.p.c = this.q.g() - i2;
        rg rgVar = this.p;
        rgVar.e = this.t ? -1 : 1;
        rgVar.d = i;
        rgVar.f = 1;
        rgVar.b = i2;
        rgVar.g = Integer.MIN_VALUE;
    }

    public final void U0(int i, int i2) {
        this.p.c = i2 - this.q.k();
        rg rgVar = this.p;
        rgVar.d = i;
        rgVar.e = this.t ? 1 : -1;
        rgVar.f = -1;
        rgVar.b = i2;
        rgVar.g = Integer.MIN_VALUE;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:111:0x01cd  */
    /* JADX WARN: Removed duplicated region for block: B:143:0x0242  */
    /* JADX WARN: Removed duplicated region for block: B:95:0x018a  */
    /* JADX WARN: Type inference failed for: r4v12 */
    /* JADX WARN: Type inference failed for: r4v13, types: [boolean, int] */
    /* JADX WARN: Type inference failed for: r4v14 */
    @Override // defpackage.ql
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void X(defpackage.wl r18, defpackage.am r19) {
        /*
            Method dump skipped, instruction units count: 1110
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.recyclerview.widget.LinearLayoutManager.X(wl, am):void");
    }

    @Override // defpackage.ql
    public void Y(am amVar) {
        this.y = null;
        this.w = -1;
        this.x = Integer.MIN_VALUE;
        this.z.c();
    }

    @Override // defpackage.ql
    public final void Z(Parcelable parcelable) {
        if (parcelable instanceof sg) {
            sg sgVar = (sg) parcelable;
            this.y = sgVar;
            if (this.w != -1) {
                sgVar.a = -1;
            }
            h0();
        }
    }

    @Override // defpackage.ql
    public final Parcelable a0() {
        sg sgVar = this.y;
        if (sgVar != null) {
            sg sgVar2 = new sg();
            sgVar2.a = sgVar.a;
            sgVar2.b = sgVar.b;
            sgVar2.c = sgVar.c;
            return sgVar2;
        }
        sg sgVar3 = new sg();
        if (u() <= 0) {
            sgVar3.a = -1;
            return sgVar3;
        }
        y0();
        boolean z = this.r ^ this.t;
        sgVar3.c = z;
        if (z) {
            View viewH0 = H0();
            sgVar3.b = this.q.g() - this.q.b(viewH0);
            sgVar3.a = ql.C(viewH0);
            return sgVar3;
        }
        View viewI0 = I0();
        sgVar3.a = ql.C(viewI0);
        sgVar3.b = this.q.e(viewI0) - this.q.k();
        return sgVar3;
    }

    @Override // defpackage.ql
    public final void b(String str) {
        RecyclerView recyclerView;
        if (this.y != null || (recyclerView = this.b) == null) {
            return;
        }
        recyclerView.h(str);
    }

    @Override // defpackage.ql
    public final boolean c() {
        return this.o == 0;
    }

    @Override // defpackage.ql
    public final boolean d() {
        return this.o == 1;
    }

    @Override // defpackage.ql
    public final void g(int i, int i2, am amVar, le leVar) {
        if (this.o != 0) {
            i = i2;
        }
        if (u() == 0 || i == 0) {
            return;
        }
        y0();
        S0(i > 0 ? 1 : -1, Math.abs(i), true, amVar);
        t0(amVar, this.p, leVar);
    }

    @Override // defpackage.ql
    public final void h(int i, le leVar) {
        boolean z;
        int i2;
        sg sgVar = this.y;
        if (sgVar == null || (i2 = sgVar.a) < 0) {
            O0();
            z = this.t;
            i2 = this.w;
            if (i2 == -1) {
                i2 = z ? i - 1 : 0;
            }
        } else {
            z = sgVar.c;
        }
        int i3 = z ? -1 : 1;
        for (int i4 = 0; i4 < this.B && i2 >= 0 && i2 < i; i4++) {
            leVar.a(i2, 0);
            i2 += i3;
        }
    }

    @Override // defpackage.ql
    public final int i(am amVar) {
        return u0(amVar);
    }

    @Override // defpackage.ql
    public int i0(int i, wl wlVar, am amVar) {
        if (this.o == 1) {
            return 0;
        }
        return P0(i, wlVar, amVar);
    }

    @Override // defpackage.ql
    public int j(am amVar) {
        return v0(amVar);
    }

    @Override // defpackage.ql
    public int j0(int i, wl wlVar, am amVar) {
        if (this.o == 0) {
            return 0;
        }
        return P0(i, wlVar, amVar);
    }

    @Override // defpackage.ql
    public int k(am amVar) {
        return w0(amVar);
    }

    @Override // defpackage.ql
    public final int l(am amVar) {
        return u0(amVar);
    }

    @Override // defpackage.ql
    public int m(am amVar) {
        return v0(amVar);
    }

    @Override // defpackage.ql
    public int n(am amVar) {
        return w0(amVar);
    }

    @Override // defpackage.ql
    public final View p(int i) {
        int iU = u();
        if (iU == 0) {
            return null;
        }
        int iC = i - ql.C(t(0));
        if (iC >= 0 && iC < iU) {
            View viewT = t(iC);
            if (ql.C(viewT) == i) {
                return viewT;
            }
        }
        return super.p(i);
    }

    @Override // defpackage.ql
    public rl q() {
        return new rl(-2, -2);
    }

    @Override // defpackage.ql
    public final boolean q0() {
        if (this.l != 1073741824 && this.k != 1073741824) {
            int iU = u();
            for (int i = 0; i < iU; i++) {
                ViewGroup.LayoutParams layoutParams = t(i).getLayoutParams();
                if (layoutParams.width < 0 && layoutParams.height < 0) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override // defpackage.ql
    public boolean s0() {
        return this.y == null && this.r == this.u;
    }

    public void t0(am amVar, rg rgVar, le leVar) {
        int i = rgVar.d;
        if (i < 0 || i >= amVar.b()) {
            return;
        }
        leVar.a(i, Math.max(0, rgVar.g));
    }

    public final int u0(am amVar) {
        if (u() == 0) {
            return 0;
        }
        y0();
        ra raVar = this.q;
        boolean z = !this.v;
        return hb.l(amVar, raVar, B0(z), A0(z), this, this.v);
    }

    public final int v0(am amVar) {
        if (u() == 0) {
            return 0;
        }
        y0();
        ra raVar = this.q;
        boolean z = !this.v;
        return hb.m(amVar, raVar, B0(z), A0(z), this, this.v, this.t);
    }

    public final int w0(am amVar) {
        if (u() == 0) {
            return 0;
        }
        y0();
        ra raVar = this.q;
        boolean z = !this.v;
        return hb.n(amVar, raVar, B0(z), A0(z), this, this.v);
    }

    public final int x0(int i) {
        return i != 1 ? i != 2 ? i != 17 ? i != 33 ? i != 66 ? (i == 130 && this.o == 1) ? 1 : Integer.MIN_VALUE : this.o == 0 ? 1 : Integer.MIN_VALUE : this.o == 1 ? -1 : Integer.MIN_VALUE : this.o == 0 ? -1 : Integer.MIN_VALUE : (this.o != 1 && J0()) ? -1 : 1 : (this.o != 1 && J0()) ? 1 : -1;
    }

    public final void y0() {
        if (this.p == null) {
            rg rgVar = new rg();
            rgVar.a = true;
            rgVar.h = 0;
            rgVar.i = 0;
            rgVar.k = null;
            this.p = rgVar;
        }
    }

    public final int z0(wl wlVar, rg rgVar, am amVar, boolean z) {
        int i;
        int i2 = rgVar.c;
        int i3 = rgVar.g;
        if (i3 != Integer.MIN_VALUE) {
            if (i2 < 0) {
                rgVar.g = i3 + i2;
            }
            M0(wlVar, rgVar);
        }
        int i4 = rgVar.c + rgVar.h;
        while (true) {
            if ((!rgVar.l && i4 <= 0) || (i = rgVar.d) < 0 || i >= amVar.b()) {
                break;
            }
            qg qgVar = this.A;
            qgVar.a = 0;
            qgVar.b = false;
            qgVar.c = false;
            qgVar.d = false;
            K0(wlVar, amVar, rgVar, qgVar);
            if (!qgVar.b) {
                int i5 = rgVar.b;
                int i6 = qgVar.a;
                rgVar.b = (rgVar.f * i6) + i5;
                if (!qgVar.c || rgVar.k != null || !amVar.f) {
                    rgVar.c -= i6;
                    i4 -= i6;
                }
                int i7 = rgVar.g;
                if (i7 != Integer.MIN_VALUE) {
                    int i8 = i7 + i6;
                    rgVar.g = i8;
                    int i9 = rgVar.c;
                    if (i9 < 0) {
                        rgVar.g = i8 + i9;
                    }
                    M0(wlVar, rgVar);
                }
                if (z && qgVar.d) {
                    break;
                }
            } else {
                break;
            }
        }
        return i2 - rgVar.c;
    }

    public LinearLayoutManager() {
        this.o = 1;
        this.s = false;
        this.t = false;
        this.u = false;
        this.v = true;
        this.w = -1;
        this.x = Integer.MIN_VALUE;
        this.y = null;
        this.z = new pg();
        this.A = new qg();
        this.B = 2;
        this.C = new int[2];
        Q0(1);
        b(null);
        if (this.s) {
            this.s = false;
            h0();
        }
    }

    @Override // defpackage.ql
    public final void M(RecyclerView recyclerView) {
    }

    public void L0(wl wlVar, am amVar, pg pgVar, int i) {
    }
}
