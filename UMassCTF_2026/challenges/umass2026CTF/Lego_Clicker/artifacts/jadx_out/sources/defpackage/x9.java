package defpackage;

import android.content.res.AssetManager;
import android.os.Build;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.Serializable;
import java.util.concurrent.Executor;

/* JADX INFO: compiled from: r8-map-id-51b54c9e59b63e0fb0abe04daff984b4fb85d07ef3fcb7973595bce215c10515 */
/* JADX INFO: loaded from: classes.dex */
public final class x9 {
    public final Executor a;
    public final vk b;
    public final byte[] c;
    public final File d;
    public final String e;
    public boolean f = false;
    public y9[] g;
    public byte[] h;

    public x9(AssetManager assetManager, Executor executor, vk vkVar, String str, File file) {
        byte[] bArr;
        this.a = executor;
        this.b = vkVar;
        this.e = str;
        this.d = file;
        int i = Build.VERSION.SDK_INT;
        if (i < 31) {
            switch (i) {
                case 24:
                case 25:
                    bArr = d.y;
                    break;
                case 26:
                    bArr = d.x;
                    break;
                case 27:
                    bArr = d.w;
                    break;
                case 28:
                case 29:
                case 30:
                    bArr = d.v;
                    break;
                default:
                    bArr = null;
                    break;
            }
        } else {
            bArr = d.u;
        }
        this.c = bArr;
    }

    public final FileInputStream a(AssetManager assetManager, String str) {
        try {
            return assetManager.openFd(str).createInputStream();
        } catch (FileNotFoundException e) {
            String message = e.getMessage();
            if (message == null || !message.contains("compressed")) {
                return null;
            }
            this.b.g();
            return null;
        }
    }

    public final void b(final int i, final Serializable serializable) {
        this.a.execute(new Runnable() { // from class: w9
            @Override // java.lang.Runnable
            public final void run() {
                this.a.b.i(i, serializable);
            }
        });
    }
}
