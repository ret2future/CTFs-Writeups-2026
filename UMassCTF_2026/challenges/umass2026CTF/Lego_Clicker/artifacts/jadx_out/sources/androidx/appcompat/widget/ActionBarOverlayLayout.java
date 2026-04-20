package androidx.appcompat.widget;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.AttributeSet;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewPropertyAnimator;
import android.view.Window;
import android.view.WindowInsets;
import android.widget.OverScroller;
import com.example.LegoClicker.R;
import defpackage.aj;
import defpackage.bs;
import defpackage.d;
import defpackage.ds;
import defpackage.du;
import defpackage.e1;
import defpackage.et;
import defpackage.eu;
import defpackage.g9;
import defpackage.gi;
import defpackage.gq;
import defpackage.jf;
import defpackage.os;
import defpackage.rj;
import defpackage.s0;
import defpackage.sj;
import defpackage.st;
import defpackage.t0;
import defpackage.tj;
import defpackage.u0;
import defpackage.ut;
import defpackage.v0;
import defpackage.vt;
import defpackage.w0;
import defpackage.wt;
import defpackage.xp;
import defpackage.xt;
import defpackage.z6;
import java.util.WeakHashMap;

/* JADX INFO: compiled from: r8-map-id-51b54c9e59b63e0fb0abe04daff984b4fb85d07ef3fcb7973595bce215c10515 */
/* JADX INFO: loaded from: classes.dex */
public class ActionBarOverlayLayout extends ViewGroup implements rj, sj {
    public static final int[] C = {R.attr.actionBarSize, android.R.attr.windowContentOverlay};
    public static final eu D;
    public static final Rect E;
    public final tj A;
    public final w0 B;
    public int a;
    public int b;
    public ContentFrameLayout c;
    public ActionBarContainer d;
    public g9 e;
    public Drawable f;
    public boolean g;
    public boolean h;
    public boolean i;
    public boolean j;
    public int k;
    public int l;
    public final Rect m;
    public final Rect n;
    public final Rect o;
    public final Rect p;
    public eu q;
    public eu r;
    public eu s;
    public eu t;
    public u0 u;
    public OverScroller v;
    public ViewPropertyAnimator w;
    public final s0 x;
    public final t0 y;
    public final t0 z;

    static {
        int i = Build.VERSION.SDK_INT;
        xt wtVar = i >= 30 ? new wt() : i >= 29 ? new vt() : new ut();
        wtVar.d(jf.a(0, 1, 0, 1));
        D = wtVar.b();
        E = new Rect();
    }

