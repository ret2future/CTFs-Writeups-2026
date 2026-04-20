package defpackage;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Handler;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.PopupWindow;
import java.lang.reflect.Method;

/* JADX INFO: compiled from: r8-map-id-51b54c9e59b63e0fb0abe04daff984b4fb85d07ef3fcb7973595bce215c10515 */
/* JADX INFO: loaded from: classes.dex */
public abstract class hh implements jo {
    public static final Method A;
    public static final Method z;
    public final Context a;
    public ListAdapter b;
    public ka c;
    public int f;
    public int g;
    public boolean i;
    public boolean j;
    public boolean k;
    public eh n;
    public View o;
    public AdapterView.OnItemClickListener p;
    public final Handler u;
    public Rect w;
    public boolean x;
    public final y3 y;
    public final int d = -2;
    public int e = -2;
    public final int h = 1002;
    public int l = 0;
    public final int m = Integer.MAX_VALUE;
    public final dh q = new dh(this, 1);
    public final gh r = new gh(this);
    public final fh s = new fh(this);
    public final dh t = new dh(this, 0);
    public final Rect v = new Rect();

    static {
        if (Build.VERSION.SDK_INT <= 28) {
            try {
                z = PopupWindow.class.getDeclaredMethod("setClipToScreenEnabled", Boolean.TYPE);
            } catch (NoSuchMethodException unused) {
                Log.i("ListPopupWindow", "Could not find method setClipToScreenEnabled() on PopupWindow. Oh well.");
            }
            try {
                A = PopupWindow.class.getDeclaredMethod("setEpicenterBounds", Rect.class);
            } catch (NoSuchMethodException unused2) {
                Log.i("ListPopupWindow", "Could not find method setEpicenterBounds(Rect) on PopupWindow. Oh well.");
            }
        }
    }

