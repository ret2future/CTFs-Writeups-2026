package defpackage;

import android.content.Context;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import androidx.appcompat.widget.ActionBarContextView;
import java.lang.ref.WeakReference;

/* JADX INFO: compiled from: r8-map-id-51b54c9e59b63e0fb0abe04daff984b4fb85d07ef3fcb7973595bce215c10515 */
/* JADX INFO: loaded from: classes.dex */
public final class rt extends j1 implements ei {
    public final Context c;
    public final gi d;
    public z3 e;
    public WeakReference f;
    public final /* synthetic */ st g;

    public rt(st stVar, Context context, z3 z3Var) {
        this.g = stVar;
        this.c = context;
        this.e = z3Var;
        gi giVar = new gi(context);
        giVar.l = 1;
        this.d = giVar;
        giVar.e = this;
    }

    @Override // defpackage.j1
    public final void a() {
        st stVar = this.g;
        if (stVar.i != this) {
            return;
        }
        if (stVar.p) {
            stVar.j = this;
            stVar.k = this.e;
        } else {
            this.e.f(this);
        }
        this.e = null;
        stVar.p(false);
        ActionBarContextView actionBarContextView = stVar.f;
        if (actionBarContextView.k == null) {
            actionBarContextView.e();
        }
        stVar.c.setHideOnContentScrollEnabled(stVar.u);
        stVar.i = null;
    }

    @Override // defpackage.j1
    public final View b() {
        WeakReference weakReference = this.f;
        if (weakReference != null) {
            return (View) weakReference.get();
        }
        return null;
    }

    @Override // defpackage.j1
    public final gi c() {
        return this.d;
    }

    @Override // defpackage.j1
    public final MenuInflater d() {
        return new fp(this.c);
    }

    @Override // defpackage.j1
    public final CharSequence e() {
        return this.g.f.getSubtitle();
    }

    @Override // defpackage.j1
    public final CharSequence f() {
        return this.g.f.getTitle();
    }

    @Override // defpackage.j1
    public final void g() {
        if (this.g.i != this) {
            return;
        }
        gi giVar = this.d;
        giVar.w();
        try {
            this.e.e(this, giVar);
        } finally {
            giVar.v();
        }
    }

    @Override // defpackage.j1
    public final boolean h() {
        return this.g.f.s;
    }

    @Override // defpackage.j1
    public final void i(View view) {
        this.g.f.setCustomView(view);
        this.f = new WeakReference(view);
    }

    @Override // defpackage.j1
    public final void j(int i) {
        l(this.g.a.getResources().getString(i));
    }

    @Override // defpackage.ei
    public final void k(gi giVar) {
        if (this.e == null) {
            return;
        }
        g();
        e1 e1Var = this.g.f.d;
        if (e1Var != null) {
            e1Var.l();
        }
    }

    @Override // defpackage.j1
    public final void l(CharSequence charSequence) {
        this.g.f.setSubtitle(charSequence);
    }

    @Override // defpackage.j1
    public final void m(int i) {
        o(this.g.a.getResources().getString(i));
    }

    @Override // defpackage.ei
    public final boolean n(gi giVar, MenuItem menuItem) {
        z3 z3Var = this.e;
        if (z3Var != null) {
            return ((i1) z3Var.b).c(this, menuItem);
        }
        return false;
    }

    @Override // defpackage.j1
    public final void o(CharSequence charSequence) {
        this.g.f.setTitle(charSequence);
    }

    @Override // defpackage.j1
    public final void p(boolean z) {
        this.b = z;
        this.g.f.setTitleOptional(z);
    }
}
