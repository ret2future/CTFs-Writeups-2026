package defpackage;

import java.io.Serializable;

/* JADX INFO: compiled from: r8-map-id-51b54c9e59b63e0fb0abe04daff984b4fb85d07ef3fcb7973595bce215c10515 */
/* JADX INFO: loaded from: classes.dex */
public final class hp implements Serializable {
    public zj a;
    public volatile Object b = fr.b;
    public final Object c = this;

    public hp(zj zjVar) {
        this.a = zjVar;
    }

    public final Object a() {
        Object objB;
        Object obj = this.b;
        fr frVar = fr.b;
        if (obj != frVar) {
            return obj;
        }
        synchronized (this.c) {
            objB = this.b;
            if (objB == frVar) {
                zj zjVar = this.a;
                zjVar.getClass();
                objB = d.B((vc) zjVar.b);
                this.b = objB;
                this.a = null;
            }
        }
        return objB;
    }

    public final String toString() {
        return this.b != fr.b ? String.valueOf(a()) : "Lazy value not initialized yet.";
    }
}
