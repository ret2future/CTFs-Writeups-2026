package defpackage;

import android.widget.EditText;
import java.lang.ref.WeakReference;

/* JADX INFO: compiled from: r8-map-id-51b54c9e59b63e0fb0abe04daff984b4fb85d07ef3fcb7973595bce215c10515 */
/* JADX INFO: loaded from: classes.dex */
public final class jb extends sa {
    public final WeakReference a;

    public jb(EditText editText) {
        this.a = new WeakReference(editText);
    }

    @Override // defpackage.sa
    public final void a() {
        kb.a((EditText) this.a.get(), 1);
    }
}
