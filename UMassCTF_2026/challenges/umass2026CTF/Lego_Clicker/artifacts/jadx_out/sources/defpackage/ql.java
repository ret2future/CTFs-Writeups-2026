package defpackage;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Matrix;
import android.graphics.Rect;
import android.graphics.RectF;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.WeakHashMap;

/* JADX INFO: compiled from: r8-map-id-51b54c9e59b63e0fb0abe04daff984b4fb85d07ef3fcb7973595bce215c10515 */
/* JADX INFO: loaded from: classes.dex */
public abstract class ql {
    public b7 a;
    public RecyclerView b;
    public final z3 c;
    public final z3 d;
    public boolean e;
    public boolean f;
    public final boolean g;
    public final boolean h;
    public int i;
    public boolean j;
    public int k;
    public int l;
    public int m;
    public int n;

    public ql() {
        ol olVar = new ol(this, 0);
        ol olVar2 = new ol(this, 1);
        this.c = new z3(olVar);
        this.d = new z3(olVar2);
        this.e = false;
        this.f = false;
        this.g = true;
        this.h = true;
    }

    public static int C(View view) {
        return ((rl) view.getLayoutParams()).a.b();
    }

    public static pl D(Context context, AttributeSet attributeSet, int i, int i2) {
        pl plVar = new pl();
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, el.a, i, i2);
        plVar.a = typedArrayObtainStyledAttributes.getInt(0, 1);
        plVar.b = typedArrayObtainStyledAttributes.getInt(10, 1);
        plVar.c = typedArrayObtainStyledAttributes.getBoolean(9, false);
        plVar.d = typedArrayObtainStyledAttributes.getBoolean(11, false);
        typedArrayObtainStyledAttributes.recycle();
        return plVar;
    }

    public static boolean H(int i, int i2, int i3) {
        int mode = View.MeasureSpec.getMode(i2);
        int size = View.MeasureSpec.getSize(i2);
        if (i3 > 0 && i != i3) {
            return false;
        }
        if (mode == Integer.MIN_VALUE) {
            return size >= i;
        }
        if (mode != 0) {
            return mode == 1073741824 && size == i;
        }
        return true;
    }

    public static void I(View view, int i, int i2, int i3, int i4) {
        rl rlVar = (rl) view.getLayoutParams();
        Rect rect = rlVar.b;
        view.layout(i + rect.left + ((ViewGroup.MarginLayoutParams) rlVar).leftMargin, i2 + rect.top + ((ViewGroup.MarginLayoutParams) rlVar).topMargin, (i3 - rect.right) - ((ViewGroup.MarginLayoutParams) rlVar).rightMargin, (i4 - rect.bottom) - ((ViewGroup.MarginLayoutParams) rlVar).bottomMargin);
    }

    public static int f(int i, int i2, int i3) {
        int mode = View.MeasureSpec.getMode(i);
        int size = View.MeasureSpec.getSize(i);
        return mode != Integer.MIN_VALUE ? mode != 1073741824 ? Math.max(i2, i3) : size : Math.min(size, Math.max(i2, i3));
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x001a  */
    /* JADX WARN: Removed duplicated region for block: B:14:0x0022  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static int v(boolean r4, int r5, int r6, int r7, int r8) {
        /*
            int r5 = r5 - r7
            r7 = 0
            int r5 = java.lang.Math.max(r7, r5)
            r0 = -2
            r1 = -1
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = 1073741824(0x40000000, float:2.0)
            if (r4 == 0) goto L1d
            if (r8 < 0) goto L12
        L10:
            r6 = r3
            goto L30
        L12:
            if (r8 != r1) goto L1a
            if (r6 == r2) goto L22
            if (r6 == 0) goto L1a
            if (r6 == r3) goto L22
        L1a:
            r6 = r7
            r8 = r6
            goto L30
        L1d:
            if (r8 < 0) goto L20
            goto L10
        L20:
            if (r8 != r1) goto L24
        L22:
            r8 = r5
            goto L30
        L24:
            if (r8 != r0) goto L1a
            if (r6 == r2) goto L2e
            if (r6 != r3) goto L2b
            goto L2e
        L2b:
            r8 = r5
            r6 = r7
            goto L30
        L2e:
            r8 = r5
            r6 = r2
        L30:
            int r4 = android.view.View.MeasureSpec.makeMeasureSpec(r8, r6)
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.ql.v(boolean, int, int, int, int):int");
    }

    public static void x(View view, Rect rect) {
        boolean z = RecyclerView.w0;
        rl rlVar = (rl) view.getLayoutParams();
        Rect rect2 = rlVar.b;
        rect.set((view.getLeft() - rect2.left) - ((ViewGroup.MarginLayoutParams) rlVar).leftMargin, (view.getTop() - rect2.top) - ((ViewGroup.MarginLayoutParams) rlVar).topMargin, view.getRight() + rect2.right + ((ViewGroup.MarginLayoutParams) rlVar).rightMargin, view.getBottom() + rect2.bottom + ((ViewGroup.MarginLayoutParams) rlVar).bottomMargin);
    }

    public final int A() {
        RecyclerView recyclerView = this.b;
        if (recyclerView != null) {
            return recyclerView.getPaddingRight();
        }
        return 0;
    }

    public final int B() {
        RecyclerView recyclerView = this.b;
        if (recyclerView != null) {
            return recyclerView.getPaddingTop();
        }
        return 0;
    }

    public int E(wl wlVar, am amVar) {
        return -1;
    }

    public final void F(View view, Rect rect) {
        Matrix matrix;
        Rect rect2 = ((rl) view.getLayoutParams()).b;
        rect.set(-rect2.left, -rect2.top, view.getWidth() + rect2.right, view.getHeight() + rect2.bottom);
        if (this.b != null && (matrix = view.getMatrix()) != null && !matrix.isIdentity()) {
            RectF rectF = this.b.k;
            rectF.set(rect);
            matrix.mapRect(rectF);
            rect.set((int) Math.floor(rectF.left), (int) Math.floor(rectF.top), (int) Math.ceil(rectF.right), (int) Math.ceil(rectF.bottom));
        }
        rect.offset(view.getLeft(), view.getTop());
    }

    public abstract boolean G();

    public void J(int i) {
        RecyclerView recyclerView = this.b;
        if (recyclerView != null) {
            int iE = recyclerView.f.e();
            for (int i2 = 0; i2 < iE; i2++) {
                recyclerView.f.d(i2).offsetLeftAndRight(i);
            }
        }
    }

    public void K(int i) {
        RecyclerView recyclerView = this.b;
        if (recyclerView != null) {
            int iE = recyclerView.f.e();
            for (int i2 = 0; i2 < iE; i2++) {
                recyclerView.f.d(i2).offsetTopAndBottom(i);
            }
        }
    }

    public abstract void M(RecyclerView recyclerView);

    public abstract View N(View view, int i, wl wlVar, am amVar);

    public void O(AccessibilityEvent accessibilityEvent) {
        RecyclerView recyclerView = this.b;
        wl wlVar = recyclerView.c;
        if (accessibilityEvent == null) {
            return;
        }
        boolean z = true;
        if (!recyclerView.canScrollVertically(1) && !this.b.canScrollVertically(-1) && !this.b.canScrollHorizontally(-1) && !this.b.canScrollHorizontally(1)) {
            z = false;
        }
        accessibilityEvent.setScrollable(z);
        jl jlVar = this.b.l;
        if (jlVar != null) {
            accessibilityEvent.setItemCount(jlVar.a());
        }
    }

    public void P(wl wlVar, am amVar, d0 d0Var) {
        AccessibilityNodeInfo accessibilityNodeInfo = d0Var.a;
        if (this.b.canScrollVertically(-1) || this.b.canScrollHorizontally(-1)) {
            accessibilityNodeInfo.addAction(8192);
            accessibilityNodeInfo.setScrollable(true);
        }
        if (this.b.canScrollVertically(1) || this.b.canScrollHorizontally(1)) {
            accessibilityNodeInfo.addAction(4096);
            accessibilityNodeInfo.setScrollable(true);
        }
        accessibilityNodeInfo.setCollectionInfo(AccessibilityNodeInfo.CollectionInfo.obtain(E(wlVar, amVar), w(wlVar, amVar), false, 0));
    }

    public final void R(View view, d0 d0Var) {
        em emVarH = RecyclerView.H(view);
        if (emVarH == null || emVarH.g()) {
            return;
        }
        b7 b7Var = this.a;
        if (b7Var.c.contains(emVarH.a)) {
            return;
        }
        RecyclerView recyclerView = this.b;
        Q(recyclerView.c, recyclerView.d0, view, d0Var);
    }

    public abstract void X(wl wlVar, am amVar);

    public abstract void Y(am amVar);

    public abstract void Z(Parcelable parcelable);

    public final void a(View view, int i, boolean z) {
        em emVarH = RecyclerView.H(view);
        if (z || emVarH.g()) {
            ko koVar = (ko) this.b.g.b;
            ts tsVarA = (ts) koVar.getOrDefault(emVarH, null);
            if (tsVarA == null) {
                tsVarA = ts.a();
                koVar.put(emVarH, tsVarA);
            }
            tsVarA.a |= 1;
        } else {
            this.b.g.v(emVarH);
        }
        rl rlVar = (rl) view.getLayoutParams();
        if (emVarH.o() || emVarH.h()) {
            if (emVarH.h()) {
                emVarH.m.l(emVarH);
            } else {
                emVarH.i &= -33;
            }
            this.a.b(view, i, view.getLayoutParams(), false);
        } else {
            ViewParent parent = view.getParent();
            RecyclerView recyclerView = this.b;
            b7 b7Var = this.a;
            if (parent == recyclerView) {
                a7 a7Var = b7Var.b;
                int iIndexOfChild = b7Var.a.a.indexOfChild(view);
                int iB = (iIndexOfChild == -1 || a7Var.d(iIndexOfChild)) ? -1 : iIndexOfChild - a7Var.b(iIndexOfChild);
                if (i == -1) {
                    i = this.a.e();
                }
                if (iB == -1) {
                    throw new IllegalStateException("Added View has RecyclerView as parent but view is not a real child. Unfiltered index:" + this.b.indexOfChild(view) + this.b.y());
                }
                if (iB != i) {
                    ql qlVar = this.b.m;
                    View viewT = qlVar.t(iB);
                    if (viewT == null) {
                        throw new IllegalArgumentException("Cannot move a child from non-existing index:" + iB + qlVar.b.toString());
                    }
                    qlVar.t(iB);
                    qlVar.a.c(iB);
                    rl rlVar2 = (rl) viewT.getLayoutParams();
                    em emVarH2 = RecyclerView.H(viewT);
                    boolean zG = emVarH2.g();
                    RecyclerView recyclerView2 = qlVar.b;
                    if (zG) {
                        ko koVar2 = (ko) recyclerView2.g.b;
                        ts tsVarA2 = (ts) koVar2.getOrDefault(emVarH2, null);
                        if (tsVarA2 == null) {
                            tsVarA2 = ts.a();
                            koVar2.put(emVarH2, tsVarA2);
                        }
                        tsVarA2.a = 1 | tsVarA2.a;
                    } else {
                        recyclerView2.g.v(emVarH2);
                    }
                    qlVar.a.b(viewT, i, rlVar2, emVarH2.g());
                }
            } else {
                b7Var.a(view, i, false);
                rlVar.c = true;
            }
        }
        if (rlVar.d) {
            if (RecyclerView.x0) {
                Log.d("RecyclerView", "consuming pending invalidate on child " + rlVar.a);
            }
            emVarH.a.invalidate();
            rlVar.d = false;
        }
    }

    public abstract Parcelable a0();

    public abstract void b(String str);

    public abstract boolean c();

    public final void c0(wl wlVar) {
        for (int iU = u() - 1; iU >= 0; iU--) {
            if (!RecyclerView.H(t(iU)).n()) {
                View viewT = t(iU);
                f0(iU);
                wlVar.h(viewT);
            }
        }
    }

    public abstract boolean d();

    public final void d0(wl wlVar) {
        ArrayList arrayList;
        int size = wlVar.a.size();
        int i = size - 1;
        while (true) {
            arrayList = wlVar.a;
            if (i < 0) {
                break;
            }
            View view = ((em) arrayList.get(i)).a;
            em emVarH = RecyclerView.H(view);
            if (!emVarH.n()) {
                emVarH.m(false);
                if (emVarH.i()) {
                    this.b.removeDetachedView(view, false);
                }
                nl nlVar = this.b.J;
                if (nlVar != null) {
                    nlVar.d(emVarH);
                }
                emVarH.m(true);
                em emVarH2 = RecyclerView.H(view);
                emVarH2.m = null;
                emVarH2.n = false;
                emVarH2.i &= -33;
                wlVar.i(emVarH2);
            }
            i--;
        }
        arrayList.clear();
        ArrayList arrayList2 = wlVar.b;
        if (arrayList2 != null) {
            arrayList2.clear();
        }
        if (size > 0) {
            this.b.invalidate();
        }
    }

    public boolean e(rl rlVar) {
        return rlVar != null;
    }

    public final void e0(View view, wl wlVar) {
        b7 b7Var = this.a;
        il ilVar = b7Var.a;
        int i = b7Var.d;
        if (i == 1) {
            z6.o("Cannot call removeView(At) within removeView(At)");
            return;
        }
        if (i == 2) {
            z6.o("Cannot call removeView(At) within removeViewIfHidden");
            return;
        }
        try {
            b7Var.d = 1;
            b7Var.e = view;
            int iIndexOfChild = ilVar.a.indexOfChild(view);
            if (iIndexOfChild >= 0) {
                if (b7Var.b.f(iIndexOfChild)) {
                    b7Var.j(view);
                }
                ilVar.h(iIndexOfChild);
            }
            b7Var.d = 0;
            b7Var.e = null;
            wlVar.h(view);
        } catch (Throwable th) {
            b7Var.d = 0;
            b7Var.e = null;
            throw th;
        }
    }

    public final void f0(int i) {
        if (t(i) != null) {
            b7 b7Var = this.a;
            il ilVar = b7Var.a;
            int i2 = b7Var.d;
            if (i2 == 1) {
                z6.o("Cannot call removeView(At) within removeView(At)");
                return;
            }
            if (i2 == 2) {
                z6.o("Cannot call removeView(At) within removeViewIfHidden");
                return;
            }
            try {
                int iF = b7Var.f(i);
                View childAt = ilVar.a.getChildAt(iF);
                if (childAt == null) {
                    b7Var.d = 0;
                    b7Var.e = null;
                    return;
                }
                b7Var.d = 1;
                b7Var.e = childAt;
                if (b7Var.b.f(iF)) {
                    b7Var.j(childAt);
                }
                ilVar.h(iF);
                b7Var.d = 0;
                b7Var.e = null;
            } catch (Throwable th) {
                b7Var.d = 0;
                b7Var.e = null;
                throw th;
            }
        }
    }

    public abstract void g(int i, int i2, am amVar, le leVar);

    /* JADX WARN: Removed duplicated region for block: B:28:0x00b2  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final boolean g0(androidx.recyclerview.widget.RecyclerView r9, android.view.View r10, android.graphics.Rect r11, boolean r12, boolean r13) {
        /*
            r8 = this;
            int r0 = r8.z()
            int r1 = r8.B()
            int r2 = r8.m
            int r3 = r8.A()
            int r2 = r2 - r3
            int r3 = r8.n
            int r4 = r8.y()
            int r3 = r3 - r4
            int r4 = r10.getLeft()
            int r5 = r11.left
            int r4 = r4 + r5
            int r5 = r10.getScrollX()
            int r4 = r4 - r5
            int r5 = r10.getTop()
            int r6 = r11.top
            int r5 = r5 + r6
            int r10 = r10.getScrollY()
            int r5 = r5 - r10
            int r10 = r11.width()
            int r10 = r10 + r4
            int r11 = r11.height()
            int r11 = r11 + r5
            int r4 = r4 - r0
            r0 = 0
            int r6 = java.lang.Math.min(r0, r4)
            int r5 = r5 - r1
            int r1 = java.lang.Math.min(r0, r5)
            int r10 = r10 - r2
            int r2 = java.lang.Math.max(r0, r10)
            int r11 = r11 - r3
            int r11 = java.lang.Math.max(r0, r11)
            androidx.recyclerview.widget.RecyclerView r3 = r8.b
            java.util.WeakHashMap r7 = defpackage.os.a
            int r3 = r3.getLayoutDirection()
            r7 = 1
            if (r3 != r7) goto L60
            if (r2 == 0) goto L5b
            goto L68
        L5b:
            int r2 = java.lang.Math.max(r6, r10)
            goto L68
        L60:
            if (r6 == 0) goto L63
            goto L67
        L63:
            int r6 = java.lang.Math.min(r4, r2)
        L67:
            r2 = r6
        L68:
            if (r1 == 0) goto L6b
            goto L6f
        L6b:
            int r1 = java.lang.Math.min(r5, r11)
        L6f:
            int[] r10 = new int[]{r2, r1}
            r11 = r10[r0]
            r10 = r10[r7]
            if (r13 == 0) goto Lb2
            android.view.View r13 = r9.getFocusedChild()
            if (r13 != 0) goto L80
            goto Lb7
        L80:
            int r1 = r8.z()
            int r2 = r8.B()
            int r3 = r8.m
            int r4 = r8.A()
            int r3 = r3 - r4
            int r4 = r8.n
            int r5 = r8.y()
            int r4 = r4 - r5
            androidx.recyclerview.widget.RecyclerView r8 = r8.b
            android.graphics.Rect r8 = r8.i
            x(r13, r8)
            int r13 = r8.left
            int r13 = r13 - r11
            if (r13 >= r3) goto Lb7
            int r13 = r8.right
            int r13 = r13 - r11
            if (r13 <= r1) goto Lb7
            int r13 = r8.top
            int r13 = r13 - r10
            if (r13 >= r4) goto Lb7
            int r8 = r8.bottom
            int r8 = r8 - r10
            if (r8 > r2) goto Lb2
            goto Lb7
        Lb2:
            if (r11 != 0) goto Lb8
            if (r10 == 0) goto Lb7
            goto Lb8
        Lb7:
            return r0
        Lb8:
            if (r12 == 0) goto Lbe
            r9.scrollBy(r11, r10)
            return r7
        Lbe:
            r9.a0(r11, r10, r0)
            return r7
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.ql.g0(androidx.recyclerview.widget.RecyclerView, android.view.View, android.graphics.Rect, boolean, boolean):boolean");
    }

    public final void h0() {
        RecyclerView recyclerView = this.b;
        if (recyclerView != null) {
            recyclerView.requestLayout();
        }
    }

    public abstract int i(am amVar);

    public abstract int i0(int i, wl wlVar, am amVar);

    public abstract int j(am amVar);

    public abstract int j0(int i, wl wlVar, am amVar);

    public abstract int k(am amVar);

    public final void k0(RecyclerView recyclerView) {
        l0(View.MeasureSpec.makeMeasureSpec(recyclerView.getWidth(), 1073741824), View.MeasureSpec.makeMeasureSpec(recyclerView.getHeight(), 1073741824));
    }

    public abstract int l(am amVar);

    public final void l0(int i, int i2) {
        this.m = View.MeasureSpec.getSize(i);
        int mode = View.MeasureSpec.getMode(i);
        this.k = mode;
        if (mode == 0 && !RecyclerView.A0) {
            this.m = 0;
        }
        this.n = View.MeasureSpec.getSize(i2);
        int mode2 = View.MeasureSpec.getMode(i2);
        this.l = mode2;
        if (mode2 != 0 || RecyclerView.A0) {
            return;
        }
        this.n = 0;
    }

    public abstract int m(am amVar);

    public void m0(Rect rect, int i, int i2) {
        int iA = A() + z() + rect.width();
        int iY = y() + B() + rect.height();
        RecyclerView recyclerView = this.b;
        WeakHashMap weakHashMap = os.a;
        this.b.setMeasuredDimension(f(i, iA, recyclerView.getMinimumWidth()), f(i2, iY, this.b.getMinimumHeight()));
    }

    public abstract int n(am amVar);

    public final void n0(int i, int i2) {
        int iU = u();
        if (iU == 0) {
            this.b.n(i, i2);
            return;
        }
        int i3 = Integer.MIN_VALUE;
        int i4 = Integer.MAX_VALUE;
        int i5 = Integer.MIN_VALUE;
        int i6 = Integer.MAX_VALUE;
        for (int i7 = 0; i7 < iU; i7++) {
            View viewT = t(i7);
            Rect rect = this.b.i;
            x(viewT, rect);
            int i8 = rect.left;
            if (i8 < i6) {
                i6 = i8;
            }
            int i9 = rect.right;
            if (i9 > i3) {
                i3 = i9;
            }
            int i10 = rect.top;
            if (i10 < i4) {
                i4 = i10;
            }
            int i11 = rect.bottom;
            if (i11 > i5) {
                i5 = i11;
            }
        }
        this.b.i.set(i6, i4, i3, i5);
        m0(this.b.i, i, i2);
    }

    public final void o(wl wlVar) {
        for (int iU = u() - 1; iU >= 0; iU--) {
            View viewT = t(iU);
            em emVarH = RecyclerView.H(viewT);
            if (emVarH.n()) {
                if (RecyclerView.x0) {
                    Log.d("RecyclerView", "ignoring view " + emVarH);
                }
            } else if (!emVarH.e() || emVarH.g()) {
                t(iU);
                this.a.c(iU);
                wlVar.j(viewT);
                this.b.g.v(emVarH);
            } else {
                this.b.l.getClass();
                f0(iU);
                wlVar.i(emVarH);
            }
        }
    }

    public final void o0(RecyclerView recyclerView) {
        if (recyclerView == null) {
            this.b = null;
            this.a = null;
            this.m = 0;
            this.n = 0;
        } else {
            this.b = recyclerView;
            this.a = recyclerView.f;
            this.m = recyclerView.getWidth();
            this.n = recyclerView.getHeight();
        }
        this.k = 1073741824;
        this.l = 1073741824;
    }

    public View p(int i) {
        int iU = u();
        for (int i2 = 0; i2 < iU; i2++) {
            View viewT = t(i2);
            em emVarH = RecyclerView.H(viewT);
            if (emVarH != null && emVarH.b() == i && !emVarH.n() && (this.b.d0.f || !emVarH.g())) {
                return viewT;
            }
        }
        return null;
    }

    public final boolean p0(View view, int i, int i2, rl rlVar) {
        return (!view.isLayoutRequested() && this.g && H(view.getWidth(), i, ((ViewGroup.MarginLayoutParams) rlVar).width) && H(view.getHeight(), i2, ((ViewGroup.MarginLayoutParams) rlVar).height)) ? false : true;
    }

    public abstract rl q();

    public boolean q0() {
        return false;
    }

    public rl r(Context context, AttributeSet attributeSet) {
        return new rl(context, attributeSet);
    }

    public final boolean r0(View view, int i, int i2, rl rlVar) {
        return (this.g && H(view.getMeasuredWidth(), i, ((ViewGroup.MarginLayoutParams) rlVar).width) && H(view.getMeasuredHeight(), i2, ((ViewGroup.MarginLayoutParams) rlVar).height)) ? false : true;
    }

    public rl s(ViewGroup.LayoutParams layoutParams) {
        return layoutParams instanceof rl ? new rl((rl) layoutParams) : layoutParams instanceof ViewGroup.MarginLayoutParams ? new rl((ViewGroup.MarginLayoutParams) layoutParams) : new rl(layoutParams);
    }

    public abstract boolean s0();

    public final View t(int i) {
        b7 b7Var = this.a;
        if (b7Var != null) {
            return b7Var.d(i);
        }
        return null;
    }

    public final int u() {
        b7 b7Var = this.a;
        if (b7Var != null) {
            return b7Var.e();
        }
        return 0;
    }

    public int w(wl wlVar, am amVar) {
        return -1;
    }

    public final int y() {
        RecyclerView recyclerView = this.b;
        if (recyclerView != null) {
            return recyclerView.getPaddingBottom();
        }
        return 0;
    }

    public final int z() {
        RecyclerView recyclerView = this.b;
        if (recyclerView != null) {
            return recyclerView.getPaddingLeft();
        }
        return 0;
    }

    public void b0(int i) {
    }

    public void L() {
    }

    public void T() {
    }

    public void S(int i, int i2) {
    }

    public void U(int i, int i2) {
    }

    public void V(int i, int i2) {
    }

    public void W(int i, int i2) {
    }

    public void h(int i, le leVar) {
    }

    public void Q(wl wlVar, am amVar, View view, d0 d0Var) {
    }
}
