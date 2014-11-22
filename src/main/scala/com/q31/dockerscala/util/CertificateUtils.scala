package com.q31.dockerscala.util

import java.io._

import java.security._
import java.security.spec.PKCS8EncodedKeySpec
import java.security.spec.X509EncodedKeySpec
import org.bouncycastle.cert.X509CertificateHolder
import org.bouncycastle.cert.jcajce.JcaX509CertificateConverter
import org.bouncycastle.openssl.PEMKeyPair
import org.bouncycastle.openssl.PEMParser
import scala.util.Try

/**
 * @author Joe San (codeintheopen@gmail.com)
 */
object CertificateUtils {

  def verifyCertificatesExist(dockerCertPath: String): Boolean = {
    List("ca.pem", "cert.pem", "key.pem").forall { case str => new File(dockerCertPath, str).exists }
  }

  def createKeyStore(dockerCertPath: String) = Try {
    val keyPair = loadPrivateKey(dockerCertPath)
    val privateCertificate = loadCertificate(dockerCertPath)
    val keyStore = KeyStore.getInstance("JKS")
    keyStore.load(null)
    keyStore.setKeyEntry("docker", keyPair.getPrivate(), "docker".toCharArray(), List(privateCertificate).toArray)
    keyStore
  }

  def createTrustStore(dockerCertPath: String) = Try {
    val buffReader = tryWithFinallyLoaner[BufferedReader](new BufferedReader(new FileReader(new File(dockerCertPath, "ca.pem"))))
    val pemParser = tryWithFinallyLoaner[PEMParser](new PEMParser(buffReader))
    try {
      val certificateHolder = pemParser.readObject().asInstanceOf[X509CertificateHolder]
      val caCertificate = new JcaX509CertificateConverter().setProvider("BC").getCertificate(certificateHolder)
      val trustStore = KeyStore.getInstance("JKS")
      trustStore.load(null)
      trustStore.setCertificateEntry("ca", caCertificate)
      trustStore
    }
    finally {
      buffReader.close()
      pemParser.close()
    }
  }

  private def tryWithFinallyLoaner[T <: Closeable](t: T) = try t finally t.close()

  private def loadCertificate(dockerCertPath: String) = {
    val buffReader = tryWithFinallyLoaner[BufferedReader](new BufferedReader(new FileReader(new File(dockerCertPath, "cert.pem"))))
    val pemParser = tryWithFinallyLoaner[PEMParser](new PEMParser(buffReader))
    try {
      val certificateHolder =  pemParser.readObject().asInstanceOf[X509CertificateHolder]
      new JcaX509CertificateConverter().setProvider("BC").getCertificate(certificateHolder)
    }
    finally {
      buffReader.close()
      pemParser.close()
    }
  }

  private def loadPrivateKey(dockerCertPath: String) = {

    val buffReader = tryWithFinallyLoaner[BufferedReader](new BufferedReader(new FileReader(new File(dockerCertPath, "myKey.pem"))))
    val pemParser = tryWithFinallyLoaner[PEMParser](new PEMParser(buffReader))
    try {
      val pemKeyPair = pemParser.readObject().asInstanceOf[PEMKeyPair]
      val pemPrivateKeyEncoded = pemKeyPair.getPrivateKeyInfo().getEncoded()
      val pemPublicKeyEncoded = pemKeyPair.getPublicKeyInfo().getEncoded()
      val factory = KeyFactory.getInstance("RSA")
      val publicKeySpec = new X509EncodedKeySpec(pemPublicKeyEncoded)
      val privateKeySpec = new PKCS8EncodedKeySpec(pemPrivateKeyEncoded)
      new KeyPair(factory.generatePublic(publicKeySpec), factory.generatePrivate(privateKeySpec))
    } finally {
      buffReader.close()
      pemParser.close()
    }
  }
}