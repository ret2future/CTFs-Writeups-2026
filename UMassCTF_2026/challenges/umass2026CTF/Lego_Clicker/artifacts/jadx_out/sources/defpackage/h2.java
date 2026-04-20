package defpackage;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.TypedValue;
import android.view.ContextThemeWrapper;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.TextView;
import android.window.OnBackInvokedDispatcher;
import androidx.activity.c;
import androidx.appcompat.app.AlertController$RecycleListView;
import androidx.core.widget.NestedScrollView;
import androidx.lifecycle.a;
import com.example.LegoClicker.R;
import java.util.WeakHashMap;

/* JADX INFO: compiled from: r8-map-id-51b54c9e59b63e0fb0abe04daff984b4fb85d07ef3fcb7973595bce215c10515 */
/* JADX INFO: loaded from: classes.dex */
public final class h2 extends Dialog implements DialogInterface, p2, kg, zn {
    public a a;
    public final yn b;
    public final c c;
    public n3 d;
    public final o3 e;
    public final f2 f;

    /* JADX WARN: Type inference failed for: r2v4, types: [o3] */
    public h2(ContextThemeWrapper contextThemeWrapper, int i) {
        int i2;
        int iG = g(contextThemeWrapper, i);
        if (iG == 0) {
            TypedValue typedValue = new TypedValue();
            contextThemeWrapper.getTheme().resolveAttribute(R.attr.dialogTheme, typedValue, true);
            i2 = typedValue.resourceId;
        } else {
            i2 = iG;
        }
        super(contextThemeWrapper, i2);
        this.b = new yn(this);
        this.c = new c(new k1(3, this));
        this.e = new vf() { // from class: o3
            @Override // defpackage.vf
            public final boolean superDispatchKeyEvent(KeyEvent keyEvent) {
                return this.a.i(keyEvent);
            }
        };
        z2 z2VarB = b();
        if (iG == 0) {
            TypedValue typedValue2 = new TypedValue();
            contextThemeWrapper.getTheme().resolveAttribute(R.attr.dialogTheme, typedValue2, true);
            iG = typedValue2.resourceId;
        }
        ((n3) z2VarB).T = iG;
        z2VarB.d();
        this.f = new f2(getContext(), this, getWindow());
    }

    public static void a(h2 h2Var) {
        super.onBackPressed();
    }

    public static int g(Context context, int i) {
        if (((i >>> 24) & 255) >= 1) {
            return i;
        }
        TypedValue typedValue = new TypedValue();
        context.getTheme().resolveAttribute(R.attr.alertDialogTheme, typedValue, true);
        return typedValue.resourceId;
    }

    @Override // android.app.Dialog
    public final void addContentView(View view, ViewGroup.LayoutParams layoutParams) {
        n3 n3Var = (n3) b();
        n3Var.x();
        ((ViewGroup) n3Var.A.findViewById(android.R.id.content)).addView(view, layoutParams);
        n3Var.m.a(n3Var.l.getCallback());
    }

    public final z2 b() {
        if (this.d == null) {
            x2 x2Var = z2.a;
            this.d = new n3(getContext(), getWindow(), this, this);
        }
        return this.d;
    }

    public final void c() {
        View decorView = getWindow().getDecorView();
        decorView.getClass();
        decorView.setTag(R.id.view_tree_lifecycle_owner, this);
        View decorView2 = getWindow().getDecorView();
        decorView2.getClass();
        decorView2.setTag(R.id.view_tree_saved_state_registry_owner, this);
        View decorView3 = getWindow().getDecorView();
        decorView3.getClass();
        decorView3.setTag(R.id.view_tree_on_back_pressed_dispatcher_owner, this);
    }

    public final void d(Bundle bundle) {
        super.onCreate(bundle);
        if (Build.VERSION.SDK_INT >= 33) {
            OnBackInvokedDispatcher onBackInvokedDispatcher = getOnBackInvokedDispatcher();
            onBackInvokedDispatcher.getClass();
            c cVar = this.c;
            cVar.getClass();
            cVar.e = onBackInvokedDispatcher;
            cVar.c();
        }
        this.b.b(bundle);
        a aVar = this.a;
        if (aVar == null) {
            aVar = new a(this);
            this.a = aVar;
        }
        aVar.e(dg.ON_CREATE);
    }

    @Override // android.app.Dialog, android.content.DialogInterface
    public final void dismiss() {
        super.dismiss();
        b().e();
    }

