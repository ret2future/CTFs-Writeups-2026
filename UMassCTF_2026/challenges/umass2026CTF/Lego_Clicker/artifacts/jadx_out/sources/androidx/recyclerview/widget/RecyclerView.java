package androidx.recyclerview.widget;

import android.R;
import android.animation.LayoutTransition;
import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.StateListDrawable;
import android.os.Build;
import android.os.Parcelable;
import android.os.SystemClock;
import android.os.Trace;
import android.util.AttributeSet;
import android.util.Log;
import android.util.SparseArray;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityManager;
import android.view.animation.Interpolator;
import android.widget.EdgeEffect;
import android.widget.OverScroller;
import defpackage.a2;
import defpackage.am;
import defpackage.b6;
import defpackage.b7;
import defpackage.bm;
import defpackage.cm;
import defpackage.d;
import defpackage.dm;
import defpackage.ds;
import defpackage.el;
import defpackage.em;
import defpackage.fs;
import defpackage.gm;
import defpackage.hb;
import defpackage.hl;
import defpackage.il;
import defpackage.jl;
import defpackage.ko;
import defpackage.le;
import defpackage.lk;
import defpackage.ll;
import defpackage.lo;
import defpackage.lq;
import defpackage.ml;
import defpackage.ne;
import defpackage.nl;
import defpackage.os;
import defpackage.p9;
import defpackage.ps;
import defpackage.qj;
import defpackage.ql;
import defpackage.rh;
import defpackage.rl;
import defpackage.sl;
import defpackage.ss;
import defpackage.tj;
import defpackage.tl;
import defpackage.ts;
import defpackage.ul;
import defpackage.vl;
import defpackage.wl;
import defpackage.xl;
import defpackage.yb;
import defpackage.yl;
import defpackage.z1;
import defpackage.z3;
import defpackage.z6;
import defpackage.zl;
import java.lang.ref.WeakReference;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.WeakHashMap;

/* JADX INFO: compiled from: r8-map-id-51b54c9e59b63e0fb0abe04daff984b4fb85d07ef3fcb7973595bce215c10515 */
/* JADX INFO: loaded from: classes.dex */
public class RecyclerView extends ViewGroup {
    public static final Class[] C0;
    public static final hl D0;
    public static final bm E0;
    public static boolean w0 = false;
    public static boolean x0 = false;
    public boolean A;
    public boolean B;
    public int C;
    public int D;
    public ml E;
    public EdgeEffect F;
    public EdgeEffect G;
    public EdgeEffect H;
    public EdgeEffect I;
    public nl J;
    public int K;
    public int L;
    public VelocityTracker M;
    public int N;
    public int O;
    public int P;
    public int Q;
    public int R;
    public final int S;
    public final int T;
    public final float U;
    public final float V;
    public boolean W;
    public final float a;
    public final dm a0;
    public final yl b;
    public ne b0;
    public final wl c;
    public final le c0;
    public zl d;
    public final am d0;
    public final a2 e;
    public tl e0;
    public final b7 f;
    public ArrayList f0;
    public final z3 g;
    public boolean g0;
    public boolean h;
    public boolean h0;
    public final Rect i;
    public final il i0;
    public final Rect j;
    public boolean j0;
    public final RectF k;
    public gm k0;
    public jl l;
    public final int[] l0;
    public ql m;
    public qj m0;
    public final ArrayList n;
    public final int[] n0;
    public final ArrayList o;
    public final int[] o0;
    public final ArrayList p;
    public final int[] p0;
    public yb q;
    public final ArrayList q0;
    public boolean r;
    public final b6 r0;
    public boolean s;
    public boolean s0;
    public boolean t;
    public int t0;
    public int u;
    public int u0;
    public boolean v;
    public final il v0;
    public boolean w;
    public boolean x;
    public int y;
    public final AccessibilityManager z;
    public static final int[] y0 = {R.attr.nestedScrollingEnabled};
    public static final float z0 = (float) (Math.log(0.78d) / Math.log(0.9d));
    public static final boolean A0 = true;
    public static final boolean B0 = true;

    static {
        Class cls = Integer.TYPE;
        C0 = new Class[]{Context.class, AttributeSet.class, cls, cls};
        D0 = new hl();
        E0 = new bm();
    }

