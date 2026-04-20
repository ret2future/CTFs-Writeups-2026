package defpackage;

import android.text.TextUtils;

/* JADX INFO: compiled from: r8-map-id-51b54c9e59b63e0fb0abe04daff984b4fb85d07ef3fcb7973595bce215c10515 */
/* JADX INFO: loaded from: classes.dex */
public final class n8 implements wj, eb {
    public final /* synthetic */ int a;
    public final String b;

    public /* synthetic */ n8(int i, String str) {
        this.a = i;
        this.b = str;
    }

    @Override // defpackage.eb
    public boolean d(CharSequence charSequence, int i, int i2, dr drVar) {
        if (!TextUtils.equals(charSequence.subSequence(i, i2), this.b)) {
            return true;
        }
        drVar.c = (drVar.c & 3) | 4;
        return false;
    }

    @Override // defpackage.wj
    public Object q() {
        int i = this.a;
        String str = this.b;
        switch (i) {
            case 0:
                throw new of(str);
            default:
                throw new of(str);
        }
    }

    @Override // defpackage.eb
    public Object a() {
        return this;
    }
}