    public ActionBarOverlayLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.b = 0;
        this.m = new Rect();
        this.n = new Rect();
        this.o = new Rect();
        this.p = new Rect();
        new Rect();
        new Rect();
        new Rect();
        new Rect();
        eu euVar = eu.b;
        this.q = euVar;
        this.r = euVar;
        this.s = euVar;
        this.t = euVar;
        this.x = new s0(this);
        this.y = new t0(this, 0);
        this.z = new t0(this, 1);
        i(context);
        this.A = new tj();
        w0 w0Var = new w0(context);
        w0Var.setWillNotDraw(true);
        this.B = w0Var;
        addView(w0Var);
    }

    public static boolean g(View view, Rect rect, boolean z) {
        boolean z2;
        v0 v0Var = (v0) view.getLayoutParams();
        int i = ((ViewGroup.MarginLayoutParams) v0Var).leftMargin;
        int i2 = rect.left;
        if (i != i2) {
            ((ViewGroup.MarginLayoutParams) v0Var).leftMargin = i2;
            z2 = true;
        } else {
            z2 = false;
        }
        int i3 = ((ViewGroup.MarginLayoutParams) v0Var).topMargin;
        int i4 = rect.top;
        if (i3 != i4) {
            ((ViewGroup.MarginLayoutParams) v0Var).topMargin = i4;
            z2 = true;
        }
        int i5 = ((ViewGroup.MarginLayoutParams) v0Var).rightMargin;
        int i6 = rect.right;
        if (i5 != i6) {
            ((ViewGroup.MarginLayoutParams) v0Var).rightMargin = i6;
            z2 = true;
        }
        if (z) {
            int i7 = ((ViewGroup.MarginLayoutParams) v0Var).bottomMargin;
            int i8 = rect.bottom;
            if (i7 != i8) {
                ((ViewGroup.MarginLayoutParams) v0Var).bottomMargin = i8;
                return true;
            }
        }
        return z2;
    }

    @Override // defpackage.rj
    public final void a(View view, View view2, int i, int i2) {
        if (i2 == 0) {
            onNestedScrollAccepted(view, view2, i);
        }
    }

    @Override // defpackage.rj
    public final void b(ViewGroup viewGroup, int i, int i2, int i3, int i4, int i5) {
        if (i5 == 0) {
            onNestedScroll(viewGroup, i, i2, i3, i4);
        }
    }

    @Override // defpackage.rj
    public final void c(View view, int i) {
        if (i == 0) {
            onStopNestedScroll(view);
        }
    }

    @Override // android.view.ViewGroup
    public final boolean checkLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return layoutParams instanceof v0;
    }

    @Override // android.view.View
    public final void draw(Canvas canvas) {
        int translationY;
        super.draw(canvas);
        if (this.f != null) {
            if (this.d.getVisibility() == 0) {
                translationY = (int) (this.d.getTranslationY() + this.d.getBottom() + 0.5f);
            } else {
                translationY = 0;
            }
            this.f.setBounds(0, translationY, getWidth(), this.f.getIntrinsicHeight() + translationY);
            this.f.draw(canvas);
        }
    }

    @Override // defpackage.sj
    public final void e(ViewGroup viewGroup, int i, int i2, int i3, int i4, int i5, int[] iArr) {
        b(viewGroup, i, i2, i3, i4, i5);
    }

    @Override // defpackage.rj
    public final boolean f(View view, View view2, int i, int i2) {
        return i2 == 0 && onStartNestedScroll(view, view2, i);
    }

    @Override // android.view.View
    public final boolean fitSystemWindows(Rect rect) {
        return super.fitSystemWindows(rect);
    }

    @Override // android.view.ViewGroup
    public final ViewGroup.LayoutParams generateDefaultLayoutParams() {
        return new v0(-1, -1);
    }

    @Override // android.view.ViewGroup
    public final ViewGroup.LayoutParams generateLayoutParams(AttributeSet attributeSet) {
        return new v0(getContext(), attributeSet);
    }

    public int getActionBarHideOffset() {
        ActionBarContainer actionBarContainer = this.d;
        if (actionBarContainer != null) {
            return -((int) actionBarContainer.getTranslationY());
        }
        return 0;
    }

    @Override // android.view.ViewGroup
    public int getNestedScrollAxes() {
        tj tjVar = this.A;
        return tjVar.b | tjVar.a;
    }

    public CharSequence getTitle() {
        k();
        return ((gq) this.e).a.getTitle();
    }

    public final void h() {
        removeCallbacks(this.y);
        removeCallbacks(this.z);
        ViewPropertyAnimator viewPropertyAnimator = this.w;
        if (viewPropertyAnimator != null) {
            viewPropertyAnimator.cancel();
        }
    }

    public final void i(Context context) {
        TypedArray typedArrayObtainStyledAttributes = getContext().getTheme().obtainStyledAttributes(C);
        this.a = typedArrayObtainStyledAttributes.getDimensionPixelSize(0, 0);
        Drawable drawable = typedArrayObtainStyledAttributes.getDrawable(1);
        this.f = drawable;
        setWillNotDraw(drawable == null);
        typedArrayObtainStyledAttributes.recycle();
        this.v = new OverScroller(context);
    }

    public final void j(int i) {
        k();
        if (i == 2) {
            ((gq) this.e).getClass();
            Log.i("ToolbarWidgetWrapper", "Progress display unsupported");
        } else if (i == 5) {
            ((gq) this.e).getClass();
            Log.i("ToolbarWidgetWrapper", "Progress display unsupported");
        } else {
            if (i != 109) {
                return;
            }
            setOverlayMode(true);
        }
    }

    public final void k() {
        g9 wrapper;
        if (this.c == null) {
            this.c = (ContentFrameLayout) findViewById(R.id.action_bar_activity_content);
            this.d = (ActionBarContainer) findViewById(R.id.action_bar_container);
            KeyEvent.Callback callbackFindViewById = findViewById(R.id.action_bar);
            if (callbackFindViewById instanceof g9) {
                wrapper = (g9) callbackFindViewById;
            } else {
                if (!(callbackFindViewById instanceof Toolbar)) {
                    z6.o("Can't make a decor toolbar out of ".concat(callbackFindViewById.getClass().getSimpleName()));
                    return;
                }
                wrapper = ((Toolbar) callbackFindViewById).getWrapper();
            }
            this.e = wrapper;
        }
    }

    public final void l(Menu menu, aj ajVar) {
        k();
        gq gqVar = (gq) this.e;
        Toolbar toolbar = gqVar.a;
        if (gqVar.m == null) {
            gqVar.m = new e1(toolbar.getContext());
        }
        e1 e1Var = gqVar.m;
        e1Var.e = ajVar;
        gi giVar = (gi) menu;
        if (giVar == null && toolbar.a == null) {
            return;
        }
        toolbar.f();
        gi giVar2 = toolbar.a.p;
        if (giVar2 == giVar) {
            return;
        }
        if (giVar2 != null) {
            giVar2.r(toolbar.L);
            giVar2.r(toolbar.M);
        }
        if (toolbar.M == null) {
            toolbar.M = new xp(toolbar);
        }
        e1Var.q = true;
        Context context = toolbar.j;
        if (giVar != null) {
            giVar.b(e1Var, context);
            giVar.b(toolbar.M, toolbar.j);
        } else {
            e1Var.i(context, null);
            toolbar.M.i(toolbar.j, null);
            e1Var.h();
            toolbar.M.h();
        }
        toolbar.a.setPopupTheme(toolbar.k);
        toolbar.a.setPresenter(e1Var);
        toolbar.L = e1Var;
        toolbar.v();
    }

    @Override // android.view.View
    public final WindowInsets onApplyWindowInsets(WindowInsets windowInsets) {
        k();
        eu euVarC = eu.c(windowInsets, this);
        du duVar = euVarC.a;
        boolean zG = g(this.d, new Rect(duVar.g().a, duVar.g().b, duVar.g().c, duVar.g().d), false);
        WeakHashMap weakHashMap = os.a;
        Rect rect = this.m;
        ds.b(this, euVarC, rect);
        eu euVarH = duVar.h(rect.left, rect.top, rect.right, rect.bottom);
        this.q = euVarH;
        boolean z = true;
        if (!this.r.equals(euVarH)) {
            this.r = this.q;
            zG = true;
        }
        Rect rect2 = this.n;
        if (rect2.equals(rect)) {
            z = zG;
        } else {
            rect2.set(rect);
        }
        if (z) {
            requestLayout();
        }
        return duVar.a().a.c().a.b().b();
    }

    @Override // android.view.View
    public final void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        i(getContext());
        WeakHashMap weakHashMap = os.a;
        bs.c(this);
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        h();
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void onLayout(boolean z, int i, int i2, int i3, int i4) {
        int childCount = getChildCount();
        int paddingLeft = getPaddingLeft();
        int paddingTop = getPaddingTop();
        for (int i5 = 0; i5 < childCount; i5++) {
            View childAt = getChildAt(i5);
            if (childAt.getVisibility() != 8) {
                v0 v0Var = (v0) childAt.getLayoutParams();
                int measuredWidth = childAt.getMeasuredWidth();
                int measuredHeight = childAt.getMeasuredHeight();
                int i6 = ((ViewGroup.MarginLayoutParams) v0Var).leftMargin + paddingLeft;
                int i7 = ((ViewGroup.MarginLayoutParams) v0Var).topMargin + paddingTop;
                childAt.layout(i6, i7, measuredWidth + i6, measuredHeight + i7);
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:22:0x00aa  */
    @Override // android.view.View
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void onMeasure(int r13, int r14) {
        /*
            Method dump skipped, instruction units count: 403
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.appcompat.widget.ActionBarOverlayLayout.onMeasure(int, int):void");
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public final boolean onNestedFling(View view, float f, float f2, boolean z) {
        if (!this.i || !z) {
            return false;
        }
        this.v.fling(0, 0, 0, (int) f2, 0, 0, Integer.MIN_VALUE, Integer.MAX_VALUE);
        if (this.v.getFinalY() > this.d.getHeight()) {
            h();
            this.z.run();
        } else {
            h();
            this.y.run();
        }
        this.j = true;
        return true;
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public final boolean onNestedPreFling(View view, float f, float f2) {
        return false;
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public final void onNestedScroll(View view, int i, int i2, int i3, int i4) {
        int i5 = this.k + i2;
        this.k = i5;
        setActionBarHideOffset(i5);
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public final void onNestedScrollAccepted(View view, View view2, int i) {
        st stVar;
        et etVar;
        this.A.a = i;
        this.k = getActionBarHideOffset();
        h();
        u0 u0Var = this.u;
        if (u0Var == null || (etVar = (stVar = (st) u0Var).s) == null) {
            return;
        }
        etVar.a();
        stVar.s = null;
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public final boolean onStartNestedScroll(View view, View view2, int i) {
        if ((i & 2) == 0 || this.d.getVisibility() != 0) {
            return false;
        }
        return this.i;
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public final void onStopNestedScroll(View view) {
        if (!this.i || this.j) {
            return;
        }
        if (this.k <= this.d.getHeight()) {
            h();
            postDelayed(this.y, 600L);
        } else {
            h();
            postDelayed(this.z, 600L);
        }
    }

    @Override // android.view.View
    public final void onWindowSystemUiVisibilityChanged(int i) {
        super.onWindowSystemUiVisibilityChanged(i);
        k();
        int i2 = this.l ^ i;
        this.l = i;
        boolean z = (i & 4) == 0;
        boolean z2 = (i & 256) != 0;
        u0 u0Var = this.u;
        if (u0Var != null) {
            st stVar = (st) u0Var;
            stVar.o = !z2;
            if (z || !z2) {
                if (stVar.p) {
                    stVar.p = false;
                    stVar.s(true);
                }
            } else if (!stVar.p) {
                stVar.p = true;
                stVar.s(true);
            }
        }
        if ((i2 & 256) == 0 || this.u == null) {
            return;
        }
        WeakHashMap weakHashMap = os.a;
        bs.c(this);
    }

    @Override // android.view.View
    public final void onWindowVisibilityChanged(int i) {
        super.onWindowVisibilityChanged(i);
        this.b = i;
        u0 u0Var = this.u;
        if (u0Var != null) {
            ((st) u0Var).n = i;
        }
    }

    public void setActionBarHideOffset(int i) {
        h();
        this.d.setTranslationY(-Math.max(0, Math.min(i, this.d.getHeight())));
    }

    public void setActionBarVisibilityCallback(u0 u0Var) {
        this.u = u0Var;
        if (getWindowToken() != null) {
            ((st) this.u).n = this.b;
            int i = this.l;
            if (i != 0) {
                onWindowSystemUiVisibilityChanged(i);
                WeakHashMap weakHashMap = os.a;
                bs.c(this);
            }
        }
    }

    public void setHasNonEmbeddedTabs(boolean z) {
        this.h = z;
    }

    public void setHideOnContentScrollEnabled(boolean z) {
        if (z != this.i) {
            this.i = z;
            if (z) {
                return;
            }
            h();
            setActionBarHideOffset(0);
        }
    }

    public void setIcon(int i) {
        k();
        gq gqVar = (gq) this.e;
        gqVar.d = i != 0 ? d.w(gqVar.a.getContext(), i) : null;
        gqVar.c();
    }

    public void setLogo(int i) {
        k();
        gq gqVar = (gq) this.e;
        gqVar.e = i != 0 ? d.w(gqVar.a.getContext(), i) : null;
        gqVar.c();
    }

    public void setOverlayMode(boolean z) {
        this.g = z;
    }

    public void setWindowCallback(Window.Callback callback) {
        k();
        ((gq) this.e).k = callback;
    }

    public void setWindowTitle(CharSequence charSequence) {
        k();
        gq gqVar = (gq) this.e;
        if (gqVar.g) {
            return;
        }
        Toolbar toolbar = gqVar.a;
        gqVar.h = charSequence;
        if ((gqVar.b & 8) != 0) {
            toolbar.setTitle(charSequence);
            if (gqVar.g) {
                os.i(toolbar.getRootView(), charSequence);
            }
        }
    }

    @Override // android.view.ViewGroup
    public final boolean shouldDelayChildPressedState() {
        return false;
    }

    @Override // android.view.ViewGroup
    public final ViewGroup.LayoutParams generateLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return new v0(layoutParams);
    }

    public void setIcon(Drawable drawable) {
        k();
        gq gqVar = (gq) this.e;
        gqVar.d = drawable;
        gqVar.c();
    }

    public ActionBarOverlayLayout(Context context) {
        this(context, null);
    }

    public void setShowingForActionMode(boolean z) {
    }

    public void setUiOptions(int i) {
    }

    @Override // defpackage.rj
    public final void d(int i, int i2, int[] iArr, int i3) {
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public final void onNestedPreScroll(View view, int i, int i2, int[] iArr) {
    }
}
