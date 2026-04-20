package defpackage;

import android.text.TextUtils;
import android.view.View;

/* JADX INFO: compiled from: r8-map-id-51b54c9e59b63e0fb0abe04daff984b4fb85d07ef3fcb7973595bce215c10515 */
/* JADX INFO: loaded from: classes.dex */
public final class zr extends ip {
    public final /* synthetic */ int e;

    public zr(int i, Class cls, int i2, int i3, int i4) {
        this.e = i4;
        this.a = i;
        this.d = cls;
        this.c = i2;
        this.b = i3;
    }

    @Override // defpackage.ip
    public final Object b(View view) {
        switch (this.e) {
            case 0:
                return Boolean.valueOf(hs.d(view));
            case 1:
                return hs.b(view);
            default:
                return Boolean.valueOf(hs.c(view));
        }
    }

    @Override // defpackage.ip
    public final void c(View view, Object obj) {
        switch (this.e) {
            case 0:
                hs.j(view, ((Boolean) obj).booleanValue());
                break;
            case 1:
                hs.h(view, (CharSequence) obj);
                break;
            default:
                hs.g(view, ((Boolean) obj).booleanValue());
                break;
        }
    }

    @Override // defpackage.ip
    public final boolean e(Object obj, Object obj2) {
        switch (this.e) {
            case 0:
                Boolean bool = (Boolean) obj;
                Boolean bool2 = (Boolean) obj2;
                return !((bool != null && bool.booleanValue()) == (bool2 != null && bool2.booleanValue()));
            case 1:
                return !TextUtils.equals((CharSequence) obj, (CharSequence) obj2);
            default:
                Boolean bool3 = (Boolean) obj;
                Boolean bool4 = (Boolean) obj2;
                return !((bool3 != null && bool3.booleanValue()) == (bool4 != null && bool4.booleanValue()));
        }
    }
}
