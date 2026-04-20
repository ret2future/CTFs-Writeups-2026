package defpackage;

import android.content.Context;
import android.content.IntentFilter;
import android.view.MenuItem;

/* JADX INFO: compiled from: r8-map-id-51b54c9e59b63e0fb0abe04daff984b4fb85d07ef3fcb7973595bce215c10515 */
/* JADX INFO: loaded from: classes.dex */
public abstract class k3 {
    public Object a;
    public Object b;

    public k3(Context context) {
        this.a = context;
    }

    public void c() {
        j3 j3Var = (j3) this.a;
        if (j3Var != null) {
            try {
                ((n3) this.b).k.unregisterReceiver(j3Var);
            } catch (IllegalArgumentException unused) {
            }
            this.a = null;
        }
    }

    public abstract IntentFilter d();

    public abstract int e();

    public MenuItem f(MenuItem menuItem) {
        if (!(menuItem instanceof gp)) {
            return menuItem;
        }
        gp gpVar = (gp) menuItem;
        if (((ko) this.b) == null) {
            this.b = new ko();
        }
        MenuItem menuItem2 = (MenuItem) ((ko) this.b).getOrDefault(gpVar, null);
        if (menuItem2 != null) {
            return menuItem2;
        }
        si siVar = new si((Context) this.a, gpVar);
        ((ko) this.b).put(gpVar, siVar);
        return siVar;
    }

    public abstract void g();

    public void h() {
        c();
        IntentFilter intentFilterD = d();
        if (intentFilterD.countActions() == 0) {
            return;
        }
        if (((j3) this.a) == null) {
            this.a = new j3(this);
        }
        ((n3) this.b).k.registerReceiver((j3) this.a, intentFilterD);
    }

    public k3(n3 n3Var) {
        this.b = n3Var;
    }
}
