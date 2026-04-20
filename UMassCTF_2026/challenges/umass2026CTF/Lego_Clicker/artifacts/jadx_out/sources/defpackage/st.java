package defpackage;

import android.R;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.res.TypedArray;
import android.util.TypedValue;
import android.view.ContextThemeWrapper;
import android.view.KeyCharacterMap;
import android.view.KeyEvent;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import androidx.appcompat.widget.ActionBarContainer;
import androidx.appcompat.widget.ActionBarContextView;
import androidx.appcompat.widget.ActionBarOverlayLayout;
import androidx.appcompat.widget.Toolbar;
import java.util.ArrayList;
import java.util.WeakHashMap;

/* JADX INFO: compiled from: r8-map-id-51b54c9e59b63e0fb0abe04daff984b4fb85d07ef3fcb7973595bce215c10515 */
/* JADX INFO: loaded from: classes.dex */
public final class st extends o0 implements u0 {
    public static final AccelerateInterpolator y = new AccelerateInterpolator();
    public static final DecelerateInterpolator z = new DecelerateInterpolator();
    public Context a;
    public Context b;
    public ActionBarOverlayLayout c;
    public ActionBarContainer d;
    public g9 e;
    public ActionBarContextView f;
    public final View g;
    public boolean h;
    public rt i;
    public rt j;
    public z3 k;
    public boolean l;
    public final ArrayList m;
    public int n;
    public boolean o;
    public boolean p;
    public boolean q;
    public boolean r;
    public et s;
    public boolean t;
    public boolean u;
    public final qt v;
    public final qt w;
    public final e0 x;

    public st(Activity activity, boolean z2) {
        new ArrayList();
        this.m = new ArrayList();
        this.n = 0;
        this.o = true;
        this.r = true;
        this.v = new qt(this, 0);
        this.w = new qt(this, 1);
        this.x = new e0(20, this);
        View decorView = activity.getWindow().getDecorView();
        q(decorView);
        if (z2) {
            return;
        }
        this.g = decorView.findViewById(R.id.content);
    }

    @Override // defpackage.o0
    public final boolean b() {
        xp xpVar;
        g9 g9Var = this.e;
        if (g9Var == null || (xpVar = ((gq) g9Var).a.M) == null || xpVar.b == null) {
            return false;
        }
        xp xpVar2 = ((gq) g9Var).a.M;
        oi oiVar = xpVar2 == null ? null : xpVar2.b;
        if (oiVar == null) {
            return true;
        }
        oiVar.collapseActionView();
        return true;
    }

    @Override // defpackage.o0
    public final void c(boolean z2) {
        if (z2 == this.l) {
            return;
        }
        this.l = z2;
        ArrayList arrayList = this.m;
        if (arrayList.size() <= 0) {
            return;
        }
        arrayList.get(0).getClass();
        z6.a();
    }

    @Override // defpackage.o0
    public final int d() {
        return ((gq) this.e).b;
    }

    @Override // defpackage.o0
    public final Context e() {
        if (this.b == null) {
            TypedValue typedValue = new TypedValue();
            this.a.getTheme().resolveAttribute(com.example.LegoClicker.R.attr.actionBarWidgetTheme, typedValue, true);
            int i = typedValue.resourceId;
            if (i != 0) {
                this.b = new ContextThemeWrapper(this.a, i);
            } else {
                this.b = this.a;
            }
        }
        return this.b;
    }

    @Override // defpackage.o0
    public final void g() {
        r(this.a.getResources().getBoolean(com.example.LegoClicker.R.bool.abc_action_bar_embed_tabs));
    }

    @Override // defpackage.o0
    public final boolean i(int i, KeyEvent keyEvent) {
        gi giVar;
        rt rtVar = this.i;
        if (rtVar == null || (giVar = rtVar.d) == null) {
            return false;
        }
        giVar.setQwertyMode(KeyCharacterMap.load(keyEvent.getDeviceId()).getKeyboardType() != 1);
        return giVar.performShortcut(i, keyEvent, 0);
    }

    @Override // defpackage.o0
    public final void l(boolean z2) {
        if (this.h) {
            return;
        }
        int i = z2 ? 4 : 0;
        gq gqVar = (gq) this.e;
        int i2 = gqVar.b;
        this.h = true;
        gqVar.a((i & 4) | (i2 & (-5)));
    }

    @Override // defpackage.o0
    public final void m(boolean z2) {
        et etVar;
        this.t = z2;
        if (z2 || (etVar = this.s) == null) {
            return;
        }
        etVar.a();
    }

