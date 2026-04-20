package defpackage;

import android.content.Context;
import java.util.concurrent.Callable;

/* JADX INFO: compiled from: r8-map-id-51b54c9e59b63e0fb0abe04daff984b4fb85d07ef3fcb7973595bce215c10515 */
/* JADX INFO: loaded from: classes.dex */
public final class dc implements Callable {
    public final /* synthetic */ int a;
    public final /* synthetic */ String b;
    public final /* synthetic */ Context c;
    public final /* synthetic */ a2 d;
    public final /* synthetic */ int e;

    public /* synthetic */ dc(String str, Context context, a2 a2Var, int i, int i2) {
        this.a = i2;
        this.b = str;
        this.c = context;
        this.d = a2Var;
        this.e = i;
    }

    @Override // java.util.concurrent.Callable
    public final Object call() {
        int i = this.a;
        int i2 = this.e;
        a2 a2Var = this.d;
        Context context = this.c;
        String str = this.b;
        switch (i) {
            case 0:
                return gc.a(str, context, a2Var, i2);
            default:
                try {
                    return gc.a(str, context, a2Var, i2);
                } catch (Throwable unused) {
                    return new fc(-3);
                }
        }
    }
}