    @Override // android.app.Dialog, android.view.Window.Callback
    public final boolean dispatchKeyEvent(KeyEvent keyEvent) {
        return hb.v(this.e, getWindow().getDecorView(), this, keyEvent);
    }

    public final void e(Bundle bundle) {
        b().a();
        d(bundle);
        b().d();
    }

    public final void f() {
        a aVar = this.a;
        if (aVar == null) {
            aVar = new a(this);
            this.a = aVar;
        }
        aVar.e(dg.ON_DESTROY);
        this.a = null;
        super.onStop();
    }

    @Override // android.app.Dialog
    public final View findViewById(int i) {
        n3 n3Var = (n3) b();
        n3Var.x();
        return n3Var.l.findViewById(i);
    }

    @Override // defpackage.kg
    public final fg getLifecycle() {
        a aVar = this.a;
        if (aVar != null) {
            return aVar;
        }
        a aVar2 = new a(this);
        this.a = aVar2;
        return aVar2;
    }

    @Override // defpackage.zn
    public final xn getSavedStateRegistry() {
        return this.b.b;
    }

    public final void h(CharSequence charSequence) {
        super.setTitle(charSequence);
        b().l(charSequence);
    }

    public final boolean i(KeyEvent keyEvent) {
        return super.dispatchKeyEvent(keyEvent);
    }

    @Override // android.app.Dialog
    public final void invalidateOptionsMenu() {
        b().b();
    }

    @Override // android.app.Dialog
    public final void onBackPressed() {
        this.c.b();
    }

