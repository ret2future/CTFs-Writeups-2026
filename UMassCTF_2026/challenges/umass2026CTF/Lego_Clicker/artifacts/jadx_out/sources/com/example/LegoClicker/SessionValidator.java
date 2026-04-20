package com.example.LegoClicker;

/* JADX INFO: compiled from: r8-map-id-51b54c9e59b63e0fb0abe04daff984b4fb85d07ef3fcb7973595bce215c10515 */
/* JADX INFO: loaded from: classes.dex */
public final class SessionValidator {
    public static final boolean a;

    /* JADX INFO: compiled from: r8-map-id-51b54c9e59b63e0fb0abe04daff984b4fb85d07ef3fcb7973595bce215c10515 */
    public static final class SessionToken {
        public long mask() {
            return 0L;
        }
    }

    static {
        boolean z = false;
        try {
            int[] iArr = {86, 95, 93, 85, 89, 85, 72, 95};
            char[] cArr = new char[8];
            for (int i = 0; i < 8; i++) {
                cArr[i] = (char) (iArr[i] ^ 58);
            }
            System.loadLibrary(new String(cArr));
            z = true;
        } catch (Throwable unused) {
        }
        a = z;
    }

    public static String a(long j, long j2) {
        if (!a) {
            return "";
        }
        try {
            SessionToken sessionToken = new SessionToken();
            int[] iArr = {47, 37, 50, 63, 30, 46, 53, 63, 55, 31, 61, 63, 52, 57};
            char[] cArr = new char[14];
            for (int i = 0; i < 14; i++) {
                cArr[i] = (char) (iArr[i] ^ 92);
            }
            String str = new String(cArr);
            Class cls = Long.TYPE;
            return (String) SessionValidator.class.getDeclaredMethod(str, cls, cls).invoke(null, Long.valueOf(j + sessionToken.mask()), Long.valueOf(j2 + sessionToken.mask()));
        } catch (Throwable unused) {
            return "";
        }
    }

    public static native String refreshTileMap(long j, long j2);

    @Deprecated
    public static native String syncBrickCache(long j, long j2);

    @Deprecated
    public static native String validateBrickToken(long j, long j2);
}
