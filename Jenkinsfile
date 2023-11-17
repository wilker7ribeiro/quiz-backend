@Library(['aic-jenkins-sharedlib']) _

mavenBuildPipeline {
    nomePod                         = 'jdk11' // habilita a troca da versão do compilador
    habilitarValidacaoEstatica      = true // habilita a validação estática do código fonte
    habilitarConstrucao             = true // habilita a construção da aplicação
    habilitarTestesUnidade          = true // habilita a execução dos testes de unidade
    habilitarTestesIntegracao       = true // habilita a execução dos testes de integração
    habilitarSonar                  = true // habilita a execução do SonarQube Scanner
    habilitarEmpacotamento          = true // habilita o empacotamento da aplicação
    habilitarEmpacotamentoDocker    = true // habilita o build e publicação da imagem docker
    habilitarPublicacao             = false // habilita a publicação do pacote no repositório corporativo
    habilitarDebug                  = false // habilita o debug
}
// Documentação das pipelines: https://fontes.intranet.bb.com.br/aic/publico/atendimento/-/wikis/Pipelines
