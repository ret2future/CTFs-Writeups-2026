package defpackage;

import android.content.Context;
import android.graphics.Rect;
import android.os.Build;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowInsets;
import android.widget.FrameLayout;
import androidx.appcompat.widget.ActionBarContextView;
import com.example.LegoClicker.R;
import java.lang.reflect.Method;
import java.util.WeakHashMap;

/* JADX INFO: compiled from: r8-map-id-51b54c9e59b63e0fb0abe04daff984b4fb85d07ef3fcb7973595bce215c10515 */
/* JADX INFO: loaded from: classes.dex */
public final class b3 implements yj, r8, aj {
    public final /* synthetic */ int a;
    public final /* synthetic */ n3 b;

    public /* synthetic */ b3(n3 n3Var, int i) {
        this.a = i;
        this.b = n3Var;
    }

    @Override // defpackage.aj
    public void a(gi giVar, boolean z) {
        m3 m3Var;
        int i = this.a;
        n3 n3Var = this.b;
        switch (i) {
            case 2:
                n3Var.s(giVar);
                break;
            default:
                gi giVarK = giVar.k();
                int i2 = 0;
                boolean z2 = giVarK != giVar;
                if (z2) {
                    giVar = giVarK;
                }
                m3[] m3VarArr = n3Var.L;
                int length = m3VarArr != null ? m3VarArr.length : 0;
                while (true) {
                    if (i2 >= length) {
                        m3Var = null;
                    } else {
                        m3Var = m3VarArr[i2];
                        if (m3Var == null || m3Var.h != giVar) {
                            i2++;
                        }
                    }
                }
                if (m3Var != null) {
                    if (!z2) {
                        n3Var.t(m3Var, z);
                    } else {
                        n3Var.r(m3Var.a, m3Var, giVarK);
                        n3Var.t(m3Var, true);
                    }
                }
                break;
        }
    }

