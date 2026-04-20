package defpackage;

import android.content.ClipDescription;
import android.net.Uri;
import android.view.inputmethod.InputContentInfo;

/* JADX INFO: compiled from: r8-map-id-51b54c9e59b63e0fb0abe04daff984b4fb85d07ef3fcb7973595bce215c10515 */
/* JADX INFO: loaded from: classes.dex */
public final class ff implements gf {
    public final InputContentInfo a;

    public ff(Uri uri, ClipDescription clipDescription, Uri uri2) {
        this.a = new InputContentInfo(uri, clipDescription, uri2);
    }

    @Override // defpackage.gf
    public final void a() {
        this.a.requestPermission();
    }

    @Override // defpackage.gf
    public final Uri b() {
        return this.a.getLinkUri();
    }

    @Override // defpackage.gf
    public final ClipDescription c() {
        return this.a.getDescription();
    }

    @Override // defpackage.gf
    public final Object d() {
        return this.a;
    }

    @Override // defpackage.gf
    public final Uri e() {
        return this.a.getContentUri();
    }

    public ff(Object obj) {
        this.a = (InputContentInfo) obj;
    }
}
