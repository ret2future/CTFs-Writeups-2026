package defpackage;

import android.content.Context;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import androidx.appcompat.widget.ActionBarContextView;
import java.lang.ref.WeakReference;

/* JADX INFO: compiled from: r8-map-id-51b54c9e59b63e0fb0abe04daff984b4fb85d07ef3fcb7973595bce215c10515 */
/* JADX INFO: loaded from: classes.dex */
public final class zo extends j1 implements ei {
    public Context c;
    public ActionBarContextView d;
    public z3 e;
    public WeakReference f;
    public boolean g;
    public gi h;

    @Override // defpackage.j1
    public final void a() {
        if (this.g) {
            return;
        }
        this.g = true;
        this.e.f(this);
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
        return this.h;
    }

    @Override // defpackage.j1
    public final MenuInflater d() {
        return new fp(this.d.getContext());
    }

    @Override // defpackage.j1
    public final CharSequence e() {
        return this.d.getSubtitle();
    }

    @Override // defpackage.j1
    public final CharSequence f() {
        return this.d.getTitle();
    }

    @Override // defpackage.j1
    public final void g() {
        this.e.e(this, this.h);
    }

    @Override // defpackage.j1
    public final boolean h() {
        return this.d.s;
    }

    @Override // defpackage.j1
    public final void i(View view) {
        this.d.setCustomView(view);
        this.f = view != null ? new WeakReference(view) : null;
    }

    @Override // defpackage.j1
    public final void j(int i) {
        l(this.c.getString(i));
    }

    @Override // defpackage.ei
    public final void k(gi giVar) {
        g();
        e1 e1Var = this.d.d;
        if (e1Var != null) {
            e1Var.l();
        }
    }

    @Override // defpackage.j1
    public final void l(CharSequence charSequence) {
        this.d.setSubtitle(charSequence);
    }

    @Override // defpackage.j1
    public final void m(int i) {
        o(this.c.getString(i));
    }

    @Override // defpackage.ei
    public final boolean n(gi giVar, MenuItem menuItem) {
        return ((i1) this.e.b).c(this, menuItem);
    }

    @Override // defpackage.j1
    public final void o(CharSequence charSequence) {
        this.d.setTitle(charSequence);
    }

    @Override // defpackage.j1
    public final void p(boolean z) {
        this.b = z;
        this.d.setTitleOptional(z);
    }
}
