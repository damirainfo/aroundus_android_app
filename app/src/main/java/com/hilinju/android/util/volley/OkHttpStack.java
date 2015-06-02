package com.hilinju.android.util.volley;

import android.content.res.AssetManager;

import com.android.volley.toolbox.HurlStack;
import com.hilinju.android.AppContext;
import com.hilinju.android.R;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.OkUrlFactory;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.GeneralSecurityException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.SecureRandom;
import java.security.cert.Certificate;
import java.security.cert.CertificateFactory;

import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManagerFactory;

/**
 * Created by qiuxj on 2015/6/2.
 */
public class OkHttpStack extends HurlStack {
    private final OkUrlFactory okUrlFactory;

    public OkHttpStack() {
        OkUrlFactory factory = new OkUrlFactory(this.createSSLHttpClient());
        if(factory == null){
            throw new NullPointerException("Client must not be null.");
        }
        this.okUrlFactory = new OkUrlFactory(this.createSSLHttpClient());
    }

    public OkHttpStack(OkUrlFactory okUrlFactory) {
        if (okUrlFactory == null) {
            throw new NullPointerException("Client must not be null.");
        }
        this.okUrlFactory = okUrlFactory;
    }

    protected OkHttpClient createSSLHttpClient(){
        OkHttpClient okHttpClient = new OkHttpClient();
        SSLContext sslContext;
        try {
            sslContext = SSLContext.getInstance("SSL");
            KeyStore keyStore = readKeyStore();
            TrustManagerFactory trustManagerFactory = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
            trustManagerFactory.init(keyStore);
            KeyManagerFactory keyManagerFactory = KeyManagerFactory.getInstance(KeyManagerFactory.getDefaultAlgorithm());
            keyManagerFactory.init(keyStore, null);
            sslContext.init(keyManagerFactory.getKeyManagers(), trustManagerFactory.getTrustManagers(), new SecureRandom());
        } catch (GeneralSecurityException e) {
            throw new AssertionError(); // The system has no TLS. Just give up.
        }
        okHttpClient.setSslSocketFactory(sslContext.getSocketFactory());
        return okHttpClient;
    }


    protected KeyStore readKeyStore() throws KeyStoreException {
        KeyStore keyStore = KeyStore.getInstance(KeyStore.getDefaultType());
        try {
            CertificateFactory cf = CertificateFactory.getInstance("X.509");
            AssetManager am = AppContext.getInstance().getAssets();
            InputStream fis = am.open("sub.class1.server.ca.crt");
            keyStore.load(null, null);
            Certificate ca = cf.generateCertificate(fis);
            keyStore.setCertificateEntry("ca", ca);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return keyStore;
    }

    @Override
    protected HttpURLConnection createConnection(URL url) throws IOException {
        return okUrlFactory.open(url);
    }

}
