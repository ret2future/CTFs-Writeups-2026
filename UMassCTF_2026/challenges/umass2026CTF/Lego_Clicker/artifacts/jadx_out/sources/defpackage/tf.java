package defpackage;

import java.io.Closeable;
import java.io.Flushable;
import java.io.IOException;
import java.io.Writer;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.regex.Pattern;

/* JADX INFO: compiled from: r8-map-id-51b54c9e59b63e0fb0abe04daff984b4fb85d07ef3fcb7973595bce215c10515 */
/* JADX INFO: loaded from: classes.dex */
public class tf implements Closeable, Flushable {
    public static final Pattern i = Pattern.compile("-?(?:0|[1-9][0-9]*)(?:\\.[0-9]+)?(?:[eE][-+]?[0-9]+)?");
    public static final String[] j = new String[128];
    public static final String[] k;
    public final Writer a;
    public int[] b;
    public int c;
    public final String d;
    public boolean e;
    public boolean f;
    public String g;
    public boolean h;

    static {
        for (int i2 = 0; i2 <= 31; i2++) {
            j[i2] = String.format("\\u%04x", Integer.valueOf(i2));
        }
        String[] strArr = j;
        strArr[34] = "\\\"";
        strArr[92] = "\\\\";
        strArr[9] = "\\t";
        strArr[8] = "\\b";
        strArr[10] = "\\n";
        strArr[13] = "\\r";
        strArr[12] = "\\f";
        String[] strArr2 = (String[]) strArr.clone();
        k = strArr2;
        strArr2[60] = "\\u003c";
        strArr2[62] = "\\u003e";
        strArr2[38] = "\\u0026";
        strArr2[61] = "\\u003d";
        strArr2[39] = "\\u0027";
    }

    public tf(Writer writer) {
        int[] iArr = new int[32];
        this.b = iArr;
        this.c = 0;
        if (iArr.length == 0) {
            this.b = Arrays.copyOf(iArr, 0);
        }
        int[] iArr2 = this.b;
        int i2 = this.c;
        this.c = i2 + 1;
        iArr2[i2] = 6;
        this.d = ":";
        this.h = true;
        Objects.requireNonNull(writer, "out == null");
        this.a = writer;
    }

    public final void a() throws IOException {
        int iJ = j();
        if (iJ == 1) {
            this.b[this.c - 1] = 2;
            h();
            return;
        }
        Writer writer = this.a;
        if (iJ == 2) {
            writer.append(',');
            h();
            return;
        }
        if (iJ == 4) {
            writer.append((CharSequence) this.d);
            this.b[this.c - 1] = 5;
            return;
        }
        if (iJ != 6) {
            if (iJ != 7) {
                z6.o("Nesting problem.");
                return;
            } else if (!this.e) {
                z6.o("JSON must have only one top-level value.");
                return;
            }
        }
        this.b[this.c - 1] = 7;
    }

    public void b() throws IOException {
        q();
        a();
        int i2 = this.c;
        int[] iArr = this.b;
        if (i2 == iArr.length) {
            this.b = Arrays.copyOf(iArr, i2 * 2);
        }
        int[] iArr2 = this.b;
        int i3 = this.c;
        this.c = i3 + 1;
        iArr2[i3] = 1;
        this.a.write(91);
    }

    public void c() throws IOException {
        q();
        a();
        int i2 = this.c;
        int[] iArr = this.b;
        if (i2 == iArr.length) {
            this.b = Arrays.copyOf(iArr, i2 * 2);
        }
        int[] iArr2 = this.b;
        int i3 = this.c;
        this.c = i3 + 1;
        iArr2[i3] = 3;
        this.a.write(123);
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        this.a.close();
        int i2 = this.c;
        if (i2 > 1 || (i2 == 1 && this.b[i2 - 1] != 7)) {
            throw new IOException("Incomplete document");
        }
        this.c = 0;
    }

    public final void d(int i2, int i3, char c) throws IOException {
        int iJ = j();
        if (iJ != i3 && iJ != i2) {
            z6.o("Nesting problem.");
            return;
        }
        if (this.g != null) {
            z6.r(this.g, "Dangling name: ");
            return;
        }
        this.c--;
        if (iJ == i3) {
            h();
        }
        this.a.write(c);
    }

    public void e() throws IOException {
        d(1, 2, ']');
    }

    public void f() throws IOException {
        d(3, 5, '}');
    }

