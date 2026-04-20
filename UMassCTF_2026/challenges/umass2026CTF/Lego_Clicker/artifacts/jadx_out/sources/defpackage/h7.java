package defpackage;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.WildcardType;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/* JADX INFO: compiled from: r8-map-id-51b54c9e59b63e0fb0abe04daff984b4fb85d07ef3fcb7973595bce215c10515 */
/* JADX INFO: loaded from: classes.dex */
public final class h7 implements qq {
    public final /* synthetic */ int a;
    public final fr b;

    public /* synthetic */ h7(fr frVar, int i) {
        this.a = i;
        this.b = frVar;
    }

    public static pq b(fr frVar, re reVar, wq wqVar, lf lfVar) {
        pq pqVarA;
        Object objQ = frVar.e(new wq(lfVar.value())).q();
        boolean zNullSafe = lfVar.nullSafe();
        if (objQ instanceof pq) {
            pqVarA = (pq) objQ;
        } else {
            if (!(objQ instanceof qq)) {
                throw new IllegalArgumentException("Invalid attempt to bind an instance of " + objQ.getClass().getName() + " as a @JsonAdapter for " + d.d0(wqVar.b) + ". @JsonAdapter value must be a TypeAdapter, TypeAdapterFactory, JsonSerializer or JsonDeserializer.");
            }
            pqVarA = ((qq) objQ).a(reVar, wqVar);
        }
        return (pqVarA == null || !zNullSafe) ? pqVarA : new so(pqVarA, 3);
    }

    @Override // defpackage.qq
    public final pq a(re reVar, wq wqVar) {
        Class cls;
        Type[] actualTypeArguments;
        int i = this.a;
        cls = Object.class;
        fr frVar = this.b;
        switch (i) {
            case 0:
                Type type = wqVar.b;
                Class cls2 = wqVar.a;
                if (!Collection.class.isAssignableFrom(cls2)) {
                    return null;
                }
                if (type instanceof WildcardType) {
                    type = ((WildcardType) type).getUpperBounds()[0];
                }
                d.i(Collection.class.isAssignableFrom(cls2));
                Type typeU = d.U(type, cls2, d.z(type, cls2, Collection.class), new HashMap());
                cls = typeU instanceof ParameterizedType ? ((ParameterizedType) typeU).getActualTypeArguments()[0] : Object.class;
                return new y5(reVar, cls, reVar.c(new wq(cls)), frVar.e(wqVar));
            case 1:
                lf lfVar = (lf) wqVar.a.getAnnotation(lf.class);
                if (lfVar == null) {
                    return null;
                }
                return b(frVar, reVar, wqVar, lfVar);
            default:
                Type type2 = wqVar.b;
                Class cls3 = wqVar.a;
                if (!Map.class.isAssignableFrom(cls3)) {
                    return null;
                }
                if (type2 == Properties.class) {
                    actualTypeArguments = new Type[]{String.class, String.class};
                } else {
                    if (type2 instanceof WildcardType) {
                        type2 = ((WildcardType) type2).getUpperBounds()[0];
                    }
                    d.i(Map.class.isAssignableFrom(cls3));
                    Type typeU2 = d.U(type2, cls3, d.z(type2, cls3, Map.class), new HashMap());
                    actualTypeArguments = typeU2 instanceof ParameterizedType ? ((ParameterizedType) typeU2).getActualTypeArguments() : new Type[]{cls, cls};
                }
                Type type3 = actualTypeArguments[0];
                pq pqVarC = (type3 == Boolean.TYPE || type3 == Boolean.class) ? vq.c : reVar.c(new wq(type3));
                pq pqVarC2 = reVar.c(new wq(actualTypeArguments[1]));
                wj wjVarE = frVar.e(wqVar);
                Type[] typeArr = actualTypeArguments;
                return new bi(this, reVar, typeArr[0], pqVarC, typeArr[1], pqVarC2, wjVarE);
        }
    }
}
