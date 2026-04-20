package defpackage;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;

/* JADX INFO: compiled from: r8-map-id-51b54c9e59b63e0fb0abe04daff984b4fb85d07ef3fcb7973595bce215c10515 */
/* JADX INFO: loaded from: classes.dex */
public final class bp extends gi implements SubMenu {
    public final oi A;
    public final gi z;

    public bp(Context context, gi giVar, oi oiVar) {
        super(context);
        this.z = giVar;
        this.A = oiVar;
    }

    @Override // defpackage.gi
    public final boolean d(oi oiVar) {
        return this.z.d(oiVar);
    }

    @Override // defpackage.gi
    public final boolean e(gi giVar, MenuItem menuItem) {
        return super.e(giVar, menuItem) || this.z.e(giVar, menuItem);
    }

    @Override // defpackage.gi
    public final boolean f(oi oiVar) {
        return this.z.f(oiVar);
    }

    @Override // android.view.SubMenu
    public final MenuItem getItem() {
        return this.A;
    }

    @Override // defpackage.gi
    public final String j() {
        oi oiVar = this.A;
        int i = oiVar != null ? oiVar.a : 0;
        if (i == 0) {
            return null;
        }
        return "android:menu:actionviewstates:" + i;
    }

    @Override // defpackage.gi
    public final gi k() {
        return this.z.k();
    }

    @Override // defpackage.gi
    public final boolean m() {
        return this.z.m();
    }

    @Override // defpackage.gi
    public final boolean n() {
        return this.z.n();
    }

    @Override // defpackage.gi
    public final boolean o() {
        return this.z.o();
    }

    @Override // defpackage.gi, android.view.Menu
    public final void setGroupDividerEnabled(boolean z) {
        this.z.setGroupDividerEnabled(z);
    }

    @Override // android.view.SubMenu
    public final SubMenu setHeaderIcon(Drawable drawable) {
        u(0, null, 0, drawable, null);
        return this;
    }

    @Override // android.view.SubMenu
    public final SubMenu setHeaderTitle(CharSequence charSequence) {
        u(0, charSequence, 0, null, null);
        return this;
    }

    @Override // android.view.SubMenu
    public final SubMenu setHeaderView(View view) {
        u(0, null, 0, null, view);
        return this;
    }

    @Override // android.view.SubMenu
    public final SubMenu setIcon(Drawable drawable) {
        this.A.setIcon(drawable);
        return this;
    }

    @Override // defpackage.gi, android.view.Menu
    public final void setQwertyMode(boolean z) {
        this.z.setQwertyMode(z);
    }

    @Override // android.view.SubMenu
    public final SubMenu setIcon(int i) {
        this.A.setIcon(i);
        return this;
    }

    @Override // android.view.SubMenu
    public final SubMenu setHeaderIcon(int i) {
        u(0, null, i, null, null);
        return this;
    }

    @Override // android.view.SubMenu
    public final SubMenu setHeaderTitle(int i) {
        u(i, null, 0, null, null);
        return this;
    }
}
