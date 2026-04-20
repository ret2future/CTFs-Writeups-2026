package defpackage;

import java.lang.reflect.Field;
import java.util.LinkedHashMap;

/* JADX INFO: compiled from: r8-map-id-51b54c9e59b63e0fb0abe04daff984b4fb85d07ef3fcb7973595bce215c10515 */
/* JADX INFO: loaded from: classes.dex */
public final class om extends nm {
    public final wj b;

    public om(wj wjVar, LinkedHashMap linkedHashMap) {
        super(linkedHashMap);
        this.b = wjVar;
    }

    @Override // defpackage.nm
    public final Object c() {
        return this.b.q();
    }

    @Override // defpackage.nm
    public final void e(Object obj, sf sfVar, mm mmVar) throws IllegalAccessException {
        Field field = mmVar.b;
        Object objA = mmVar.h.a(sfVar);
        if (objA == null && mmVar.k) {
            return;
        }
        if (mmVar.l) {
            throw new of("Cannot set value of 'static final' ".concat(lm.d(field, false)));
        }
        field.set(obj, objA);
    }

    @Override // defpackage.nm
    public final Object d(Object obj) {
        return obj;
    }
}