    @Override // defpackage.o0
    public final void n(CharSequence charSequence) {
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

    @Override // defpackage.o0
    public final j1 o(z3 z3Var) {
        rt rtVar = this.i;
        if (rtVar != null) {
            rtVar.a();
        }
        this.c.setHideOnContentScrollEnabled(false);
        this.f.e();
        rt rtVar2 = new rt(this, this.f.getContext(), z3Var);
        gi giVar = rtVar2.d;
        giVar.w();
        try {
            if (!((i1) rtVar2.e.b).b(rtVar2, giVar)) {
                return null;
            }
            this.i = rtVar2;
            rtVar2.g();
            this.f.c(rtVar2);
            p(true);
            return rtVar2;
        } finally {
            giVar.v();
        }
    }

    public final void p(boolean z2) {
        dt dtVarI;
        dt dtVarI2;
        boolean z3 = this.q;
        if (z2) {
            if (!z3) {
                this.q = true;
                ActionBarOverlayLayout actionBarOverlayLayout = this.c;
                if (actionBarOverlayLayout != null) {
                    actionBarOverlayLayout.setShowingForActionMode(true);
                }
                s(false);
            }
        } else if (z3) {
            this.q = false;
            ActionBarOverlayLayout actionBarOverlayLayout2 = this.c;
            if (actionBarOverlayLayout2 != null) {
                actionBarOverlayLayout2.setShowingForActionMode(false);
            }
            s(false);
        }
        boolean zIsLaidOut = this.d.isLaidOut();
        g9 g9Var = this.e;
        if (!zIsLaidOut) {
            if (z2) {
                ((gq) g9Var).a.setVisibility(4);
                this.f.setVisibility(0);
                return;
            } else {
                ((gq) g9Var).a.setVisibility(0);
                this.f.setVisibility(8);
                return;
            }
        }
        if (z2) {
            gq gqVar = (gq) g9Var;
            dtVarI = os.a(gqVar.a);
            dtVarI.a(0.0f);
            dtVarI.c(100L);
            dtVarI.d(new fq(gqVar, 4));
            dtVarI2 = this.f.i(0, 200L);
        } else {
            gq gqVar2 = (gq) g9Var;
            dt dtVarA = os.a(gqVar2.a);
            dtVarA.a(1.0f);
            dtVarA.c(200L);
            dtVarA.d(new fq(gqVar2, 0));
            dtVarI = this.f.i(8, 100L);
            dtVarI2 = dtVarA;
        }
        et etVar = new et();
        ArrayList arrayList = etVar.a;
        arrayList.add(dtVarI);
        View view = (View) dtVarI.a.get();
        long duration = view != null ? view.animate().getDuration() : 0L;
        View view2 = (View) dtVarI2.a.get();
        if (view2 != null) {
            view2.animate().setStartDelay(duration);
        }
        arrayList.add(dtVarI2);
        etVar.b();
    }

    public final void q(View view) {
        g9 wrapper;
        ActionBarOverlayLayout actionBarOverlayLayout = (ActionBarOverlayLayout) view.findViewById(com.example.LegoClicker.R.id.decor_content_parent);
        this.c = actionBarOverlayLayout;
        if (actionBarOverlayLayout != null) {
            actionBarOverlayLayout.setActionBarVisibilityCallback(this);
        }
        KeyEvent.Callback callbackFindViewById = view.findViewById(com.example.LegoClicker.R.id.action_bar);
        if (callbackFindViewById instanceof g9) {
            wrapper = (g9) callbackFindViewById;
        } else {
            if (!(callbackFindViewById instanceof Toolbar)) {
                throw new IllegalStateException("Can't make a decor toolbar out of ".concat(callbackFindViewById != null ? callbackFindViewById.getClass().getSimpleName() : "null"));
            }
            wrapper = ((Toolbar) callbackFindViewById).getWrapper();
        }
        this.e = wrapper;
        this.f = (ActionBarContextView) view.findViewById(com.example.LegoClicker.R.id.action_context_bar);
        ActionBarContainer actionBarContainer = (ActionBarContainer) view.findViewById(com.example.LegoClicker.R.id.action_bar_container);
        this.d = actionBarContainer;
        g9 g9Var = this.e;
        if (g9Var == null || this.f == null || actionBarContainer == null) {
            z6.o(st.class.getSimpleName().concat(" can only be used with a compatible window decor layout"));
            return;
        }
        Context context = ((gq) g9Var).a.getContext();
        this.a = context;
        if ((((gq) this.e).b & 4) != 0) {
            this.h = true;
        }
        int i = context.getApplicationInfo().targetSdkVersion;
        this.e.getClass();
        r(context.getResources().getBoolean(com.example.LegoClicker.R.bool.abc_action_bar_embed_tabs));
        TypedArray typedArrayObtainStyledAttributes = this.a.obtainStyledAttributes(null, fl.a, com.example.LegoClicker.R.attr.actionBarStyle, 0);
        if (typedArrayObtainStyledAttributes.getBoolean(14, false)) {
            ActionBarOverlayLayout actionBarOverlayLayout2 = this.c;
            if (!actionBarOverlayLayout2.g) {
                z6.o("Action bar must be in overlay mode (Window.FEATURE_OVERLAY_ACTION_BAR) to enable hide on content scroll");
                return;
            } else {
                this.u = true;
                actionBarOverlayLayout2.setHideOnContentScrollEnabled(true);
            }
        }
        int dimensionPixelSize = typedArrayObtainStyledAttributes.getDimensionPixelSize(12, 0);
        if (dimensionPixelSize != 0) {
            ActionBarContainer actionBarContainer2 = this.d;
            WeakHashMap weakHashMap = os.a;
            ds.s(actionBarContainer2, dimensionPixelSize);
        }
        typedArrayObtainStyledAttributes.recycle();
    }

    public final void r(boolean z2) {
        if (z2) {
            this.d.setTabContainer(null);
            ((gq) this.e).getClass();
        } else {
            ((gq) this.e).getClass();
            this.d.setTabContainer(null);
        }
        this.e.getClass();
        ((gq) this.e).a.setCollapsible(false);
        this.c.setHasNonEmbeddedTabs(false);
    }

    public final void s(boolean z2) {
        boolean z3 = this.q || !this.p;
        boolean z4 = this.r;
        final e0 e0Var = this.x;
        View view = this.g;
        if (!z3) {
            if (z4) {
                this.r = false;
                et etVar = this.s;
                if (etVar != null) {
                    etVar.a();
                }
                int i = this.n;
                qt qtVar = this.v;
                if (i != 0 || (!this.t && !z2)) {
                    qtVar.b();
                    return;
                }
                this.d.setAlpha(1.0f);
                this.d.setTransitioning(true);
                et etVar2 = new et();
                float f = -this.d.getHeight();
                if (z2) {
                    this.d.getLocationInWindow(new int[]{0, 0});
                    f -= r12[1];
                }
                dt dtVarA = os.a(this.d);
                dtVarA.e(f);
                final View view2 = (View) dtVarA.a.get();
                if (view2 != null) {
                    view2.animate().setUpdateListener(e0Var != null ? new ValueAnimator.AnimatorUpdateListener(view2) { // from class: ct
                        @Override // android.animation.ValueAnimator.AnimatorUpdateListener
                        public final void onAnimationUpdate(ValueAnimator valueAnimator) {
                            ((View) ((st) this.a.b).d.getParent()).invalidate();
                        }
                    } : null);
                }
                boolean z5 = etVar2.e;
                ArrayList arrayList = etVar2.a;
                if (!z5) {
                    arrayList.add(dtVarA);
                }
                if (this.o && view != null) {
                    dt dtVarA2 = os.a(view);
                    dtVarA2.e(f);
                    if (!etVar2.e) {
                        arrayList.add(dtVarA2);
                    }
                }
                boolean z6 = etVar2.e;
                if (!z6) {
                    etVar2.c = y;
                }
                if (!z6) {
                    etVar2.b = 250L;
                }
                if (!z6) {
                    etVar2.d = qtVar;
                }
                this.s = etVar2;
                etVar2.b();
                return;
            }
            return;
        }
        if (z4) {
            return;
        }
        this.r = true;
        et etVar3 = this.s;
        if (etVar3 != null) {
            etVar3.a();
        }
        this.d.setVisibility(0);
        int i2 = this.n;
        qt qtVar2 = this.w;
        if (i2 == 0 && (this.t || z2)) {
            this.d.setTranslationY(0.0f);
            float f2 = -this.d.getHeight();
            if (z2) {
                this.d.getLocationInWindow(new int[]{0, 0});
                f2 -= r12[1];
            }
            this.d.setTranslationY(f2);
            et etVar4 = new et();
            dt dtVarA3 = os.a(this.d);
            dtVarA3.e(0.0f);
            final View view3 = (View) dtVarA3.a.get();
            if (view3 != null) {
                view3.animate().setUpdateListener(e0Var != null ? new ValueAnimator.AnimatorUpdateListener(view3) { // from class: ct
                    @Override // android.animation.ValueAnimator.AnimatorUpdateListener
                    public final void onAnimationUpdate(ValueAnimator valueAnimator) {
                        ((View) ((st) this.a.b).d.getParent()).invalidate();
                    }
                } : null);
            }
            boolean z7 = etVar4.e;
            ArrayList arrayList2 = etVar4.a;
            if (!z7) {
                arrayList2.add(dtVarA3);
            }
            if (this.o && view != null) {
                view.setTranslationY(f2);
                dt dtVarA4 = os.a(view);
                dtVarA4.e(0.0f);
                if (!etVar4.e) {
                    arrayList2.add(dtVarA4);
                }
            }
            boolean z8 = etVar4.e;
            if (!z8) {
                etVar4.c = z;
            }
            if (!z8) {
                etVar4.b = 250L;
            }
            if (!z8) {
                etVar4.d = qtVar2;
            }
            this.s = etVar4;
            etVar4.b();
        } else {
            this.d.setAlpha(1.0f);
            this.d.setTranslationY(0.0f);
            if (this.o && view != null) {
                view.setTranslationY(0.0f);
            }
            qtVar2.b();
        }
        ActionBarOverlayLayout actionBarOverlayLayout = this.c;
        if (actionBarOverlayLayout != null) {
            WeakHashMap weakHashMap = os.a;
            bs.c(actionBarOverlayLayout);
        }
    }

    public st(Dialog dialog) {
        new ArrayList();
        this.m = new ArrayList();
        this.n = 0;
        this.o = true;
        this.r = true;
        this.v = new qt(this, 0);
        this.w = new qt(this, 1);
        this.x = new e0(20, this);
        q(dialog.getWindow().getDecorView());
    }
}
