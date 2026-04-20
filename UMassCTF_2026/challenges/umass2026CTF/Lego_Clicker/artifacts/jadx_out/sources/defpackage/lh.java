package defpackage;

/* JADX INFO: compiled from: r8-map-id-51b54c9e59b63e0fb0abe04daff984b4fb85d07ef3fcb7973595bce215c10515 */
/* JADX INFO: loaded from: classes.dex */
public final class lh extends jh {
    public final vc a;

    public lh(vc vcVar, zs zsVar) {
        this.a = vcVar;
        j5 j5Var = new j5(zsVar, kh.e);
        String canonicalName = kh.class.getCanonicalName();
        if (canonicalName != null) {
        } else {
            z6.f("Local and anonymous classes can not be ViewModels");
            throw null;
        }
    }

    public final String toString() {
        int iLastIndexOf;
        StringBuilder sb = new StringBuilder(128);
        sb.append("LoaderManager{");
        sb.append(Integer.toHexString(System.identityHashCode(this)));
        sb.append(" in ");
        vc vcVar = this.a;
        String simpleName = vcVar.getClass().getSimpleName();
        if (simpleName.length() <= 0 && (iLastIndexOf = (simpleName = vcVar.getClass().getName()).lastIndexOf(46)) > 0) {
            simpleName = simpleName.substring(iLastIndexOf + 1);
        }
        sb.append(simpleName);
        sb.append('{');
        sb.append(Integer.toHexString(System.identityHashCode(vcVar)));
        sb.append("}}");
        return sb.toString();
    }
}