    @Override // java.io.Flushable
    public void flush() throws IOException {
        if (this.c != 0) {
            this.a.flush();
        } else {
            z6.o("JsonWriter is closed.");
        }
    }

    public void g(String str) {
        Objects.requireNonNull(str, "name == null");
        if (this.g != null) {
            throw new IllegalStateException();
        }
        if (this.c != 0) {
            this.g = str;
        } else {
            z6.o("JsonWriter is closed.");
        }
    }

    public tf i() throws IOException {
        if (this.g != null) {
            if (!this.h) {
                this.g = null;
                return this;
            }
            q();
        }
        a();
        this.a.write("null");
        return this;
    }

    public final int j() {
        int i2 = this.c;
        if (i2 != 0) {
            return this.b[i2 - 1];
        }
        z6.o("JsonWriter is closed.");
        return 0;
    }

    /* JADX WARN: Removed duplicated region for block: B:20:0x0034  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void k(java.lang.String r8) throws java.io.IOException {
        /*
            r7 = this;
            boolean r0 = r7.f
            if (r0 == 0) goto L7
            java.lang.String[] r0 = defpackage.tf.k
            goto L9
        L7:
            java.lang.String[] r0 = defpackage.tf.j
        L9:
            java.io.Writer r7 = r7.a
            r1 = 34
            r7.write(r1)
            int r2 = r8.length()
            r3 = 0
            r4 = r3
        L16:
            if (r3 >= r2) goto L41
            char r5 = r8.charAt(r3)
            r6 = 128(0x80, float:1.8E-43)
            if (r5 >= r6) goto L25
            r5 = r0[r5]
            if (r5 != 0) goto L32
            goto L3e
        L25:
            r6 = 8232(0x2028, float:1.1535E-41)
            if (r5 != r6) goto L2c
            java.lang.String r5 = "\\u2028"
            goto L32
        L2c:
            r6 = 8233(0x2029, float:1.1537E-41)
            if (r5 != r6) goto L3e
            java.lang.String r5 = "\\u2029"
        L32:
            if (r4 >= r3) goto L39
            int r6 = r3 - r4
            r7.write(r8, r4, r6)
        L39:
            r7.write(r5)
            int r4 = r3 + 1
        L3e:
            int r3 = r3 + 1
            goto L16
        L41:
            if (r4 >= r2) goto L47
            int r2 = r2 - r4
            r7.write(r8, r4, r2)
        L47:
            r7.write(r1)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.tf.k(java.lang.String):void");
    }

    public void l(double d) throws IOException {
        q();
        if (this.e || !(Double.isNaN(d) || Double.isInfinite(d))) {
            a();
            this.a.append((CharSequence) Double.toString(d));
        } else {
            throw new IllegalArgumentException("Numeric values must be finite, but was " + d);
        }
    }

    public void m(long j2) throws IOException {
        q();
        a();
        this.a.write(Long.toString(j2));
    }

    public void n(Number number) throws IOException {
        if (number == null) {
            i();
            return;
        }
        q();
        String string = number.toString();
        if (!string.equals("-Infinity") && !string.equals("Infinity") && !string.equals("NaN")) {
            Class<?> cls = number.getClass();
            if (cls != Integer.class && cls != Long.class && cls != Double.class && cls != Float.class && cls != Byte.class && cls != Short.class && cls != BigDecimal.class && cls != BigInteger.class && cls != AtomicInteger.class && cls != AtomicLong.class && !i.matcher(string).matches()) {
                z6.i("String created by ", cls, " is not a valid JSON number: ", string);
                return;
            }
        } else if (!this.e) {
            z6.f("Numeric values must be finite, but was ".concat(string));
            return;
        }
        a();
        this.a.append((CharSequence) string);
    }

    public void o(String str) throws IOException {
        if (str == null) {
            i();
            return;
        }
        q();
        a();
        k(str);
    }

    public void p(boolean z) throws IOException {
        q();
        a();
        this.a.write(z ? "true" : "false");
    }

    public final void q() throws IOException {
        if (this.g != null) {
            int iJ = j();
            if (iJ == 5) {
                this.a.write(44);
            } else if (iJ != 3) {
                z6.o("Nesting problem.");
                return;
            }
            h();
            this.b[this.c - 1] = 4;
            k(this.g);
            this.g = null;
        }
    }

    public final void h() {
    }
}
