package com.q31.dockerscala

import com.typesafe.config.{ConfigFactory, Config}
import org.glassfish.jersey.client.{ClientProperties, ClientConfig}
import org.glassfish.jersey.{SslConfigurator, CommonProperties}
import com.fasterxml.jackson.jaxrs.json.JacksonJsonProvider
import javax.ws.rs.client.ClientBuilder
import java.security.{KeyStore, Security}
import com.q31.dockerscala.api.DockerClientException
import com.q31.dockerscala.util.{JsonClientFilter, DockerClientResponseFilter, DockerClientConfig}

/**
 * @author Joe San (codeintheopen@gmail.com)
 */
object DockerRemoteClientFactory {

  // TODO... DockerClientContext needs WebTarget
  private lazy val clientContext: DockerClientConfig => DockerClientContext = dockerClientConfig => { new DockerClientContext(init(dockerClientConfig)) }

  def buildFromConfig(config: Config): DockerRemoteClient = new DockerRemoteClientImpl(clientContext(DockerClientConfig.withConfig(config)))

  def buildDefault(): DockerRemoteClient = new DockerRemoteClientImpl(clientContext(DockerClientConfig.default()))

  private def init(dockerClientConfig: DockerClientConfig) = {
    //Preconditions.checkNotNull(dockerClientConfig, "config was not specified");

    val clientConfig = new ClientConfig()
    clientConfig.property(CommonProperties.FEATURE_AUTO_DISCOVERY_DISABLE, true)

    clientConfig.register(classOf[DockerClientResponseFilter])
    clientConfig.register(classOf[JsonClientFilter])
    clientConfig.register(classOf[JacksonJsonProvider])

    /*if (dockerClientConfig.isLoggingFilterEnabled()) {
      clientConfig.register(new SelectiveLoggingFilter(LOGGER, true));
    } */

    clientConfig.property(ClientProperties.READ_TIMEOUT, dockerClientConfig.readTimeOut)

    val clientBuilder = ClientBuilder.newBuilder().withConfig(clientConfig);

    val dockerCertPath = dockerClientConfig.dockerCertPath

    if (dockerCertPath != null) {
      val certificatesExist = CertificateUtils.verifyCertificatesExist(dockerCertPath);

      if (certificatesExist) {

        try {

          Security.addProvider(new BouncyCastleProvider())

          val keyStore = CertificateUtils.createKeyStore(dockerCertPath);
          val trustStore = CertificateUtils.createTrustStore(dockerCertPath);

          // properties acrobatics not needed for java > 1.6
          val httpProtocols = System.getProperty("https.protocols");
          System.setProperty("https.protocols", "TLSv1");
          val sslConfig = SslConfigurator.newInstance(true);
          if (httpProtocols != null) System.setProperty("https.protocols", httpProtocols);

          sslConfig.keyStore(keyStore);
          sslConfig.keyStorePassword("docker");
          sslConfig.trustStore(trustStore);

          val sslContext = sslConfig.createSSLContext();


          clientBuilder.sslContext(sslContext);

        } catch {
          case e: Throwable => throw new DockerClientException(999, e.getMessage);
        }

      }

    }

    val client = clientBuilder.build();

    val webResource = client.target(dockerClientConfig.uri)

    if (dockerClientConfig.version.isEmpty) webResource
    else webResource.path("v" + dockerClientConfig.version)
  }
}
