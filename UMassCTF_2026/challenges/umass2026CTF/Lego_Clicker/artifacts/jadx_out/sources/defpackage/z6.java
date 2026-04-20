package defpackage;

/* JADX INFO: compiled from: r8-map-id-51b54c9e59b63e0fb0abe04daff984b4fb85d07ef3fcb7973595bce215c10515 */
/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class z6 {
    public static /* synthetic */ void a() {
        throw new ClassCastException();
    }

    public static /* synthetic */ void b(Object obj, Object obj2, Object obj3, Throwable th) {
        throw new of("Failed parsing '" + obj + obj2 + obj3, th);
    }

    public static /* synthetic */ void c(Object obj, Object obj2, String str) {
        throw new IllegalStateException(str + obj + obj2);
    }

    public static /* synthetic */ void d(Object obj, Object obj2, Throwable th) {
        throw new pc("Unable to instantiate fragment " + obj + obj2, th);
    }

    public static /* synthetic */ void e(Object obj, String str) {
        throw new of(str + ((Object) obj.toString()));
    }

    public static /* synthetic */ void f(String str) {
        throw new IllegalArgumentException(str);
    }

    public static /* synthetic */ void g(String str, int i, Object obj, int i2) {
        throw new IndexOutOfBoundsException(str + i + obj + i2);
    }

    public static /* synthetic */ void h(String str, int i, Object obj, Object obj2) {
        throw new of(str + i + obj + obj2);
    }

    public static /* synthetic */ void i(String str, Object obj, Object obj2, Object obj3) {
        throw new IllegalArgumentException(str + obj + obj2 + obj3);
    }

    public static /* synthetic */ void j(String str, Object obj, Throwable th) {
        throw new RuntimeException(str + obj, th);
    }

    public static /* synthetic */ void k(StringBuilder sb, Object obj) {
        sb.append(obj);
        throw new IllegalArgumentException(sb.toString());
    }

    public static /* synthetic */ void l(Object obj, Object obj2, Object obj3, Throwable th) {
        StringBuilder sb = new StringBuilder();
        sb.append(obj);
        sb.append(obj2);
        sb.append(obj3);
        throw new IllegalStateException(sb.toString(), th);
    }

    public static /* synthetic */ void m(Object obj, Object obj2, String str) {
        throw new IllegalStateException(str + obj + obj2);
    }

    public static /* synthetic */ void n(Object obj, String str) {
        throw new IllegalArgumentException(str + obj);
    }

    public static /* synthetic */ void o(String str) {
        throw new IllegalStateException(str);
    }

    public static /* synthetic */ void p(String str, Object obj, Object obj2, Object obj3) {
        throw new of(str + obj + obj2 + obj3);
    }

    public static /* synthetic */ void q(Object obj, String str) {
        throw new IllegalStateException(str + obj);
    }

    public static /* synthetic */ void r(Object obj, String str) {
        throw new IllegalStateException(str + obj);
    }
}
