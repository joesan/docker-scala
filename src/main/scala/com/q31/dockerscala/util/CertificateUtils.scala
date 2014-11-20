package com.q31.dockerscala.util

import java.io.{FileReader, BufferedReader, File}

import java.security._
import java.security.cert.Certificate
import java.security.cert.CertificateException
import java.security.spec.InvalidKeySpecException
import java.security.spec.PKCS8EncodedKeySpec
import java.security.spec.X509EncodedKeySpec
import org.bouncycastle.cert.X509CertificateHolder
import org.bouncycastle.cert.jcajce.JcaX509CertificateConverter
import org.bouncycastle.openssl.PEMKeyPair
import org.bouncycastle.openssl.PEMParser
import scala.util.Try
;

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
    keyStore.setKeyEntry("docker", keyPair.getPrivate(), "docker".toCharArray(), new Certificate[]{privateCertificate})
    return keyStore
  }

  def createTrustStore(dockerCertPath: String) = {
    val caPath = new File(dockerCertPath, "ca.pem");
    val reader = new BufferedReader(new FileReader(caPath));
    PEMParser pemParser = null;
    try {
      pemParser = new PEMParser(reader);
      X509CertificateHolder certificateHolder = (X509CertificateHolder) pemParser.readObject();
    Certificate caCertificate = new JcaX509CertificateConverter().setProvider("BC").getCertificate(certificateHolder);
    KeyStore trustStore = KeyStore.getInstance("JKS");
    trustStore.load(null);
    trustStore.setCertificateEntry("ca", caCertificate);
    return trustStore;
    }
    finally {
      if(pemParser != null) {
        IOUtils.closeQuietly(pemParser);
      }
      if(reader != null) {
        IOUtils.closeQuietly(reader);
      }
    }
  }

  private def loadCertificate(dockerCertPath: String) = {
    val certificate = new File(dockerCertPath, "cert.pem")
    val reader = new BufferedReader(new FileReader(certificate))
    PEMParser pemParser = null;
    try {
      pemParser = new PEMParser(reader);
      X509CertificateHolder certificateHolder = (X509CertificateHolder) pemParser.readObject();
    return new JcaX509CertificateConverter().setProvider("BC").getCertificate(certificateHolder);
    }
    finally {
      if(pemParser != null) {
        IOUtils.closeQuietly(pemParser);
      }
      if(reader != null) {
        IOUtils.closeQuietly(reader);
      }
    }
  }

  private def tryReadCertificate(file: File): Try[BufferedReader] = Try { new BufferedReader(new FileReader(file)) }

  private def tryLoadPemParser(reader: BufferedReader): Try[PEMParser] = Try { new PEMParser(reader) }

  private def createKeyPair(buffReader: BufferedReader, pemParser: PEMParser) = {
    val pemKeyPair = pemParser.readObject().asInstanceOf[PEMKeyPair]
    val pemPrivateKeyEncoded = pemKeyPair.getPrivateKeyInfo().getEncoded()
    val pemPublicKeyEncoded = pemKeyPair.getPublicKeyInfo().getEncoded()
    val factory = KeyFactory.getInstance("RSA")
    val publicKeySpec = new X509EncodedKeySpec(pemPublicKeyEncoded)
    val privateKeySpec = new PKCS8EncodedKeySpec(pemPrivateKeyEncoded)
    new KeyPair(factory.generatePublic(publicKeySpec), factory.generatePrivate(privateKeySpec))
  }

  private def loadPrivateKey(dockerCertPath: String) = Try {
    val certReaderTry = tryReadCertificate(new File(dockerCertPath, "myKey.pem"))
    for {
      certReader <- certReaderTry
      pemParser  <- tryLoadPemParser(certReader)
    } yield createKeyPair(certReader, pemParser)

    certReaderTry foreach(_.close)
    pemParserTry foreach (_.close)
  }
}