    /* JADX WARN: Unreachable blocks removed: 1, instructions: 1 */
    public eu b(View view, eu euVar) {
        int i;
        boolean z;
        eu euVarB;
        boolean z2;
        boolean z3;
        du duVar = euVar.a;
        int i2 = duVar.g().b;
        n3 n3Var = this.b;
        Context context = n3Var.k;
        int i3 = duVar.g().b;
        ActionBarContextView actionBarContextView = n3Var.v;
        if (actionBarContextView == null || !(actionBarContextView.getLayoutParams() instanceof ViewGroup.MarginLayoutParams)) {
            i = 0;
            z = false;
        } else {
            ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) n3Var.v.getLayoutParams();
            if (n3Var.v.isShown()) {
                if (n3Var.c0 == null) {
                    n3Var.c0 = new Rect();
                    n3Var.d0 = new Rect();
                }
                Rect rect = n3Var.c0;
                Rect rect2 = n3Var.d0;
                rect.set(duVar.g().a, duVar.g().b, duVar.g().c, duVar.g().d);
                ViewGroup viewGroup = n3Var.A;
                if (Build.VERSION.SDK_INT >= 29) {
                    boolean z4 = jt.a;
                    ht.a(viewGroup, rect, rect2);
                    z3 = true;
                } else {
                    if (jt.a) {
                        z3 = true;
                    } else {
                        jt.a = true;
                        try {
                            Method declaredMethod = View.class.getDeclaredMethod("computeFitSystemWindows", Rect.class, Rect.class);
                            jt.b = declaredMethod;
                            if (declaredMethod.isAccessible()) {
                                z3 = true;
                            } else {
                                z3 = true;
                                try {
                                    jt.b.setAccessible(true);
                                } catch (NoSuchMethodException unused) {
                                    Log.d("ViewUtils", "Could not find method computeFitSystemWindows. Oh well.");
                                }
                            }
                        } catch (NoSuchMethodException unused2) {
                            z3 = true;
                        }
                    }
                    Method method = jt.b;
                    if (method != null) {
                        try {
                            method.invoke(viewGroup, rect, rect2);
                        } catch (Exception e) {
                            Log.d("ViewUtils", "Could not invoke computeFitSystemWindows", e);
                        }
                    }
                }
                int i4 = rect.top;
                int i5 = rect.left;
                int i6 = rect.right;
                ViewGroup viewGroup2 = n3Var.A;
                WeakHashMap weakHashMap = os.a;
                eu euVarA = es.a(viewGroup2);
                int i7 = euVarA == null ? 0 : euVarA.a.g().a;
                int i8 = euVarA == null ? 0 : euVarA.a.g().c;
                if (marginLayoutParams.topMargin == i4 && marginLayoutParams.leftMargin == i5 && marginLayoutParams.rightMargin == i6) {
                    z2 = false;
                } else {
                    marginLayoutParams.topMargin = i4;
                    marginLayoutParams.leftMargin = i5;
                    marginLayoutParams.rightMargin = i6;
                    z2 = z3;
                }
                if (i4 <= 0 || n3Var.C != null) {
                    View view2 = n3Var.C;
                    if (view2 != null) {
                        ViewGroup.MarginLayoutParams marginLayoutParams2 = (ViewGroup.MarginLayoutParams) view2.getLayoutParams();
                        int i9 = marginLayoutParams2.height;
                        int i10 = marginLayoutParams.topMargin;
                        if (i9 != i10 || marginLayoutParams2.leftMargin != i7 || marginLayoutParams2.rightMargin != i8) {
                            marginLayoutParams2.height = i10;
                            marginLayoutParams2.leftMargin = i7;
                            marginLayoutParams2.rightMargin = i8;
                            n3Var.C.setLayoutParams(marginLayoutParams2);
                        }
                    }
                } else {
                    View view3 = new View(context);
                    n3Var.C = view3;
                    view3.setVisibility(8);
                    FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(-1, marginLayoutParams.topMargin, 51);
                    layoutParams.leftMargin = i7;
                    layoutParams.rightMargin = i8;
                    n3Var.A.addView(n3Var.C, -1, layoutParams);
                }
                View view4 = n3Var.C;
                boolean z5 = view4 != null ? z3 : false;
                if (z5 && view4.getVisibility() != 0) {
                    View view5 = n3Var.C;
                    view5.setBackgroundColor((view5.getWindowSystemUiVisibility() & 8192) != 0 ? z8.a(context, R.color.abc_decor_view_status_guard_light) : z8.a(context, R.color.abc_decor_view_status_guard));
                }
                if (!n3Var.H && z5) {
                    i3 = 0;
                }
                z = z5;
                i = 0;
            } else {
                i = 0;
                if (marginLayoutParams.topMargin != 0) {
                    marginLayoutParams.topMargin = 0;
                    z2 = true;
                    z = false;
                } else {
                    z = false;
                    z2 = false;
                }
            }
            if (z2) {
                n3Var.v.setLayoutParams(marginLayoutParams);
            }
        }
        View view6 = n3Var.C;
        if (view6 != null) {
            view6.setVisibility(z ? i : 8);
        }
        if (i2 != i3) {
            int i11 = duVar.g().a;
            int i12 = duVar.g().c;
            int i13 = duVar.g().d;
            int i14 = Build.VERSION.SDK_INT;
            xt wtVar = i14 >= 30 ? new wt(euVar) : i14 >= 29 ? new vt(euVar) : new ut(euVar);
            wtVar.d(jf.a(i11, i3, i12, i13));
            euVarB = wtVar.b();
        } else {
            euVarB = euVar;
        }
        WeakHashMap weakHashMap2 = os.a;
        WindowInsets windowInsetsB = euVarB.b();
        if (windowInsetsB == null) {
            return euVarB;
        }
        WindowInsets windowInsetsB2 = bs.b(view, windowInsetsB);
        return !windowInsetsB2.equals(windowInsetsB) ? eu.c(windowInsetsB2, view) : euVarB;
    }

    @Override // defpackage.aj
    public boolean s(gi giVar) {
        Window.Callback callback;
        int i = this.a;
        n3 n3Var = this.b;
        switch (i) {
            case 2:
                Window.Callback callback2 = n3Var.l.getCallback();
                if (callback2 != null) {
                    callback2.onMenuOpened(108, giVar);
                }
                break;
            default:
                if (giVar == giVar.k() && n3Var.F && (callback = n3Var.l.getCallback()) != null && !n3Var.Q) {
                    callback.onMenuOpened(108, giVar);
                }
                break;
        }
        return true;
    }
}
