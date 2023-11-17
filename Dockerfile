FROM docker.binarios.intranet.bb.com.br/bb/dev/dev-java-jdk-11:11.0.16

COPY --chown=185 target/*.jar /deployments/app.jar

USER root
COPY inicio.sh /usr/local/bin/inicio.sh
RUN chmod 755 /usr/local/bin/inicio.sh

USER jboss
CMD ["/usr/local/bin/inicio.sh"]