    /* JADX WARN: Multi-variable type inference failed */
    public RecyclerView(Context context, AttributeSet attributeSet, int i) throws Throwable {
        float fA;
        char c;
        Throwable th;
        char c2;
        char c3;
        int i2;
        TypedArray typedArray;
        int i3;
        ClassLoader classLoader;
        Object[] objArr;
        Constructor constructor;
        super(context, attributeSet, i);
        this.b = new yl(this);
        this.c = new wl(this);
        this.g = new z3(12);
        this.i = new Rect();
        this.j = new Rect();
        this.k = new RectF();
        this.n = new ArrayList();
        this.o = new ArrayList();
        this.p = new ArrayList();
        this.u = 0;
        this.A = false;
        this.B = false;
        this.C = 0;
        this.D = 0;
        this.E = E0;
        p9 p9Var = new p9();
        p9Var.a = null;
        p9Var.b = new ArrayList();
        p9Var.c = 120L;
        p9Var.d = 120L;
        p9Var.e = 250L;
        p9Var.f = 250L;
        p9Var.g = true;
        p9Var.h = new ArrayList();
        p9Var.i = new ArrayList();
        p9Var.j = new ArrayList();
        p9Var.k = new ArrayList();
        p9Var.l = new ArrayList();
        p9Var.m = new ArrayList();
        p9Var.n = new ArrayList();
        p9Var.o = new ArrayList();
        p9Var.p = new ArrayList();
        p9Var.q = new ArrayList();
        p9Var.r = new ArrayList();
        this.J = p9Var;
        this.K = 0;
        this.L = -1;
        this.U = Float.MIN_VALUE;
        this.V = Float.MIN_VALUE;
        this.W = true;
        this.a0 = new dm(this);
        this.c0 = B0 ? new le() : null;
        am amVar = new am();
        amVar.a = 0;
        amVar.b = 0;
        amVar.c = 1;
        amVar.d = 0;
        amVar.e = false;
        amVar.f = false;
        amVar.g = false;
        amVar.h = false;
        amVar.i = false;
        amVar.j = false;
        this.d0 = amVar;
        this.g0 = false;
        this.h0 = false;
        il ilVar = new il(this);
        this.i0 = ilVar;
        this.j0 = false;
        this.l0 = new int[2];
        this.n0 = new int[2];
        this.o0 = new int[2];
        this.p0 = new int[2];
        this.q0 = new ArrayList();
        this.r0 = new b6(6, this);
        this.t0 = 0;
        this.u0 = 0;
        this.v0 = new il(this);
        setScrollContainer(true);
        setFocusableInTouchMode(true);
        ViewConfiguration viewConfiguration = ViewConfiguration.get(context);
        this.R = viewConfiguration.getScaledTouchSlop();
        int i4 = Build.VERSION.SDK_INT;
        if (i4 >= 26) {
            Method method = ss.a;
            fA = ps.a(viewConfiguration);
        } else {
            fA = ss.a(viewConfiguration, context);
        }
        this.U = fA;
        this.V = i4 >= 26 ? ps.b(viewConfiguration) : ss.a(viewConfiguration, context);
        this.S = viewConfiguration.getScaledMinimumFlingVelocity();
        this.T = viewConfiguration.getScaledMaximumFlingVelocity();
        this.a = context.getResources().getDisplayMetrics().density * 160.0f * 386.0878f * 0.84f;
        setWillNotDraw(getOverScrollMode() == 2);
        this.J.a = ilVar;
        this.e = new a2(new il(this));
        this.f = new b7(new il(this));
        WeakHashMap weakHashMap = os.a;
        if ((i4 >= 26 ? fs.c(this) : 0) == 0 && i4 >= 26) {
            fs.m(this, 8);
        }
        if (getImportantForAccessibility() == 0) {
            setImportantForAccessibility(1);
        }
        this.z = (AccessibilityManager) getContext().getSystemService("accessibility");
        setAccessibilityDelegateCompat(new gm(this));
        int[] iArr = el.a;
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, iArr, i, 0);
        os.g(this, context, iArr, attributeSet, typedArrayObtainStyledAttributes, i);
        String string = typedArrayObtainStyledAttributes.getString(8);
        if (typedArrayObtainStyledAttributes.getInt(2, -1) == -1) {
            setDescendantFocusability(262144);
        }
        this.h = typedArrayObtainStyledAttributes.getBoolean(1, true);
        if (typedArrayObtainStyledAttributes.getBoolean(3, false)) {
            StateListDrawable stateListDrawable = (StateListDrawable) typedArrayObtainStyledAttributes.getDrawable(6);
            Drawable drawable = typedArrayObtainStyledAttributes.getDrawable(7);
            StateListDrawable stateListDrawable2 = (StateListDrawable) typedArrayObtainStyledAttributes.getDrawable(4);
            Drawable drawable2 = typedArrayObtainStyledAttributes.getDrawable(5);
            if (stateListDrawable == null || drawable == null || stateListDrawable2 == null || drawable2 == null) {
                z6.f("Trying to set fast scroller without both required drawables.".concat(y()));
                throw null;
            }
            Resources resources = getContext().getResources();
            c3 = 2;
            c2 = 1;
            typedArray = typedArrayObtainStyledAttributes;
            i2 = i;
            c = 3;
            th = null;
            i3 = 4;
            new yb(this, stateListDrawable, drawable, stateListDrawable2, drawable2, resources.getDimensionPixelSize(com.example.LegoClicker.R.dimen.fastscroll_default_thickness), resources.getDimensionPixelSize(com.example.LegoClicker.R.dimen.fastscroll_minimum_range), resources.getDimensionPixelOffset(com.example.LegoClicker.R.dimen.fastscroll_margin));
        } else {
            c = 3;
            th = null;
            c2 = 1;
            c3 = 2;
            i2 = i;
            typedArray = typedArrayObtainStyledAttributes;
            i3 = 4;
        }
        typedArray.recycle();
        if (string != null) {
            String strTrim = string.trim();
            if (!strTrim.isEmpty()) {
                if (strTrim.charAt(0) == '.') {
                    strTrim = context.getPackageName() + strTrim;
                } else if (!strTrim.contains(".")) {
                    strTrim = RecyclerView.class.getPackage().getName() + '.' + strTrim;
                }
                String str = strTrim;
                try {
                    try {
                        if (isInEditMode()) {
                            classLoader = getClass().getClassLoader();
                        } else {
                            try {
                                classLoader = context.getClassLoader();
                            } catch (ClassNotFoundException e) {
                                e = e;
                                th = null;
                                z6.l(attributeSet.getPositionDescription(), ": Unable to find LayoutManager ", str, e);
                                throw th;
                            } catch (IllegalAccessException e2) {
                                e = e2;
                                th = null;
                                z6.l(attributeSet.getPositionDescription(), ": Cannot access non-public constructor ", str, e);
                                throw th;
                            } catch (InstantiationException e3) {
                                e = e3;
                                th = null;
                                z6.l(attributeSet.getPositionDescription(), ": Could not instantiate the LayoutManager: ", str, e);
                                throw th;
                            } catch (InvocationTargetException e4) {
                                e = e4;
                                th = null;
                                z6.l(attributeSet.getPositionDescription(), ": Could not instantiate the LayoutManager: ", str, e);
                                throw th;
                            }
                        }
                        Class<? extends U> clsAsSubclass = Class.forName(str, false, classLoader).asSubclass(ql.class);
                        try {
                            Constructor constructor2 = clsAsSubclass.getConstructor(C0);
                            objArr = new Object[i3];
                            objArr[0] = context;
                            objArr[c2] = attributeSet;
                            objArr[c3] = Integer.valueOf(i2);
                            objArr[c] = 0;
                            constructor = constructor2;
                        } catch (NoSuchMethodException e5) {
                            try {
                                objArr = null;
                                constructor = clsAsSubclass.getConstructor(th);
                            } catch (NoSuchMethodException e6) {
                                e6.initCause(e5);
                                throw new IllegalStateException(attributeSet.getPositionDescription() + ": Error creating LayoutManager " + str, e6);
                            }
                        }
                        constructor.setAccessible(c2);
                        setLayoutManager((ql) constructor.newInstance(objArr));
                    } catch (ClassCastException e7) {
                        z6.l(attributeSet.getPositionDescription(), ": Class is not a LayoutManager ", str, e7);
                        throw null;
                    }
                } catch (ClassNotFoundException e8) {
                    e = e8;
                } catch (IllegalAccessException e9) {
                    e = e9;
                } catch (InstantiationException e10) {
                    e = e10;
                } catch (InvocationTargetException e11) {
                    e = e11;
                }
            }
        }
        int[] iArr2 = y0;
        TypedArray typedArrayObtainStyledAttributes2 = context.obtainStyledAttributes(attributeSet, iArr2, i2, 0);
        os.g(this, context, iArr2, attributeSet, typedArrayObtainStyledAttributes2, i2);
        boolean z = typedArrayObtainStyledAttributes2.getBoolean(0, true);
        typedArrayObtainStyledAttributes2.recycle();
        setNestedScrollingEnabled(z);
        setTag(com.example.LegoClicker.R.id.is_pooling_container_tag, Boolean.TRUE);
    }

    public static RecyclerView D(View view) {
        if (!(view instanceof ViewGroup)) {
            return null;
        }
        if (view instanceof RecyclerView) {
            return (RecyclerView) view;
        }
        ViewGroup viewGroup = (ViewGroup) view;
        int childCount = viewGroup.getChildCount();
        for (int i = 0; i < childCount; i++) {
            RecyclerView recyclerViewD = D(viewGroup.getChildAt(i));
            if (recyclerViewD != null) {
                return recyclerViewD;
            }
        }
        return null;
    }

    public static em H(View view) {
        if (view == null) {
            return null;
        }
        return ((rl) view.getLayoutParams()).a;
    }

    private qj getScrollingChildHelper() {
        if (this.m0 == null) {
            this.m0 = new qj(this);
        }
        return this.m0;
    }

    public static void i(em emVar) {
        WeakReference weakReference = emVar.b;
        if (weakReference != null) {
            View view = (View) weakReference.get();
            while (view != null) {
                if (view == emVar.a) {
                    return;
                }
                Object parent = view.getParent();
                view = parent instanceof View ? (View) parent : null;
            }
            emVar.b = null;
        }
    }

    public static int l(int i, EdgeEffect edgeEffect, EdgeEffect edgeEffect2, int i2) {
        if (i > 0 && edgeEffect != null && d.v(edgeEffect) != 0.0f) {
            int iRound = Math.round(d.J(edgeEffect, ((-i) * 4.0f) / i2, 0.5f) * ((-i2) / 4.0f));
            if (iRound != i) {
                edgeEffect.finish();
            }
            return i - iRound;
        }
        if (i >= 0 || edgeEffect2 == null || d.v(edgeEffect2) == 0.0f) {
            return i;
        }
        float f = i2;
        int iRound2 = Math.round(d.J(edgeEffect2, (i * 4.0f) / f, 0.5f) * (f / 4.0f));
        if (iRound2 != i) {
            edgeEffect2.finish();
        }
        return i - iRound2;
    }

    public static void setDebugAssertionsEnabled(boolean z) {
        w0 = z;
    }

    public static void setVerboseLoggingEnabled(boolean z) {
        x0 = z;
    }

    public final View A(View view) {
        ViewParent parent = view.getParent();
        while (parent != null && parent != this && (parent instanceof View)) {
            view = parent;
            parent = view.getParent();
        }
        if (parent == this) {
            return view;
        }
        return null;
    }

    /* JADX WARN: Removed duplicated region for block: B:23:0x005e A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:27:0x0061 A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final boolean B(android.view.MotionEvent r12) {
        /*
            r11 = this;
            int r0 = r12.getAction()
            java.util.ArrayList r1 = r11.p
            int r2 = r1.size()
            r3 = 0
            r4 = r3
        Lc:
            if (r4 >= r2) goto L64
            java.lang.Object r5 = r1.get(r4)
            yb r5 = (defpackage.yb) r5
            int r6 = r5.v
            r7 = 1
            r8 = 2
            if (r6 != r7) goto L59
            float r6 = r12.getX()
            float r9 = r12.getY()
            boolean r6 = r5.b(r6, r9)
            float r9 = r12.getX()
            float r10 = r12.getY()
            boolean r9 = r5.a(r9, r10)
            int r10 = r12.getAction()
            if (r10 != 0) goto L61
            if (r6 != 0) goto L3c
            if (r9 == 0) goto L61
        L3c:
            if (r9 == 0) goto L49
            r5.w = r7
            float r6 = r12.getX()
            int r6 = (int) r6
            float r6 = (float) r6
            r5.p = r6
            goto L55
        L49:
            if (r6 == 0) goto L55
            r5.w = r8
            float r6 = r12.getY()
            int r6 = (int) r6
            float r6 = (float) r6
            r5.m = r6
        L55:
            r5.d(r8)
            goto L5b
        L59:
            if (r6 != r8) goto L61
        L5b:
            r6 = 3
            if (r0 == r6) goto L61
            r11.q = r5
            return r7
        L61:
            int r4 = r4 + 1
            goto Lc
        L64:
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.recyclerview.widget.RecyclerView.B(android.view.MotionEvent):boolean");
    }

    public final void C(int[] iArr) {
        b7 b7Var = this.f;
        int iE = b7Var.e();
        if (iE == 0) {
            iArr[0] = -1;
            iArr[1] = -1;
            return;
        }
        int i = Integer.MAX_VALUE;
        int i2 = Integer.MIN_VALUE;
        for (int i3 = 0; i3 < iE; i3++) {
            em emVarH = H(b7Var.d(i3));
            if (!emVarH.n()) {
                int iB = emVarH.b();
                if (iB < i) {
                    i = iB;
                }
                if (iB > i2) {
                    i2 = iB;
                }
            }
        }
        iArr[0] = i;
        iArr[1] = i2;
    }

    public final em E(int i) {
        em emVar = null;
        if (this.A) {
            return null;
        }
        b7 b7Var = this.f;
        int iH = b7Var.h();
        for (int i2 = 0; i2 < iH; i2++) {
            em emVarH = H(b7Var.g(i2));
            if (emVarH != null && !emVarH.g() && F(emVarH) == i) {
                if (!b7Var.c.contains(emVarH.a)) {
                    return emVarH;
                }
                emVar = emVarH;
            }
        }
        return emVar;
    }

    public final int F(em emVar) {
        if ((emVar.i & 524) == 0 && emVar.d()) {
            int i = emVar.c;
            ArrayList arrayList = (ArrayList) this.e.c;
            int size = arrayList.size();
            for (int i2 = 0; i2 < size; i2++) {
                z1 z1Var = (z1) arrayList.get(i2);
                int i3 = z1Var.a;
                if (i3 != 1) {
                    if (i3 == 2) {
                        int i4 = z1Var.b;
                        if (i4 <= i) {
                            int i5 = z1Var.c;
                            if (i4 + i5 <= i) {
                                i -= i5;
                            }
                        } else {
                            continue;
                        }
                    } else if (i3 == 8) {
                        int i6 = z1Var.b;
                        if (i6 == i) {
                            i = z1Var.c;
                        } else {
                            if (i6 < i) {
                                i--;
                            }
                            if (z1Var.c <= i) {
                                i++;
                            }
                        }
                    }
                } else if (z1Var.b <= i) {
                    i += z1Var.c;
                }
            }
            return i;
        }
        return -1;
    }

    public final em G(View view) {
        ViewParent parent = view.getParent();
        if (parent == null || parent == this) {
            return H(view);
        }
        z6.i("View ", view, " is not a direct child of ", this);
        return null;
    }

    public final Rect I(View view) {
        rl rlVar = (rl) view.getLayoutParams();
        boolean z = rlVar.c;
        Rect rect = rlVar.b;
        if (!z || (this.d0.f && (rlVar.a.j() || rlVar.a.e()))) {
            return rect;
        }
        rect.set(0, 0, 0, 0);
        ArrayList arrayList = this.o;
        int size = arrayList.size();
        for (int i = 0; i < size; i++) {
            Rect rect2 = this.i;
            rect2.set(0, 0, 0, 0);
            ((yb) arrayList.get(i)).getClass();
            ((rl) view.getLayoutParams()).a.getClass();
            rect2.set(0, 0, 0, 0);
            rect.left += rect2.left;
            rect.top += rect2.top;
            rect.right += rect2.right;
            rect.bottom += rect2.bottom;
        }
        rlVar.c = false;
        return rect;
    }

    public final boolean J() {
        return !this.t || this.A || this.e.f();
    }

    public final boolean K() {
        return this.C > 0;
    }

    public final void L() {
        b7 b7Var = this.f;
        int iH = b7Var.h();
        for (int i = 0; i < iH; i++) {
            ((rl) b7Var.g(i).getLayoutParams()).c = true;
        }
        ArrayList arrayList = this.c.c;
        int size = arrayList.size();
        for (int i2 = 0; i2 < size; i2++) {
            rl rlVar = (rl) ((em) arrayList.get(i2)).a.getLayoutParams();
            if (rlVar != null) {
                rlVar.c = true;
            }
        }
    }

    public final void M(int i, int i2, boolean z) {
        int i3 = i + i2;
        b7 b7Var = this.f;
        int iH = b7Var.h();
        for (int i4 = 0; i4 < iH; i4++) {
            em emVarH = H(b7Var.g(i4));
            if (emVarH != null && !emVarH.n()) {
                int i5 = emVarH.c;
                am amVar = this.d0;
                if (i5 >= i3) {
                    if (x0) {
                        Log.d("RecyclerView", "offsetPositionRecordsForRemove attached child " + i4 + " holder " + emVarH + " now at position " + (emVarH.c - i2));
                    }
                    emVarH.k(-i2, z);
                    amVar.e = true;
                } else if (i5 >= i) {
                    if (x0) {
                        Log.d("RecyclerView", "offsetPositionRecordsForRemove attached child " + i4 + " holder " + emVarH + " now REMOVED");
                    }
                    emVarH.a(8);
                    emVarH.k(-i2, z);
                    emVarH.c = i - 1;
                    amVar.e = true;
                }
            }
        }
        wl wlVar = this.c;
        ArrayList arrayList = wlVar.c;
        for (int size = arrayList.size() - 1; size >= 0; size--) {
            em emVar = (em) arrayList.get(size);
            if (emVar != null) {
                int i6 = emVar.c;
                if (i6 >= i3) {
                    if (x0) {
                        Log.d("RecyclerView", "offsetPositionRecordsForRemove cached " + size + " holder " + emVar + " now at position " + (emVar.c - i2));
                    }
                    emVar.k(-i2, z);
                } else if (i6 >= i) {
                    emVar.a(8);
                    wlVar.g(size);
                }
            }
        }
        requestLayout();
    }

    public final void N() {
        this.C++;
    }

    public final void O(boolean z) {
        int i;
        AccessibilityManager accessibilityManager;
        int i2 = this.C - 1;
        this.C = i2;
        if (i2 < 1) {
            if (w0 && i2 < 0) {
                z6.o("layout or scroll counter cannot go below zero.Some calls are not matching".concat(y()));
                return;
            }
            this.C = 0;
            if (z) {
                int i3 = this.y;
                this.y = 0;
                if (i3 != 0 && (accessibilityManager = this.z) != null && accessibilityManager.isEnabled()) {
                    AccessibilityEvent accessibilityEventObtain = AccessibilityEvent.obtain();
                    accessibilityEventObtain.setEventType(2048);
                    accessibilityEventObtain.setContentChangeTypes(i3);
                    sendAccessibilityEventUnchecked(accessibilityEventObtain);
                }
                ArrayList arrayList = this.q0;
                for (int size = arrayList.size() - 1; size >= 0; size--) {
                    em emVar = (em) arrayList.get(size);
                    if (emVar.a.getParent() == this && !emVar.n() && (i = emVar.p) != -1) {
                        View view = emVar.a;
                        WeakHashMap weakHashMap = os.a;
                        view.setImportantForAccessibility(i);
                        emVar.p = -1;
                    }
                }
                arrayList.clear();
            }
        }
    }

    public final void P(MotionEvent motionEvent) {
        int actionIndex = motionEvent.getActionIndex();
        if (motionEvent.getPointerId(actionIndex) == this.L) {
            int i = actionIndex == 0 ? 1 : 0;
            this.L = motionEvent.getPointerId(i);
            int x = (int) (motionEvent.getX(i) + 0.5f);
            this.P = x;
            this.N = x;
            int y = (int) (motionEvent.getY(i) + 0.5f);
            this.Q = y;
            this.O = y;
        }
    }

    public final void Q() {
        if (this.j0 || !this.r) {
            return;
        }
        WeakHashMap weakHashMap = os.a;
        postOnAnimation(this.r0);
        this.j0 = true;
    }

    public final void R(boolean z) {
        this.B = z | this.B;
        this.A = true;
        b7 b7Var = this.f;
        int iH = b7Var.h();
        for (int i = 0; i < iH; i++) {
            em emVarH = H(b7Var.g(i));
            if (emVarH != null && !emVarH.n()) {
                emVarH.a(6);
            }
        }
        L();
        wl wlVar = this.c;
        ArrayList arrayList = wlVar.c;
        int size = arrayList.size();
        for (int i2 = 0; i2 < size; i2++) {
            em emVar = (em) arrayList.get(i2);
            if (emVar != null) {
                emVar.a(6);
                emVar.a(1024);
            }
        }
        wlVar.f();
    }

    public final void S(em emVar, tj tjVar) {
        emVar.i &= -8193;
        boolean z = this.d0.g;
        z3 z3Var = this.g;
        if (z && emVar.j() && !emVar.g() && !emVar.n()) {
            this.l.getClass();
            ((rh) z3Var.c).b(emVar.c, emVar);
        }
        ko koVar = (ko) z3Var.b;
        ts tsVarA = (ts) koVar.getOrDefault(emVar, null);
        if (tsVarA == null) {
            tsVarA = ts.a();
            koVar.put(emVar, tsVarA);
        }
        tsVarA.b = tjVar;
        tsVarA.a |= 4;
    }

    public final int T(int i, float f) {
        float height = f / getHeight();
        float width = i / getWidth();
        EdgeEffect edgeEffect = this.F;
        float f2 = 0.0f;
        if (edgeEffect == null || d.v(edgeEffect) == 0.0f) {
            EdgeEffect edgeEffect2 = this.H;
            if (edgeEffect2 != null && d.v(edgeEffect2) != 0.0f) {
                boolean zCanScrollHorizontally = canScrollHorizontally(1);
                EdgeEffect edgeEffect3 = this.H;
                if (zCanScrollHorizontally) {
                    edgeEffect3.onRelease();
                } else {
                    float fJ = d.J(edgeEffect3, width, height);
                    if (d.v(this.H) == 0.0f) {
                        this.H.onRelease();
                    }
                    f2 = fJ;
                }
                invalidate();
            }
        } else {
            boolean zCanScrollHorizontally2 = canScrollHorizontally(-1);
            EdgeEffect edgeEffect4 = this.F;
            if (zCanScrollHorizontally2) {
                edgeEffect4.onRelease();
            } else {
                float f3 = -d.J(edgeEffect4, -width, 1.0f - height);
                if (d.v(this.F) == 0.0f) {
                    this.F.onRelease();
                }
                f2 = f3;
            }
            invalidate();
        }
        return Math.round(f2 * getWidth());
    }

    public final int U(int i, float f) {
        float width = f / getWidth();
        float height = i / getHeight();
        EdgeEffect edgeEffect = this.G;
        float f2 = 0.0f;
        if (edgeEffect == null || d.v(edgeEffect) == 0.0f) {
            EdgeEffect edgeEffect2 = this.I;
            if (edgeEffect2 != null && d.v(edgeEffect2) != 0.0f) {
                boolean zCanScrollVertically = canScrollVertically(1);
                EdgeEffect edgeEffect3 = this.I;
                if (zCanScrollVertically) {
                    edgeEffect3.onRelease();
                } else {
                    float fJ = d.J(edgeEffect3, height, 1.0f - width);
                    if (d.v(this.I) == 0.0f) {
                        this.I.onRelease();
                    }
                    f2 = fJ;
                }
                invalidate();
            }
        } else {
            boolean zCanScrollVertically2 = canScrollVertically(-1);
            EdgeEffect edgeEffect4 = this.G;
            if (zCanScrollVertically2) {
                edgeEffect4.onRelease();
            } else {
                float f3 = -d.J(edgeEffect4, -height, width);
                if (d.v(this.G) == 0.0f) {
                    this.G.onRelease();
                }
                f2 = f3;
            }
            invalidate();
        }
        return Math.round(f2 * getHeight());
    }

    public final void V(View view, View view2) {
        View view3 = view2 != null ? view2 : view;
        int width = view3.getWidth();
        int height = view3.getHeight();
        Rect rect = this.i;
        rect.set(0, 0, width, height);
        ViewGroup.LayoutParams layoutParams = view3.getLayoutParams();
        if (layoutParams instanceof rl) {
            rl rlVar = (rl) layoutParams;
            if (!rlVar.c) {
                Rect rect2 = rlVar.b;
                rect.left -= rect2.left;
                rect.right += rect2.right;
                rect.top -= rect2.top;
                rect.bottom += rect2.bottom;
            }
        }
        if (view2 != null) {
            offsetDescendantRectToMyCoords(view2, rect);
            offsetRectIntoDescendantCoords(view, rect);
        }
        this.m.g0(this, view, this.i, !this.t, view2 == null);
    }

    public final void W() {
        VelocityTracker velocityTracker = this.M;
        if (velocityTracker != null) {
            velocityTracker.clear();
        }
        boolean zIsFinished = false;
        d0(0);
        EdgeEffect edgeEffect = this.F;
        if (edgeEffect != null) {
            edgeEffect.onRelease();
            zIsFinished = this.F.isFinished();
        }
        EdgeEffect edgeEffect2 = this.G;
        if (edgeEffect2 != null) {
            edgeEffect2.onRelease();
            zIsFinished |= this.G.isFinished();
        }
        EdgeEffect edgeEffect3 = this.H;
        if (edgeEffect3 != null) {
            edgeEffect3.onRelease();
            zIsFinished |= this.H.isFinished();
        }
        EdgeEffect edgeEffect4 = this.I;
        if (edgeEffect4 != null) {
            edgeEffect4.onRelease();
            zIsFinished |= this.I.isFinished();
        }
        if (zIsFinished) {
            WeakHashMap weakHashMap = os.a;
            postInvalidateOnAnimation();
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:31:0x00c9  */
    /* JADX WARN: Removed duplicated region for block: B:33:0x00e1  */
    /* JADX WARN: Removed duplicated region for block: B:37:0x00fe  */
    /* JADX WARN: Removed duplicated region for block: B:41:0x0106  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final boolean X(int r18, int r19, android.view.MotionEvent r20, int r21) {
        /*
            Method dump skipped, instruction units count: 298
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.recyclerview.widget.RecyclerView.X(int, int, android.view.MotionEvent, int):boolean");
    }

    public final void Y(int i, int i2, int[] iArr) {
        em emVar;
        b0();
        N();
        int i3 = lq.a;
        Trace.beginSection("RV Scroll");
        am amVar = this.d0;
        z(amVar);
        wl wlVar = this.c;
        int iI0 = i != 0 ? this.m.i0(i, wlVar, amVar) : 0;
        int iJ0 = i2 != 0 ? this.m.j0(i2, wlVar, amVar) : 0;
        Trace.endSection();
        b7 b7Var = this.f;
        int iE = b7Var.e();
        for (int i4 = 0; i4 < iE; i4++) {
            View viewD = b7Var.d(i4);
            em emVarG = G(viewD);
            if (emVarG != null && (emVar = emVarG.h) != null) {
                View view = emVar.a;
                int left = viewD.getLeft();
                int top = viewD.getTop();
                if (left != view.getLeft() || top != view.getTop()) {
                    view.layout(left, top, view.getWidth() + left, view.getHeight() + top);
                }
            }
        }
        O(true);
        c0(false);
        if (iArr != null) {
            iArr[0] = iI0;
            iArr[1] = iJ0;
        }
    }

    public final boolean Z(EdgeEffect edgeEffect, int i, int i2) {
        if (i > 0) {
            return true;
        }
        float fV = d.v(edgeEffect) * i2;
        float fAbs = Math.abs(-i) * 0.35f;
        float f = this.a * 0.015f;
        double dLog = Math.log(fAbs / f);
        double d = z0;
        return ((float) (Math.exp((d / (d - 1.0d)) * dLog) * ((double) f))) < fV;
    }

    public final void a0(int i, int i2, boolean z) {
        ql qlVar = this.m;
        if (qlVar == null) {
            Log.e("RecyclerView", "Cannot smooth scroll without a LayoutManager set. Call setLayoutManager with a non-null argument.");
            return;
        }
        if (this.w) {
            return;
        }
        int i3 = !qlVar.c() ? 0 : i;
        int i4 = !this.m.d() ? 0 : i2;
        if (i3 == 0 && i4 == 0) {
            return;
        }
        if (z) {
            int i5 = i3 != 0 ? 1 : 0;
            if (i4 != 0) {
                i5 |= 2;
            }
            getScrollingChildHelper().g(i5, 1);
        }
        dm dmVar = this.a0;
        RecyclerView recyclerView = dmVar.g;
        int iAbs = Math.abs(i3);
        int iAbs2 = Math.abs(i4);
        boolean z2 = iAbs > iAbs2;
        int width = z2 ? recyclerView.getWidth() : recyclerView.getHeight();
        if (!z2) {
            iAbs = iAbs2;
        }
        int iMin = Math.min((int) (((iAbs / width) + 1.0f) * 300.0f), 2000);
        Interpolator interpolator = dmVar.d;
        hl hlVar = D0;
        if (interpolator != hlVar) {
            dmVar.d = hlVar;
            dmVar.c = new OverScroller(recyclerView.getContext(), hlVar);
        }
        dmVar.b = 0;
        dmVar.a = 0;
        recyclerView.setScrollState(2);
        dmVar.c.startScroll(0, 0, i3, i4, iMin);
        if (dmVar.e) {
            dmVar.f = true;
            return;
        }
        RecyclerView recyclerView2 = dmVar.g;
        recyclerView2.removeCallbacks(dmVar);
        WeakHashMap weakHashMap = os.a;
        recyclerView2.postOnAnimation(dmVar);
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void addFocusables(ArrayList arrayList, int i, int i2) {
        ql qlVar = this.m;
        if (qlVar != null) {
            qlVar.getClass();
        }
        super.addFocusables(arrayList, i, i2);
    }

    public final void b0() {
        int i = this.u + 1;
        this.u = i;
        if (i != 1 || this.w) {
            return;
        }
        this.v = false;
    }

    public final void c0(boolean z) {
        if (this.u < 1) {
            if (w0) {
                z6.o("stopInterceptRequestLayout was called more times than startInterceptRequestLayout.".concat(y()));
                return;
            }
            this.u = 1;
        }
        if (!z && !this.w) {
            this.v = false;
        }
        if (this.u == 1) {
            if (z && this.v && !this.w && this.m != null && this.l != null) {
                o();
            }
            if (!this.w) {
                this.v = false;
            }
        }
        this.u--;
    }

    @Override // android.view.ViewGroup
    public final boolean checkLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return (layoutParams instanceof rl) && this.m.e((rl) layoutParams);
    }

    @Override // android.view.View
    public final int computeHorizontalScrollExtent() {
        ql qlVar = this.m;
        if (qlVar != null && qlVar.c()) {
            return this.m.i(this.d0);
        }
        return 0;
    }

    @Override // android.view.View
    public final int computeHorizontalScrollOffset() {
        ql qlVar = this.m;
        if (qlVar != null && qlVar.c()) {
            return this.m.j(this.d0);
        }
        return 0;
    }

    @Override // android.view.View
    public final int computeHorizontalScrollRange() {
        ql qlVar = this.m;
        if (qlVar != null && qlVar.c()) {
            return this.m.k(this.d0);
        }
        return 0;
    }

    @Override // android.view.View
    public final int computeVerticalScrollExtent() {
        ql qlVar = this.m;
        if (qlVar != null && qlVar.d()) {
            return this.m.l(this.d0);
        }
        return 0;
    }

    @Override // android.view.View
    public final int computeVerticalScrollOffset() {
        ql qlVar = this.m;
        if (qlVar != null && qlVar.d()) {
            return this.m.m(this.d0);
        }
        return 0;
    }

    @Override // android.view.View
    public final int computeVerticalScrollRange() {
        ql qlVar = this.m;
        if (qlVar != null && qlVar.d()) {
            return this.m.n(this.d0);
        }
        return 0;
    }

    public final void d0(int i) {
        getScrollingChildHelper().h(i);
    }

    @Override // android.view.View
    public final boolean dispatchNestedFling(float f, float f2, boolean z) {
        return getScrollingChildHelper().a(f, f2, z);
    }

    @Override // android.view.View
    public final boolean dispatchNestedPreFling(float f, float f2) {
        return getScrollingChildHelper().b(f, f2);
    }

    @Override // android.view.View
    public final boolean dispatchNestedPreScroll(int i, int i2, int[] iArr, int[] iArr2) {
        return getScrollingChildHelper().c(i, i2, iArr, iArr2, 0);
    }

    @Override // android.view.View
    public final boolean dispatchNestedScroll(int i, int i2, int i3, int i4, int[] iArr) {
        return getScrollingChildHelper().d(i, i2, i3, i4, iArr, 0, null);
    }

    @Override // android.view.View
    public final boolean dispatchPopulateAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
        onPopulateAccessibilityEvent(accessibilityEvent);
        return true;
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void dispatchRestoreInstanceState(SparseArray sparseArray) {
        dispatchThawSelfOnly(sparseArray);
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void dispatchSaveInstanceState(SparseArray sparseArray) {
        dispatchFreezeSelfOnly(sparseArray);
    }

    @Override // android.view.View
    public final void draw(Canvas canvas) {
        boolean z;
        super.draw(canvas);
        ArrayList arrayList = this.o;
        int size = arrayList.size();
        boolean z2 = false;
        int i = 0;
        while (true) {
            if (i >= size) {
                break;
            }
            yb ybVar = (yb) arrayList.get(i);
            if (ybVar.q != ybVar.s.getWidth() || ybVar.r != ybVar.s.getHeight()) {
                ybVar.q = ybVar.s.getWidth();
                ybVar.r = ybVar.s.getHeight();
                ybVar.d(0);
            } else if (ybVar.A != 0) {
                if (ybVar.t) {
                    int i2 = ybVar.q;
                    int i3 = ybVar.e;
                    int i4 = i2 - i3;
                    int i5 = ybVar.l;
                    int i6 = ybVar.k;
                    int i7 = i5 - (i6 / 2);
                    StateListDrawable stateListDrawable = ybVar.c;
                    stateListDrawable.setBounds(0, 0, i3, i6);
                    Drawable drawable = ybVar.d;
                    drawable.setBounds(0, 0, ybVar.f, ybVar.r);
                    RecyclerView recyclerView = ybVar.s;
                    WeakHashMap weakHashMap = os.a;
                    if (recyclerView.getLayoutDirection() == 1) {
                        drawable.draw(canvas);
                        canvas.translate(i3, i7);
                        canvas.scale(-1.0f, 1.0f);
                        stateListDrawable.draw(canvas);
                        canvas.scale(-1.0f, 1.0f);
                        canvas.translate(-i3, -i7);
                    } else {
                        canvas.translate(i4, 0.0f);
                        drawable.draw(canvas);
                        canvas.translate(0.0f, i7);
                        stateListDrawable.draw(canvas);
                        canvas.translate(-i4, -i7);
                    }
                }
                if (ybVar.u) {
                    int i8 = ybVar.r;
                    int i9 = ybVar.i;
                    int i10 = i8 - i9;
                    int i11 = ybVar.o;
                    int i12 = ybVar.n;
                    StateListDrawable stateListDrawable2 = ybVar.g;
                    stateListDrawable2.setBounds(0, 0, i12, i9);
                    Drawable drawable2 = ybVar.h;
                    drawable2.setBounds(0, 0, ybVar.q, ybVar.j);
                    canvas.translate(0.0f, i10);
                    drawable2.draw(canvas);
                    canvas.translate(i11 - (i12 / 2), 0.0f);
                    stateListDrawable2.draw(canvas);
                    canvas.translate(-r8, -i10);
                }
            }
            i++;
        }
        EdgeEffect edgeEffect = this.F;
        if (edgeEffect == null || edgeEffect.isFinished()) {
            z = false;
        } else {
            int iSave = canvas.save();
            int paddingBottom = this.h ? getPaddingBottom() : 0;
            canvas.rotate(270.0f);
            canvas.translate((-getHeight()) + paddingBottom, 0.0f);
            EdgeEffect edgeEffect2 = this.F;
            z = edgeEffect2 != null && edgeEffect2.draw(canvas);
            canvas.restoreToCount(iSave);
        }
        EdgeEffect edgeEffect3 = this.G;
        if (edgeEffect3 != null && !edgeEffect3.isFinished()) {
            int iSave2 = canvas.save();
            if (this.h) {
                canvas.translate(getPaddingLeft(), getPaddingTop());
            }
            EdgeEffect edgeEffect4 = this.G;
            z |= edgeEffect4 != null && edgeEffect4.draw(canvas);
            canvas.restoreToCount(iSave2);
        }
        EdgeEffect edgeEffect5 = this.H;
        if (edgeEffect5 != null && !edgeEffect5.isFinished()) {
            int iSave3 = canvas.save();
            int width = getWidth();
            int paddingTop = this.h ? getPaddingTop() : 0;
            canvas.rotate(90.0f);
            canvas.translate(paddingTop, -width);
            EdgeEffect edgeEffect6 = this.H;
            z |= edgeEffect6 != null && edgeEffect6.draw(canvas);
            canvas.restoreToCount(iSave3);
        }
        EdgeEffect edgeEffect7 = this.I;
        if (edgeEffect7 != null && !edgeEffect7.isFinished()) {
            int iSave4 = canvas.save();
            canvas.rotate(180.0f);
            if (this.h) {
                canvas.translate(getPaddingRight() + (-getWidth()), getPaddingBottom() + (-getHeight()));
            } else {
                canvas.translate(-getWidth(), -getHeight());
            }
            EdgeEffect edgeEffect8 = this.I;
            if (edgeEffect8 != null && edgeEffect8.draw(canvas)) {
                z2 = true;
            }
            z |= z2;
            canvas.restoreToCount(iSave4);
        }
        if ((z || this.J == null || arrayList.size() <= 0 || !this.J.f()) ? z : true) {
            WeakHashMap weakHashMap2 = os.a;
            postInvalidateOnAnimation();
        }
    }

    @Override // android.view.ViewGroup
    public final boolean drawChild(Canvas canvas, View view, long j) {
        return super.drawChild(canvas, view, j);
    }

    /* JADX WARN: Removed duplicated region for block: B:118:0x016b  */
    /* JADX WARN: Removed duplicated region for block: B:137:0x01a2 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:138:0x01a3  */
    /* JADX WARN: Removed duplicated region for block: B:30:0x0063  */
    /* JADX WARN: Removed duplicated region for block: B:31:0x0065  */
    /* JADX WARN: Removed duplicated region for block: B:33:0x0068  */
    /* JADX WARN: Removed duplicated region for block: B:34:0x006a  */
    /* JADX WARN: Removed duplicated region for block: B:37:0x006e  */
    /* JADX WARN: Removed duplicated region for block: B:38:0x0071  */
    /* JADX WARN: Removed duplicated region for block: B:41:0x0078  */
    /* JADX WARN: Removed duplicated region for block: B:42:0x007a  */
    /* JADX WARN: Removed duplicated region for block: B:44:0x007d  */
    /* JADX WARN: Removed duplicated region for block: B:58:0x00b9  */
    /* JADX WARN: Removed duplicated region for block: B:67:0x00d0 A[ADDED_TO_REGION] */
    @Override // android.view.ViewGroup, android.view.ViewParent
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final android.view.View focusSearch(android.view.View r17, int r18) {
        /*
            Method dump skipped, instruction units count: 424
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.recyclerview.widget.RecyclerView.focusSearch(android.view.View, int):android.view.View");
    }

    public final void g(em emVar) {
        View view = emVar.a;
        boolean z = view.getParent() == this;
        this.c.l(G(view));
        boolean zI = emVar.i();
        b7 b7Var = this.f;
        if (zI) {
            b7Var.b(view, -1, view.getLayoutParams(), true);
            return;
        }
        if (!z) {
            b7Var.a(view, -1, true);
            return;
        }
        int iIndexOfChild = b7Var.a.a.indexOfChild(view);
        if (iIndexOfChild < 0) {
            z6.n(view, "view is not a child, cannot hide ");
        } else {
            b7Var.b.h(iIndexOfChild);
            b7Var.i(view);
        }
    }

    @Override // android.view.ViewGroup
    public final ViewGroup.LayoutParams generateDefaultLayoutParams() {
        ql qlVar = this.m;
        if (qlVar != null) {
            return qlVar.q();
        }
        z6.o("RecyclerView has no LayoutManager".concat(y()));
        return null;
    }

    @Override // android.view.ViewGroup
    public final ViewGroup.LayoutParams generateLayoutParams(AttributeSet attributeSet) {
        ql qlVar = this.m;
        if (qlVar != null) {
            return qlVar.r(getContext(), attributeSet);
        }
        z6.o("RecyclerView has no LayoutManager".concat(y()));
        return null;
    }

    @Override // android.view.ViewGroup, android.view.View
    public CharSequence getAccessibilityClassName() {
        return "androidx.recyclerview.widget.RecyclerView";
    }

    public jl getAdapter() {
        return this.l;
    }

    @Override // android.view.View
    public int getBaseline() {
        ql qlVar = this.m;
        if (qlVar == null) {
            return super.getBaseline();
        }
        qlVar.getClass();
        return -1;
    }

    @Override // android.view.ViewGroup
    public final int getChildDrawingOrder(int i, int i2) {
        return super.getChildDrawingOrder(i, i2);
    }

    @Override // android.view.ViewGroup
    public boolean getClipToPadding() {
        return this.h;
    }

    public gm getCompatAccessibilityDelegate() {
        return this.k0;
    }

    public ml getEdgeEffectFactory() {
        return this.E;
    }

    public nl getItemAnimator() {
        return this.J;
    }

    public int getItemDecorationCount() {
        return this.o.size();
    }

    public ql getLayoutManager() {
        return this.m;
    }

    public int getMaxFlingVelocity() {
        return this.T;
    }

    public int getMinFlingVelocity() {
        return this.S;
    }

    public long getNanoTime() {
        if (B0) {
            return System.nanoTime();
        }
        return 0L;
    }

    public sl getOnFlingListener() {
        return null;
    }

    public boolean getPreserveFocusAfterLayout() {
        return this.W;
    }

    public vl getRecycledViewPool() {
        return this.c.c();
    }

    public int getScrollState() {
        return this.K;
    }

    public final void h(String str) {
        if (!K()) {
            if (this.D > 0) {
                Log.w("RecyclerView", "Cannot call this method in a scroll callback. Scroll callbacks mightbe run during a measure & layout pass where you cannot change theRecyclerView data. Any method call that might change the structureof the RecyclerView or the adapter contents should be postponed tothe next frame.", new IllegalStateException(y()));
            }
        } else if (str == null) {
            z6.o("Cannot call this method while RecyclerView is computing a layout or scrolling".concat(y()));
        } else {
            z6.o(str);
        }
    }

    @Override // android.view.View
    public final boolean hasNestedScrollingParent() {
        return getScrollingChildHelper().f(0);
    }

    @Override // android.view.View
    public final boolean isAttachedToWindow() {
        return this.r;
    }

    @Override // android.view.ViewGroup
    public final boolean isLayoutSuppressed() {
        return this.w;
    }

    @Override // android.view.View
    public final boolean isNestedScrollingEnabled() {
        return getScrollingChildHelper().d;
    }

    public final void j() {
        b7 b7Var = this.f;
        int iH = b7Var.h();
        for (int i = 0; i < iH; i++) {
            em emVarH = H(b7Var.g(i));
            if (!emVarH.n()) {
                emVarH.d = -1;
                emVarH.f = -1;
            }
        }
        wl wlVar = this.c;
        ArrayList arrayList = wlVar.a;
        ArrayList arrayList2 = wlVar.c;
        int size = arrayList2.size();
        for (int i2 = 0; i2 < size; i2++) {
            em emVar = (em) arrayList2.get(i2);
            emVar.d = -1;
            emVar.f = -1;
        }
        int size2 = arrayList.size();
        for (int i3 = 0; i3 < size2; i3++) {
            em emVar2 = (em) arrayList.get(i3);
            emVar2.d = -1;
            emVar2.f = -1;
        }
        ArrayList arrayList3 = wlVar.b;
        if (arrayList3 != null) {
            int size3 = arrayList3.size();
            for (int i4 = 0; i4 < size3; i4++) {
                em emVar3 = (em) wlVar.b.get(i4);
                emVar3.d = -1;
                emVar3.f = -1;
            }
        }
    }

    public final void k(int i, int i2) {
        boolean zIsFinished;
        EdgeEffect edgeEffect = this.F;
        if (edgeEffect == null || edgeEffect.isFinished() || i <= 0) {
            zIsFinished = false;
        } else {
            this.F.onRelease();
            zIsFinished = this.F.isFinished();
        }
        EdgeEffect edgeEffect2 = this.H;
        if (edgeEffect2 != null && !edgeEffect2.isFinished() && i < 0) {
            this.H.onRelease();
            zIsFinished |= this.H.isFinished();
        }
        EdgeEffect edgeEffect3 = this.G;
        if (edgeEffect3 != null && !edgeEffect3.isFinished() && i2 > 0) {
            this.G.onRelease();
            zIsFinished |= this.G.isFinished();
        }
        EdgeEffect edgeEffect4 = this.I;
        if (edgeEffect4 != null && !edgeEffect4.isFinished() && i2 < 0) {
            this.I.onRelease();
            zIsFinished |= this.I.isFinished();
        }
        if (zIsFinished) {
            WeakHashMap weakHashMap = os.a;
            postInvalidateOnAnimation();
        }
    }

    public final void m() {
        if (!this.t || this.A) {
            int i = lq.a;
            Trace.beginSection("RV FullInvalidate");
            o();
            Trace.endSection();
            return;
        }
        a2 a2Var = this.e;
        if (a2Var.f()) {
            a2Var.getClass();
            if (a2Var.f()) {
                int i2 = lq.a;
                Trace.beginSection("RV FullInvalidate");
                o();
                Trace.endSection();
            }
        }
    }

    public final void n(int i, int i2) {
        int paddingRight = getPaddingRight() + getPaddingLeft();
        WeakHashMap weakHashMap = os.a;
        setMeasuredDimension(ql.f(i, paddingRight, getMinimumWidth()), ql.f(i2, getPaddingBottom() + getPaddingTop(), getMinimumHeight()));
    }

    /* JADX WARN: Removed duplicated region for block: B:123:0x0286  */
    /* JADX WARN: Removed duplicated region for block: B:168:0x036a  */
    /* JADX WARN: Removed duplicated region for block: B:197:0x03bd  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void o() {
        /*
            Method dump skipped, instruction units count: 987
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.recyclerview.widget.RecyclerView.o():void");
    }

    /* JADX WARN: Removed duplicated region for block: B:21:0x0063  */
    @Override // android.view.ViewGroup, android.view.View
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void onAttachedToWindow() {
        /*
            r5 = this;
            super.onAttachedToWindow()
            r0 = 0
            r5.C = r0
            r1 = 1
            r5.r = r1
            boolean r2 = r5.t
            if (r2 == 0) goto L15
            boolean r2 = r5.isLayoutRequested()
            if (r2 != 0) goto L15
            r2 = r1
            goto L16
        L15:
            r2 = r0
        L16:
            r5.t = r2
            wl r2 = r5.c
            r2.d()
            ql r2 = r5.m
            if (r2 == 0) goto L23
            r2.f = r1
        L23:
            r5.j0 = r0
            boolean r0 = androidx.recyclerview.widget.RecyclerView.B0
            if (r0 == 0) goto L89
            java.lang.ThreadLocal r0 = defpackage.ne.e
            java.lang.Object r1 = r0.get()
            ne r1 = (defpackage.ne) r1
            r5.b0 = r1
            if (r1 != 0) goto L71
            ne r1 = new ne
            r1.<init>()
            java.util.ArrayList r2 = new java.util.ArrayList
            r2.<init>()
            r1.a = r2
            java.util.ArrayList r2 = new java.util.ArrayList
            r2.<init>()
            r1.d = r2
            r5.b0 = r1
            java.util.WeakHashMap r1 = defpackage.os.a
            android.view.Display r1 = r5.getDisplay()
            boolean r2 = r5.isInEditMode()
            if (r2 != 0) goto L63
            if (r1 == 0) goto L63
            float r1 = r1.getRefreshRate()
            r2 = 1106247680(0x41f00000, float:30.0)
            int r2 = (r1 > r2 ? 1 : (r1 == r2 ? 0 : -1))
            if (r2 < 0) goto L63
            goto L65
        L63:
            r1 = 1114636288(0x42700000, float:60.0)
        L65:
            ne r2 = r5.b0
            r3 = 1315859240(0x4e6e6b28, float:1.0E9)
            float r3 = r3 / r1
            long r3 = (long) r3
            r2.c = r3
            r0.set(r2)
        L71:
            ne r0 = r5.b0
            java.util.ArrayList r0 = r0.a
            boolean r1 = androidx.recyclerview.widget.RecyclerView.w0
            if (r1 == 0) goto L86
            boolean r1 = r0.contains(r5)
            if (r1 != 0) goto L80
            goto L86
        L80:
            java.lang.String r5 = "RecyclerView already present in worker list!"
            defpackage.z6.o(r5)
            return
        L86:
            r0.add(r5)
        L89:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.recyclerview.widget.RecyclerView.onAttachedToWindow():void");
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void onDetachedFromWindow() {
        ne neVar;
        super.onDetachedFromWindow();
        nl nlVar = this.J;
        if (nlVar != null) {
            nlVar.e();
        }
        int i = 0;
        setScrollState(0);
        dm dmVar = this.a0;
        dmVar.g.removeCallbacks(dmVar);
        dmVar.c.abortAnimation();
        this.r = false;
        ql qlVar = this.m;
        if (qlVar != null) {
            qlVar.f = false;
            qlVar.M(this);
        }
        this.q0.clear();
        removeCallbacks(this.r0);
        this.g.getClass();
        while (ts.d.a() != null) {
        }
        wl wlVar = this.c;
        ArrayList arrayList = wlVar.c;
        for (int i2 = 0; i2 < arrayList.size(); i2++) {
            hb.h(((em) arrayList.get(i2)).a);
        }
        wlVar.e(wlVar.h.l, false);
        while (i < getChildCount()) {
            int i3 = i + 1;
            View childAt = getChildAt(i);
            if (childAt == null) {
                throw new IndexOutOfBoundsException();
            }
            lk lkVar = (lk) childAt.getTag(com.example.LegoClicker.R.id.pooling_container_listener_holder_tag);
            if (lkVar == null) {
                lkVar = new lk();
                childAt.setTag(com.example.LegoClicker.R.id.pooling_container_listener_holder_tag, lkVar);
            }
            ArrayList arrayList2 = lkVar.a;
            arrayList2.getClass();
            int size = arrayList2.size() - 1;
            if (-1 < size) {
                arrayList2.get(size).getClass();
                z6.a();
                return;
            }
            i = i3;
        }
        if (!B0 || (neVar = this.b0) == null) {
            return;
        }
        boolean zRemove = neVar.a.remove(this);
        if (!w0 || zRemove) {
            this.b0 = null;
        } else {
            z6.o("RecyclerView removal failed!");
        }
    }

    @Override // android.view.View
    public final void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        ArrayList arrayList = this.o;
        int size = arrayList.size();
        for (int i = 0; i < size; i++) {
            ((yb) arrayList.get(i)).getClass();
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:28:0x0064  */
    /* JADX WARN: Removed duplicated region for block: B:31:0x006a  */
    /* JADX WARN: Removed duplicated region for block: B:33:0x006e  */
    /* JADX WARN: Removed duplicated region for block: B:35:0x007a  */
    /* JADX WARN: Removed duplicated region for block: B:37:0x0082  */
    @Override // android.view.View
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final boolean onGenericMotionEvent(android.view.MotionEvent r14) {
        /*
            Method dump skipped, instruction units count: 242
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.recyclerview.widget.RecyclerView.onGenericMotionEvent(android.view.MotionEvent):boolean");
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // android.view.ViewGroup
    public final boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        boolean z;
        boolean z2;
        if (!this.w) {
            this.q = null;
            if (B(motionEvent)) {
                W();
                setScrollState(0);
                return true;
            }
            ql qlVar = this.m;
            if (qlVar != null) {
                boolean zC = qlVar.c();
                boolean zD = this.m.d();
                if (this.M == null) {
                    this.M = VelocityTracker.obtain();
                }
                this.M.addMovement(motionEvent);
                int actionMasked = motionEvent.getActionMasked();
                int actionIndex = motionEvent.getActionIndex();
                if (actionMasked == 0) {
                    if (this.x) {
                        this.x = false;
                    }
                    this.L = motionEvent.getPointerId(0);
                    int x = (int) (motionEvent.getX() + 0.5f);
                    this.P = x;
                    this.N = x;
                    int y = (int) (motionEvent.getY() + 0.5f);
                    this.Q = y;
                    this.O = y;
                    EdgeEffect edgeEffect = this.F;
                    if (edgeEffect == null || d.v(edgeEffect) == 0.0f || canScrollHorizontally(-1)) {
                        z = false;
                    } else {
                        d.J(this.F, 0.0f, 1.0f - (motionEvent.getY() / getHeight()));
                        z = true;
                    }
                    EdgeEffect edgeEffect2 = this.H;
                    boolean z3 = z;
                    if (edgeEffect2 != null) {
                        z3 = z;
                        if (d.v(edgeEffect2) != 0.0f) {
                            z3 = z;
                            if (!canScrollHorizontally(1)) {
                                d.J(this.H, 0.0f, motionEvent.getY() / getHeight());
                                z3 = true;
                            }
                        }
                    }
                    EdgeEffect edgeEffect3 = this.G;
                    boolean z4 = z3;
                    if (edgeEffect3 != null) {
                        z4 = z3;
                        if (d.v(edgeEffect3) != 0.0f) {
                            z4 = z3;
                            if (!canScrollVertically(-1)) {
                                d.J(this.G, 0.0f, motionEvent.getX() / getWidth());
                                z4 = true;
                            }
                        }
                    }
                    EdgeEffect edgeEffect4 = this.I;
                    boolean z5 = z4;
                    if (edgeEffect4 != null) {
                        z5 = z4;
                        if (d.v(edgeEffect4) != 0.0f) {
                            z5 = z4;
                            if (!canScrollVertically(1)) {
                                d.J(this.I, 0.0f, 1.0f - (motionEvent.getX() / getWidth()));
                                z5 = true;
                            }
                        }
                    }
                    if (z5 || this.K == 2) {
                        getParent().requestDisallowInterceptTouchEvent(true);
                        setScrollState(1);
                        d0(1);
                    }
                    int[] iArr = this.o0;
                    iArr[1] = 0;
                    iArr[0] = 0;
                    int i = zC;
                    if (zD) {
                        i = (zC ? 1 : 0) | 2;
                    }
                    getScrollingChildHelper().g(i, 0);
                } else if (actionMasked == 1) {
                    this.M.clear();
                    d0(0);
                } else if (actionMasked == 2) {
                    int iFindPointerIndex = motionEvent.findPointerIndex(this.L);
                    if (iFindPointerIndex < 0) {
                        Log.e("RecyclerView", "Error processing scroll; pointer index for id " + this.L + " not found. Did any MotionEvents get skipped?");
                        return false;
                    }
                    int x2 = (int) (motionEvent.getX(iFindPointerIndex) + 0.5f);
                    int y2 = (int) (motionEvent.getY(iFindPointerIndex) + 0.5f);
                    if (this.K != 1) {
                        int i2 = x2 - this.N;
                        int i3 = y2 - this.O;
                        if (!zC || Math.abs(i2) <= this.R) {
                            z2 = false;
                        } else {
                            this.P = x2;
                            z2 = true;
                        }
                        if (zD && Math.abs(i3) > this.R) {
                            this.Q = y2;
                            z2 = true;
                        }
                        if (z2) {
                            setScrollState(1);
                        }
                    }
                } else if (actionMasked == 3) {
                    W();
                    setScrollState(0);
                } else if (actionMasked == 5) {
                    this.L = motionEvent.getPointerId(actionIndex);
                    int x3 = (int) (motionEvent.getX(actionIndex) + 0.5f);
                    this.P = x3;
                    this.N = x3;
                    int y3 = (int) (motionEvent.getY(actionIndex) + 0.5f);
                    this.Q = y3;
                    this.O = y3;
                } else if (actionMasked == 6) {
                    P(motionEvent);
                }
                if (this.K == 1) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void onLayout(boolean z, int i, int i2, int i3, int i4) {
        int i5 = lq.a;
        Trace.beginSection("RV OnLayout");
        o();
        Trace.endSection();
        this.t = true;
    }

    @Override // android.view.View
    public final void onMeasure(int i, int i2) {
        ql qlVar = this.m;
        if (qlVar == null) {
            n(i, i2);
            return;
        }
        boolean zG = qlVar.G();
        boolean z = false;
        am amVar = this.d0;
        if (!zG) {
            if (this.s) {
                this.m.b.n(i, i2);
                return;
            }
            if (amVar.j) {
                setMeasuredDimension(getMeasuredWidth(), getMeasuredHeight());
                return;
            }
            jl jlVar = this.l;
            if (jlVar != null) {
                amVar.d = jlVar.a();
            } else {
                amVar.d = 0;
            }
            b0();
            this.m.b.n(i, i2);
            c0(false);
            amVar.f = false;
            return;
        }
        int mode = View.MeasureSpec.getMode(i);
        int mode2 = View.MeasureSpec.getMode(i2);
        this.m.b.n(i, i2);
        if (mode == 1073741824 && mode2 == 1073741824) {
            z = true;
        }
        this.s0 = z;
        if (z || this.l == null) {
            return;
        }
        if (amVar.c == 1) {
            p();
        }
        this.m.l0(i, i2);
        amVar.h = true;
        q();
        this.m.n0(i, i2);
        if (this.m.q0()) {
            this.m.l0(View.MeasureSpec.makeMeasureSpec(getMeasuredWidth(), 1073741824), View.MeasureSpec.makeMeasureSpec(getMeasuredHeight(), 1073741824));
            amVar.h = true;
            q();
            this.m.n0(i, i2);
        }
        this.t0 = getMeasuredWidth();
        this.u0 = getMeasuredHeight();
    }

    @Override // android.view.ViewGroup
    public final boolean onRequestFocusInDescendants(int i, Rect rect) {
        if (K()) {
            return false;
        }
        return super.onRequestFocusInDescendants(i, rect);
    }

    @Override // android.view.View
    public final void onRestoreInstanceState(Parcelable parcelable) {
        if (!(parcelable instanceof zl)) {
            super.onRestoreInstanceState(parcelable);
            return;
        }
        zl zlVar = (zl) parcelable;
        this.d = zlVar;
        super.onRestoreInstanceState(zlVar.a);
        requestLayout();
    }

    @Override // android.view.View
    public final Parcelable onSaveInstanceState() {
        zl zlVar = new zl(super.onSaveInstanceState());
        zl zlVar2 = this.d;
        if (zlVar2 != null) {
            zlVar.c = zlVar2.c;
            return zlVar;
        }
        ql qlVar = this.m;
        if (qlVar != null) {
            zlVar.c = qlVar.a0();
            return zlVar;
        }
        zlVar.c = null;
        return zlVar;
    }

    @Override // android.view.View
    public final void onSizeChanged(int i, int i2, int i3, int i4) {
        super.onSizeChanged(i, i2, i3, i4);
        if (i == i3 && i2 == i4) {
            return;
        }
        this.I = null;
        this.G = null;
        this.H = null;
        this.F = null;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:182:0x0342  */
    /* JADX WARN: Removed duplicated region for block: B:198:0x0381  */
    /* JADX WARN: Removed duplicated region for block: B:201:0x0387 A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:202:0x0389  */
    /* JADX WARN: Removed duplicated region for block: B:208:0x03a6  */
    /* JADX WARN: Removed duplicated region for block: B:210:0x03ae  */
    /* JADX WARN: Removed duplicated region for block: B:97:0x01f5 A[PHI: r1
      0x01f5: PHI (r1v58 int) = (r1v42 int), (r1v62 int) binds: [B:90:0x01e0, B:95:0x01f1] A[DONT_GENERATE, DONT_INLINE]] */
    @Override // android.view.View
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final boolean onTouchEvent(android.view.MotionEvent r19) {
        /*
            Method dump skipped, instruction units count: 1041
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.recyclerview.widget.RecyclerView.onTouchEvent(android.view.MotionEvent):boolean");
    }

    /* JADX WARN: Removed duplicated region for block: B:255:0x03bf  */
    /* JADX WARN: Removed duplicated region for block: B:359:0x024d A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:48:0x00f4  */
    /* JADX WARN: Removed duplicated region for block: B:50:0x00fb  */
    /* JADX WARN: Removed duplicated region for block: B:56:0x011b  */
    /* JADX WARN: Removed duplicated region for block: B:57:0x011f  */
    /* JADX WARN: Removed duplicated region for block: B:59:0x0127  */
    /* JADX WARN: Removed duplicated region for block: B:61:0x012c  */
    /* JADX WARN: Removed duplicated region for block: B:92:0x01a1  */
    /* JADX WARN: Removed duplicated region for block: B:93:0x01ac  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void p() {
        /*
            Method dump skipped, instruction units count: 1414
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.recyclerview.widget.RecyclerView.p():void");
    }

    public final void q() {
        b0();
        N();
        am amVar = this.d0;
        amVar.a(6);
        this.e.b();
        amVar.d = this.l.a();
        amVar.b = 0;
        if (this.d != null) {
            jl jlVar = this.l;
            int iC = lo.c(jlVar.b);
            if (iC == 1 ? jlVar.a() > 0 : iC != 2) {
                Parcelable parcelable = this.d.c;
                if (parcelable != null) {
                    this.m.Z(parcelable);
                }
                this.d = null;
            }
        }
        amVar.f = false;
        this.m.X(this.c, amVar);
        amVar.e = false;
        amVar.i = amVar.i && this.J != null;
        amVar.c = 4;
        O(true);
        c0(false);
    }

    public final boolean r(int i, int i2, int[] iArr, int[] iArr2, int i3) {
        return getScrollingChildHelper().c(i, i2, iArr, iArr2, i3);
    }

    @Override // android.view.ViewGroup
    public final void removeDetachedView(View view, boolean z) {
        em emVarH = H(view);
        if (emVarH != null) {
            if (emVarH.i()) {
                emVarH.i &= -257;
            } else if (!emVarH.n()) {
                StringBuilder sb = new StringBuilder("Called removeDetachedView with a view which is not flagged as tmp detached.");
                sb.append(emVarH);
                z6.k(sb, y());
                return;
            }
        } else if (w0) {
            StringBuilder sb2 = new StringBuilder("No ViewHolder found for child: ");
            sb2.append(view);
            z6.k(sb2, y());
            return;
        }
        view.clearAnimation();
        H(view);
        jl jlVar = this.l;
        super.removeDetachedView(view, z);
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public final void requestChildFocus(View view, View view2) {
        this.m.getClass();
        if (!K() && view2 != null) {
            V(view, view2);
        }
        super.requestChildFocus(view, view2);
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public final boolean requestChildRectangleOnScreen(View view, Rect rect, boolean z) {
        return this.m.g0(this, view, rect, z, false);
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public final void requestDisallowInterceptTouchEvent(boolean z) {
        ArrayList arrayList = this.p;
        int size = arrayList.size();
        for (int i = 0; i < size; i++) {
            ((yb) arrayList.get(i)).getClass();
        }
        super.requestDisallowInterceptTouchEvent(z);
    }

    @Override // android.view.View, android.view.ViewParent
    public final void requestLayout() {
        if (this.u != 0 || this.w) {
            this.v = true;
        } else {
            super.requestLayout();
        }
    }

    public final void s(int i, int i2, int i3, int i4, int[] iArr, int i5, int[] iArr2) {
        getScrollingChildHelper().d(i, i2, i3, i4, iArr, i5, iArr2);
    }

    @Override // android.view.View
    public final void scrollBy(int i, int i2) {
        ql qlVar = this.m;
        if (qlVar == null) {
            Log.e("RecyclerView", "Cannot scroll without a LayoutManager set. Call setLayoutManager with a non-null argument.");
            return;
        }
        if (this.w) {
            return;
        }
        boolean zC = qlVar.c();
        boolean zD = this.m.d();
        if (zC || zD) {
            if (!zC) {
                i = 0;
            }
            if (!zD) {
                i2 = 0;
            }
            X(i, i2, null, 0);
        }
    }

    @Override // android.view.View
    public final void scrollTo(int i, int i2) {
        Log.w("RecyclerView", "RecyclerView does not support scrolling to an absolute position. Use scrollToPosition instead");
    }

    @Override // android.view.View, android.view.accessibility.AccessibilityEventSource
    public final void sendAccessibilityEventUnchecked(AccessibilityEvent accessibilityEvent) {
        if (!K()) {
            super.sendAccessibilityEventUnchecked(accessibilityEvent);
        } else {
            int contentChangeTypes = accessibilityEvent != null ? accessibilityEvent.getContentChangeTypes() : 0;
            this.y |= contentChangeTypes != 0 ? contentChangeTypes : 0;
        }
    }

    public void setAccessibilityDelegateCompat(gm gmVar) {
        this.k0 = gmVar;
        os.h(this, gmVar);
    }

    public void setAdapter(jl jlVar) {
        setLayoutFrozen(false);
        jl jlVar2 = this.l;
        yl ylVar = this.b;
        if (jlVar2 != null) {
            jlVar2.a.unregisterObserver(ylVar);
            this.l.getClass();
        }
        nl nlVar = this.J;
        if (nlVar != null) {
            nlVar.e();
        }
        ql qlVar = this.m;
        wl wlVar = this.c;
        if (qlVar != null) {
            qlVar.c0(wlVar);
            this.m.d0(wlVar);
        }
        wlVar.a.clear();
        wlVar.f();
        a2 a2Var = this.e;
        a2Var.i((ArrayList) a2Var.c);
        a2Var.i((ArrayList) a2Var.d);
        jl jlVar3 = this.l;
        this.l = jlVar;
        if (jlVar != null) {
            jlVar.a.registerObserver(ylVar);
        }
        ql qlVar2 = this.m;
        if (qlVar2 != null) {
            qlVar2.L();
        }
        jl jlVar4 = this.l;
        wlVar.a.clear();
        wlVar.f();
        wlVar.e(jlVar3, true);
        vl vlVarC = wlVar.c();
        if (jlVar3 != null) {
            vlVarC.b--;
        }
        if (vlVarC.b == 0) {
            SparseArray sparseArray = vlVarC.a;
            for (int i = 0; i < sparseArray.size(); i++) {
                ul ulVar = (ul) sparseArray.valueAt(i);
                ArrayList arrayList = ulVar.a;
                int size = arrayList.size();
                int i2 = 0;
                while (i2 < size) {
                    Object obj = arrayList.get(i2);
                    i2++;
                    hb.h(((em) obj).a);
                }
                ulVar.a.clear();
            }
        }
        if (jlVar4 != null) {
            vlVarC.b++;
        }
        wlVar.d();
        this.d0.e = true;
        R(false);
        requestLayout();
    }

    public void setChildDrawingOrderCallback(ll llVar) {
        if (llVar == null) {
            return;
        }
        setChildrenDrawingOrderEnabled(false);
    }

    @Override // android.view.ViewGroup
    public void setClipToPadding(boolean z) {
        if (z != this.h) {
            this.I = null;
            this.G = null;
            this.H = null;
            this.F = null;
        }
        this.h = z;
        super.setClipToPadding(z);
        if (this.t) {
            requestLayout();
        }
    }

    public void setEdgeEffectFactory(ml mlVar) {
        mlVar.getClass();
        this.E = mlVar;
        this.I = null;
        this.G = null;
        this.H = null;
        this.F = null;
    }

    public void setHasFixedSize(boolean z) {
        this.s = z;
    }

    public void setItemAnimator(nl nlVar) {
        nl nlVar2 = this.J;
        if (nlVar2 != null) {
            nlVar2.e();
            this.J.a = null;
        }
        this.J = nlVar;
        if (nlVar != null) {
            nlVar.a = this.i0;
        }
    }

    public void setItemViewCacheSize(int i) {
        wl wlVar = this.c;
        wlVar.e = i;
        wlVar.m();
    }

    @Deprecated
    public void setLayoutFrozen(boolean z) {
        suppressLayout(z);
    }

    public void setLayoutManager(ql qlVar) {
        RecyclerView recyclerView;
        if (qlVar == this.m) {
            return;
        }
        setScrollState(0);
        dm dmVar = this.a0;
        dmVar.g.removeCallbacks(dmVar);
        dmVar.c.abortAnimation();
        ql qlVar2 = this.m;
        wl wlVar = this.c;
        if (qlVar2 != null) {
            nl nlVar = this.J;
            if (nlVar != null) {
                nlVar.e();
            }
            this.m.c0(wlVar);
            this.m.d0(wlVar);
            wlVar.a.clear();
            wlVar.f();
            if (this.r) {
                ql qlVar3 = this.m;
                qlVar3.f = false;
                qlVar3.M(this);
            }
            this.m.o0(null);
            this.m = null;
        } else {
            wlVar.a.clear();
            wlVar.f();
        }
        b7 b7Var = this.f;
        b7Var.b.g();
        ArrayList arrayList = b7Var.c;
        int size = arrayList.size() - 1;
        while (true) {
            recyclerView = b7Var.a.a;
            if (size < 0) {
                break;
            }
            em emVarH = H((View) arrayList.get(size));
            if (emVarH != null) {
                int i = emVarH.o;
                if (recyclerView.K()) {
                    emVarH.p = i;
                    recyclerView.q0.add(emVarH);
                } else {
                    View view = emVarH.a;
                    WeakHashMap weakHashMap = os.a;
                    view.setImportantForAccessibility(i);
                }
                emVarH.o = 0;
            }
            arrayList.remove(size);
            size--;
        }
        int childCount = recyclerView.getChildCount();
        for (int i2 = 0; i2 < childCount; i2++) {
            View childAt = recyclerView.getChildAt(i2);
            H(childAt);
            jl jlVar = recyclerView.l;
            childAt.clearAnimation();
        }
        recyclerView.removeAllViews();
        this.m = qlVar;
        if (qlVar != null) {
            if (qlVar.b != null) {
                StringBuilder sb = new StringBuilder("LayoutManager ");
                sb.append(qlVar);
                String strY = qlVar.b.y();
                sb.append(" is already attached to a RecyclerView:");
                sb.append(strY);
                throw new IllegalArgumentException(sb.toString());
            }
            qlVar.o0(this);
            if (this.r) {
                this.m.f = true;
            }
        }
        wlVar.m();
        requestLayout();
    }

    @Override // android.view.ViewGroup
    @Deprecated
    public void setLayoutTransition(LayoutTransition layoutTransition) {
        if (layoutTransition == null) {
            super.setLayoutTransition(null);
        } else {
            z6.f("Providing a LayoutTransition into RecyclerView is not supported. Please use setItemAnimator() instead for animating changes to the items in this RecyclerView");
        }
    }

    @Override // android.view.View
    public void setNestedScrollingEnabled(boolean z) {
        qj scrollingChildHelper = getScrollingChildHelper();
        if (scrollingChildHelper.d) {
            ViewGroup viewGroup = scrollingChildHelper.c;
            WeakHashMap weakHashMap = os.a;
            ds.z(viewGroup);
        }
        scrollingChildHelper.d = z;
    }

    @Deprecated
    public void setOnScrollListener(tl tlVar) {
        this.e0 = tlVar;
    }

    public void setPreserveFocusAfterLayout(boolean z) {
        this.W = z;
    }

    public void setRecycledViewPool(vl vlVar) {
        wl wlVar = this.c;
        RecyclerView recyclerView = wlVar.h;
        wlVar.e(recyclerView.l, false);
        if (wlVar.g != null) {
            r1.b--;
        }
        wlVar.g = vlVar;
        if (vlVar != null && recyclerView.getAdapter() != null) {
            wlVar.g.b++;
        }
        wlVar.d();
    }

    public void setScrollState(int i) {
        if (i == this.K) {
            return;
        }
        if (x0) {
            Log.d("RecyclerView", "setting scroll state to " + i + " from " + this.K, new Exception());
        }
        this.K = i;
        if (i != 2) {
            dm dmVar = this.a0;
            dmVar.g.removeCallbacks(dmVar);
            dmVar.c.abortAnimation();
        }
        ql qlVar = this.m;
        if (qlVar != null) {
            qlVar.b0(i);
        }
        ArrayList arrayList = this.f0;
        if (arrayList != null) {
            for (int size = arrayList.size() - 1; size >= 0; size--) {
                ((tl) this.f0.get(size)).getClass();
            }
        }
    }

    public void setScrollingTouchSlop(int i) {
        ViewConfiguration viewConfiguration = ViewConfiguration.get(getContext());
        if (i != 0) {
            if (i == 1) {
                this.R = viewConfiguration.getScaledPagingTouchSlop();
                return;
            }
            Log.w("RecyclerView", "setScrollingTouchSlop(): bad argument constant " + i + "; using default value");
        }
        this.R = viewConfiguration.getScaledTouchSlop();
    }

    public void setViewCacheExtension(cm cmVar) {
        this.c.getClass();
    }

    @Override // android.view.View
    public final boolean startNestedScroll(int i) {
        return getScrollingChildHelper().g(i, 0);
    }

    @Override // android.view.View
    public final void stopNestedScroll() {
        getScrollingChildHelper().h(0);
    }

    @Override // android.view.ViewGroup
    public final void suppressLayout(boolean z) {
        if (z != this.w) {
            h("Do not suppressLayout in layout or scroll");
            if (!z) {
                this.w = false;
                if (this.v && this.m != null && this.l != null) {
                    requestLayout();
                }
                this.v = false;
                return;
            }
            long jUptimeMillis = SystemClock.uptimeMillis();
            onTouchEvent(MotionEvent.obtain(jUptimeMillis, jUptimeMillis, 3, 0.0f, 0.0f, 0));
            this.w = true;
            this.x = true;
            setScrollState(0);
            dm dmVar = this.a0;
            dmVar.g.removeCallbacks(dmVar);
            dmVar.c.abortAnimation();
        }
    }

    public final void t(int i, int i2) {
        this.D++;
        int scrollX = getScrollX();
        int scrollY = getScrollY();
        onScrollChanged(scrollX, scrollY, scrollX - i, scrollY - i2);
        tl tlVar = this.e0;
        if (tlVar != null) {
            tlVar.a(this);
        }
        ArrayList arrayList = this.f0;
        if (arrayList != null) {
            for (int size = arrayList.size() - 1; size >= 0; size--) {
                ((tl) this.f0.get(size)).a(this);
            }
        }
        this.D--;
    }

    public final void u() {
        if (this.I != null) {
            return;
        }
        ((bm) this.E).getClass();
        EdgeEffect edgeEffect = new EdgeEffect(getContext());
        this.I = edgeEffect;
        if (this.h) {
            edgeEffect.setSize((getMeasuredWidth() - getPaddingLeft()) - getPaddingRight(), (getMeasuredHeight() - getPaddingTop()) - getPaddingBottom());
        } else {
            edgeEffect.setSize(getMeasuredWidth(), getMeasuredHeight());
        }
    }

    public final void v() {
        if (this.F != null) {
            return;
        }
        ((bm) this.E).getClass();
        EdgeEffect edgeEffect = new EdgeEffect(getContext());
        this.F = edgeEffect;
        if (this.h) {
            edgeEffect.setSize((getMeasuredHeight() - getPaddingTop()) - getPaddingBottom(), (getMeasuredWidth() - getPaddingLeft()) - getPaddingRight());
        } else {
            edgeEffect.setSize(getMeasuredHeight(), getMeasuredWidth());
        }
    }

    public final void w() {
        if (this.H != null) {
            return;
        }
        ((bm) this.E).getClass();
        EdgeEffect edgeEffect = new EdgeEffect(getContext());
        this.H = edgeEffect;
        if (this.h) {
            edgeEffect.setSize((getMeasuredHeight() - getPaddingTop()) - getPaddingBottom(), (getMeasuredWidth() - getPaddingLeft()) - getPaddingRight());
        } else {
            edgeEffect.setSize(getMeasuredHeight(), getMeasuredWidth());
        }
    }

    public final void x() {
        if (this.G != null) {
            return;
        }
        ((bm) this.E).getClass();
        EdgeEffect edgeEffect = new EdgeEffect(getContext());
        this.G = edgeEffect;
        if (this.h) {
            edgeEffect.setSize((getMeasuredWidth() - getPaddingLeft()) - getPaddingRight(), (getMeasuredHeight() - getPaddingTop()) - getPaddingBottom());
        } else {
            edgeEffect.setSize(getMeasuredWidth(), getMeasuredHeight());
        }
    }

    public final String y() {
        return " " + super.toString() + ", adapter:" + this.l + ", layout:" + this.m + ", context:" + getContext();
    }

    public final void z(am amVar) {
        if (getScrollState() != 2) {
            amVar.getClass();
            return;
        }
        OverScroller overScroller = this.a0.c;
        overScroller.getFinalX();
        overScroller.getCurrX();
        amVar.getClass();
        overScroller.getFinalY();
        overScroller.getCurrY();
    }

    @Override // android.view.ViewGroup
    public final ViewGroup.LayoutParams generateLayoutParams(ViewGroup.LayoutParams layoutParams) {
        ql qlVar = this.m;
        if (qlVar != null) {
            return qlVar.s(layoutParams);
        }
        z6.o("RecyclerView has no LayoutManager".concat(y()));
        return null;
    }

    public void setOnFlingListener(sl slVar) {
    }

    @Deprecated
    public void setRecyclerListener(xl xlVar) {
    }

    public RecyclerView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, com.example.LegoClicker.R.attr.recyclerViewStyle);
    }

    public RecyclerView(Context context) {
        this(context, null);
    }
}
