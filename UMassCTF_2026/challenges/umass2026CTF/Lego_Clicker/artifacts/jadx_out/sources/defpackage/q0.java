package defpackage;

import android.os.Message;
import android.view.View;
import androidx.appcompat.widget.Toolbar;

/* JADX INFO: compiled from: r8-map-id-51b54c9e59b63e0fb0abe04daff984b4fb85d07ef3fcb7973595bce215c10515 */
/* JADX INFO: loaded from: classes.dex */
public final class q0 implements View.OnClickListener {
    public final /* synthetic */ int a;
    public final /* synthetic */ Object b;

    public /* synthetic */ q0(int i, Object obj) {
        this.a = i;
        this.b = obj;
    }

    @Override // android.view.View.OnClickListener
    public final void onClick(View view) {
        Message message;
        Message message2;
        Message message3;
        int i = this.a;
        Message messageObtain = null;
        messageObtain = null;
        Object obj = this.b;
        switch (i) {
            case 0:
                ((j1) obj).a();
                break;
            case 1:
                f2 f2Var = (f2) obj;
                if (view == f2Var.g && (message3 = f2Var.i) != null) {
                    messageObtain = Message.obtain(message3);
                } else if (view == f2Var.j && (message2 = f2Var.l) != null) {
                    messageObtain = Message.obtain(message2);
                } else if (view == f2Var.m && (message = f2Var.o) != null) {
                    messageObtain = Message.obtain(message);
                }
                if (messageObtain != null) {
                    messageObtain.sendToTarget();
                }
                f2Var.C.obtainMessage(1, f2Var.b).sendToTarget();
                break;
            default:
                xp xpVar = ((Toolbar) obj).M;
                oi oiVar = xpVar != null ? xpVar.b : null;
                if (oiVar != null) {
                    oiVar.collapseActionView();
                }
                break;
        }
    }
}
