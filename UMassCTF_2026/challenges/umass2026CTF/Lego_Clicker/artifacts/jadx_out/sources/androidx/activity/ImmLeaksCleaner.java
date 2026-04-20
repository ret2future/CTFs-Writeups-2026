package androidx.activity;

import android.view.inputmethod.InputMethodManager;
import defpackage.dg;
import defpackage.ig;
import defpackage.kg;

/* JADX INFO: compiled from: r8-map-id-51b54c9e59b63e0fb0abe04daff984b4fb85d07ef3fcb7973595bce215c10515 */
/* JADX INFO: loaded from: classes.dex */
final class ImmLeaksCleaner implements ig {
    public static int a;

    @Override // defpackage.ig
    public final void d(kg kgVar, dg dgVar) {
        if (dgVar != dg.ON_DESTROY) {
            return;
        }
        if (a == 0) {
            try {
                a = 2;
                InputMethodManager.class.getDeclaredField("mServedView").setAccessible(true);
                InputMethodManager.class.getDeclaredField("mNextServedView").setAccessible(true);
                InputMethodManager.class.getDeclaredField("mH").setAccessible(true);
                a = 1;
            } catch (NoSuchFieldException unused) {
            }
        }
        if (a == 1) {
            throw null;
        }
    }
}
