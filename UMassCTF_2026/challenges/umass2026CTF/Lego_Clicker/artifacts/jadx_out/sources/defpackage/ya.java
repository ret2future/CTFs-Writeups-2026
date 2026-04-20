package defpackage;

import android.text.Editable;

/* JADX INFO: compiled from: r8-map-id-51b54c9e59b63e0fb0abe04daff984b4fb85d07ef3fcb7973595bce215c10515 */
/* JADX INFO: loaded from: classes.dex */
public final class ya extends Editable.Factory {
    public static final Object a = new Object();
    public static volatile ya b;
    public static Class c;

    @Override // android.text.Editable.Factory
    public final Editable newEditable(CharSequence charSequence) {
        Class cls = c;
        return cls != null ? new oo(cls, charSequence) : super.newEditable(charSequence);
    }
}
