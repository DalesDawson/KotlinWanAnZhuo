package com.e006.release.http

import java.security.cert.X509Certificate
import javax.net.ssl.X509TrustManager

/**
 * 创 建 人：zhengquan
 * 创建日期：2020/4/26
 * 修改时间：
 * 修改备注：
 */
class TrustAllCerts : X509TrustManager {
    override fun checkClientTrusted(
        chain: Array<X509Certificate>,
        authType: String
    ) {
    }

    override fun checkServerTrusted(
        chain: Array<X509Certificate>,
        authType: String
    ) {
    }

    override fun getAcceptedIssuers(): Array<X509Certificate?> {
        return arrayOfNulls(0)
    }
}