    public hh(Context context, AttributeSet attributeSet, int i) {
        int resourceId;
        this.a = context;
        this.u = new Handler(context.getMainLooper());
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, fl.o, i, 0);
        this.f = typedArrayObtainStyledAttributes.getDimensionPixelOffset(0, 0);
        int dimensionPixelOffset = typedArrayObtainStyledAttributes.getDimensionPixelOffset(1, 0);
        this.g = dimensionPixelOffset;
        if (dimensionPixelOffset != 0) {
            this.i = true;
        }
        typedArrayObtainStyledAttributes.recycle();
        y3 y3Var = new y3(context, attributeSet, i, 0);
        TypedArray typedArrayObtainStyledAttributes2 = context.obtainStyledAttributes(attributeSet, fl.s, i, 0);
        if (typedArrayObtainStyledAttributes2.hasValue(2)) {
            nk.c(y3Var, typedArrayObtainStyledAttributes2.getBoolean(2, false));
        }
        y3Var.setBackgroundDrawable((!typedArrayObtainStyledAttributes2.hasValue(0) || (resourceId = typedArrayObtainStyledAttributes2.getResourceId(0, 0)) == 0) ? typedArrayObtainStyledAttributes2.getDrawable(0) : d.w(context, resourceId));
        typedArrayObtainStyledAttributes2.recycle();
        this.y = y3Var;
        y3Var.setInputMethodMode(1);
    }

    public ka a(Context context, boolean z2) {
        return new ka(context, z2);
    }

    @Override // defpackage.jo
    public final boolean b() {
        return this.y.isShowing();
    }

    public final void c(int i) {
        this.f = i;
    }

    public final int d() {
        return this.f;
    }

    @Override // defpackage.jo
    public final void dismiss() {
        y3 y3Var = this.y;
        y3Var.dismiss();
        y3Var.setContentView(null);
        this.c = null;
        this.u.removeCallbacks(this.q);
    }

    @Override // defpackage.jo
    public final void f() {
        int i;
        int paddingBottom;
        ka kaVar;
        ka kaVar2 = this.c;
        Context context = this.a;
        y3 y3Var = this.y;
        if (kaVar2 == null) {
            ka kaVarA = a(context, !this.x);
            this.c = kaVarA;
            kaVarA.setAdapter(this.b);
            this.c.setOnItemClickListener(this.p);
            this.c.setFocusable(true);
            this.c.setFocusableInTouchMode(true);
            this.c.setOnItemSelectedListener(new ah(this));
            this.c.setOnScrollListener(this.s);
            y3Var.setContentView(this.c);
        }
        Drawable background = y3Var.getBackground();
        Rect rect = this.v;
        if (background != null) {
            background.getPadding(rect);
            int i2 = rect.top;
            i = rect.bottom + i2;
            if (!this.i) {
                this.g = -i2;
            }
        } else {
            rect.setEmpty();
            i = 0;
        }
        int iA = bh.a(y3Var, this.o, this.g, y3Var.getInputMethodMode() == 2);
        int i3 = this.d;
        if (i3 == -1) {
            paddingBottom = iA + i;
        } else {
            int i4 = this.e;
            int iA2 = this.c.a(i4 != -2 ? i4 != -1 ? View.MeasureSpec.makeMeasureSpec(i4, 1073741824) : View.MeasureSpec.makeMeasureSpec(context.getResources().getDisplayMetrics().widthPixels - (rect.left + rect.right), 1073741824) : View.MeasureSpec.makeMeasureSpec(context.getResources().getDisplayMetrics().widthPixels - (rect.left + rect.right), Integer.MIN_VALUE), iA);
            paddingBottom = iA2 + (iA2 > 0 ? this.c.getPaddingBottom() + this.c.getPaddingTop() + i : 0);
        }
        boolean z2 = y3Var.getInputMethodMode() == 2;
        nk.d(y3Var, this.h);
        if (y3Var.isShowing()) {
            if (this.o.isAttachedToWindow()) {
                int width = this.e;
                if (width == -1) {
                    width = -1;
                } else if (width == -2) {
                    width = this.o.getWidth();
                }
                if (i3 == -1) {
                    i3 = z2 ? paddingBottom : -1;
                    int i5 = this.e;
                    if (z2) {
                        y3Var.setWidth(i5 == -1 ? -1 : 0);
                        y3Var.setHeight(0);
                    } else {
                        y3Var.setWidth(i5 == -1 ? -1 : 0);
                        y3Var.setHeight(-1);
                    }
                } else if (i3 == -2) {
                    i3 = paddingBottom;
                }
                y3Var.setOutsideTouchable(true);
                int i6 = width;
                View view = this.o;
                int i7 = this.f;
                int i8 = this.g;
                int i9 = i6 < 0 ? -1 : i6;
                if (i3 < 0) {
                    i3 = -1;
                }
                y3Var.update(view, i7, i8, i9, i3);
                return;
            }
            return;
        }
        int width2 = this.e;
        if (width2 == -1) {
            width2 = -1;
        } else if (width2 == -2) {
            width2 = this.o.getWidth();
        }
        if (i3 == -1) {
            i3 = -1;
        } else if (i3 == -2) {
            i3 = paddingBottom;
        }
        y3Var.setWidth(width2);
        y3Var.setHeight(i3);
        if (Build.VERSION.SDK_INT <= 28) {
            Method method = z;
            if (method != null) {
                try {
                    method.invoke(y3Var, Boolean.TRUE);
                } catch (Exception unused) {
                    Log.i("ListPopupWindow", "Could not call setClipToScreenEnabled() on PopupWindow. Oh well.");
                }
            }
        } else {
            ch.b(y3Var, true);
        }
        y3Var.setOutsideTouchable(true);
        y3Var.setTouchInterceptor(this.r);
        if (this.k) {
            nk.c(y3Var, this.j);
        }
        if (Build.VERSION.SDK_INT <= 28) {
            Method method2 = A;
            if (method2 != null) {
                try {
                    method2.invoke(y3Var, this.w);
                } catch (Exception e) {
                    Log.e("ListPopupWindow", "Could not invoke setEpicenterBounds on PopupWindow", e);
                }
            }
        } else {
            ch.a(y3Var, this.w);
        }
        y3Var.showAsDropDown(this.o, this.f, this.g, this.l);
        this.c.setSelection(-1);
        if ((!this.x || this.c.isInTouchMode()) && (kaVar = this.c) != null) {
            kaVar.setListSelectionHidden(true);
            kaVar.requestLayout();
        }
        if (this.x) {
            return;
        }
        this.u.post(this.t);
    }

    public final int g() {
        if (this.i) {
            return this.g;
        }
        return 0;
    }

    public final Drawable h() {
        return this.y.getBackground();
    }

    @Override // defpackage.jo
    public final ka j() {
        return this.c;
    }

    public final void l(Drawable drawable) {
        this.y.setBackgroundDrawable(drawable);
    }

    public final void m(int i) {
        this.g = i;
        this.i = true;
    }

    public void n(ListAdapter listAdapter) {
        eh ehVar = this.n;
        if (ehVar == null) {
            this.n = new eh(this);
        } else {
            ListAdapter listAdapter2 = this.b;
            if (listAdapter2 != null) {
                listAdapter2.unregisterDataSetObserver(ehVar);
            }
        }
        this.b = listAdapter;
        if (listAdapter != null) {
            listAdapter.registerDataSetObserver(this.n);
        }
        ka kaVar = this.c;
        if (kaVar != null) {
            kaVar.setAdapter(this.b);
        }
    }

    public final void q(int i) {
        Drawable background = this.y.getBackground();
        if (background == null) {
            this.e = i;
            return;
        }
        Rect rect = this.v;
        background.getPadding(rect);
        this.e = rect.left + rect.right + i;
    }
}
