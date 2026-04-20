package defpackage;

import android.os.Build;
import android.view.View;
import java.util.Objects;

/* JADX INFO: compiled from: r8-map-id-51b54c9e59b63e0fb0abe04daff984b4fb85d07ef3fcb7973595bce215c10515 */
/* JADX INFO: loaded from: classes.dex */
public class du {
    public static final eu b;
    public final eu a;

    static {
        int i = Build.VERSION.SDK_INT;
        b = (i >= 30 ? new wt() : i >= 29 ? new vt() : new ut()).b().a.a().a.b().a.c();
    }

    public du(eu euVar) {
        this.a = euVar;
    }

    public eu a() {
        return this.a;
    }

    public eu b() {
        return this.a;
    }

    public eu c() {
        return this.a;
    }

    public ba e() {
        return null;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof du)) {
            return false;
        }
        du duVar = (du) obj;
        return j() == duVar.j() && i() == duVar.i() && Objects.equals(g(), duVar.g()) && Objects.equals(f(), duVar.f()) && Objects.equals(e(), duVar.e());
    }

    public jf f() {
        return jf.e;
    }

    public jf g() {
        return jf.e;
    }

    public eu h(int i, int i2, int i3, int i4) {
        return b;
    }

    public int hashCode() {
        return Objects.hash(Boolean.valueOf(j()), Boolean.valueOf(i()), g(), f(), e());
    }

    public boolean i() {
        return false;
    }

    public boolean j() {
        return false;
    }

    public void d(View view) {
    }

    public void k(jf[] jfVarArr) {
    }

    public void l(eu euVar) {
    }

    public void m(jf jfVar) {
    }
}
