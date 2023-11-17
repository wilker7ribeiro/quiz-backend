# big-exporter-metricas-ozone

- [Git Repository](https://fontes.intranet.bb.com.br/big/big-bbweek-dungeons-data-quiz/big-bbweek-dungeons-data-quiz-backend)

1. [Descrição](#1-descrição)
2. [Requerimentos](#2-requerimentos)
3. [Bibliotecas_usadas](#3-bibliotecas-usadas)
4. [Execução](#4-execução)
5. [Estrutura](#5-estrutura)
6. [Referências](#6-referẽncias)


## Descrição

Este projeto é codificado em Java e utiliza o framework [Spring](<https://spring.io/>).



## Requerimentos

<p>Devem estar instaladas e configuradas as seguintes ferramentas:</p>
<ul>
   <li>Java 11</li>
   <li>Maven 3.8.1</li>
   <li>Docker ou Podman</li>
</ul>

## Bibliotecas Usadas
<ul>
   <li>Spring Boot Starter Web</li>
   <li>Spring Boot Starter Data JPA</li>
   <li>Spring Boot Starter Actuator</li>
   <li>Spring Boot Starter Validation</li>
   <li>Spring Boot Configuration Processor</li>
   <li>Model Mapper</li>   
   <li>Oracle Driver</li>
   <li>H2 Driver - Usado para testes unitários</li>
   <li>Micrometer Registry Prometheus</li>
   <li>Lombok</li>
   <li>Hibernate jpamodelgen</li>  
</ul>


## Execução

Para rodar o projeto, execute os comandos dentro do diretorio criado para o projeto.

A primeira parte de comando altera a permisão do script para permitir a execucao a segunda parte executa o script.

``` bash
chmod +x ./run/run.sh 
chmod +x mvnw 
./run/run.sh
```

Execute o comando abaixo:

```bash 
./run/run.sh  ou             
```

Nota: você deve ter o maven instalado na sua máquina e o arquivo settings.xml devidamente configurado na pasta .m2 (ela fica no home da sua máquina). Você deve pegar o settings pelo site do atf (atf.intranet.bb.com.br) pela opção bb-mavem-repo. Sete a sua senha do sisbb antes de gerar o arquivo para que ele contenha o hash dela.

O seu projeto será executado com hot deploy. Com esta opção, caso você faça alguma alteração no seu código, ela já será refletida no projeto em execução, não sendo necessário derrubar o projeto e subi-lo novamente.


Para checar o status da execução, acesse o diretório /run:

``` bash
docker-compose ps
```

Acesse o endpoint **hello world** no caminho: http://localhost:8080/v1/hello

## Estrutura

O projeto possui a seguinte estrutura:

![](img/arq-exporter.png)

### Serviço - Metrics

Serviço que busca métricas no formato exposto pelo CDP e converte para o formato do Prometheus enviando para o mesmo ao ser requerido.

### Serviço - Monitor

Serviço que executa e guarda as informações geradas a partir das requisições ao CDP e aos demais componentes que não são possíveis executar pela API do CDP.

### Links da aplicação


| Componente                 | Descrição             | Endpoint                | Saiba mais em |
| :---                   | :----:                | :----:                   | ---:         |
| **_Info_**                   | Obtém as informações sobre versão da aplicação, commit e dependências            | <http://localhost:8080/info>             | [info](#31-info) |
| **_Documentação das APIs_**  | Realiza a documentação automática das apis e endpoints        | <http://localhost:8080/api-docs/> | [api-docs](#32-documentação-da-api)     |
| **_Métricas_**               | Métricas expostas pela aplicação, Prometheus e Grafana (monitoração)                 | <http://localhost:8080/metrics>, <http://localhost:9090/> e <http://localhost:3100/> | [metrics](#33-métricas)       |
| **_Rastreamento_**           | Realiza o rastreamento da aplicação           | <http://localhost:16686> | [tracing](#34-tracing)   |
| **_Saúde da aplicação_**     | Mede a saúde da aplicação para o Kubernets (monitoração)           | <http://localhost:8080/health/live> e <http://localhost:8080/health/ready> | [health](#35-health)       |



A fim de expor as váriaveis de ambiente, versão da aplicação e outras informações deste projeto, foi configurado o seguinte endpoint: <http://localhost:8080/info>



Para acessar a documentação da API no Swagger UI, acesse o endpoint: <http://localhost:8080/api-specs/ui/>



Este projeto expõe métricas no endpoint: <http://localhost:8080/metrics>

Para monitorar as métricas são utilizadas as ferramentas [Prometheus](<https://prometheus.io/docs/introduction/overview/>) e [Grafana](<http://docs.grafana.org/>), expostos nos endpoints: <http://localhost:9090/> e <http://localhost:3100/>, respectivamente.

Para conhecer mais sobre "Os 4 Sinais de Ouro" ("The Four Golden Signals") da monitoração, clique [aqui](https://landing.google.com/sre/sre-book/chapters/monitoring-distributed-systems/).



Para realizar o tracing este projeto utiliza uma implementação da ferramenta [Jaeger](<https://www.jaegertracing.io/>) chamada [jaeger-tracer-decorator](https://github.com/CarlosPanarello/jaeger-tracer-decorator).

A interface do Jaeger é exposta no endpoint: <http://localhost:16686>



Para que o [Kubernetes](<https://kubernetes.io/pt/>) possa monitorar a saúde da aplicação, foram incluidos três endpoints (/health e /ready) com os seguintes objetivos:

1. [/health/live:](<http://localhost:8080/health/live>) testar quando a aplicação está "viva";
2. [/health/ready:](<http://localhost:8080/health/ready>) testar se a aplicação está pronta para atender.

**Atenção:** o **/health** deve ser o mais simples possível de forma a não comprometer a monitoração pelo Kubernetes.




Para editar os testes unitários, edite as classes do diretório: **/src/test/**.

Para executar os testes unitários, execute o comando:

``` bash
mvn test
```

Ao executar o script run.sh ou o run_hot_deploy.sh, os testes também são executados e você pode conferir os resultados destes no console.




## Referências

<https://cloudera.github.io/cm_api/docs/java-client-swagger/> \
<https://arcozone.cloudera.com/cm7/7.6.5/generic/jar/cm_api/apidocs/index.html> \
<https://github.com/kloudsense/cloudera_exporter>
