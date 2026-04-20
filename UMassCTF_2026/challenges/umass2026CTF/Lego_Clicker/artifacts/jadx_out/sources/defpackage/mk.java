package defpackage;

/* JADX INFO: compiled from: r8-map-id-51b54c9e59b63e0fb0abe04daff984b4fb85d07ef3fcb7973595bce215c10515 */
/* JADX INFO: loaded from: classes.dex */
public final class mk {
    public final Object[] a;
    public int b;

    public mk(int i) {
        if (i > 0) {
            this.a = new Object[i];
        } else {
            z6.f("The max pool size must be > 0");
            throw null;
        }
    }

    public Object a() {
        int i = this.b;
        if (i <= 0) {
            return null;
        }
        int i2 = i - 1;
        Object[] objArr = this.a;
        Object obj = objArr[i2];
        obj.getClass();
        objArr[i2] = null;
        this.b--;
        return obj;
    }

    public void b(v5 v5Var) {
        int i = this.b;
        Object[] objArr = this.a;
        if (i < objArr.length) {
            objArr[i] = v5Var;
            this.b = i + 1;
        }
    }

    public void c(Object obj) {
        obj.getClass();
        int i = this.b;
        int i2 = 0;
        while (true) {
            Object[] objArr = this.a;
            if (i2 >= i) {
                int i3 = this.b;
                if (i3 < objArr.length) {
                    objArr[i3] = obj;
                    this.b = i3 + 1;
                    return;
                }
                return;
            }
            if (objArr[i2] == obj) {
                z6.o("Already in the pool!");
                return;
            }
            i2++;
        }
    }

    public mk() {
        this.a = new Object[256];
    }
}
