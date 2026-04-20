package defpackage;

import android.content.Context;
import android.content.ContextWrapper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import androidx.appcompat.view.menu.ExpandedMenuView;

/* JADX INFO: compiled from: r8-map-id-51b54c9e59b63e0fb0abe04daff984b4fb85d07ef3fcb7973595bce215c10515 */
/* JADX INFO: loaded from: classes.dex */
public final class zg implements bj, AdapterView.OnItemClickListener {
    public Context a;
    public LayoutInflater b;
    public gi c;
    public ExpandedMenuView d;
    public aj e;
    public yg f;

    public zg(ContextWrapper contextWrapper) {
        this.a = contextWrapper;
        this.b = LayoutInflater.from(contextWrapper);
    }

    @Override // defpackage.bj
    public final void a(gi giVar, boolean z) {
        aj ajVar = this.e;
        if (ajVar != null) {
            ajVar.a(giVar, z);
        }
    }

    @Override // defpackage.bj
    public final boolean c() {
        return false;
    }

    @Override // defpackage.bj
    public final boolean d(oi oiVar) {
        return false;
    }

    @Override // defpackage.bj
    public final void e(aj ajVar) {
        throw null;
    }

    @Override // defpackage.bj
    public final boolean g(oi oiVar) {
        return false;
    }

    @Override // defpackage.bj
    public final void h() {
        yg ygVar = this.f;
        if (ygVar != null) {
            ygVar.notifyDataSetChanged();
        }
    }

    @Override // defpackage.bj
    public final void i(Context context, gi giVar) {
        if (this.a != null) {
            this.a = context;
            if (this.b == null) {
                this.b = LayoutInflater.from(context);
            }
        }
        this.c = giVar;
        yg ygVar = this.f;
        if (ygVar != null) {
            ygVar.notifyDataSetChanged();
        }
    }

    @Override // defpackage.bj
    public final boolean k(bp bpVar) {
        boolean zHasVisibleItems = bpVar.hasVisibleItems();
        Context context = bpVar.a;
        if (!zHasVisibleItems) {
            return false;
        }
        hi hiVar = new hi();
        hiVar.a = bpVar;
        g2 g2Var = new g2(context);
        c2 c2Var = (c2) g2Var.b;
        zg zgVar = new zg(c2Var.a);
        hiVar.c = zgVar;
        zgVar.e = hiVar;
        bpVar.b(zgVar, context);
        zg zgVar2 = hiVar.c;
        if (zgVar2.f == null) {
            zgVar2.f = new yg(zgVar2);
        }
        c2Var.m = zgVar2.f;
        c2Var.n = hiVar;
        View view = bpVar.o;
        if (view != null) {
            c2Var.e = view;
        } else {
            c2Var.c = bpVar.n;
            c2Var.d = bpVar.m;
        }
        c2Var.l = hiVar;
        h2 h2VarA = g2Var.a();
        hiVar.b = h2VarA;
        h2VarA.setOnDismissListener(hiVar);
        WindowManager.LayoutParams attributes = hiVar.b.getWindow().getAttributes();
        attributes.type = 1003;
        attributes.flags |= 131072;
        hiVar.b.show();
        aj ajVar = this.e;
        if (ajVar == null) {
            return true;
        }
        ajVar.s(bpVar);
        return true;
    }

    @Override // android.widget.AdapterView.OnItemClickListener
    public final void onItemClick(AdapterView adapterView, View view, int i, long j) {
        this.c.q(this.f.getItem(i), this, 0);
    }
}
