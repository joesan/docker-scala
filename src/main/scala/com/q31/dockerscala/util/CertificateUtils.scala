package com.q31.dockerscala.util

import java.io.File

/**
 * @author Joe San (codeintheopen@gmail.com)
 */
object CertificateUtils {

  def verifyCertificatesExist(dockerCertPath: String): Boolean = {
    List("ca.pem", "cert.pem", "key.pem").forall { case str => new File(dockerCertPath, str).exists }
  }


}
