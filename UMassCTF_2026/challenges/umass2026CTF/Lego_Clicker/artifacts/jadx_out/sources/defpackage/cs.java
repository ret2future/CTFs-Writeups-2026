package defpackage;

import android.os.Build;
import android.view.View;
import android.view.WindowInsets;

/* JADX INFO: compiled from: r8-map-id-51b54c9e59b63e0fb0abe04daff984b4fb85d07ef3fcb7973595bce215c10515 */
/* JADX INFO: loaded from: classes.dex */
public final class cs implements View.OnApplyWindowInsetsListener {
    public eu a = null;
    public final /* synthetic */ View b;
    public final /* synthetic */ yj c;

    public cs(View view, yj yjVar) {
        this.b = view;
        this.c = yjVar;
    }

    @Override // android.view.View.OnApplyWindowInsetsListener
    public WindowInsets onApplyWindowInsets(View view, WindowInsets windowInsets) {
        eu euVarC = eu.c(windowInsets, view);
        int i = Build.VERSION.SDK_INT;
        yj yjVar = this.c;
        if (i < 30) {
            ds.a(windowInsets, this.b);
            if (euVarC.equals(this.a)) {
                return ((b3) yjVar).b(view, euVarC).b();
            }
        }
        this.a = euVarC;
        eu euVarB = ((b3) yjVar).b(view, euVarC);
        if (i >= 30) {
            return euVarB.b();
        }
        bs.c(view);
        return euVarB.b();
    }
}
