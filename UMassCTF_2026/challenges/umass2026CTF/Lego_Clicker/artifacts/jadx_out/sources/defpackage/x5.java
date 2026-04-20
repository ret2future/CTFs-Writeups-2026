package defpackage;

import java.lang.reflect.GenericArrayType;
import java.lang.reflect.Type;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.Date;

/* JADX INFO: compiled from: r8-map-id-51b54c9e59b63e0fb0abe04daff984b4fb85d07ef3fcb7973595bce215c10515 */
/* JADX INFO: loaded from: classes.dex */
public final class x5 implements qq {
    public final /* synthetic */ int a;

    public /* synthetic */ x5(int i) {
        this.a = i;
    }

    @Override // defpackage.qq
    public final pq a(re reVar, wq wqVar) {
        int i = 0;
        switch (this.a) {
            case 0:
                Type type = wqVar.b;
                boolean z = type instanceof GenericArrayType;
                if (!z && (!(type instanceof Class) || !((Class) type).isArray())) {
                    return null;
                }
                Type genericComponentType = z ? ((GenericArrayType) type).getGenericComponentType() : ((Class) type).getComponentType();
                return new y5(reVar, reVar.c(new wq(genericComponentType)), d.A(genericComponentType));
            case 1:
                if (wqVar.a == Date.class) {
                    return new ro(2);
                }
                return null;
            case 2:
                if (wqVar.a == Object.class) {
                    return new xj(reVar);
                }
                return null;
            case 3:
                if (wqVar.a == java.sql.Date.class) {
                    return new ro(0);
                }
                return null;
            case 4:
                if (wqVar.a == Time.class) {
                    return new ro(1);
                }
                return null;
            case 5:
                if (wqVar.a == Timestamp.class) {
                    return new so(reVar.c(new wq(Date.class)), i);
                }
                return null;
            default:
                Class superclass = wqVar.a;
                if (!Enum.class.isAssignableFrom(superclass) || superclass == Enum.class) {
                    return null;
                }
                if (!superclass.isEnum()) {
                    superclass = superclass.getSuperclass();
                }
                return new bi(superclass);
        }
    }
}
