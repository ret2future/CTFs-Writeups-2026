package defpackage;

/* JADX INFO: compiled from: r8-map-id-51b54c9e59b63e0fb0abe04daff984b4fb85d07ef3fcb7973595bce215c10515 */
/* JADX INFO: loaded from: classes.dex */
public final class ue extends mt {
    @Override // defpackage.t9
    public final void a(t9 t9Var) {
        v9 v9Var = this.h;
        if (v9Var.c && !v9Var.j) {
            v9Var.d((int) ((((v9) v9Var.l.get(0)).g * ((te) this.b).p0) + 0.5f));
        }
    }

    @Override // defpackage.mt
    public final void d() {
        k8 k8Var = this.b;
        te teVar = (te) k8Var;
        int i = teVar.q0;
        int i2 = teVar.r0;
        int i3 = teVar.t0;
        v9 v9Var = this.h;
        if (i3 == 1) {
            if (i != -1) {
                v9Var.l.add(k8Var.S.d.h);
                this.b.S.d.h.k.add(v9Var);
                v9Var.f = i;
            } else if (i2 != -1) {
                v9Var.l.add(k8Var.S.d.i);
                this.b.S.d.i.k.add(v9Var);
                v9Var.f = -i2;
            } else {
                v9Var.b = true;
                v9Var.l.add(k8Var.S.d.i);
                this.b.S.d.i.k.add(v9Var);
            }
            m(this.b.d.h);
            m(this.b.d.i);
            return;
        }
        if (i != -1) {
            v9Var.l.add(k8Var.S.e.h);
            this.b.S.e.h.k.add(v9Var);
            v9Var.f = i;
        } else if (i2 != -1) {
            v9Var.l.add(k8Var.S.e.i);
            this.b.S.e.i.k.add(v9Var);
            v9Var.f = -i2;
        } else {
            v9Var.b = true;
            v9Var.l.add(k8Var.S.e.i);
            this.b.S.e.i.k.add(v9Var);
        }
        m(this.b.e.h);
        m(this.b.e.i);
    }

    @Override // defpackage.mt
    public final void e() {
        k8 k8Var = this.b;
        int i = ((te) k8Var).t0;
        v9 v9Var = this.h;
        if (i == 1) {
            k8Var.X = v9Var.g;
        } else {
            k8Var.Y = v9Var.g;
        }
    }

    @Override // defpackage.mt
    public final void f() {
        this.h.c();
    }

    @Override // defpackage.mt
    public final boolean k() {
        return false;
    }

    public final void m(v9 v9Var) {
        v9 v9Var2 = this.h;
        v9Var2.k.add(v9Var);
        v9Var.l.add(v9Var2);
    }
}
