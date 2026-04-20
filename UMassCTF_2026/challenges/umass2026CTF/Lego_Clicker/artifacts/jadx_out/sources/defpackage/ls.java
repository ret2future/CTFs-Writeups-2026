package defpackage;

import android.view.ContentInfo;
import android.view.OnReceiveContentListener;
import android.view.View;
import java.util.Objects;

/* JADX INFO: compiled from: r8-map-id-51b54c9e59b63e0fb0abe04daff984b4fb85d07ef3fcb7973595bce215c10515 */
/* JADX INFO: loaded from: classes.dex */
public final class ls implements OnReceiveContentListener {
    public final ek a;

    public ls(ek ekVar) {
        this.a = ekVar;
    }

    public final ContentInfo onReceiveContent(View view, ContentInfo contentInfo) {
        w8 w8Var = new w8(new e0(contentInfo));
        w8 w8VarA = ((pp) this.a).a(view, w8Var);
        if (w8VarA == null) {
            return null;
        }
        if (w8VarA == w8Var) {
            return contentInfo;
        }
        ContentInfo contentInfoH = w8VarA.a.h();
        Objects.requireNonNull(contentInfoH);
        return s8.e(contentInfoH);
    }
}
