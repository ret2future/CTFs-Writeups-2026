package defpackage;

import android.os.Build;
import android.os.Bundle;
import android.view.inputmethod.InputConnection;
import android.view.inputmethod.InputConnectionWrapper;
import android.view.inputmethod.InputContentInfo;

/* JADX INFO: compiled from: r8-map-id-51b54c9e59b63e0fb0abe04daff984b4fb85d07ef3fcb7973595bce215c10515 */
/* JADX INFO: loaded from: classes.dex */
public final class df extends InputConnectionWrapper {
    public final /* synthetic */ vh a;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public df(InputConnection inputConnection, vh vhVar) {
        super(inputConnection, false);
        this.a = vhVar;
    }

    @Override // android.view.inputmethod.InputConnectionWrapper, android.view.inputmethod.InputConnection
    public final boolean commitContent(InputContentInfo inputContentInfo, int i, Bundle bundle) {
        e0 e0Var = null;
        if (inputContentInfo != null && Build.VERSION.SDK_INT >= 25) {
            e0Var = new e0(13, new ff(inputContentInfo));
        }
        if (this.a.a(e0Var, i, bundle)) {
            return true;
        }
        return super.commitContent(inputContentInfo, i, bundle);
    }
}