    @Override // android.app.Dialog
    public final void onCreate(Bundle bundle) {
        int i;
        ListAdapter listAdapter;
        View viewFindViewById;
        e(bundle);
        f2 f2Var = this.f;
        f2Var.b.setContentView(f2Var.x);
        Context context = f2Var.a;
        Window window = f2Var.c;
        View viewFindViewById2 = window.findViewById(R.id.parentPanel);
        View viewFindViewById3 = viewFindViewById2.findViewById(R.id.topPanel);
        View viewFindViewById4 = viewFindViewById2.findViewById(R.id.contentPanel);
        View viewFindViewById5 = viewFindViewById2.findViewById(R.id.buttonPanel);
        ViewGroup viewGroup = (ViewGroup) viewFindViewById2.findViewById(R.id.customPanel);
        window.setFlags(131072, 131072);
        viewGroup.setVisibility(8);
        View viewFindViewById6 = viewGroup.findViewById(R.id.topPanel);
        View viewFindViewById7 = viewGroup.findViewById(R.id.contentPanel);
        View viewFindViewById8 = viewGroup.findViewById(R.id.buttonPanel);
        ViewGroup viewGroupA = f2.a(viewFindViewById6, viewFindViewById3);
        ViewGroup viewGroupA2 = f2.a(viewFindViewById7, viewFindViewById4);
        ViewGroup viewGroupA3 = f2.a(viewFindViewById8, viewFindViewById5);
        NestedScrollView nestedScrollView = (NestedScrollView) window.findViewById(R.id.scrollView);
        f2Var.p = nestedScrollView;
        nestedScrollView.setFocusable(false);
        f2Var.p.setNestedScrollingEnabled(false);
        TextView textView = (TextView) viewGroupA2.findViewById(android.R.id.message);
        f2Var.t = textView;
        if (textView != null) {
            String str = f2Var.e;
            if (str != null) {
                textView.setText(str);
            } else {
                textView.setVisibility(8);
                f2Var.p.removeView(f2Var.t);
                if (f2Var.f != null) {
                    ViewGroup viewGroup2 = (ViewGroup) f2Var.p.getParent();
                    int iIndexOfChild = viewGroup2.indexOfChild(f2Var.p);
                    viewGroup2.removeViewAt(iIndexOfChild);
                    viewGroup2.addView(f2Var.f, iIndexOfChild, new ViewGroup.LayoutParams(-1, -1));
                } else {
                    viewGroupA2.setVisibility(8);
                }
            }
        }
        Button button = (Button) viewGroupA3.findViewById(android.R.id.button1);
        f2Var.g = button;
        q0 q0Var = f2Var.D;
        button.setOnClickListener(q0Var);
        boolean zIsEmpty = TextUtils.isEmpty(f2Var.h);
        Button button2 = f2Var.g;
        if (zIsEmpty) {
            button2.setVisibility(8);
            i = 0;
        } else {
            button2.setText(f2Var.h);
            f2Var.g.setVisibility(0);
            i = 1;
        }
        Button button3 = (Button) viewGroupA3.findViewById(android.R.id.button2);
        f2Var.j = button3;
        button3.setOnClickListener(q0Var);
        boolean zIsEmpty2 = TextUtils.isEmpty(f2Var.k);
        Button button4 = f2Var.j;
        if (zIsEmpty2) {
            button4.setVisibility(8);
        } else {
            button4.setText(f2Var.k);
            f2Var.j.setVisibility(0);
            i |= 2;
        }
        Button button5 = (Button) viewGroupA3.findViewById(android.R.id.button3);
        f2Var.m = button5;
        button5.setOnClickListener(q0Var);
        boolean zIsEmpty3 = TextUtils.isEmpty(f2Var.n);
        Button button6 = f2Var.m;
        if (zIsEmpty3) {
            button6.setVisibility(8);
        } else {
            button6.setText(f2Var.n);
            f2Var.m.setVisibility(0);
            i |= 4;
        }
        TypedValue typedValue = new TypedValue();
        context.getTheme().resolveAttribute(R.attr.alertDialogCenterButtons, typedValue, true);
        if (typedValue.data != 0) {
            if (i == 1) {
                Button button7 = f2Var.g;
                LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) button7.getLayoutParams();
                layoutParams.gravity = 1;
                layoutParams.weight = 0.5f;
                button7.setLayoutParams(layoutParams);
            } else if (i == 2) {
                Button button8 = f2Var.j;
                LinearLayout.LayoutParams layoutParams2 = (LinearLayout.LayoutParams) button8.getLayoutParams();
                layoutParams2.gravity = 1;
                layoutParams2.weight = 0.5f;
                button8.setLayoutParams(layoutParams2);
            } else if (i == 4) {
                Button button9 = f2Var.m;
                LinearLayout.LayoutParams layoutParams3 = (LinearLayout.LayoutParams) button9.getLayoutParams();
                layoutParams3.gravity = 1;
                layoutParams3.weight = 0.5f;
                button9.setLayoutParams(layoutParams3);
            }
        }
        if (i == 0) {
            viewGroupA3.setVisibility(8);
        }
        if (f2Var.u != null) {
            viewGroupA.addView(f2Var.u, 0, new ViewGroup.LayoutParams(-1, -2));
            window.findViewById(R.id.title_template).setVisibility(8);
        } else {
            f2Var.r = (ImageView) window.findViewById(android.R.id.icon);
            if (TextUtils.isEmpty(f2Var.d) || !f2Var.B) {
                window.findViewById(R.id.title_template).setVisibility(8);
                f2Var.r.setVisibility(8);
                viewGroupA.setVisibility(8);
            } else {
                TextView textView2 = (TextView) window.findViewById(R.id.alertTitle);
                f2Var.s = textView2;
                textView2.setText(f2Var.d);
                Drawable drawable = f2Var.q;
                if (drawable != null) {
                    f2Var.r.setImageDrawable(drawable);
                } else {
                    f2Var.s.setPadding(f2Var.r.getPaddingLeft(), f2Var.r.getPaddingTop(), f2Var.r.getPaddingRight(), f2Var.r.getPaddingBottom());
                    f2Var.r.setVisibility(8);
                }
            }
        }
        boolean z = viewGroup.getVisibility() != 8;
        int i2 = (viewGroupA == null || viewGroupA.getVisibility() == 8) ? 0 : 1;
        boolean z2 = viewGroupA3.getVisibility() != 8;
        if (!z2 && (viewFindViewById = viewGroupA2.findViewById(R.id.textSpacerNoButtons)) != null) {
            viewFindViewById.setVisibility(0);
        }
        if (i2 != 0) {
            NestedScrollView nestedScrollView2 = f2Var.p;
            if (nestedScrollView2 != null) {
                nestedScrollView2.setClipToPadding(true);
            }
            View viewFindViewById9 = (f2Var.e == null && f2Var.f == null) ? null : viewGroupA.findViewById(R.id.titleDividerNoCustom);
            if (viewFindViewById9 != null) {
                viewFindViewById9.setVisibility(0);
            }
        } else {
            View viewFindViewById10 = viewGroupA2.findViewById(R.id.textSpacerNoTitle);
            if (viewFindViewById10 != null) {
                viewFindViewById10.setVisibility(0);
            }
        }
        AlertController$RecycleListView alertController$RecycleListView = f2Var.f;
        if (alertController$RecycleListView != null && (!z2 || i2 == 0)) {
            alertController$RecycleListView.setPadding(alertController$RecycleListView.getPaddingLeft(), i2 != 0 ? alertController$RecycleListView.getPaddingTop() : alertController$RecycleListView.a, alertController$RecycleListView.getPaddingRight(), z2 ? alertController$RecycleListView.getPaddingBottom() : alertController$RecycleListView.b);
        }
        if (!z) {
            View view = f2Var.f;
            if (view == null) {
                view = f2Var.p;
            }
            if (view != null) {
                int i3 = z2 ? 2 : 0;
                View viewFindViewById11 = window.findViewById(R.id.scrollIndicatorUp);
                View viewFindViewById12 = window.findViewById(R.id.scrollIndicatorDown);
                WeakHashMap weakHashMap = os.a;
                es.d(view, i2 | i3, 3);
                if (viewFindViewById11 != null) {
                    viewGroupA2.removeView(viewFindViewById11);
                }
                if (viewFindViewById12 != null) {
                    viewGroupA2.removeView(viewFindViewById12);
                }
            }
        }
        AlertController$RecycleListView alertController$RecycleListView2 = f2Var.f;
        if (alertController$RecycleListView2 == null || (listAdapter = f2Var.v) == null) {
            return;
        }
        alertController$RecycleListView2.setAdapter(listAdapter);
        int i4 = f2Var.w;
        if (i4 > -1) {
            alertController$RecycleListView2.setItemChecked(i4, true);
            alertController$RecycleListView2.setSelection(i4);
        }
    }

    @Override // android.app.Dialog, android.view.KeyEvent.Callback
    public final boolean onKeyDown(int i, KeyEvent keyEvent) {
        NestedScrollView nestedScrollView = this.f.p;
        if (nestedScrollView == null || !nestedScrollView.j(keyEvent)) {
            return super.onKeyDown(i, keyEvent);
        }
        return true;
    }

    @Override // android.app.Dialog, android.view.KeyEvent.Callback
    public final boolean onKeyUp(int i, KeyEvent keyEvent) {
        NestedScrollView nestedScrollView = this.f.p;
        if (nestedScrollView == null || !nestedScrollView.j(keyEvent)) {
            return super.onKeyUp(i, keyEvent);
        }
        return true;
    }

    @Override // android.app.Dialog
    public final Bundle onSaveInstanceState() {
        Bundle bundleOnSaveInstanceState = super.onSaveInstanceState();
        bundleOnSaveInstanceState.getClass();
        this.b.c(bundleOnSaveInstanceState);
        return bundleOnSaveInstanceState;
    }

    @Override // android.app.Dialog
    public final void onStart() {
        super.onStart();
        a aVar = this.a;
        if (aVar == null) {
            aVar = new a(this);
            this.a = aVar;
        }
        aVar.e(dg.ON_RESUME);
    }

    @Override // android.app.Dialog
    public final void onStop() {
        f();
        n3 n3Var = (n3) b();
        n3Var.B();
        o0 o0Var = n3Var.o;
        if (o0Var != null) {
            o0Var.m(false);
        }
    }

    @Override // defpackage.p2
    public final j1 onWindowStartingSupportActionMode(i1 i1Var) {
        return null;
    }

    @Override // android.app.Dialog
    public final void setContentView(int i) {
        c();
        b().h(i);
    }

    @Override // android.app.Dialog
    public final void setTitle(int i) {
        super.setTitle(i);
        b().l(getContext().getString(i));
    }

    @Override // android.app.Dialog
    public final void setContentView(View view) {
        c();
        b().i(view);
    }

    @Override // android.app.Dialog
    public final void setContentView(View view, ViewGroup.LayoutParams layoutParams) {
        c();
        b().j(view, layoutParams);
    }

    @Override // android.app.Dialog
    public final void setTitle(CharSequence charSequence) {
        h(charSequence);
        f2 f2Var = this.f;
        f2Var.d = charSequence;
        TextView textView = f2Var.s;
        if (textView != null) {
            textView.setText(charSequence);
        }
    }

    @Override // defpackage.p2
    public final void onSupportActionModeFinished(j1 j1Var) {
    }

    @Override // defpackage.p2
    public final void onSupportActionModeStarted(j1 j1Var) {
    }
}
