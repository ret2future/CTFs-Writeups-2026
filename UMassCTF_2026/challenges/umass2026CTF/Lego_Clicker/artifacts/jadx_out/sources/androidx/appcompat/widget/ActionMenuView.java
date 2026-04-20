package androidx.appcompat.widget;

import android.content.Context;
import android.content.res.Configuration;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.ContextThemeWrapper;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityEvent;
import android.widget.LinearLayout;
import androidx.appcompat.view.menu.ActionMenuItemView;
import defpackage.a1;
import defpackage.aj;
import defpackage.cq;
import defpackage.d1;
import defpackage.e0;
import defpackage.e1;
import defpackage.ei;
import defpackage.ej;
import defpackage.f1;
import defpackage.fi;
import defpackage.fr;
import defpackage.g1;
import defpackage.gi;
import defpackage.h1;
import defpackage.jt;
import defpackage.ng;
import defpackage.og;
import defpackage.oi;

/* JADX INFO: compiled from: r8-map-id-51b54c9e59b63e0fb0abe04daff984b4fb85d07ef3fcb7973595bce215c10515 */
/* JADX INFO: loaded from: classes.dex */
public class ActionMenuView extends og implements fi, ej {
    public h1 A;
    public gi p;
    public Context q;
    public int r;
    public boolean s;
    public e1 t;
    public cq u;
    public ei v;
    public boolean w;
    public int x;
    public final int y;
    public final int z;

