# Projeto React Native com TurboModule

Este repositório contém um projeto React Native que integra um TurboModule personalizado. O TurboModule inclui um serviço que é executado a cada 5 minutos, independente de o aplicativo estar em primeiro ou segundo plano.

## Configuração do Projeto

Para configurar o projeto em sua máquina local, siga os passos abaixo:

1. Clone o repositório:
   ```sh
   git clone https://github.com/mensonones/PocBgTask.git
   ````
2. (Pelo terminal) Acesse a pasta TurboModuleBgTask e instale as dependências:
    ```sh
    yarn install
   ```
3. (Pelo terminal) Ainda na pasta TurboModuleBgTask:
   ```sh
    yarn add ../RTNModuleTask
   ```
4. (Pelo terminal) Acesse a subpasta android da pasta TurboModuleBgTask:
    ```sh
    ./gradlew generateCodegenArtifactsFromSchema
    ```
5. (Pelo terminal) Na raiz da pasta TurboModuleBgTask. Inicie o Metro Bundler:
   ```
   yarn start
   ```
6. (Pelo terminal)  Na raiz da pasta TurboModuleBgTask. Execute o aplicativo no emulador/dispositivo:
   ```
   yarn android
   ```   
   
## Screens

<p align="center">
  <img src="https://github.com/mensonones/PocBgTask/blob/main/TurboModuleServiceBg1.jpg" alt="Serviço TurboModule em primeiro plano" width="45%"/>
  <img src="https://github.com/mensonones/PocBgTask/blob/main/TurboModuleServiceBg2.jpg" alt="Serviço TurboModule em segundo plano" width="45%"/>
</p>


