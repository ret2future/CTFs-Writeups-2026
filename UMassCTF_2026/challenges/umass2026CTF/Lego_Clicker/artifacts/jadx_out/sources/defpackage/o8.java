package defpackage;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/* JADX INFO: compiled from: r8-map-id-51b54c9e59b63e0fb0abe04daff984b4fb85d07ef3fcb7973595bce215c10515 */
/* JADX INFO: loaded from: classes.dex */
public final class o8 implements wj {
    public final /* synthetic */ int a;
    public final /* synthetic */ Type b;

    public /* synthetic */ o8(Type type, int i) {
        this.a = i;
        this.b = type;
    }

    @Override // defpackage.wj
    public final Object q() {
        int i = this.a;
        Type type = this.b;
        switch (i) {
            case 0:
                if (!(type instanceof ParameterizedType)) {
                    z6.e(type, "Invalid EnumSet type: ");
                } else {
                    Type type2 = ((ParameterizedType) type).getActualTypeArguments()[0];
                    if (!(type2 instanceof Class)) {
                        z6.e(type, "Invalid EnumSet type: ");
                    }
                }
                break;
            default:
                if (!(type instanceof ParameterizedType)) {
                    z6.e(type, "Invalid EnumMap type: ");
                } else {
                    Type type3 = ((ParameterizedType) type).getActualTypeArguments()[0];
                    if (!(type3 instanceof Class)) {
                        z6.e(type, "Invalid EnumMap type: ");
                    }
                }
                break;
        }
        return null;
    }
}