    public ActionMenuView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        setBaselineAligned(false);
        float f = context.getResources().getDisplayMetrics().density;
        this.y = (int) (56.0f * f);
        this.z = (int) (f * 4.0f);
        this.q = context;
        this.r = 0;
    }

    public static g1 i() {
        g1 g1Var = new g1(-2, -2);
        g1Var.a = false;
        ((LinearLayout.LayoutParams) g1Var).gravity = 16;
        return g1Var;
    }

    public static g1 j(ViewGroup.LayoutParams layoutParams) {
        g1 g1Var;
        if (layoutParams == null) {
            return i();
        }
        if (layoutParams instanceof g1) {
            g1 g1Var2 = (g1) layoutParams;
            g1Var = new g1(g1Var2);
            g1Var.a = g1Var2.a;
        } else {
            g1Var = new g1(layoutParams);
        }
        if (((LinearLayout.LayoutParams) g1Var).gravity <= 0) {
            ((LinearLayout.LayoutParams) g1Var).gravity = 16;
        }
        return g1Var;
    }

    @Override // defpackage.fi
    public final boolean a(oi oiVar) {
        return this.p.q(oiVar, null, 0);
    }

    @Override // defpackage.ej
    public final void b(gi giVar) {
        this.p = giVar;
    }

    @Override // defpackage.og, android.view.ViewGroup
    public final boolean checkLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return layoutParams instanceof g1;
    }

    @Override // android.view.View
    public final boolean dispatchPopulateAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
        return false;
    }

    @Override // defpackage.og
    /* JADX INFO: renamed from: e */
    public final /* bridge */ /* synthetic */ ng generateDefaultLayoutParams() {
        return i();
    }

    @Override // defpackage.og
    /* JADX INFO: renamed from: f */
    public final ng generateLayoutParams(AttributeSet attributeSet) {
        return new g1(getContext(), attributeSet);
    }

    @Override // defpackage.og
    /* JADX INFO: renamed from: g */
    public final /* bridge */ /* synthetic */ ng generateLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return j(layoutParams);
    }

    @Override // defpackage.og, android.view.ViewGroup
    public final /* bridge */ /* synthetic */ ViewGroup.LayoutParams generateDefaultLayoutParams() {
        return i();
    }

    @Override // defpackage.og, android.view.ViewGroup
    public final ViewGroup.LayoutParams generateLayoutParams(AttributeSet attributeSet) {
        return new g1(getContext(), attributeSet);
    }

    public Menu getMenu() {
        if (this.p == null) {
            Context context = getContext();
            gi giVar = new gi(context);
            this.p = giVar;
            giVar.e = new e0(2, this);
            e1 e1Var = new e1(context);
            this.t = e1Var;
            e1Var.l = true;
            e1Var.m = true;
            aj frVar = this.u;
            if (frVar == null) {
                frVar = new fr(4);
            }
            e1Var.e = frVar;
            this.p.b(e1Var, this.q);
            e1 e1Var2 = this.t;
            e1Var2.h = this;
            this.p = e1Var2.c;
        }
        return this.p;
    }

    public Drawable getOverflowIcon() {
        getMenu();
        e1 e1Var = this.t;
        d1 d1Var = e1Var.i;
        if (d1Var != null) {
            return d1Var.getDrawable();
        }
        if (e1Var.k) {
            return e1Var.j;
        }
        return null;
    }

    public int getPopupTheme() {
        return this.r;
    }

    public int getWindowAnimations() {
        return 0;
    }

    public final boolean k(int i) {
        boolean zA = false;
        if (i == 0) {
            return false;
        }
        KeyEvent.Callback childAt = getChildAt(i - 1);
        KeyEvent.Callback childAt2 = getChildAt(i);
        if (i < getChildCount() && (childAt instanceof f1)) {
            zA = ((f1) childAt).a();
        }
        return (i <= 0 || !(childAt2 instanceof f1)) ? zA : ((f1) childAt2).b() | zA;
    }

    @Override // android.view.View
    public final void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        e1 e1Var = this.t;
        if (e1Var != null) {
            e1Var.h();
            if (this.t.j()) {
                this.t.f();
                this.t.l();
            }
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        e1 e1Var = this.t;
        if (e1Var != null) {
            e1Var.f();
            a1 a1Var = e1Var.t;
            if (a1Var == null || !a1Var.b()) {
                return;
            }
            a1Var.i.dismiss();
        }
    }

    @Override // defpackage.og, android.view.ViewGroup, android.view.View
    public final void onLayout(boolean z, int i, int i2, int i3, int i4) {
        int width;
        int paddingLeft;
        if (!this.w) {
            super.onLayout(z, i, i2, i3, i4);
            return;
        }
        int childCount = getChildCount();
        int i5 = (i4 - i2) / 2;
        int dividerWidth = getDividerWidth();
        int i6 = i3 - i;
        int paddingRight = (i6 - getPaddingRight()) - getPaddingLeft();
        boolean z2 = jt.a;
        boolean z3 = getLayoutDirection() == 1;
        int i7 = 0;
        int i8 = 0;
        for (int i9 = 0; i9 < childCount; i9++) {
            View childAt = getChildAt(i9);
            if (childAt.getVisibility() != 8) {
                g1 g1Var = (g1) childAt.getLayoutParams();
                if (g1Var.a) {
                    int measuredWidth = childAt.getMeasuredWidth();
                    if (k(i9)) {
                        measuredWidth += dividerWidth;
                    }
                    int measuredHeight = childAt.getMeasuredHeight();
                    if (z3) {
                        paddingLeft = getPaddingLeft() + ((LinearLayout.LayoutParams) g1Var).leftMargin;
                        width = paddingLeft + measuredWidth;
                    } else {
                        width = (getWidth() - getPaddingRight()) - ((LinearLayout.LayoutParams) g1Var).rightMargin;
                        paddingLeft = width - measuredWidth;
                    }
                    int i10 = i5 - (measuredHeight / 2);
                    childAt.layout(paddingLeft, i10, width, measuredHeight + i10);
                    paddingRight -= measuredWidth;
                    i7 = 1;
                } else {
                    paddingRight -= (childAt.getMeasuredWidth() + ((LinearLayout.LayoutParams) g1Var).leftMargin) + ((LinearLayout.LayoutParams) g1Var).rightMargin;
                    k(i9);
                    i8++;
                }
            }
        }
        if (childCount == 1 && i7 == 0) {
            View childAt2 = getChildAt(0);
            int measuredWidth2 = childAt2.getMeasuredWidth();
            int measuredHeight2 = childAt2.getMeasuredHeight();
            int i11 = (i6 / 2) - (measuredWidth2 / 2);
            int i12 = i5 - (measuredHeight2 / 2);
            childAt2.layout(i11, i12, measuredWidth2 + i11, measuredHeight2 + i12);
            return;
        }
        int i13 = i8 - (i7 ^ 1);
        int iMax = Math.max(0, i13 > 0 ? paddingRight / i13 : 0);
        if (z3) {
            int width2 = getWidth() - getPaddingRight();
            for (int i14 = 0; i14 < childCount; i14++) {
                View childAt3 = getChildAt(i14);
                g1 g1Var2 = (g1) childAt3.getLayoutParams();
                if (childAt3.getVisibility() != 8 && !g1Var2.a) {
                    int i15 = width2 - ((LinearLayout.LayoutParams) g1Var2).rightMargin;
                    int measuredWidth3 = childAt3.getMeasuredWidth();
                    int measuredHeight3 = childAt3.getMeasuredHeight();
                    int i16 = i5 - (measuredHeight3 / 2);
                    childAt3.layout(i15 - measuredWidth3, i16, i15, measuredHeight3 + i16);
                    width2 = i15 - ((measuredWidth3 + ((LinearLayout.LayoutParams) g1Var2).leftMargin) + iMax);
                }
            }
            return;
        }
        int paddingLeft2 = getPaddingLeft();
        for (int i17 = 0; i17 < childCount; i17++) {
            View childAt4 = getChildAt(i17);
            g1 g1Var3 = (g1) childAt4.getLayoutParams();
            if (childAt4.getVisibility() != 8 && !g1Var3.a) {
                int i18 = paddingLeft2 + ((LinearLayout.LayoutParams) g1Var3).leftMargin;
                int measuredWidth4 = childAt4.getMeasuredWidth();
                int measuredHeight4 = childAt4.getMeasuredHeight();
                int i19 = i5 - (measuredHeight4 / 2);
                childAt4.layout(i18, i19, i18 + measuredWidth4, measuredHeight4 + i19);
                paddingLeft2 = measuredWidth4 + ((LinearLayout.LayoutParams) g1Var3).rightMargin + iMax + i18;
            }
        }
    }

    /* JADX WARN: Type inference failed for: r11v15 */
    /* JADX WARN: Type inference failed for: r11v16, types: [boolean, int] */
    /* JADX WARN: Type inference failed for: r11v18 */
    /* JADX WARN: Type inference failed for: r11v41 */
    @Override // defpackage.og, android.view.View
    public final void onMeasure(int i, int i2) {
        int i3;
        int i4;
        ?? r11;
        int i5;
        int i6;
        gi giVar;
        boolean z = this.w;
        boolean z2 = View.MeasureSpec.getMode(i) == 1073741824;
        this.w = z2;
        if (z != z2) {
            this.x = 0;
        }
        int size = View.MeasureSpec.getSize(i);
        if (this.w && (giVar = this.p) != null && size != this.x) {
            this.x = size;
            giVar.p(true);
        }
        int childCount = getChildCount();
        if (!this.w || childCount <= 0) {
            for (int i7 = 0; i7 < childCount; i7++) {
                g1 g1Var = (g1) getChildAt(i7).getLayoutParams();
                ((LinearLayout.LayoutParams) g1Var).rightMargin = 0;
                ((LinearLayout.LayoutParams) g1Var).leftMargin = 0;
            }
            super.onMeasure(i, i2);
            return;
        }
        int mode = View.MeasureSpec.getMode(i2);
        int size2 = View.MeasureSpec.getSize(i);
        int size3 = View.MeasureSpec.getSize(i2);
        int paddingRight = getPaddingRight() + getPaddingLeft();
        int paddingBottom = getPaddingBottom() + getPaddingTop();
        int childMeasureSpec = ViewGroup.getChildMeasureSpec(i2, paddingBottom, -2);
        int i8 = size2 - paddingRight;
        int i9 = this.y;
        int i10 = i8 / i9;
        int i11 = i8 % i9;
        if (i10 == 0) {
            setMeasuredDimension(i8, 0);
            return;
        }
        int i12 = (i11 / i10) + i9;
        int childCount2 = getChildCount();
        int iMax = 0;
        int i13 = 0;
        int iMax2 = 0;
        int i14 = 0;
        boolean z3 = false;
        int i15 = 0;
        long j = 0;
        while (true) {
            i3 = this.z;
            if (i14 >= childCount2) {
                break;
            }
            View childAt = getChildAt(i14);
            int i16 = size3;
            int i17 = paddingBottom;
            if (childAt.getVisibility() == 8) {
                i5 = i12;
            } else {
                boolean z4 = childAt instanceof ActionMenuItemView;
                i13++;
                if (z4) {
                    childAt.setPadding(i3, 0, i3, 0);
                }
                g1 g1Var2 = (g1) childAt.getLayoutParams();
                g1Var2.f = false;
                g1Var2.c = 0;
                g1Var2.b = 0;
                g1Var2.d = false;
                ((LinearLayout.LayoutParams) g1Var2).leftMargin = 0;
                ((LinearLayout.LayoutParams) g1Var2).rightMargin = 0;
                g1Var2.e = z4 && !TextUtils.isEmpty(((ActionMenuItemView) childAt).getText());
                int i18 = g1Var2.a ? 1 : i10;
                g1 g1Var3 = (g1) childAt.getLayoutParams();
                int i19 = i10;
                i5 = i12;
                int iMakeMeasureSpec = View.MeasureSpec.makeMeasureSpec(View.MeasureSpec.getSize(childMeasureSpec) - i17, View.MeasureSpec.getMode(childMeasureSpec));
                ActionMenuItemView actionMenuItemView = z4 ? (ActionMenuItemView) childAt : null;
                boolean z5 = (actionMenuItemView == null || TextUtils.isEmpty(actionMenuItemView.getText())) ? false : true;
                boolean z6 = z5;
                if (i18 <= 0 || (z5 && i18 < 2)) {
                    i6 = 0;
                } else {
                    childAt.measure(View.MeasureSpec.makeMeasureSpec(i5 * i18, Integer.MIN_VALUE), iMakeMeasureSpec);
                    int measuredWidth = childAt.getMeasuredWidth();
                    i6 = measuredWidth / i5;
                    if (measuredWidth % i5 != 0) {
                        i6++;
                    }
                    if (z6 && i6 < 2) {
                        i6 = 2;
                    }
                }
                g1Var3.d = !g1Var3.a && z6;
                g1Var3.b = i6;
                childAt.measure(View.MeasureSpec.makeMeasureSpec(i6 * i5, 1073741824), iMakeMeasureSpec);
                iMax2 = Math.max(iMax2, i6);
                if (g1Var2.d) {
                    i15++;
                }
                if (g1Var2.a) {
                    z3 = true;
                }
                i10 = i19 - i6;
                iMax = Math.max(iMax, childAt.getMeasuredHeight());
                if (i6 == 1) {
                    j |= (long) (1 << i14);
                }
            }
            i14++;
            size3 = i16;
            paddingBottom = i17;
            i12 = i5;
        }
        int i20 = size3;
        int i21 = i10;
        int i22 = i12;
        boolean z7 = z3 && i13 == 2;
        int i23 = i21;
        boolean z8 = false;
        while (i15 > 0 && i23 > 0) {
            int i24 = Integer.MAX_VALUE;
            long j2 = 0;
            int i25 = 0;
            int i26 = 0;
            while (i26 < childCount2) {
                int i27 = iMax;
                g1 g1Var4 = (g1) getChildAt(i26).getLayoutParams();
                boolean z9 = z7;
                if (g1Var4.d) {
                    int i28 = g1Var4.b;
                    if (i28 < i24) {
                        j2 = 1 << i26;
                        i24 = i28;
                        i25 = 1;
                    } else if (i28 == i24) {
                        j2 |= 1 << i26;
                        i25++;
                    }
                }
                i26++;
                z7 = z9;
                iMax = i27;
            }
            i4 = iMax;
            boolean z10 = z7;
            j |= j2;
            if (i25 > i23) {
                break;
            }
            int i29 = i24 + 1;
            int i30 = 0;
            while (i30 < childCount2) {
                View childAt2 = getChildAt(i30);
                g1 g1Var5 = (g1) childAt2.getLayoutParams();
                boolean z11 = z3;
                long j3 = 1 << i30;
                if ((j2 & j3) != 0) {
                    if (z10 && g1Var5.e) {
                        r11 = 1;
                        r11 = 1;
                        if (i23 == 1) {
                            childAt2.setPadding(i3 + i22, 0, i3, 0);
                        }
                    } else {
                        r11 = 1;
                    }
                    g1Var5.b += r11;
                    g1Var5.f = r11;
                    i23--;
                } else if (g1Var5.b == i29) {
                    j |= j3;
                }
                i30++;
                z3 = z11;
            }
            z7 = z10;
            iMax = i4;
            z8 = true;
        }
        i4 = iMax;
        boolean z12 = !z3 && i13 == 1;
        if (i23 > 0 && j != 0 && (i23 < i13 - 1 || z12 || iMax2 > 1)) {
            float fBitCount = Long.bitCount(j);
            if (!z12) {
                if ((j & 1) != 0 && !((g1) getChildAt(0).getLayoutParams()).e) {
                    fBitCount -= 0.5f;
                }
                int i31 = childCount2 - 1;
                if ((j & ((long) (1 << i31))) != 0 && !((g1) getChildAt(i31).getLayoutParams()).e) {
                    fBitCount -= 0.5f;
                }
            }
            int i32 = fBitCount > 0.0f ? (int) ((i23 * i22) / fBitCount) : 0;
            boolean z13 = z8;
            for (int i33 = 0; i33 < childCount2; i33++) {
                if ((j & ((long) (1 << i33))) != 0) {
                    View childAt3 = getChildAt(i33);
                    g1 g1Var6 = (g1) childAt3.getLayoutParams();
                    if (childAt3 instanceof ActionMenuItemView) {
                        g1Var6.c = i32;
                        g1Var6.f = true;
                        if (i33 == 0 && !g1Var6.e) {
                            ((LinearLayout.LayoutParams) g1Var6).leftMargin = (-i32) / 2;
                        }
                        z13 = true;
                    } else if (g1Var6.a) {
                        g1Var6.c = i32;
                        g1Var6.f = true;
                        ((LinearLayout.LayoutParams) g1Var6).rightMargin = (-i32) / 2;
                        z13 = true;
                    } else {
                        if (i33 != 0) {
                            ((LinearLayout.LayoutParams) g1Var6).leftMargin = i32 / 2;
                        }
                        if (i33 != childCount2 - 1) {
                            ((LinearLayout.LayoutParams) g1Var6).rightMargin = i32 / 2;
                        }
                    }
                }
            }
            z8 = z13;
        }
        if (z8) {
            for (int i34 = 0; i34 < childCount2; i34++) {
                View childAt4 = getChildAt(i34);
                g1 g1Var7 = (g1) childAt4.getLayoutParams();
                if (g1Var7.f) {
                    childAt4.measure(View.MeasureSpec.makeMeasureSpec((g1Var7.b * i22) + g1Var7.c, 1073741824), childMeasureSpec);
                }
            }
        }
        setMeasuredDimension(i8, mode != 1073741824 ? i4 : i20);
    }

    public void setExpandedActionViewsExclusive(boolean z) {
        this.t.q = z;
    }

    public void setOnMenuItemClickListener(h1 h1Var) {
        this.A = h1Var;
    }

    public void setOverflowIcon(Drawable drawable) {
        getMenu();
        e1 e1Var = this.t;
        d1 d1Var = e1Var.i;
        if (d1Var != null) {
            d1Var.setImageDrawable(drawable);
        } else {
            e1Var.k = true;
            e1Var.j = drawable;
        }
    }

    public void setOverflowReserved(boolean z) {
        this.s = z;
    }

    public void setPopupTheme(int i) {
        if (this.r != i) {
            this.r = i;
            if (i == 0) {
                this.q = getContext();
            } else {
                this.q = new ContextThemeWrapper(getContext(), i);
            }
        }
    }

    public void setPresenter(e1 e1Var) {
        this.t = e1Var;
        e1Var.h = this;
        this.p = e1Var.c;
    }

    @Override // defpackage.og, android.view.ViewGroup
    public final /* bridge */ /* synthetic */ ViewGroup.LayoutParams generateLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return j(layoutParams);
    }

    public ActionMenuView(Context context) {
        this(context, null);
    }
}
