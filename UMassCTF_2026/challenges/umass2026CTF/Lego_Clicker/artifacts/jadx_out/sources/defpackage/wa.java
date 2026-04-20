package defpackage;

import android.content.Context;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import java.util.concurrent.ThreadPoolExecutor;

/* JADX INFO: compiled from: r8-map-id-51b54c9e59b63e0fb0abe04daff984b4fb85d07ef3fcb7973595bce215c10515 */
/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class wa implements Runnable {
    public final /* synthetic */ int a;
    public final /* synthetic */ Object b;
    public final /* synthetic */ Object c;
    public final /* synthetic */ Object d;

    public /* synthetic */ wa(Object obj, Object obj2, Object obj3, int i) {
        this.a = i;
        this.b = obj;
        this.c = obj2;
        this.d = obj3;
    }

    @Override // java.lang.Runnable
    public final void run() {
        switch (this.a) {
            case 0:
                e0 e0Var = (e0) this.b;
                d dVar = (d) this.c;
                ThreadPoolExecutor threadPoolExecutor = (ThreadPoolExecutor) this.d;
                try {
                    cc ccVarM = d.m((Context) e0Var.b);
                    if (ccVarM == null) {
                        throw new RuntimeException("EmojiCompat font provider not available on this device.");
                    }
                    bc bcVar = (bc) ((ua) ccVarM.b);
                    synchronized (bcVar.d) {
                        bcVar.f = threadPoolExecutor;
                        break;
                    }
                    ((ua) ccVarM.b).c(new xa(dVar, threadPoolExecutor));
                    return;
                } catch (Throwable th) {
                    dVar.H(th);
                    threadPoolExecutor.shutdown();
                    return;
                }
            default:
                wh whVar = (wh) this.b;
                TextView textView = (TextView) this.c;
                ConstraintLayout constraintLayout = (ConstraintLayout) this.d;
                if (textView.getParent() == constraintLayout) {
                    constraintLayout.removeView(textView);
                }
                whVar.i = Math.max(0, whVar.i - 1);
                return;
        }
    }
}
